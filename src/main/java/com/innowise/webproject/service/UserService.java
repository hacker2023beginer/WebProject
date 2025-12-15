package com.innowise.webproject.service;

import com.innowise.webproject.command.UserRole;
import com.innowise.webproject.command.UserRoleCommand;
import com.innowise.webproject.entity.impl.User;
import com.innowise.webproject.exception.ServiceException;

public interface UserService {
    void update(User user) throws ServiceException;
    void changeRole(int userId, UserRole newRole) throws ServiceException;
    boolean hasPermission(User user, String permission);
}
