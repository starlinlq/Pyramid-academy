package account.controllers;

import account.entity.User;
import account.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/empl/payment")
public class PaymentController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<UserDetails> welcome(Authentication auth){
       User user = userService.findByUserName(auth.getName());
        return ResponseEntity.ok(user);
    }
}
