package com.yavlash.library.model.dao.impl;

import com.yavlash.library.entity.Order;
import com.yavlash.library.entity.User;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.model.dao.BaseDaoTest;
import com.yavlash.library.model.dao.OrderDao;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImplTest extends BaseDaoTest {
    private final OrderDao orderRepository;
    private final List<Order> orders;

    public OrderDaoImplTest() {
        orderRepository = new OrderDaoImpl(getConnectionPool());
        orders = new ArrayList<>() {{
            add(Order.builder().id(1L).creationDate(LocalDate.of(2021, 12, 01)).
                    expirationDate(LocalDate.of(2022, 1, 1)).finalSum(BigDecimal.valueOf(20)).user(User.builder().id(2L).build()).build());
            add(Order.builder().id(2L).creationDate(LocalDate.of(2021, 12, 20)).
                    expirationDate(LocalDate.of(2022, 2, 14)).finalSum(BigDecimal.valueOf(30)).user(User.builder().id(4L).build()).build());
            add(Order.builder().id(3L).creationDate(LocalDate.of(2021, 12, 23)).
                    expirationDate(LocalDate.of(2022, 2, 13)).finalSum(BigDecimal.valueOf(10)).user(User.builder().id(8L).build()).build());
            add(Order.builder().id(4L).creationDate(LocalDate.of(2021, 12, 15)).
                    expirationDate(LocalDate.of(2022, 2, 12)).finalSum(BigDecimal.valueOf(9)).user(User.builder().id(3L).build()).build());
            add(Order.builder().id(5L).creationDate(LocalDate.of(2021, 12, 30)).
                    expirationDate(LocalDate.of(2022, 2, 12)).finalSum(BigDecimal.valueOf(38)).user(User.builder().id(11L).build()).build());
            add(Order.builder().id(6L).creationDate(LocalDate.of(2021, 12, 01)).
                    expirationDate(LocalDate.of(2022, 2, 11)).finalSum(BigDecimal.valueOf(40)).user(User.builder().id(7L).build()).build());
            add(Order.builder().id(7L).creationDate(LocalDate.of(2021, 12, 15)).
                    expirationDate(LocalDate.of(2022, 2, 12)).finalSum(BigDecimal.valueOf(9)).user(User.builder().id(6L).build()).build());
            add(Order.builder().id(8L).creationDate(LocalDate.of(2021, 12, 30)).
                    expirationDate(LocalDate.of(2022, 2, 12)).finalSum(BigDecimal.valueOf(38)).user(User.builder().id(10L).build()).build());
            add(Order.builder().id(9L).creationDate(LocalDate.of(2021, 12, 01)).
                    expirationDate(LocalDate.of(2022, 2, 11)).finalSum(BigDecimal.valueOf(40)).user(User.builder().id(7L).build()).build());
            add(Order.builder().id(10L).creationDate(LocalDate.of(2021, 12, 01)).
                    expirationDate(LocalDate.of(2022, 1, 1)).finalSum(BigDecimal.valueOf(20)).user(User.builder().id(12L).build()).build());
            add(Order.builder().id(11L).creationDate(LocalDate.of(2021, 12, 20)).
                    expirationDate(LocalDate.of(2022, 2, 19)).finalSum(BigDecimal.valueOf(30)).user(User.builder().id(4L).build()).build());
            add(Order.builder().id(12L).creationDate(LocalDate.of(2021, 12, 23)).
                    expirationDate(LocalDate.of(2022, 2, 13)).finalSum(BigDecimal.valueOf(10)).user(User.builder().id(5L).build()).build());
            add(Order.builder().id(13L).creationDate(LocalDate.of(2021, 12, 15)).
                    expirationDate(LocalDate.of(2022, 2, 18)).finalSum(BigDecimal.valueOf(9)).user(User.builder().id(11L).build()).build());
            add(Order.builder().id(14L).creationDate(LocalDate.of(2021, 12, 30)).
                    expirationDate(LocalDate.of(2022, 2, 20)).finalSum(BigDecimal.valueOf(38)).user(User.builder().id(8L).build()).build());
            add(Order.builder().id(15L).creationDate(LocalDate.of(2021, 12, 1)).
                    expirationDate(LocalDate.of(2022, 2, 10)).finalSum(BigDecimal.valueOf(40)).user(User.builder().id(6L).build()).build());
            add(Order.builder().id(16L).creationDate(LocalDate.of(2021, 12, 23)).
                    expirationDate(LocalDate.of(2022, 2, 1)).finalSum(BigDecimal.valueOf(36)).user(User.builder().id(3L).build()).build());
        }};
    }

    @Test
    public void findByIdTest_shouldFindOrderById() throws DaoException {
        //given
        Order expected = orders.get(0);

        //when
        Order actual = orderRepository.findById(expected.getId());

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findAllTest_shouldFindAllValuesInTable() throws DaoException {
        //given & when
        List<Order> actual = orderRepository.findAll();

        //then
        Assert.assertEquals(orders, actual);
    }

    @Test
    public void addTest_shouldAddNewString() throws DaoException {
        //given
        List<Order> expected = orderRepository.findAll();
        Assert.assertEquals(16, expected.size());

        //when
        Order newOrderActual = Order.builder().id(17L).creationDate(LocalDate.of(2022, 06, 20)).
                expirationDate(LocalDate.of(2021, 12, 4)).finalSum(BigDecimal.valueOf(30)).user(User.builder().id(2L).build()).build();
        boolean isAdded = orderRepository.save(newOrderActual);
        Order newOrderExpected = Order.builder().id(17L).creationDate(LocalDate.of(2022, 06, 20)).
                expirationDate(LocalDate.of(2021, 12, 4)).finalSum(BigDecimal.valueOf(30)).user(User.builder().id(2L).build()).build();
        expected.add(newOrderExpected);

        //then
        Assert.assertTrue(isAdded);
        Assert.assertEquals(newOrderExpected, newOrderActual);
        Assert.assertEquals(newOrderExpected, orderRepository.findById(newOrderActual.getId()));
    }

    @Test
    public void updateTest_shouldUpdateTable() throws DaoException {
        //given
        Order expected = Order.builder().id(2L).creationDate(LocalDate.of(2021, 12, 20)).
                expirationDate(LocalDate.of(2022, 2, 14)).finalSum(BigDecimal.valueOf(30)).user(User.builder().id(4L).build()).build();
        Order actual = orderRepository.findById(expected.getId());

        Assert.assertEquals(expected, actual);

        //when
        actual.setFinalSum(BigDecimal.valueOf(15));
        boolean isUpdated = orderRepository.update(actual);
        expected.setFinalSum(BigDecimal.valueOf(15));

        //then
        Assert.assertTrue(isUpdated);
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(actual, orderRepository.findById(actual.getId()));
    }

    @Test
    public void deleteTest_shouldSetDeleteStatusToStringInTable() throws DaoException {
        //given
        Order order = Order.builder().id(1L).creationDate(LocalDate.of(2021, 12, 01)).
                expirationDate(LocalDate.of(2022, 1, 1)).finalSum(BigDecimal.valueOf(20)).user(User.builder().id(2L).build()).build();

        List<Order> actual = orderRepository.findAll();
        Assert.assertEquals(16, actual.size());

        //when
        boolean isDeletedStatus = orderRepository.softDelete(order.getId());
        actual.remove(0);

        //then
        Assert.assertTrue(isDeletedStatus);
        Assert.assertEquals(actual, orderRepository.findAll());
    }
}