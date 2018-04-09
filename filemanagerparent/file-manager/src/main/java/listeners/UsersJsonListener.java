package listeners;

import models.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebListener()
public class UsersJsonListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public UsersJsonListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Users JSON Listener");

        List<User> users = new ArrayList<>();
        String rootDir = "";
        String username;
        String password_hash;

        ServletContext ctx = sce.getServletContext();

        //Read from config.properties root.dir
        try(FileInputStream input = new FileInputStream(
                "C:\\Users\\Alvina\\IdeaProjects\\tigrant\\filemanagerparent" +
                        "\\filemanager\\src\\main\\webapp\\config.properties")) {

            // load a properties file
            Properties prop = new Properties();
            prop.load(input);

            // get the property value
            rootDir = prop.getProperty("root.dir");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        users.clear();

        try {       // Read user info from JSON file and add new user to Users list

            Object obj = parser.parse(new FileReader(ctx.getRealPath("WEB-INF/users.json")));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray usersList = (JSONArray) jsonObject.get("users");

            //noinspection ForLoopReplaceableByForEach
            for (int i = 0; i < usersList.size(); i++) {

                JSONObject jsonUser = (JSONObject) usersList.get(i);

                String name = (String) jsonUser.get("name");
                String email = (String) jsonUser.get("email");
                username = (String) jsonUser.get("username");
                password_hash = (String) jsonUser.get("password_hash");  //absd33

                users.add(new User(name, email, username, password_hash));

                //Check have user a own directory, create if have not
                File userDirectory = new File(rootDir + username);

                if (!userDirectory.exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    userDirectory.mkdir();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ctx.setAttribute("rootDir", rootDir);
        ctx.setAttribute("users", users);
    }

    public void contextDestroyed(ServletContextEvent sce) {
      //
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
