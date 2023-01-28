package com.company.service;

import com.company.model.User;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getById(long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElseThrow();
    }

    @Override
    public void addUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRole("ROLE_USER");
        userRepository.saveAndFlush(newUser);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(long id, User userForUpdate) {
        userForUpdate.setPassword(passwordEncoder.encode(userForUpdate.getPassword()));
        userForUpdate.setRole("ROLE_USER");
        userRepository.saveAndFlush(userForUpdate);
    }

    @Override
    public List<User> getUsersList() {
        return userRepository.findAll();
    }
}
