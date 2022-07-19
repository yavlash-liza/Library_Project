package com.yavlash.library.model.dao.impl;

import com.yavlash.library.entity.Author;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.model.dao.AuthorDao;
import com.yavlash.library.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.yavlash.library.model.dao.ColumnName.AUTHOR_FIRST_NAME_COLUMN;
import static com.yavlash.library.model.dao.ColumnName.AUTHOR_LAST_NAME_COLUMN;

public class AuthorDaoImpl extends AbstractDaoImpl<Author> implements AuthorDao {
    private static final String FIELDS = "first_name, last_name";

    private static final String SELECT_BY_ID_QUERY = "SELECT id, " + FIELDS + " FROM authors WHERE id = ? and author_status != 'DELETED'";
    private static final String SELECT_ALL_QUERY = "SELECT id, " + FIELDS + " FROM authors WHERE author_status != 'DELETED'";
    private static final String INSERT_QUERY = "INSERT INTO authors (" + FIELDS + ") VALUES (?,?)";
    private static final String UPDATE_QUERY = "UPDATE authors SET first_name = ?, last_name = ? WHERE id = %d and author_status != 'DELETED'";
    private static final String DELETE_QUERY = "UPDATE authors SET author_status = ? WHERE id = ?";

    private static final String SELECT_BY_AUTHOR_LAST_AND_FIRST_NAME_QUERY = "SELECT id, " + FIELDS + " FROM authors " +
            " WHERE  first_name = ? and last_name = ? and author_status != 'deleted'";

    private static final String SELECT_AUTHOR_BY_BOOK_ID = "SELECT id, first_name, last_name FROM authors a " +
            " JOIN author_book_links abl ON a.id = abl.author_id " +
            " WHERE abl.book_id = %d and a.author_status != 'DELETED'";

    public AuthorDaoImpl(ConnectionPool connectionPool) {
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
    public List<Author> findAuthorsByBookId(Long id) throws DaoException {
        return findEntities(SELECT_AUTHOR_BY_BOOK_ID, id);
    }

    @Override
    public Author findByFirstAndLastName(String firstName, String lastName) throws DaoException {
        try (Connection connection = getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_AUTHOR_LAST_AND_FIRST_NAME_QUERY);
        ) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return construct(resultSet);
                }
            }
        } catch (Exception e) {
            throw new DaoException("{} findByFirstAndLastName in class: " + getClass().getSimpleName() + " has been failed: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected Author construct(ResultSet resultSet) throws SQLException {
        return Author.builder()
                .id(resultSet.getLong(ID_COLUMN))
                .firstName(resultSet.getString(AUTHOR_FIRST_NAME_COLUMN))
                .lastName(resultSet.getString(AUTHOR_LAST_NAME_COLUMN))
                .build();
    }

    @Override
    protected void settingPreparedParameter(PreparedStatement preparedStatement, Author author) throws SQLException {
        preparedStatement.setString(1, author.getFirstName());
        preparedStatement.setString(2, author.getLastName());
    }
}