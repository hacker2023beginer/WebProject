package com.innowise.webproject.service.impl;

import com.innowise.webproject.command.UserRole;
import com.innowise.webproject.dao.impl.UserDao;
import com.innowise.webproject.entity.impl.User;
import com.innowise.webproject.exception.DaoException;
import com.innowise.webproject.exception.ServiceException;
import com.innowise.webproject.service.AppAuthService;

import java.util.Optional;

public class AuthService implements AppAuthService {
    private final UserDao userDAO = new UserDao();

    @Override
    public User login(String username, String password) throws ServiceException {
        try {
            Optional<User> optionalUser = userDAO.authenticationByUsername(username);
            User user = optionalUser.orElseThrow(() -> new ServiceException("Invalid username or password"));
            if (!user.getPassword().equals(password)) {
                throw new ServiceException("Invalid username or password");
            }
            return user;
        } catch (DaoException e) {
            throw new ServiceException("Error occurred during authentication", e);
        }
    }

    @Override
    public boolean register(String username, String password) throws ServiceException {
        try {
            if (userDAO.authenticationByUsername(username).isPresent()) {
                return false;
            }
            User newUser = new User(username, password, UserRole.CLIENT);
            userDAO.addUser(newUser);
        } catch (DaoException e) {
            throw new ServiceException("Error while adding user", e);
        }
        return true;
    }
}
