package com.yavlash.library.model.dao;

import com.yavlash.library.entity.Book;
import com.yavlash.library.exception.DaoException;

public interface BookDao extends BaseDao<Book> {
    boolean addGenreToBook(Long bookId, Long genreId) throws DaoException;
    boolean addAuthorToBook(Long authorId, Long bookId) throws DaoException;
}