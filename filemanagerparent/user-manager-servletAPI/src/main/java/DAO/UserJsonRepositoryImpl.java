package DAO;

import model.User;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@SuppressWarnings("Duplicates")
public class UserJsonRepositoryImpl implements  UserRepository{
    private static final Logger logger = Logger.getLogger("repository");

    public Long createUser(User theUser) {
        List<User> userList = userListReadFromJSON();
        userList.add(theUser);

        userListWriteToJSON(userList);
        logger.info("User successfully saved.");

        return theUser.getId();
    }

    public void updateUser(User theUser) {
        List<User> userList = userListReadFromJSON();
        for (User user : userList) {
            if ( user.getId().equals( theUser.getId() ) ) {
                user.setName(theUser.getName());
                user.setEmail(theUser.getEmail());
                user.setUsername(theUser.getUsername());
                user.setPassword(theUser.getPassword());
            }
        }

        userListWriteToJSON(userList);
        logger.info("User successfully update.");
    }

    public void deleteUser(Long id) {
        List<User> userList = userListReadFromJSON();
        for (User theUser : userList) {
            if (theUser.getId().equals(id))
                userList.remove(theUser);
        }

        userListWriteToJSON(userList);
        logger.info("User successfully removed.");
    }

    public User getUser(User theUser) {
        List<User> userList = userListReadFromJSON();
        if (userList.contains(theUser)) {
            logger.info("User successfully loaded.");
            return theUser;
        }
        else return null;
    }

    public List<User> getAllUsers() {
        logger.info("Users list.");
        return userListReadFromJSON();
    }

    /**
     *Read users from JSON file and add to Users list
     * @return users list
     */
    private List<User> userListReadFromJSON() {
        List<User> userList = new CopyOnWriteArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("C:\\Users\\Public\\Documents\\" +
                    "IdeaProjects\\tigrant\\filemanagerparent\\user-manager-servletAPI\\src\\main" +
                    "\\webapp\\WEB-INF\\usersRepository.json"));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray users = (JSONArray) jsonObject.get("usersRepository");

            //noinspection ForLoopReplaceableByForEach
            for (int i = 0; i < users.size(); i++) {
                JSONObject jsonUser = (JSONObject) users.get(i);

                User user = new User();

                user.setId(Long.parseLong((String) jsonUser.get("id")));
                user.setName((String) jsonUser.get("name"));
                user.setEmail((String) jsonUser.get("email"));
                user.setUsername((String) jsonUser.get("username"));
                user.setPassword((String) jsonUser.get("password"));

                userList.add(user);
            }
        } catch (Exception e) {
            System.out.println("Cant read from JSON");
        }
        return userList;
    }

    /**
     * Write users from Users list to JSON file
     * @param userList to json
     */
    @SuppressWarnings("unchecked")
    private void userListWriteToJSON(List<User> userList) {
        JSONArray list = new JSONArray();

        for (User user : userList) {
            JSONObject jsonUser = new JSONObject();
            jsonUser.put("id", user.getId()+"");
            jsonUser.put("name", user.getName());
            jsonUser.put("email", user.getEmail());
            jsonUser.put("username", user.getUsername());
            jsonUser.put("password", user.getPassword());

            list.add(jsonUser);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("usersRepository", list);

        try (FileWriter file = new FileWriter("C:\\Users\\Public\\Documents\\" +
                "IdeaProjects\\tigrant\\filemanagerparent\\user-manager-servletAPI\\src\\" +
                "main\\webapp\\WEB-INF\\usersRepository.json")) {

            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (Exception e) {
            System.out.println("Cant write to JSON");
        }
    }
}
