package com.yavlash.library.service;

import com.yavlash.library.entity.dto.GenreDto;
import com.yavlash.library.exception.ServiceException;

import java.util.List;

public interface GenreService extends Service {
    List<GenreDto> findAll() throws ServiceException;
}