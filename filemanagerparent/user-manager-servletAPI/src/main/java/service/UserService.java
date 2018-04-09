package service;

import model.UserDTO;
import java.util.List;

public interface UserService {

    Long createUserDTO(UserDTO userDTO);

    UserDTO getUserDTO(UserDTO userDTO);

    List<UserDTO> getAllUsersDTO();

    void updateUserDTO(UserDTO userDTO);

    void deleteUserDTO(Long id);

    UserDTO getByIdDTO(Long id);

    UserDTO getByEmailDTO(String email);
}
