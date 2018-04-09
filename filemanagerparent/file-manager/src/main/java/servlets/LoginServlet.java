package servlets;

import models.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("Login Servlet doGet method");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("Login Servlet doPost method");

        List<String> userFilesNames = new ArrayList<>();
        List<File> userFilesArray = new ArrayList<>();

        boolean flag = true;

        String address;     // user files directory whole address
        String username;
        String password;

        // Check valid current session
        if (req.getSession(false) == null) {
            username = req.getParameter("username");
            password = req.getParameter("password");
        } else {
            username = (String) req.getSession().getAttribute("username");
            password = (String) req.getSession().getAttribute("password");
        }

        //Calculate Hash256 code for user entered password
        String passHASH = null;
        try {
            passHASH = hash256(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Check the array of users to find user with similar password and username
        ServletContext ctx = req.getServletContext();

        //noinspection unchecked
        List<User> users = (List<User>) ctx.getAttribute("users");

        for (User user : users) {
            if(user.getUsername().equals(username) &&
                    user.getPassword_hash().equals(passHASH)) {     // Find user

                flag = false;

                //Create session for this user
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                session.setAttribute("name", user.getName());

                // user files directory whole address
                address = ctx.getAttribute("rootDir") + username;
                session.setAttribute("address", address);

                //User files add to userFilesArray and files names to userFilesNames
                File[] userFiles = new File(address).listFiles();

                if (userFiles == null) {
                    req.getRequestDispatcher("/view/home.jsp").forward(req, resp);
                } else {
                    for (File file : userFiles) {
                        if (file.isFile())
                            userFilesNames.add(file.getName());
                        userFilesArray.add(file);
                    }
                }

                session.setAttribute("userFilesNames", userFilesNames);
                session.setAttribute("userFilesArray", userFilesArray);

                req.getRequestDispatcher("/view/home.jsp").forward(req, resp);
            }
        }

        // Haven't user with similar username and password
        if (flag) {
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/view/login.html");
            PrintWriter out= resp.getWriter();
            out.println("<font color=red>Either username or password is wrong.</font>");
            rd.include(req, resp);
        }
    }

    //-------------------------Password calculate method---------------------------
    private static String hash256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        byte[] bytes = md.digest();

        StringBuilder result = new StringBuilder();

        for (byte byt : bytes) {
            result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        }

        return result.toString();
    }
    //-----------------------------------------------------------------------------
}
