package com.crave.crave.service;
import com.crave.crave.dto.UserDTO;
import com.crave.crave.model.User;
import com.crave.crave.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    @Override
    public void save(User user){
        userRepo.save(user);
    }

    @Override
    public UserDTO findByEmail(String email){
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username){
      return userRepo.findByUsername(username);
    }

    @Override
    public void changePassword(User user, String password){

    }

    @Override
    public UserDTO toUserDTO(User user){
        return UserMapper.toUserDto(user);
    }

    static class UserMapper{
        public static UserDTO toUserDto(User user){
            return new UserDTO().setUsername(user.getUsername()).setEmail(user.getEmail()).setId(user.getId());
        }
    }
}

