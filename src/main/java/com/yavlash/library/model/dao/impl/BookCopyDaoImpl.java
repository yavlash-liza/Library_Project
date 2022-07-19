package com.yavlash.library.model.dao.impl;

import com.yavlash.library.entity.Book;
import com.yavlash.library.entity.BookCopy;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.model.dao.BookCopyDao;
import com.yavlash.library.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.yavlash.library.model.dao.ColumnName.*;

public class BookCopyDaoImpl extends AbstractDaoImpl<BookCopy> implements BookCopyDao {

    private static final String FIELDS =
        "bc.price_per_day, bc.published_year, bc.book_id, bc.register_date, bc.copy_status";

    private static final String SELECT_BY_ID_QUERY =
        "SELECT bc.id, " + FIELDS + " FROM book_copies bc WHERE bc.id = ? and bc.copy_status != 'DELETED'";
    private static final String SELECT_ALL_QUERY =
        "SELECT bc.id, " + FIELDS + " FROM book_copies bc WHERE bc.copy_status != 'DELETED'";
    private static final String INSERT_QUERY =
        "INSERT INTO book_copies (price_per_day, published_year, book_id) VALUES (?,?,?)";
    private static final String UPDATE_QUERY =
        "UPDATE book_copies SET price_per_day = ?, published_year = ?, book_id = ? WHERE id = %d and copy_status != 'DELETED'";
    private static final String DELETE_QUERY = "UPDATE book_copies SET copy_status = ? WHERE id = ?";

    private static final String FIND_BY_BOOK_ID_QUERY = "SELECT bc.id, " + FIELDS + " FROM book_copies bc " +
        "WHERE bc.book_id = %d and bc.copy_status != 'DELETED'";

    private static final String SELECT_BOOK_COPIES_BY_ORDER_ID_QUERY =
        "SELECT bc.id, " + FIELDS + ", b.title FROM order_book_copy_links obcl" +
            " LEFT JOIN book_copies bc ON obcl.book_copy_id = bc.id " +
            " LEFT JOIN books b ON bc.book_id = b.id " +
            " WHERE obcl.order_id = %d and bc.copy_status != 'DELETED'";

    public BookCopyDaoImpl(ConnectionPool connectionPool) {
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
    public List<BookCopy> findBookCopiesByBookId(Long id) throws DaoException {
        return findEntities(FIND_BY_BOOK_ID_QUERY, id);
    }

    @Override
    public List<BookCopy> findBookCopiesByOrderId(Long orderId) throws DaoException {
        List<BookCopy> bookCopies = new ArrayList<>();
        String query = String.format(SELECT_BOOK_COPIES_BY_ORDER_ID_QUERY, orderId);
        try (Connection connection = getConnectionPool().takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                bookCopies.add(constructBookCopy(resultSet));
            }
            return bookCopies;
        } catch (Exception e) {
            throw new DaoException(
                "{} find bookCopies in class: " + getClass().getSimpleName() + " has been failed: " + e.getMessage(),
                e);
        }
    }

    private BookCopy constructBookCopy(ResultSet resultSet) throws SQLException {
        BookCopy bookCopy = construct(resultSet);
        Book book = bookCopy.getBook();
        book.setTitle(resultSet.getString(TITLE_COLUMN));
        return bookCopy;
    }

    @Override
    protected BookCopy construct(ResultSet resultSet) throws SQLException {
        return BookCopy.builder()
            .id(resultSet.getLong(ID_COLUMN))
            .pricePerDay(resultSet.getBigDecimal(PRICE_PER_DAY_COLUMN))
            .registerDate(resultSet.getDate(REGISTER_DATE_COLUMN).toLocalDate())
            .publishedYear(resultSet.getInt(PUBLISHED_YEAR_COLUMN))
            .copyStatus(resultSet.getString(COPY_STATUS_COLUMN))
            .book(Book.builder().id(resultSet.getLong(BOOK_ID_COLUMN)).build())
            .build();
    }

    @Override
    protected void settingPreparedParameter(PreparedStatement preparedStatement, BookCopy bookCopy)
        throws SQLException {
        preparedStatement.setBigDecimal(1, bookCopy.getPricePerDay());
        preparedStatement.setInt(2, bookCopy.getPublishedYear());
        preparedStatement.setLong(3, bookCopy.getBook().getId());
    }
}