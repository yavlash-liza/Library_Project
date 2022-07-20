package com.yavlash.library.service;

import com.yavlash.library.model.dao.AuthorDao;
import com.yavlash.library.model.dao.BookCopyDao;
import com.yavlash.library.model.dao.BookDao;
import com.yavlash.library.model.dao.GenreDao;
import com.yavlash.library.model.dao.OrderDao;
import com.yavlash.library.model.dao.UserDao;
import com.yavlash.library.model.dao.impl.AuthorDaoImpl;
import com.yavlash.library.model.dao.impl.BookCopyDaoImpl;
import com.yavlash.library.model.dao.impl.BookDaoImpl;
import com.yavlash.library.model.dao.impl.GenreDaoImpl;
import com.yavlash.library.model.dao.impl.OrderDaoImpl;
import com.yavlash.library.model.dao.impl.UserDaoImpl;
import com.yavlash.library.model.pool.ConnectionPool;

import com.yavlash.library.util.PasswordEncryptor;
import java.util.HashMap;
import java.util.Map;

public class FactoryDependencyService {
    private final Map<String, Object> dependencies;

    private FactoryDependencyService(Map<String, Object> dependencies) {
        this.dependencies = dependencies;
    }

    private static class FactoryDependencyServiceHolder {
        public static final FactoryDependencyService instance = new FactoryDependencyService(new HashMap<>());
    }

    public static FactoryDependencyService getInstance() {
        return FactoryDependencyServiceHolder.instance;
    }

    public AuthorDao getAuthorDao() {
        if (!dependencies.containsKey(AuthorDao.class.getSimpleName())) {
            dependencies.put(AuthorDao.class.getSimpleName(), new AuthorDaoImpl(ConnectionPool.getInstance()));
        }
        return (AuthorDaoImpl) dependencies.get(AuthorDao.class.getSimpleName());
    }

    public BookDao getBookDao() {
        if (!dependencies.containsKey(BookDao.class.getSimpleName())) {
            dependencies.put(BookDao.class.getSimpleName(), new BookDaoImpl(ConnectionPool.getInstance()));
        }
        return (BookDaoImpl) dependencies.get(BookDao.class.getSimpleName());
    }

    public BookCopyDao getBookCopyDao() {
        if (!dependencies.containsKey(BookCopyDao.class.getSimpleName())) {
            dependencies.put(BookCopyDao.class.getSimpleName(), new BookCopyDaoImpl(ConnectionPool.getInstance()));
        }
        return (BookCopyDaoImpl) dependencies.get(BookCopyDao.class.getSimpleName());
    }

    public GenreDao getGenreDao() {
        if (!dependencies.containsKey(GenreDao.class.getSimpleName())) {
            dependencies.put(GenreDao.class.getSimpleName(), new GenreDaoImpl(ConnectionPool.getInstance()));
        }
        return (GenreDaoImpl) dependencies.get(GenreDao.class.getSimpleName());
    }

    public UserDao getUserDao() {
        if (!dependencies.containsKey(UserDao.class.getSimpleName())) {
            dependencies.put(UserDao.class.getSimpleName(), new UserDaoImpl(ConnectionPool.getInstance()));
        }
        return (UserDaoImpl) dependencies.get(UserDao.class.getSimpleName());
    }

    public OrderDao getOrderDao() {
        if (!dependencies.containsKey(OrderDao.class.getSimpleName())) {
            dependencies.put(OrderDao.class.getSimpleName(), new OrderDaoImpl(ConnectionPool.getInstance()));
        }
        return (OrderDaoImpl) dependencies.get(OrderDao.class.getSimpleName());
    }

    public PasswordEncryptor getPasswordEncryptor() {
        if (!dependencies.containsKey(PasswordEncryptor.class.getSimpleName())) {
            dependencies.put(PasswordEncryptor.class.getSimpleName(), new PasswordEncryptor());
        }
        return (PasswordEncryptor) dependencies.get(PasswordEncryptor.class.getSimpleName());
    }
}
