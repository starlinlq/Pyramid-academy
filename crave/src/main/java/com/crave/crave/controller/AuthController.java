package com.crave.crave.controller;

import com.crave.crave.dto.UserDTO;
import com.crave.crave.model.User;
import com.crave.crave.service.UserService;
import com.crave.crave.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth/")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        user.addRole("ROLE_USER");
        userService.save(user);
        UserDTO userDTO = userService.toUserDTO(user);
        userDTO.setToken(jwtTokenUtil.generateToken(user));
        return ResponseEntity.ok(userDTO);
    }

    @RequestMapping("authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authRequest) throws Exception {

        authenticate(authRequest.getUsername(), authRequest.getPassword());

        Optional<User>  user = userService
                .findByUsername(authRequest.getUsername());

       if(user.isPresent()){
           UserDTO userDTO = userService.toUserDTO(user.get());
           userDTO.setToken(jwtTokenUtil.generateToken(user.get()));

           return ResponseEntity.ok(userDTO);
       }
       return new ResponseEntity<String>( "Invalid user", HttpStatus.BAD_REQUEST);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
