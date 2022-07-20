package com.yavlash.library.service;

import com.yavlash.library.entity.dto.AuthorDto;
import com.yavlash.library.exception.ServiceException;

import java.util.List;

public interface AuthorService extends Service {
    List<AuthorDto> findAll() throws ServiceException;
    boolean save(AuthorDto authorDto) throws ServiceException;
}
