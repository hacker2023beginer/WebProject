package com.innowise.webproject.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class PostgresConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final String PROPERTY_PATH = "db"; // db.properties in resources

    private ArrayBlockingQueue<Connection> freeConnections;
    private ArrayBlockingQueue<Connection> takenConnections;

    private static final ReentrantLock lock = new ReentrantLock();
    private static volatile PostgresConnectionPool instance;

    // Singleton
    public static PostgresConnectionPool getInstance() {
        if (instance == null) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new PostgresConnectionPool();
                }
            } catch (Exception e) {
                logger.fatal("Cannot get instance of connection pool", e);
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private PostgresConnectionPool() {
        try {
            lock.lock();
            if (instance != null) {
                throw new UnsupportedOperationException("Use getInstance() instead");
            } else {
                DriverManager.registerDriver(new org.postgresql.Driver());
                init();
            }
        } catch (SQLException e) {
            logger.fatal("Cannot initialize PostgreSQL connection pool", e);
        } finally {
            lock.unlock();
        }
    }

    private void init() {
        ResourceBundle resource = ResourceBundle.getBundle(PROPERTY_PATH, Locale.getDefault());
        if (resource == null) {
            logger.fatal("Error while reading properties");
        } else {
            String connectionURL = resource.getString("db.url");
            String initialCapacityString = resource.getString("db.poolsize");
            String user = resource.getString("db.user");
            String pass = resource.getString("db.password");

            int initialCapacity = Integer.parseInt(initialCapacityString);

            freeConnections = new ArrayBlockingQueue<>(initialCapacity);
            takenConnections = new ArrayBlockingQueue<>(initialCapacity);

            for (int i = 0; i < initialCapacity; i++) {
                try {
                    Connection connection = DriverManager.getConnection(connectionURL, user, pass);
                    freeConnections.add(connection);
                } catch (SQLException e) {
                    logger.fatal("Pool cannot be initialized with given parameters", e);
                }
            }
            logger.info("PostgreSQL connection pool initialized with {} connections", initialCapacity);
        }
    }

    public Connection getConnection() {
        try {
            Connection connection = freeConnections.take();
            takenConnections.offer(connection);
            logger.info("Connection taken. Free connections: {}", freeConnections.size());
            return connection;
        } catch (InterruptedException e) {
            logger.fatal("Could not get a connection to the database", e);
        }
        return null;
    }

    public void releaseConnection(Connection connection) {
        takenConnections.remove(connection);
        freeConnections.offer(connection);
        logger.info("Connection released. Free connections: {}", freeConnections.size());
    }

    public void destroy() {
        try {
            while (!freeConnections.isEmpty()) {
                Connection connection = freeConnections.take();
                connection.close();
            }
            while (!takenConnections.isEmpty()) {
                Connection connection = takenConnections.take();
                connection.close();
            }
        } catch (InterruptedException | SQLException e) {
            logger.fatal("Couldn't close connections while destroying the pool", e);
        }

        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
            logger.info("All drivers unregistered");
        } catch (SQLException e) {
            logger.fatal("Drivers could not be unregistered", e);
        }
    }
}
