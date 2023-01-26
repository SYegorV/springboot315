package com.company.service;

import com.company.model.Role;

import java.util.List;

public interface RoleService {
    Role roleByID(long id);
    void addRole(Role role);
    List<Role> getAllRoles();
}
