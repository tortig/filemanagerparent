package DAO;

import model.User;
import java.util.List;

public interface UserRepository {

    Long createUser(User user);   // Save theUser in data base and return ID

    User getUser(User user);     // Read theUser info from data base and return User object

    void updateUser(User user);   // Update theUser in data base

    void deleteUser(Long id);   // Delete theUser from data base

    List<User> getAllUsers();     // Read all Users info from data base and return Users list
}
