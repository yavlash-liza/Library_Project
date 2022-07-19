package com.yavlash.library.model.dao;

import com.yavlash.library.entity.Genre;
import com.yavlash.library.exception.DaoException;

import java.util.List;

public interface GenreDao extends BaseDao<Genre> {
    List<Genre> findGenresByBookId(Long id) throws DaoException;
}