package com.yavlash.library.model.dao.impl;

import com.yavlash.library.entity.Genre;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.model.dao.GenreDao;
import com.yavlash.library.model.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.yavlash.library.model.dao.ColumnName.GENRE_NAME_COLUMN;

public class GenreDaoImpl extends AbstractDaoImpl<Genre> implements GenreDao {
    private static final String SELECT_BY_ID_QUERY = "SELECT id, genre_name FROM genres WHERE id = ? and genre_status != 'DELETED'";
    private static final String SELECT_ALL_QUERY = "SELECT id, genre_name FROM genres WHERE genre_status != 'DELETED'";
    private static final String INSERT_QUERY = "INSERT INTO genres (genre_name) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE genres SET genre_name = ? WHERE id = %d and genre_status != 'DELETED'";
    private static final String DELETE_QUERY = "UPDATE genres SET genre_status = ? WHERE id = ?";
    private static final String SELECT_GENRE_BY_BOOK_ID = "SELECT id, genre_name FROM genres g " +
            " JOIN book_genre_links bgl ON g.id = bgl.genre_id " +
            " WHERE bgl.book_id = %d and g.genre_status != 'DELETED'";

    public GenreDaoImpl(ConnectionPool connectionPool) {
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
    public List<Genre> findGenresByBookId(Long id) throws DaoException {
        return findEntities(SELECT_GENRE_BY_BOOK_ID, id);
    }

    @Override
    protected Genre construct(ResultSet resultSet) throws SQLException {
        return Genre.builder()
                .id(resultSet.getLong(ID_COLUMN))
                .bookGenre(resultSet.getString(GENRE_NAME_COLUMN))
                .build();
    }

    @Override
    protected void settingPreparedParameter(PreparedStatement preparedStatement, Genre genre) throws SQLException {
        preparedStatement.setString(1, genre.getBookGenre());
    }
}