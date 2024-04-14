package com.springbootlab.cc1.service;

import java.util.List;
import java.util.Optional;

import com.springbootlab.cc1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootlab.cc1.model.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        Optional<User> userExists = userRepository.findByEmail(user.getEmail());
        if (userExists.isPresent())
            return;
        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        boolean userExists = userRepository.existsByEmail(email);
        if (userExists) {
            return userRepository.findByEmail(email).get();
        }
        return new User();
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User updateUser(String email, User user) {
        Optional<User> userExists = userRepository.findByEmail(email);
        if (userExists.isPresent()) {
            User existingUser = userExists.get();
            existingUser.setName(user.getName());
            existingUser.setPassword(user.getPassword());
            return userRepository.save(existingUser);
        }
        return new User();
    }

    public String deleteUser(String email) {
        boolean userExists = userRepository.existsByEmail(email);
        if (userExists) {
            userRepository.deleteByEmail(email);
            return "User deleted successfully!";
        }
        return "Record not found with email id " + email;
    }

    public User updateUserBy(String email, User user) {
        Optional<User> userExists = userRepository.findByEmail(email);
        return userExists.map(existingUser -> {
            Optional.ofNullable(user.getName()).ifPresent(existingUser::setName);
            Optional.ofNullable(user.getPassword()).ifPresent(existingUser::setPassword);
            return userRepository.save(existingUser);
        }).orElse(new User());
    }

    public User getUserByName(String name) {
        boolean userExists = userRepository.existsByName(name);
        if (userExists) {
            return userRepository.findByName(name).get();
        }
        return new User();
    }

    public User updateUserByName(String name, User user) {
        Optional<User> userExists = userRepository.findByName(name);
        return userExists.map(existingUser -> {
            Optional.ofNullable(user.getEmail()).ifPresent(existingUser::setEmail);
            Optional.ofNullable(user.getPassword()).ifPresent(existingUser::setPassword);
            return userRepository.save(existingUser);
        }).orElse(new User());
    }

    public String deleteUserByEmail(String email) {
        boolean userExists = userRepository.existsByEmail(email);
        if (userExists) {
            userRepository.deleteByEmail(email);
            return "User deleted successfully!";
        }
        return "Record not found with email id " + email;
    }


    public String deleteUserByName(String name) {
        boolean userExists = userRepository.existsByName(name);
        if (userExists) {
            userRepository.deleteByName(name);
            return "User deleted successfully!";
        }
        return "Record not found with name " + name;
    }

    public long countUsers() {
        return userRepository.count();
    }



    public List<User> searchUsersByEmail(String email) {
        return userRepository.findByEmailContainingIgnoreCase(email);
    }

    public List<User> findUsersByEmailEndingWith(String domain) {
        return userRepository.findByEmailEndingWith(domain);
    }


    public List<User> findAllUsersOrderedByName() {
        return userRepository.findAllByOrderByNameAsc();
    }



}

