package com.innowise.webproject.service;

import com.innowise.webproject.entity.impl.User;
import com.innowise.webproject.exception.ServiceException;

public interface AppAuthService {
    User login(String username, String password) throws ServiceException;
    boolean register(String username, String password) throws ServiceException;
}
