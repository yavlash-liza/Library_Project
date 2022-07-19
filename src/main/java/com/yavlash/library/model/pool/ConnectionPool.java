package com.yavlash.library.model.pool;

import com.yavlash.library.util.PropertyManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool{
    private static final Logger logger = LogManager.getLogger();
    private static final String POOL_SIZE_PROPERTY_NAME = "pool.size";
    private static final int DEFAULT_POOL_SIZE = 4;
    private static ConnectionPool instance;
    private static final ReentrantLock lock = new ReentrantLock(true);
    private static final AtomicBoolean create = new AtomicBoolean(false);
    private int poolSize;
    private BlockingQueue<ProxyConnection> availableConnection;
    private BlockingQueue<ProxyConnection> usedConnection;

    private ConnectionPool() {
        availableConnection = new LinkedBlockingQueue<>();
        usedConnection = new LinkedBlockingQueue<>();
        try {
            poolSize = Integer.parseInt(PropertyManager.DATABASE_CONFIG.getString(POOL_SIZE_PROPERTY_NAME));
        } catch (NumberFormatException exception){
            poolSize = DEFAULT_POOL_SIZE;
            logger.error("{} Can't get pool size from property file, default pool size = {}", poolSize, exception);
        }
        for (int i = 0; i < poolSize; i++) {
            try {
                availableConnection.offer(ProxyConnectionCreator.create());
            } catch (SQLException exception) {
                logger.error("{} proxy connection wasn't created", i, exception);
            }
        }
        if (availableConnection.isEmpty()){
            logger.log(Level.FATAL, "Connection pool is empty");
            throw new RuntimeException("Connection pool is empty");
        }
        logger.log(Level.INFO,"Connection pool created expected size = {}, actual = {}", availableConnection.size(), poolSize);
    }

    public static ConnectionPool getInstance() {
        if (!create.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    create.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection takeConnection() {
        ProxyConnection connection = null;
        try {
            connection = availableConnection.take();
            usedConnection.offer(connection);
        } catch (InterruptedException exception) {
            logger.error("Taked connection was interrupted", exception);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection){
        boolean result = false;
        if (connection instanceof ProxyConnection){
            try {
                ProxyConnection proxyConnection = (ProxyConnection) connection;
                result = usedConnection.remove(proxyConnection);
                if (result){
                    availableConnection.put(proxyConnection);
                }
            } catch (InterruptedException exception) {
                logger.error("Released connection was interrupted", exception);
                Thread.currentThread().interrupt();
            }
        }
        return result;
    }

    public void closePool() {
        for (int i = 0; i < poolSize; i++) {
            try {
                availableConnection.take().realClose();
            } catch (SQLException exception) {
                logger.error("SQL error while destroy {} connection", i, exception);
            } catch (InterruptedException exception) {
                logger.error("Destroying pool was interrupted", exception);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
        instance = null;
        create.set(false);
        logger.log(Level.INFO, "Pool is destroyed");
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException exception) {
                logger.error("SQL error while deregister driver", exception);
            }
        });
    }
}