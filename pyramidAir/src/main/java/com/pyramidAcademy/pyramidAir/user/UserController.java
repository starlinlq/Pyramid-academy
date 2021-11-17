package com.pyramidAcademy.pyramidAir.user;

import com.pyramidAcademy.pyramidAir.customResponse.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<CustomResponse<User>> register(@Valid @RequestBody User user){
        User alreadyTaken = userService.getUserByUsername(user.getUsername());

        if(alreadyTaken != null){
            return new ResponseEntity<>(new CustomResponse<>(null, "User already taken", false), HttpStatus.OK);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userService.save(user);
        return new ResponseEntity<>(new CustomResponse<>(user, "User created", true), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomResponse<User>> login(@RequestBody User user){
        User validUser  = userService.getUserByUsername(user.getUsername());
        if(validUser != null){
            return new ResponseEntity<>(new CustomResponse<>(validUser, "success", true), HttpStatus.OK );
        } else
            return new ResponseEntity<>(new CustomResponse<>(null, "user not found", false), HttpStatus.NOT_FOUND);
    }

}
