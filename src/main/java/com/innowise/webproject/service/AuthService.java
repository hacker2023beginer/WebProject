package com.innowise.webproject.service;

import com.innowise.webproject.command.impl.ClientUserRole;
import com.innowise.webproject.dao.impl.UserDAO;
import com.innowise.webproject.entity.User;

public class AuthService {
    private final UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean register(String username, String password) {
        if (userDAO.findByUsername(username) != null) {
            return false;
        }
        User newUser = new User(username, password, new ClientUserRole());
        userDAO.create(newUser);
        return true;
    }
}
