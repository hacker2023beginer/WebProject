package com.innowise.webproject.dao;

import com.innowise.webproject.entity.User;
import com.innowise.webproject.exception.DAOException;

public interface Dao {
    User authenticationByUsername(String username) throws DAOException; // authentication
    User authenticationById(int id) throws DAOException;
    void addUser(User user) throws DAOException;
    void updateUser(User user) throws DAOException;
    void deleteUser(int id) throws DAOException;
}
