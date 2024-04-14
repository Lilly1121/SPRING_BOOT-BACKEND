package com.springbootlab.cc1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.springbootlab.cc1.model.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByEmailJPQL(String email);

    @Query("SELECT u FROM User u WHERE u.name = :name")
    User getUserByNameJPQL(String name);

    @Query("SELECT u FROM User u WHERE u.email LIKE %:email%")
    List<User> searchUsersByEmailJPQL(String email);

    @Query("SELECT u FROM User u WHERE u.email LIKE %:domain")
    List<User> findUsersByEmailEndingWithJPQL(String domain);

    @Query("SELECT u FROM User u ORDER BY u.name")
    List<User> findAllUsersOrderedByNameJPQL();

    User findByEmail(String email);

    User findByName(String name);

   
}
