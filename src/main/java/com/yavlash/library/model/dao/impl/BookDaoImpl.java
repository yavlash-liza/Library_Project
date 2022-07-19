package com.yavlash.library.model.dao.impl;

import com.yavlash.library.entity.Book;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.model.dao.BookDao;
import com.yavlash.library.model.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.yavlash.library.model.dao.ColumnName.*;

public class BookDaoImpl extends AbstractDaoImpl<Book> implements BookDao {
    private static final String FIELDS = "b.title, b.release_year, b.pages, b.book_photo_path";

    private static final String SELECT_BY_ID_QUERY = "SELECT b.id, " + FIELDS + " FROM books b WHERE b.id = ? and b.book_status != 'DELETED'";
    private static final String SELECT_ALL_QUERY = "SELECT b.id, " + FIELDS + " FROM books b WHERE b.book_status != 'DELETED'";
    private static final String INSERT_QUERY = "INSERT INTO books (title, release_year, pages, book_photo_path) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY =
            "UPDATE books SET title = ?, release_year = ?, pages = ?, book_photo_path = ? WHERE id = %d and book_status != 'DELETED'";
    private static final String DELETE_QUERY = "UPDATE books SET book_status = ? WHERE id = ?";

    private static final String ADD_GENRE_TO_BOOK_QUERY = "INSERT INTO book_genre_links (book_id, genre_id) VALUES (?,?)";

    private static final String ADD_AUTHOR_TO_BOOK_QUERY = "INSERT INTO author_book_links (author_id, book_id) VALUES (?,?)";

    public BookDaoImpl(ConnectionPool connectionPool) {
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
    public boolean addGenreToBook(Long bookId, Long genreId) throws DaoException {
        return addEntityToEntity(ADD_GENRE_TO_BOOK_QUERY, bookId, genreId);
    }

    @Override
    public boolean addAuthorToBook(Long authorId, Long bookId) throws DaoException {
        return addEntityToEntity(ADD_AUTHOR_TO_BOOK_QUERY, authorId, bookId);
    }

    @Override
    protected Book construct(ResultSet resultSet) throws SQLException {
        return Book.builder()
                .id(resultSet.getLong(ID_COLUMN))
                .title(resultSet.getString(TITLE_COLUMN))
                .releaseYear(resultSet.getInt(RELEASE_COLUMN))
                .pages(resultSet.getInt(PAGES_COLUMN))
                .bookPhotoPath(resultSet.getString(BOOK_PHOTO_PATH_COLUMN))
                .build();
    }

    @Override
    protected void settingPreparedParameter(PreparedStatement preparedStatement, Book book) throws SQLException {
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setInt(2, book.getReleaseYear());
        preparedStatement.setInt(3, book.getPages());
        preparedStatement.setString(4, book.getBookPhotoPath());
    }
}