package com.crave.crave.repository;

import com.crave.crave.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    @Query("select u.id from User u where u.username = ?1")
    long getUserIdByUsername(String username);
}
