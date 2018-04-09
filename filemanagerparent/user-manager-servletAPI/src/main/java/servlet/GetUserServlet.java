package servlet;

import model.UserDTO;
import org.apache.log4j.Logger;
import service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GetUserServlet")
public class GetUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        HttpSession session = request.getSession();

        Logger logger = (Logger) ctx.getAttribute("logger");
        logger.debug("Received request to show all users");

        UserService userService = (UserService) ctx.getAttribute("userService");

        // Attach users to the Context
        session.setAttribute("listUsers", userService.getAllUsersDTO());

        // Go to users.jsp
        request.getRequestDispatcher("WEB-INF/pages/users.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        UserService userService = (UserService) ctx.getAttribute("userService");
        HttpSession session = request.getSession();

        Logger logger = (Logger) ctx.getAttribute("logger");
        logger.debug("Received request to get user by id");

        if (request.getParameter("id") == null)
            doPost(request, response);

        Long id = Long.valueOf(request.getParameter("id"));
        UserDTO userDTO = userService.getByIdDTO(id);
        session.setAttribute("searchedUserName", userDTO.getName());
        logger.debug(userDTO.getUsername());

        // Go to users.jsp
        request.getRequestDispatcher("WEB-INF/pages/users.jsp").forward(request, response);
    }
}
