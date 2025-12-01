package com.innowise.webproject.connectionpool;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.concurrent.locks.ReentrantLock;

public class PostgresConnectionPool {
    private static PostgresConnectionPool instance;
    private static ReentrantLock lock = new ReentrantLock();

    public static PostgresConnectionPool getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new PostgresConnectionPool();
            }
        }
        return instance;
    }

    private PostgresConnectionPool() {
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:")
    }
}
