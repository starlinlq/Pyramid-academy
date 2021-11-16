package engine.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
   public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email){
       return  userRepository.findUserByEmail(email);
    }


    public void save(User user){
        this.userRepository.save(user);
    }

}
