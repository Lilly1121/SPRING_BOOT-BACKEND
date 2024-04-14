package com.springbootlab.cc1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootlab.cc1.model.User;

public interface UserRepository extends JpaRepository<User, String> {

    // Optional<User> existsByEmail(String email);
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    void deleteByEmail(String email);

    Optional<User> findByName(String name);

    boolean existsByName(String name);

    void deleteByName(String name);

    List<User> findByEmailContainingIgnoreCase(String email);

    List<User> findByEmailEndingWith(String domain);


    List<User> findAllByOrderByNameAsc();
}