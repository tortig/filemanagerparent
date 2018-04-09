package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet(name = "UploadServlet")
public class UploadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Upload Servlet doPost method");

        HttpSession session = request.getSession();

        String address = (String) session.getAttribute("address");
        Part part = request.getPart("file");
        File file = new File(address + "\\" + part.getSubmittedFileName());
        FileOutputStream outputStream = new FileOutputStream(file);

        try (InputStream inputStream = part.getInputStream()) {
            byte[] in = new byte[1024];

            while (inputStream.read(in, 0, 1024) != -1) {
                outputStream.write(in);
            }
        }
        outputStream.close();

        //noinspection ResultOfMethodCallIgnored
        file.mkdir();

        request.getRequestDispatcher("/login").forward(request,response);
    }
}
