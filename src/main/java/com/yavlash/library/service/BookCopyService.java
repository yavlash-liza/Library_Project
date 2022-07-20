package com.yavlash.library.service;

import com.yavlash.library.entity.dto.BookCopyDto;
import com.yavlash.library.entity.dto.BookCopyListDto;
import com.yavlash.library.entity.dto.BookCopySaveDto;
import com.yavlash.library.exception.ServiceException;
import java.util.List;

public interface BookCopyService {
    BookCopyDto findById(Long bookCopyId) throws ServiceException;
    List<BookCopyListDto> findAll(List<Long> bookCopiesId) throws ServiceException;
    boolean save(BookCopySaveDto bookCopySaveDto) throws ServiceException;
    boolean update(BookCopySaveDto bookCopySaveDto) throws ServiceException;
}
