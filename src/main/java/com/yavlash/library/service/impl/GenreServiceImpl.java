package com.yavlash.library.service.impl;

import com.yavlash.library.entity.Genre;
import com.yavlash.library.entity.dto.GenreDto;
import com.yavlash.library.entity.dto.converter.GenreConverter;
import com.yavlash.library.exception.ServiceException;
import com.yavlash.library.model.dao.GenreDao;
import com.yavlash.library.service.GenreService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    @Override
    public List<GenreDto> findAll() throws ServiceException {
        try {
            List<Genre> genres = genreDao.findAll();
            return GenreConverter.toDto(genres);
        } catch (Exception e) {
            logger.error("Exception in method genre findAll()", e);
            throw new ServiceException("Exception when find all genres", e);
        }
    }
}