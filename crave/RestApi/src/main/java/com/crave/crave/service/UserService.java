package com.crave.crave.service;

import com.crave.crave.dto.UserDTO;
import com.crave.crave.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {
    void save(User user);
    UserDTO findByEmail(String email);
    Optional<User> findByUsername(String username);
    void changePassword(User user, String password);
    UserDTO toUserDTO(User user);
    long getUserIdByUsername(String username);
}
