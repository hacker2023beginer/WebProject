package com.innowise.webproject.dao;

import com.innowise.webproject.entity.impl.User;
import com.innowise.webproject.exception.DaoException;

import java.util.Optional;

public interface BaseDao {
    Optional<User> authenticationByUsername(String username) throws DaoException;
    Optional<User> authenticationById(int id) throws DaoException;
    boolean addUser(User user) throws DaoException;
    boolean updateUser(User user) throws DaoException;
    boolean deleteUser(int id) throws DaoException;
}
