package com.yavlash.library.service;

import com.yavlash.library.entity.dto.OrderDto;
import com.yavlash.library.entity.dto.OrderLibrarianListDto;
import com.yavlash.library.entity.dto.OrderSaveDto;
import com.yavlash.library.exception.ServiceException;

import java.util.List;

public interface OrderService extends Service {
    OrderDto findById(Long id) throws ServiceException;
    List<OrderLibrarianListDto> findAll() throws ServiceException;
    boolean save(OrderSaveDto orderSaveDto) throws ServiceException;
    boolean softDelete(Long orderId) throws ServiceException;
}