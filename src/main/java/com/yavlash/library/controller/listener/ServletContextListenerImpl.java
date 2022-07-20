package com.yavlash.library.controller.listener;

import com.yavlash.library.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().closePool();
        logger.log(Level.INFO,"Connection pool was destroyed");
    }
}