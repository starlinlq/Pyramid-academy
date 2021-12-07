package account.controllers;

import account.customException.PasswordHackerDbException;
import account.customException.PasswordLengthException;
import account.customException.SamePasswordException;
import account.customException.UserAlreadyExist;
import account.entity.User;
import account.security.CompromisedPassword;
import account.services.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    CompromisedPassword compromisedPassword;

    @PostMapping("/signup")
    public Object register(@Valid @RequestBody User newUser)  {
       validatePass(newUser.getPassword());

        if(userService.alreadyExists(newUser.getEmail())){
           throw new UserAlreadyExist();
        }

        User user = userService.save(newUser);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/changepass")
    public ResponseEntity<?> changePass(@Valid  @RequestBody ResetPass newPassword, Authentication auth) throws SamePasswordException {
        validatePass(newPassword.getNew_password());
        User user = userService.findByUserName(auth.getName());

        if(encoder.matches(newPassword.getNew_password(), user.getPassword())){
            throw new SamePasswordException();
        }

        userService.updatePass(encoder.encode(newPassword.getNew_password()), user.getEmail());
        newPassword.setEmail(user.getEmail());
        newPassword.setStatus("The password has been updated successfully");
        return ResponseEntity.ok(newPassword);

    }

    private void validatePass(String password) throws PasswordHackerDbException, PasswordLengthException{
        if(password.length() < 12){
            throw new PasswordLengthException();
        }

        if(compromisedPassword.checkPassword(password)){
            throw new PasswordHackerDbException();
        }
    }


}

class ResetPass{
    @NotBlank
    private String new_password;
    private String email;
    private String status;

    @JsonProperty
    public String getStatus() {
        return status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getNew_password() {
        return new_password;
    }

    @JsonProperty
    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}
