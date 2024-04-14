package com.springbootlab.cc1.service;

import org.springframework.stereotype.Service;
import com.springbootlab.cc1.model.User;
import com.springbootlab.cc1.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(String email, User user) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            user.setId(existingUser.getId()); // Ensure the ID is set to update the existing user
            userRepository.save(user);
        }
        return user;
    }

    public String deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
            return "User deleted successfully";
        }
        return "User not found";
    }

    public Long countUsers() {
        return userRepository.count();
    }

    // Add more methods as needed...

    // Methods using JPQL queries

    public User getUserByEmailJPQL(String email) {
        return userRepository.getUserByEmailJPQL(email);
    }

    public User getUserByNameJPQL(String name) {
        return userRepository.getUserByNameJPQL(name);
    }

    public List<User> searchUsersByEmailJPQL(String email) {
        return userRepository.searchUsersByEmailJPQL(email);
    }

    public List<User> findUsersByEmailEndingWithJPQL(String domain) {
        return userRepository.findUsersByEmailEndingWithJPQL(domain);
    }

    public List<User> findAllUsersOrderedByNameJPQL() {
        return userRepository.findAllUsersOrderedByNameJPQL();
    }

    public void deleteUser(Long id) {
    }


}
