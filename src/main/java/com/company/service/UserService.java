package com.company.service;

import com.company.model.User;

import java.util.List;

public interface UserService {
    User getById(long id);
    void addUser(User newUser);
    void deleteUser(long id);
    void updateUser(long id, User userForUpdate);
    List<User> getUsersList();
}
