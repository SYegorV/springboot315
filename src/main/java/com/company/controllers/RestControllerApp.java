package com.company.controllers;

import com.company.model.Role;
import com.company.model.User;
import com.company.repository.UserRepository;
import com.company.service.RoleService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestControllerApp {
    private final UserService userService;
    private final RoleService roleService;
    private final UserRepository userRepository;
    @Autowired
    public RestControllerApp(UserService userService, RoleService roleService, UserRepository userRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRepository = userRepository;
    }
    @GetMapping
    public ResponseEntity getGet() {
        try {
            return ResponseEntity.ok("Server job");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("bag");
        }
    }
    @GetMapping("/admin")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> allUsers = userService.getUsersList();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> allRoles = roleService.getAllRoles();
        return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }
    @PostMapping("/admin")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/admin/{id}")
    public ResponseEntity<String> apiEditUser(@PathVariable("id") long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/admin/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") long id) {
        userService.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/user")
    public User showUser(Authentication auth) {
        return userRepository.getUserByMail(auth.getName()).get();
    }
}
