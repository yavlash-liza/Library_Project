package com.yavlash.library.service.impl;

import com.yavlash.library.entity.Author;
import com.yavlash.library.entity.dto.AuthorDto;
import com.yavlash.library.entity.dto.converter.AuthorConverter;
import com.yavlash.library.exception.ServiceException;
import com.yavlash.library.model.dao.AuthorDao;
import com.yavlash.library.service.AuthorService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    @Override
    public List<AuthorDto> findAll() throws ServiceException {
        try {
            List<Author> authors = authorDao.findAll();
            return AuthorConverter.toDto(authors);
        } catch (Exception e) {
            logger.error("Exception in method authors findAll()", e);
            throw new ServiceException("Exception when find all authors", e);
        }
    }

    @Override
    public boolean save(AuthorDto authorDto) throws ServiceException {
        try {
            Author author = AuthorConverter.fromDto(authorDto);
            return authorDao.save(author);
        } catch (Exception e) {
            logger.error("Author wasn't saved", e);
            throw new ServiceException("Exception when save an author", e);
        }
    }
}