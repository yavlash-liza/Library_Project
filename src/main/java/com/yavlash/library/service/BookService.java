package com.yavlash.library.service;

import com.yavlash.library.entity.dto.BookDto;
import com.yavlash.library.entity.dto.BookListDto;
import com.yavlash.library.entity.dto.BookSaveDto;
import com.yavlash.library.exception.ServiceException;

import java.util.List;

public interface BookService extends Service {
    BookDto findById(Long id) throws ServiceException;
    List<BookListDto> findAll() throws ServiceException;
    boolean save(BookSaveDto bookSaveDto) throws ServiceException;
    boolean update(BookSaveDto bookSaveDto) throws ServiceException;
    boolean softDelete(Long bookId) throws ServiceException;
}