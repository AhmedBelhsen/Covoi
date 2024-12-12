package com.example.covoiturage.Repository;

import com.example.covoiturage.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(long id);
    Optional<User> findByEmail(String email);

}
