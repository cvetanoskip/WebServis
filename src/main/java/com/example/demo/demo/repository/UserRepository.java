package com.example.demo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import com.example.demo.demo.model.User;
import java.lang.String;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom query to find a user by username
   // User findByUsername(String username);
    Optional<User> findByUsername(String username);

    void save(UserDetails user);
}