package com.innowise.webproject.service;

import com.innowise.webproject.command.impl.ClientUserRole;
import com.innowise.webproject.dao.impl.UserDAO;
import com.innowise.webproject.entity.User;
import com.innowise.webproject.exception.DAOException;

public class AuthService {
    private final UserDAO userDAO = new UserDAO();

    public User login(String username, String password) throws DAOException {
        User user = null;
        try {
            user = userDAO.authenticationByUsername(username);
        } catch (DAOException e) {
            throw new DAOException(e.getMessage());
        }
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean register(String username, String password) throws DAOException {
        if (userDAO.authenticationByUsername(username) != null) {
            return false;
        }
        User newUser = new User(username, password, new ClientUserRole());
        try {
            userDAO.addUser(newUser);
        } catch (DAOException e) {
            throw new DAOException("Error while adding user", e);
        }
        return true;
    }
}
