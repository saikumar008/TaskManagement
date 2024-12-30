package com.project.taskmanagement.Service;

import com.project.taskmanagement.DTO.UserDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(UUID id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(UUID id, UserDTO userDTO);
    void deleteUser(UUID id);
    public UserDTO partialUpdateUser(UUID id, Map<String, Object> updates);
}
