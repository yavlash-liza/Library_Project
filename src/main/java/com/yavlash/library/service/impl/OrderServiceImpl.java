package com.yavlash.library.service.impl;

import com.yavlash.library.entity.BookCopy;
import com.yavlash.library.entity.Order;
import com.yavlash.library.entity.User;
import com.yavlash.library.entity.dto.OrderDto;
import com.yavlash.library.entity.dto.OrderLibrarianListDto;
import com.yavlash.library.entity.dto.OrderSaveDto;
import com.yavlash.library.entity.dto.UserListDto;
import com.yavlash.library.entity.dto.converter.OrderConverter;
import com.yavlash.library.entity.dto.converter.UserConverter;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.exception.ServiceException;
import com.yavlash.library.model.dao.BookCopyDao;
import com.yavlash.library.model.dao.OrderDao;
import com.yavlash.library.model.dao.UserDao;
import com.yavlash.library.service.OrderService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final BookCopyDao bookCopyDao;
    private final OrderDao orderDao;
    private final UserDao userDao;

    @Override
    public OrderDto findById(Long orderId) throws ServiceException {
        try {
            Order order = orderDao.findById(orderId);
            User user = userDao.findById(order.getUser().getId());
            List<BookCopy> bookCopies = bookCopyDao.findBookCopiesByOrderId(order.getId());
            UserListDto reader = UserConverter.toListDto(user);
            return OrderConverter.toOrderDto(order, bookCopies, reader);
        } catch (Exception e) {
            logger.error("Exception in method order findById()", e);
            throw new ServiceException("Exception when find order by id", e);
        }
    }

    @Override
    public List<OrderLibrarianListDto> findAll() throws ServiceException {
        try {
            List<Order> orders = orderDao.findAllWithUser();
            return OrderConverter.toOrderLibrarianListDto(orders);
        } catch (Exception e) {
            logger.error("Exception in method order findAll()", e);
            throw new ServiceException("Exception when find all orders", e);
        }
    }

    @Override
    public boolean softDelete(Long orderId) throws ServiceException {
        try {
            orderDao.softDelete(orderId);
            return true;
        } catch (Exception e) {
            logger.error("Exception in method removeOrder()", e);
            throw new ServiceException("Exception when remove an order", e);
        }
    }

    @Override
    public boolean save(OrderSaveDto orderSaveDto) throws ServiceException {
        try {
            Order order = OrderConverter.fromOrderSaveDto(orderSaveDto);
            orderDao.save(order);
            saveBookToOrder(order.getId(), orderSaveDto.getBookCopiesId());
            return true;
        } catch (Exception e) {
            logger.error("Exception in method saveOrder()", e);
            throw new ServiceException("Exception when save an order", e);
        }
    }

    private void saveBookToOrder(Long orderId, List<Long> bookCopiesId) throws DaoException {
        for (Long bookCopyId : bookCopiesId) {
            orderDao.addBookCopyToOrder(bookCopyId, orderId);
        }
    }
}