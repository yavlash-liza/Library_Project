package com.yavlash.library.model.dao;

import com.yavlash.library.entity.Author;
import com.yavlash.library.exception.DaoException;

import java.util.List;

public interface AuthorDao extends BaseDao<Author> {
    Author findByFirstAndLastName(String firstName, String lastName) throws DaoException;
    List<Author> findAuthorsByBookId(Long id) throws DaoException;
}