package com.innowise.webproject.dao;

import com.innowise.webproject.entity.impl.User;
import com.innowise.webproject.exception.DaoException;

import java.util.Optional;

public interface Dao {
    Optional<User> authenticationByUsername(String username) throws DaoException; // authentication
    Optional<User> authenticationById(int id) throws DaoException;
    void addUser(User user) throws DaoException;
    void updateUser(User user) throws DaoException;
    void deleteUser(int id) throws DaoException;
}
