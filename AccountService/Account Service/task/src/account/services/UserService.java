package account.services;

import account.entity.User;
import account.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public User save(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        user.addRole("ROLE_USER");
        return userRepo.save(user);
    }

    public void updatePass(String password, String email){
        userRepo.updatePass(password,email);
    }

    public User findByUserName(String username){
        return userRepo.findByUserName(username).orElse(null);
    }

    public boolean alreadyExists(String email){
        return userRepo.findByEmailIgnoreCase(email).isPresent();
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmailIgnoreCase(email);
        if(user.isPresent()){
            return user.get();
        } else
            throw new UsernameNotFoundException("User not found");

    }



}
