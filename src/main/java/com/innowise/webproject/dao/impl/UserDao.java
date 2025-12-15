package com.innowise.webproject.dao.impl;

import com.innowise.webproject.command.UserRole;
import com.innowise.webproject.connectionpool.PostgresConnectionPool;
import com.innowise.webproject.dao.Dao;
import com.innowise.webproject.entity.impl.User;
import com.innowise.webproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao {
    private static final Logger logger = LogManager.getLogger();
    private static final String INSERT_USER = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT id, username, password, role FROM users WHERE id = ?";
    private static final String SELECT_BY_USERNAME = "SELECT id, username, password, role FROM users WHERE username = ?";
    private static final String SELECT_ALL = "SELECT id, username, password, role FROM users";
    private static final String UPDATE_USER = "UPDATE users SET username = ?, password = ?, role = ? WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";

    public UserDao() {
    }

    @Override
    public void addUser(User user) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().toString());
            statement.executeUpdate();
            logger.info("User added: {}", user.getUsername());
        } catch (SQLException e) {
            logger.error("Error adding user", e);
            throw new DaoException("Error adding user", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public Optional<User> authenticationById(int id) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<User> optionalUser = Optional.empty();
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        UserRole.valueOf(resultSet.getString("role"))
                );
                user.setId(resultSet.getInt("id"));
                optionalUser = Optional.of(user);
            }
            return optionalUser;
        } catch (SQLException e) {
            logger.error("Error retrieving user by ID", e);
            throw new DaoException("Error retrieving user by ID", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public Optional<User> authenticationByUsername(String username) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_USERNAME)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            Optional<User> optionalUser = Optional.empty();
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        UserRole.valueOf(resultSet.getString("role"))
                );
                user.setId(resultSet.getInt("id"));
                return optionalUser.of(user);
            }
            return optionalUser;
        } catch (SQLException e) {
            logger.error("Error retrieving user by ID", e);
            throw new DaoException("Error retrieving user by ID", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public List<User> getAllUsers() throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        UserRole.valueOf(resultSet.getString("role"))
                );
                user.setId(resultSet.getInt("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving all users", e);
            throw new DaoException("Error retrieving all users", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
        return users;
    }

    @Override
    public void updateUser(User user) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().toString());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
            logger.info("User updated: {}", user.getUsername());
        } catch (SQLException e) {
            logger.error("Error updating user", e);
            throw new DaoException("Error updating user", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void deleteUser(int id) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            logger.info("User deleted: ID {}", id);
        } catch (SQLException e) {
            logger.error("Error deleting user", e);
            throw new DaoException("Error deleting user", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
