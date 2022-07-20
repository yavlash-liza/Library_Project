package com.yavlash.library.model.dao.impl;

import com.yavlash.library.entity.Book;
import com.yavlash.library.entity.BookCopy;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.model.dao.BaseDaoTest;
import com.yavlash.library.model.dao.BookCopyDao;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookCopyDaoImplTest extends BaseDaoTest {
    private final BookCopyDao repository;
    private final List<BookCopy> bookCopies;

    public BookCopyDaoImplTest() {
        repository = new BookCopyDaoImpl(getConnectionPool());
        bookCopies = new ArrayList<>() {{
            add(BookCopy.builder().id(1L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(2)).publishedYear(1998).book(Book.builder().id(1L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(2L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(1)).publishedYear(1998).book(Book.builder().id(2L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(3L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(3)).publishedYear(1998).book(Book.builder().id(3L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(4L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(2)).publishedYear(1998).book(Book.builder().id(6L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(5L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(1)).publishedYear(1998).book(Book.builder().id(4L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(6L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(1)).publishedYear(1998).book(Book.builder().id(5L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(7L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(6)).publishedYear(1998).book(Book.builder().id(12L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(8L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(1)).publishedYear(1998).book(Book.builder().id(13L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(9L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(6)).publishedYear(1998).book(Book.builder().id(7L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(10L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(1)).publishedYear(1998).book(Book.builder().id(13L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(11L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(6)).publishedYear(1998).book(Book.builder().id(7L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(12L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(1)).publishedYear(1998).book(Book.builder().id(8L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(13L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(6)).publishedYear(1998).book(Book.builder().id(9L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(14L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(1)).publishedYear(1998).book(Book.builder().id(10L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(15L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(6)).publishedYear(1998).book(Book.builder().id(11L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(16L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(6)).publishedYear(1998).book(Book.builder().id(12L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(17L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(1)).publishedYear(1998).book(Book.builder().id(13L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(18L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(6)).publishedYear(1998).book(Book.builder().id(11L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(19L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(1)).publishedYear(1998).book(Book.builder().id(6L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(20L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(6)).publishedYear(1998).book(Book.builder().id(5L).build()).copyStatus("AVAILABLE").build());
            add(BookCopy.builder().id(21L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(1)).publishedYear(1998).book(Book.builder().id(10L).build()).copyStatus("AVAILABLE").build());
        }};
    }

    @Test
    public void findByIdTest_shouldFindBookCopyById() throws DaoException {
        //given
        BookCopy expected = bookCopies.get(0);

        //when
        BookCopy actual = repository.findById(expected.getId());

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findAllTest_shouldFindAllValuesInTable() throws DaoException {
        //given & when
        List<BookCopy> actual = repository.findAll();

        //then
        Assert.assertEquals(bookCopies, actual);
    }

    @Test
    public void addTest_shouldAddNewString() throws DaoException {
        //given
        List<BookCopy> expected = repository.findAll();
        Assert.assertEquals(21, expected.size());

        //when
        BookCopy newActual = BookCopy.builder().id(22L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(1)).publishedYear(1998).book(Book.builder().id(2L).build()).copyStatus("AVAILABLE").build();
        boolean isAdded = repository.save(newActual);
        BookCopy newExpected = BookCopy.builder().id(22L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(1)).publishedYear(1998).book(Book.builder().id(2L).build()).copyStatus("AVAILABLE").build();
        expected.add(newExpected);

        //then
        Assert.assertTrue(isAdded);
        Assert.assertEquals(newExpected, newActual);
        Assert.assertEquals(newExpected, repository.findById(newActual.getId()));
    }

    @Test
    public void updateTest_shouldUpdateTable() throws DaoException {
        //given
        BookCopy expected = BookCopy.builder().id(2L).registerDate(LocalDate.of(2022,06,20)).pricePerDay(BigDecimal.valueOf(1)).publishedYear(1998).book(Book.builder().id(2L).build()).copyStatus("AVAILABLE").build();
        BookCopy actual = repository.findById(expected.getId());

        Assert.assertEquals(expected, actual);

        //when
        actual.setPublishedYear(2020);
        boolean isUpdated = repository.update(actual);
        expected.setPublishedYear(2020);

        //then
        Assert.assertTrue(isUpdated);
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(actual, repository.findById(actual.getId()));
    }

    @Test
    public void deleteTest_shouldSetDeleteStatusToStringInTable() throws DaoException {
        //given
        BookCopy bookPhoto = BookCopy.builder().id(1L).pricePerDay(BigDecimal.valueOf(2)).publishedYear(1998).book(Book.builder().id(1L).build()).copyStatus("AVAILABLE").build();

        List<BookCopy> actual = repository.findAll();
        Assert.assertEquals(21, actual.size());

        //when
        boolean isDeleted = repository.softDelete(bookPhoto.getId());
        actual.remove(0);

        //then
        Assert.assertTrue(isDeleted);
        Assert.assertEquals(actual, repository.findAll());
    }
}