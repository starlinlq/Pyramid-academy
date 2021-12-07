package account.repositories;

import account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
     Optional<User> findByUserName(String username);
     Optional<User> findByEmailIgnoreCase(String email);

     @Modifying(clearAutomatically = true)
     @Transactional
     @Query(value = "update User u set u.password = ?1 where u.email = ?2")
     int updatePass(String password, String email);
}
