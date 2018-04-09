package servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "DownloadServlet")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Download Servlet doGet method");
        ServletContext ctx = request.getServletContext();
        HttpSession session = request.getSession();

        String rootDir = (String) ctx.getAttribute("rootDir");
        String userName= (String) session.getAttribute("username");
        String address = rootDir + userName + "\\" + request.getParameter("fileName");

        File file = new File(address);

        try(FileInputStream inputStream = new FileInputStream(file);
            ServletOutputStream outputStream = response.getOutputStream()) {

            byte[] out = new byte[1024];

            while (inputStream.read(out, 0, 1024) != -1) {
                outputStream.write(out, 0, 1024);
            }
        } catch (Exception e) {
            System.out.println("Exception in Download Servlet");
        }

        request.getRequestDispatcher("/login").forward(request,response);
    }
}
