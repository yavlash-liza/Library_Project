package com.yavlash.library.model.dao.impl;

import com.yavlash.library.entity.Order;
import com.yavlash.library.entity.User;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.model.dao.OrderDao;
import com.yavlash.library.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.yavlash.library.model.dao.ColumnName.*;

public class OrderDaoImpl extends AbstractDaoImpl<Order> implements OrderDao {
    private static final String FIELDS = "creation_date, expiration_date, fine_sum, user_id";

    private static final String SELECT_BY_ID_QUERY = "SELECT id, " + FIELDS + " FROM orders WHERE id = ? and order_status != 'DELETED'";
    private static final String SELECT_ALL_QUERY = "SELECT id, " + FIELDS + " FROM orders WHERE order_status != 'DELETED'";

    private static final String SELECT_ALL_WITH_USER_QUERY =
        "SELECT o.id, " + FIELDS + ", email FROM orders o LEFT JOIN users u ON o.user_id=u.id WHERE order_status != 'DELETED'";
    private static final String INSERT_QUERY = "INSERT INTO orders (expiration_date, fine_sum, user_id) VALUES (?,?,?)";
    private static final String UPDATE_QUERY =
            "UPDATE orders SET expiration_date = ?, fine_sum = ?, user_id = ? WHERE id = %d and order_status != 'DELETED'";
    private static final String DELETE_QUERY = "UPDATE orders SET order_status = ? WHERE id = ?";

    private static final String ADD_BOOK_COPY_TO_ORDER_QUERY = "INSERT INTO order_book_copy_links (book_copy_id, order_id) VALUES (?,?)";

    private static final String SELECT_ORDERS_BY_USER_ID_QUERY = "SELECT o.id, " + FIELDS + " FROM orders o " +
            " WHERE o.user_id = %d and o.order_status != 'DELETED'";

    public OrderDaoImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    protected String defineSelectByIdQuery() {
        return SELECT_BY_ID_QUERY;
    }

    @Override
    protected String defineSelectAllQuery() {
        return SELECT_ALL_QUERY;
    }

    @Override
    protected String defineInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    protected String defineUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String defineDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    public void addBookCopyToOrder(Long bookCopyId, Long orderId) throws DaoException {
        addEntityToEntity(ADD_BOOK_COPY_TO_ORDER_QUERY, bookCopyId, orderId);
    }

    @Override
    public List<Order> findOrdersByUserId(Long id) throws DaoException {
        return findEntities(SELECT_ORDERS_BY_USER_ID_QUERY, id);
    }

    @Override
    public List<Order> findAllWithUser() throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = getConnectionPool().takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_WITH_USER_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                orders.add(constructWithUser(resultSet));
            }
            return orders;
        } catch (Exception e) {
            throw new DaoException("{} find entities in class: " + getClass().getSimpleName() + " has been failed: " + e.getMessage(), e);
        }
    }

    private Order constructWithUser(ResultSet resultSet) throws SQLException {
        Order order = construct(resultSet);
        order.getUser().setEmail(resultSet.getString(EMAIL_COLUMN));
        return order;
    }

    @Override
    protected Order construct(ResultSet resultSet) throws SQLException {
        return Order.builder()
                .id(resultSet.getLong(ID_COLUMN))
                .creationDate(resultSet.getDate(CREATION_DATE_COLUMN).toLocalDate())
                .expirationDate(resultSet.getDate(EXPIRATION_DATE_COLUMN).toLocalDate())
                .finalSum(resultSet.getBigDecimal(FINAL_SUM_COLUMN))
                .user(User.builder().id(resultSet.getLong(ORDER_USER_ID_COLUMN)).build())
                .build();
    }

    @Override
    protected void settingPreparedParameter(PreparedStatement preparedStatement, Order order) throws SQLException {
        preparedStatement.setDate(1, Date.valueOf(order.getExpirationDate()));
        preparedStatement.setBigDecimal(2, order.getFinalSum());
        preparedStatement.setLong(3, order.getUser().getId());
    }
}