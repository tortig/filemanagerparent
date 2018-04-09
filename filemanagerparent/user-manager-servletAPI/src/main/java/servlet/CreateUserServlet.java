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
import java.io.IOException;

@WebServlet(name = "CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Go to addUser.jsp
        request.getRequestDispatcher("WEB-INF/pages/addUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        UserService userService = (UserService) ctx.getAttribute("userService");

        Logger logger = (Logger) ctx.getAttribute("logger");
        logger.debug("Received request to add new user");

        UserDTO userDTO = new UserDTO();

        Long id = (long) (Math.random()*1000);
        userDTO.setId(id);
        userDTO.setName(request.getParameter("name"));
        userDTO.setEmail(request.getParameter("email"));
        userDTO.setUsername(request.getParameter("username"));
        userDTO.setPassword(request.getParameter("password"));
        userService.createUserDTO(userDTO);

        // Go to users.jsp
        request.getRequestDispatcher("/get").forward(request, response);
    }
}