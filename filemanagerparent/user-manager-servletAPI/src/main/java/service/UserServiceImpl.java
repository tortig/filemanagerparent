package service;

import DAO.UserJsonRepositoryImpl;
import DAO.UserRepository;
import model.User;
import model.UserDTO;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

import static model.UserMapper.mapFromEntity;
import static model.UserMapper.mapToEntity;

@SuppressWarnings("Duplicates")
public class UserServiceImpl implements UserService{
    private static Logger logger = Logger.getLogger("com/egs/training/service");

    private UserRepository userRepository = new UserJsonRepositoryImpl();

    @Override
    public Long createUserDTO(UserDTO userDTO) {
        logger.debug("Adding new User");

        userRepository.createUser(mapToEntity(userDTO));

        return userDTO.getId();
    }

    @Override
    public UserDTO getUserDTO(UserDTO userDTO) {
        logger.debug("User get successfully");

        List<User> userList = userRepository.getAllUsers();

        if (userList.contains(mapToEntity(userDTO)))
            return userDTO;
        else return null;
    }

    @Override
    public List<UserDTO> getAllUsersDTO() {
        logger.debug("Get all Users");

        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> userList = userRepository.getAllUsers();

        for (User user : userList) {
            userDTOList.add(mapFromEntity(user));
        }

        return userDTOList;
    }

    @Override
    public void updateUserDTO(UserDTO userDTO) {
        logger.debug("Editing existing user");

        userRepository.updateUser(mapToEntity(userDTO));
    }

    @Override
    public void deleteUserDTO(Long id) {
        logger.debug("Deleting existing user");

        userRepository.deleteUser(id);
    }

    @Override
    public UserDTO getByIdDTO(Long id) {
        List<User> userList = userRepository.getAllUsers();

        for (User user : userList) {
            if (user.getId().equals(id)) {
                return mapFromEntity(user);
            }
        }
        return null;
    }

    @Override
    public UserDTO getByEmailDTO(String email) {
        List<User> userList = userRepository.getAllUsers();

        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return mapFromEntity(user);
            }
        }
        return null;
    }
}
