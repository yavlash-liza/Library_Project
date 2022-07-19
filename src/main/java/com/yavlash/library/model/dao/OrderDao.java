package com.yavlash.library.model.dao;

import com.yavlash.library.entity.Order;
import com.yavlash.library.exception.DaoException;

import java.util.List;

public interface OrderDao extends BaseDao<Order> {
    void addBookCopyToOrder(Long orderId, Long bookId) throws DaoException;
    List<Order> findOrdersByUserId(Long id) throws DaoException;
    List<Order> findAllWithUser()  throws DaoException;
}