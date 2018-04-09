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

@WebServlet(name = "UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        ServletContext ctx = request.getServletContext();
        HttpSession session = request.getSession();

        UserService userService = (UserService) ctx.getAttribute("userService");
        session.setAttribute("id", request.getParameter("userID"));

        Long id = Long.parseLong((String) session.getAttribute("id"));
        UserDTO userDTO = userService.getByIdDTO(id);
        session.setAttribute("userDTO", userDTO);

        // Go to editUser.jsp
        request.getRequestDispatcher("WEB-INF/pages/editUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPut(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        HttpSession session = request.getSession();
        UserService userService = (UserService) ctx.getAttribute("userService");

        Logger logger = (Logger) ctx.getAttribute("logger");
        logger.debug("Received request to update user");

        UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");

        userDTO.setName(request.getParameter("name"));
        userDTO.setEmail(request.getParameter("email"));
        userDTO.setUsername(request.getParameter("username"));
        userDTO.setPassword(request.getParameter("password"));
        userService.updateUserDTO(userDTO);

        // Go to users.jsp
        request.getRequestDispatcher("/get").forward(request, response);
    }
}
