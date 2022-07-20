package com.yavlash.library.service.impl;

import com.yavlash.library.entity.Book;
import com.yavlash.library.entity.BookCopy;
import com.yavlash.library.entity.dto.BookCopyDto;
import com.yavlash.library.entity.dto.BookCopyListDto;
import com.yavlash.library.entity.dto.BookCopySaveDto;
import com.yavlash.library.entity.dto.converter.BookCopyConverter;
import com.yavlash.library.exception.RuntimeServiceException;
import com.yavlash.library.exception.ServiceException;
import com.yavlash.library.model.dao.BookCopyDao;
import com.yavlash.library.model.dao.BookDao;
import com.yavlash.library.service.BookCopyService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BookCopyServiceImpl implements BookCopyService {
    private final BookCopyDao bookCopyDao;
    private final BookDao bookDao;

    @Override
    public BookCopyDto findById(Long bookCopyId) throws ServiceException {
        try {
            BookCopy bookCopy = bookCopyDao.findById(bookCopyId);
            Book book = bookDao.findById(bookCopy.getId());
            bookCopy.setBook(book);
            return BookCopyConverter.toDto(bookCopy);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookCopyListDto> findAll(List<Long> bookCopiesId) throws ServiceException {
        return bookCopiesId.stream()
            .map(this::findBookCopy)
            .map(BookCopyConverter::toListDto)
            .collect(Collectors.toList());
    }

    private BookCopy findBookCopy(Long bookCopyId) {
        try {
            BookCopy bookCopy = bookCopyDao.findById(bookCopyId);
            Book book = bookDao.findById(bookCopy.getId());
            bookCopy.setBook(book);
            return bookCopy;
        } catch (Exception e) {
            throw new RuntimeServiceException(e);
        }
    }

    @Override
    public boolean save(BookCopySaveDto bookCopySaveDto) throws ServiceException {
        try{
            BookCopy bookCopy = BookCopyConverter.fromSaveDto(bookCopySaveDto);
            int count = bookCopySaveDto.getCount();
            while(count-- != 0) {
                bookCopyDao.save(bookCopy);
            }
            return true;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(BookCopySaveDto bookCopySaveDto) throws ServiceException {
        try {
            BookCopy bookCopy = bookCopyDao.findById(bookCopySaveDto.getId());
            fillUpdates(bookCopy, bookCopySaveDto);
            return bookCopyDao.update(bookCopy);
        } catch (Exception e) {
            throw new ServiceException("updateBookCopy: bookCopy wasn't updated. bookCopy=" + bookCopySaveDto, e);
        }
    }

    private void fillUpdates(BookCopy bookCopy, BookCopySaveDto bookCopySaveDto) {
        if(bookCopy == null) {
            return;
        }
        if(bookCopySaveDto.getPublishedYear() != 0) {
            bookCopy.setPublishedYear(bookCopySaveDto.getPublishedYear());
        }
        if(bookCopySaveDto.getPricePerDay() != null) {
            bookCopy.setPricePerDay(bookCopySaveDto.getPricePerDay());
        }
    }
}
