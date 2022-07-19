package com.yavlash.library.model.dao;

import com.yavlash.library.entity.BaseEntity;
import com.yavlash.library.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public interface BaseDao<E extends BaseEntity<Long>> {
    Logger logger = LogManager.getLogger();
    E findById(Long id) throws DaoException;
    List<E> findAll() throws DaoException;
    boolean save(E e) throws DaoException;
    boolean update(E e) throws DaoException;
    boolean softDelete(Long id) throws DaoException;
}