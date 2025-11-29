package com.innowise.webproject.dao;

import com.innowise.webproject.entity.User;

public interface Dao {
    void findByUsername(String username); // authentication
    void create(User user);
    void update(User user);
    void delete(int id);
    void existByEmail(String email);
}
