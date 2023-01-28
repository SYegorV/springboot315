package com.company.configs;

import com.company.model.Role;
import com.company.model.User;
import com.company.service.RoleService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseInit {
    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public DatabaseInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @PostConstruct
    public void initDbUsers() {

        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        if (roleService.getAllRoles().isEmpty()) {
            roleService.addRole(roleAdmin);
            roleService.addRole(roleUser);
        }

        if (userService.getUsersList().isEmpty()) {
            User admin = new User();
            Set<Role> adminRoles = new HashSet<>();
            Collections.addAll(adminRoles, roleService.roleByID(1L), roleService.roleByID(2L));
            admin.setId(1L);
            admin.setUsername("admin");
            admin.setPassword("green");
            admin.setName("amanda");
            admin.setSurname("green");
            admin.setAge(28);
            admin.setEmail("amanda@gmail.com");
            admin.setRoles(adminRoles);
            userService.addUser(admin);

            User user = new User();
            Set<Role> userRoles = new HashSet<>();
            Collections.addAll(userRoles, roleService.roleByID(2L));
            user.setId(2L);
            user.setUsername("user");
            user.setPassword("kelly");
            user.setName("ruby");
            user.setSurname("kelly");
            user.setAge(29);
            user.setEmail("ruby@gmail.com");
            user.setRoles(userRoles);
            userService.addUser(user);
        }
    }
}
