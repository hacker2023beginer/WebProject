package com.innowise.webproject.service.impl;

import com.innowise.webproject.dao.impl.UserDao;
import com.innowise.webproject.entity.impl.User;
import com.innowise.webproject.command.UserRole;
import com.innowise.webproject.exception.DaoException;
import com.innowise.webproject.exception.ServiceException;
import com.innowise.webproject.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void update(User u) throws ServiceException {
        try {
            userDao.updateUser(u);
        } catch (DaoException e) {
            throw new ServiceException("Error updating user", e);
        }
    }

    @Override
    public void changeRole(int userId, UserRole newRole) throws ServiceException {
        try {
            Optional<User> userOpt = userDao.authenticationById(userId);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                user.setRole(newRole);
                userDao.updateUser(user);
            } else {
                throw new ServiceException("User not found with id: " + userId);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error changing role", e);
        }
    }

    @Override
    public boolean hasPermission(User user, String permission) {
        // простая RBAC‑проверка, пока заглушка
        return true;
    }
}
