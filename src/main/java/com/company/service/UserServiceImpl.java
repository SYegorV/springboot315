package com.company.service;

import com.company.model.Role;
import com.company.model.User;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getById(long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElseThrow();
    }

    @Override
    public void addUser(User newUser) {
        Set<Role> roleSet = new HashSet<>();
        if(newUser.getRoles() != null) {
            Iterator<Role> iterator = newUser.getRoles().iterator();
            while (iterator.hasNext()) {
                Role role = roleService.roleByID(iterator.next().getId());
                roleSet.add(role);
            }
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRoles(roleSet);
        newUser.setRole("ROLE_USER");
        userRepository.saveAndFlush(newUser);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(long id, User userForUpdate) {
        Set<Role> roleSet = new HashSet<>();
        if(userForUpdate.getRoles() != null) {
            Iterator<Role> iterator = userForUpdate.getRoles().iterator();
            while (iterator.hasNext()) {
                Role role = roleService.roleByID(iterator.next().getId());
                roleSet.add(role);
            }
        }
        userForUpdate.setPassword(passwordEncoder.encode(userForUpdate.getPassword()));
        userForUpdate.setRoles(roleSet);
        userRepository.saveAndFlush(userForUpdate);
    }

    @Override
    public List<User> getUsersList() {
        return userRepository.findAll();
    }
}
