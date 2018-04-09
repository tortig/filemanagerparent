package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "HomeServlet")
public class HomeServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException{
        System.out.println("Home Servlet doPost method");

        req.getSession().invalidate();

        resp.sendRedirect("/view/login.html");
        //req.getRequestDispatcher("/login/login.html").forward(request,response);
    }
}
