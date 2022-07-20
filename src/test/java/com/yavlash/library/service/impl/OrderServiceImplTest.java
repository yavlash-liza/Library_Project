package com.yavlash.library.service.impl;

import com.yavlash.library.entity.Order;
import com.yavlash.library.entity.User;
import com.yavlash.library.entity.dto.OrderLibrarianListDto;
import com.yavlash.library.entity.dto.UserOrderListDto;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.exception.ServiceException;
import com.yavlash.library.model.dao.BookCopyDao;
import com.yavlash.library.model.dao.OrderDao;
import com.yavlash.library.model.dao.UserDao;
import com.yavlash.library.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    private final BookCopyDao bookCopyDao;
    private final OrderDao orderDao;
    private final UserDao userDao;

    private final OrderService orderService;

    public OrderServiceImplTest() {
        bookCopyDao = Mockito.mock(BookCopyDao.class);
        userDao = Mockito.mock(UserDao.class);
        orderDao = Mockito.mock(OrderDao.class);
        orderService = new OrderServiceImpl(bookCopyDao, orderDao, userDao);
    }

    @Test
    public void findAllTest_shouldFindAllOrders() throws DaoException, ServiceException {
        //given
        List<Order> orders = new ArrayList<>() {{
            add(Order.builder().id(1L).expirationDate(LocalDate.of(2020, 9, 10)).finalSum(BigDecimal.valueOf(1.2))
                .user(User.builder().build()).build());
            add(Order.builder().id(2L).expirationDate(LocalDate.of(2022, 9, 10)).finalSum(BigDecimal.valueOf(30))
                .user(User.builder().build()).build());
            add(Order.builder().id(3L).expirationDate(LocalDate.of(2020, 9, 10)).finalSum(BigDecimal.valueOf(40.00))
                .user(User.builder().build()).build());
        }};

        List<OrderLibrarianListDto> expected = new ArrayList<>() {{
            add(OrderLibrarianListDto.builder().id(1L).expirationDate(LocalDate.of(2020, 9, 10)).finalSum(BigDecimal.valueOf(1.2))
                .user(UserOrderListDto.builder().build()).build());
            add(OrderLibrarianListDto.builder().id(2L).expirationDate(LocalDate.of(2022, 9, 10)).finalSum(BigDecimal.valueOf(30))
                .user(UserOrderListDto.builder().build()).build());
            add(OrderLibrarianListDto.builder().id(3L).expirationDate(LocalDate.of(2020, 9, 10)).finalSum(BigDecimal.valueOf(40.00))
                .user(UserOrderListDto.builder().build()).build());
        }};

        //when
        Mockito.when(orderDao.findAllWithUser()).thenReturn(orders);
        List<OrderLibrarianListDto> actual = orderService.findAll();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeOrderTest() throws DaoException, ServiceException {
        //given
        Long orderId = 1L;

        //when
        Mockito.when(orderDao.softDelete(orderId)).thenReturn(true);
        boolean isDeleted = orderService.softDelete(orderId);

        //then
        Assert.assertTrue(isDeleted);
    }
}