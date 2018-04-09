package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@WebServlet(name = "DownloadZipServlet")
public class DownloadZipServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("DownloadZip Servlet doPost method");

        HttpSession session = request.getSession();
        String address = (String) session.getAttribute("address");
        List<String> namesBeforeDot = new ArrayList<>(); // Names with out ".txt"

        String[] checkedFiles = request.getParameterValues("fileName");

        if (checkedFiles != null) {
            for (String name : checkedFiles) {
                String[] parts = name.split("\\.");
                namesBeforeDot.add(parts[0]);
            }

            for (int i = 0; i < checkedFiles.length; i++) {
                String fileIn = address + "\\" + checkedFiles[i];

            try(ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream
                    (address + "\\Zip\\" + namesBeforeDot.get(i) + ".zip"));
                FileInputStream inputStream = new FileInputStream(fileIn)) {

                ZipEntry entry = new ZipEntry(fileIn);
                outputStream.putNextEntry(entry);

                byte[] out = new byte[inputStream.available()];
                //noinspection ResultOfMethodCallIgnored
                inputStream.read(out);
                outputStream.write(out);
                outputStream.closeEntry();
            }catch (Exception e) {
                    System.out.println("Exception in Download Servlet");
                }
            }
        }
        request.getRequestDispatcher("/login").forward(request,response);
    }
}
