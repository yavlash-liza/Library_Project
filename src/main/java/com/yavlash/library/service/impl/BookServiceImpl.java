package com.yavlash.library.service.impl;

import com.yavlash.library.entity.Book;
import com.yavlash.library.entity.BookCopy;
import com.yavlash.library.entity.Genre;
import com.yavlash.library.entity.dto.AuthorDto;
import com.yavlash.library.entity.dto.BookDto;
import com.yavlash.library.entity.dto.BookListDto;
import com.yavlash.library.entity.dto.BookSaveDto;
import com.yavlash.library.entity.dto.converter.BookConverter;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.exception.ServiceException;
import com.yavlash.library.model.dao.AuthorDao;
import com.yavlash.library.model.dao.BookCopyDao;
import com.yavlash.library.model.dao.BookDao;
import com.yavlash.library.model.dao.GenreDao;
import com.yavlash.library.service.BookService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final BookCopyDao bookCopyDao;
    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    @Override
    public BookDto findById(Long id) throws ServiceException {
        try {
            Book book = bookDao.findById(id);
            book.setBookCopies(findBookCopies(book));
            book.setGenres(genreDao.findGenresByBookId(book.getId()));
            book.setAuthors(authorDao.findAuthorsByBookId(book.getId()));
            return BookConverter.toDto(book);
        } catch (Exception e) {
            logger.error("Exception in method findById()", e);
            throw new ServiceException("Exception when find book by id", e);
        }
    }

    private List<BookCopy> findBookCopies(Book book) throws DaoException {
        List<BookCopy> bookCopies = bookCopyDao.findBookCopiesByBookId(book.getId());
        bookCopies.forEach(bookCopy -> bookCopy.setBook(book));
        return bookCopies;
    }

    @Override
    public List<BookListDto> findAll() throws ServiceException {
        try {
            List<Book> books = bookDao.findAll();
            for (Book book : books) {
                book.setGenres(genreDao.findGenresByBookId(book.getId()));
                book.setAuthors(authorDao.findAuthorsByBookId(book.getId()));
            }
            return BookConverter.toListDto(books);
        } catch (Exception e) {
            logger.error("Exception in method book findAll()", e);
            throw new ServiceException("Exception when find all books", e);
        }
    }

    @Override
    public boolean save(BookSaveDto bookSaveDto) throws ServiceException {
        try {
            Book book = BookConverter.fromBookSaveDto(bookSaveDto);
            bookDao.save(book);
            saveBookGenreLinks(book.getId(), book.getGenres());
            saveBookAuthorLinks(book.getId(), bookSaveDto.getAuthors());
            return true;
        } catch (Exception e) {
            logger.error("Exception in method saveBook()", e);
            throw new ServiceException("Exception when save a book", e);
        }
    }

    private void saveBookGenreLinks(Long bookId, List<Genre> genres) throws DaoException {
        for (Genre genre : genres) {
            bookDao.addGenreToBook(bookId, genre.getId());
        }
    }

    private void saveBookAuthorLinks(Long bookId, List<AuthorDto> authors) throws DaoException {
        for (AuthorDto author : authors) {
            bookDao.addAuthorToBook(author.getId(), bookId);
        }
    }

    @Override
    public boolean update(BookSaveDto bookSaveDto) throws ServiceException {
        try {
            Book book = bookDao.findById(bookSaveDto.getId());
            fillUpdates(book, bookSaveDto);
            return bookDao.update(book);
        } catch (Exception e) {
            throw new ServiceException("updateBook: book wasn't updated. bookCopy=" + bookSaveDto, e);
        }
    }

    private void fillUpdates(Book book, BookSaveDto bookSaveDto) {
        if(book == null) {
            return;
        }
        if(bookSaveDto.getTitle() != null) {
            book.setTitle(bookSaveDto.getTitle());
        }
        if(bookSaveDto.getPages() != 0) {
            book.setPages(bookSaveDto.getPages());
        }
        if(bookSaveDto.getReleaseYear() != 0) {
            book.setReleaseYear(bookSaveDto.getReleaseYear());
        }
    }

    @Override
    public boolean softDelete(Long bookId) throws ServiceException {
        try {
            return bookDao.softDelete(bookId);
        } catch (Exception e) {
            logger.error("Exception in method removeBook()", e);
            throw new ServiceException("Exception when remove a book", e);
        }
    }
}