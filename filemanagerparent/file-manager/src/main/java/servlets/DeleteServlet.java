package servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteServlet")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Delete Servlet doGet method");
        ServletContext ctx = request.getServletContext();
        HttpSession session = request.getSession();

        String rootDir = (String) ctx.getAttribute("rootDir");
        String userName= (String) session.getAttribute("username");
        String address = rootDir + userName + "\\" + request.getParameter("fileName");

        File file = new File(address);

        //noinspection ResultOfMethodCallIgnored
        file.delete();

        request.getRequestDispatcher("/login").forward(request,response);
    }
}
