package servlet;

import org.apache.log4j.Logger;
import service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doDelete(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        UserService userService = (UserService) ctx.getAttribute("userService");

        Logger logger = (Logger) ctx.getAttribute("logger");
        logger.debug("Received request to delete user");

        Long id = Long.parseLong(request.getParameter("userID"));
        userService.deleteUserDTO(id);

        // Go to users.jsp
        request.getRequestDispatcher("/get").forward(request, response);
    }
}
