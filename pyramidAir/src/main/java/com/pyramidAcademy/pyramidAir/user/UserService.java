package com.pyramidAcademy.pyramidAir.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public User getUserByUsername(String name){
      return userRepository.findByUsername(name);
    }

    public User save(User user){
        userRepository.save(user);
        return user;
    }

}
