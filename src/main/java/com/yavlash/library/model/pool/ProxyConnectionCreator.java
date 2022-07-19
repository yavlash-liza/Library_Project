package com.yavlash.library.model.pool;

import com.yavlash.library.util.PropertyManager;
import com.yavlash.library.util.PropertyName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ProxyConnectionCreator {
    private static final Logger logger = LogManager.getLogger();

    private static final String DB_DRIVER;
    private static final String DB_URL;
    private static final String DB_USER;
    private static final String DB_PASSWORD;

    static {
        DB_DRIVER = PropertyManager.DATABASE_CONFIG.getString(PropertyName.DRIVER_PROPERTY_NAME);
        DB_URL = PropertyManager.DATABASE_CONFIG.getString(PropertyName.URL_PROPERTY_NAME);
        DB_USER = PropertyManager.DATABASE_CONFIG.getString(PropertyName.USER_PROPERTY_NAME);
        DB_PASSWORD = PropertyManager.DATABASE_CONFIG.getString(PropertyName.PASSWORD_PROPERTY_NAME);
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException exception) {
            logger.log(Level.FATAL, "Database driver not found {}", DB_DRIVER, exception);
            throw new RuntimeException("Database driver not found " + DB_DRIVER, exception);
        }
    }

    private ProxyConnectionCreator() {
    }

    static ProxyConnection create() throws SQLException {
        return new ProxyConnection(DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD));
    }
}