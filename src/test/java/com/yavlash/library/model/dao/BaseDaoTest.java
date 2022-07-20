package com.yavlash.library.model.dao;

import com.yavlash.library.model.pool.ConnectionPool;
import com.yavlash.library.util.FlywayService;
import org.junit.After;
import org.junit.Before;

public abstract class BaseDaoTest {
    private final FlywayService flywayService;
    private final ConnectionPool connectionPool;

    public BaseDaoTest() {
        flywayService = new FlywayService();
        connectionPool = ConnectionPool.getInstance();
    }

    @Before
    public void initDB() {
        flywayService.migrate();
    }

    @After
    public void cleanDB() {
        flywayService.clean();
    }

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }
}