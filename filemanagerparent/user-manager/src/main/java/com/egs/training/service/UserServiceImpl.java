package com.egs.training.service;

import com.egs.training.dao.UserDAO;
import com.egs.training.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private static Logger logger = Logger.getLogger("com/egs/training/service");

    @Autowired
    @Qualifier(value = "userDAO_SFImpl")
    private UserDAO userDAO;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        logger.debug("Get all Users");

        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public User getUser(Long id) {
        logger.debug("User get successfully");

        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        logger.debug("Adding new User");

        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        logger.debug("Deleting existing user");

        userDAO.deleteUser(id);
    }
}
