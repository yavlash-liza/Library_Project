package com.yavlash.library.model.dao;

import com.yavlash.library.entity.BookCopy;
import com.yavlash.library.exception.DaoException;

import java.util.List;

public interface BookCopyDao extends BaseDao<BookCopy> {
    List<BookCopy> findBookCopiesByBookId(Long id) throws DaoException;
    List<BookCopy> findBookCopiesByOrderId(Long orderId) throws DaoException;
}