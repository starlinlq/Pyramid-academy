package engine.user;

import engine.Response;
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
@RequestMapping("/api/")
public class Controller {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    Controller(UserService userService, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<User> register(@Valid @RequestBody  User user){
        User alreadyTaken  = userService.getUserByEmail(user.getEmail());
        if(alreadyTaken != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
