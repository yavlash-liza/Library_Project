package com.yavlash.library.model.dao.impl;

import com.yavlash.library.entity.Book;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.model.dao.BaseDaoTest;
import com.yavlash.library.model.dao.BookDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImplTest extends BaseDaoTest {
    private final BookDao repository;
    private final List<Book> books;

    public BookDaoImplTest() {
        repository = new BookDaoImpl(getConnectionPool());
        books = new ArrayList<>() {{
            add(Book.builder().id(1L).title("Каштанка").releaseYear(1985)
                    .pages(20).bookPhotoPath("/images/books/chehov-kashtanka.jpg").build());
            add(Book.builder().id(2L).title("Сочинения. Том 1").releaseYear(1988)
                    .pages(660).bookPhotoPath("/images/books/lermontov.jpg").build());
            add(Book.builder().id(3L).title("Великий Гэтсби").releaseYear(2014)
                    .pages(365).bookPhotoPath("/images/books/The_Great_Gatsby.jpg").build());
            add(Book.builder().id(4L).title("Сочинения. Том 2").releaseYear(1988)
                    .pages(660).bookPhotoPath("/images/books/lermontov.jpg").build());
            add(Book.builder().id(5L).title("Моби Дик").releaseYear(2021).pages(456).bookPhotoPath("/images/books/moby-dick-118.jpg").build());
            add(Book.builder().id(6L).title("Гамлет").releaseYear(1988)
                    .pages(660).bookPhotoPath("/images/books/hamlet.jpg").build());
            add(Book.builder().id(7L).title("Гарри Поттер и узник Азкабана")
                    .releaseYear(2007).pages(672).bookPhotoPath("/images/books/harry-potter-3.jpg").build());
            add(Book.builder().id(8L).title("Гарри Поттер и Филосовский камень").releaseYear(1998)
                    .pages(600).bookPhotoPath("/images/books/hp1.jpg").build());
            add(Book.builder().id(9L).title("Гарри Поттер и Тайная комната")
                    .releaseYear(2005).pages(702).bookPhotoPath("/images/books/ph2.jpg").build());
            add(Book.builder().id(10L).title("Гарри Поттер и Кубок огня").releaseYear(2000)
                    .pages(636).bookPhotoPath("/images/books/hp4.jpg").build());
            add(Book.builder().id(11L).title("Гарри Поттер и Орден Феникса")
                    .releaseYear(2003).pages(702).bookPhotoPath("/images/books/hp5.jpg").build());
            add(Book.builder().id(12L).title("Гарри Поттер и Принц‑полукровка").releaseYear(2007)
                    .pages(607).bookPhotoPath("/images/books/hp6.jpg").build());
            add(Book.builder().id(13L).title("Гарри Поттер и Дары Смерти")
                    .releaseYear(2007).pages(671).bookPhotoPath("/images/books/hp7.jpg").build());
        }};
    }

    @Test
    public void findByIdTest_shouldFindBookById() throws DaoException {
        //given
        Book expected = books.get(0);

        //when
        Book actual = repository.findById(expected.getId());

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findAllTest_shouldFindAllValuesInTable() throws DaoException {
        //given && when
        List<Book> actual = repository.findAll();

        //then
        Assert.assertEquals(books, actual);
    }

    @Test
    public void addTest_shouldAddNewString() throws DaoException {
        //given
        List<Book> expected = repository.findAll();
        Assert.assertEquals(13, expected.size());

        //when
        Book newActual = Book.builder().id(14L).title("Гарри Поттер и философский камень")
                .releaseYear(2007).pages(400).bookPhotoPath("4444hf4").build();
        boolean isAdded = repository.save(newActual);
        Book newExpected = Book.builder().id(14L).title("Гарри Поттер и философский камень")
                .releaseYear(2007).pages(400).bookPhotoPath("4444hf4").build();
        expected.add(newExpected);

        //then
        Assert.assertTrue(isAdded);
        Assert.assertEquals(newExpected, newActual);
        Assert.assertEquals(newExpected, repository.findById(newActual.getId()));
    }

    @Test
    public void updateTest_shouldUpdateTable() throws DaoException {
        //given
        Book expected = Book.builder().id(2L).title("Сочинения. Том 1").releaseYear(1988)
                .pages(660).bookPhotoPath("/images/books/lermontov.jpg").build();
        Book actual = repository.findById(expected.getId());

        Assert.assertEquals(expected, actual);

        //when
        actual.setPages(30);
        boolean isUpdated = repository.update(actual);
        expected.setPages(30);

        //then
        Assert.assertTrue(isUpdated);
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(actual, repository.findById(actual.getId()));
    }

    @Test
    public void deleteTest_shouldSetDeleteStatusToStringInTable() throws DaoException {
        //given
        Book book = Book.builder().id(1L).title("Каштанка").releaseYear(1985)
                .pages(20).bookPhotoPath("/images/books/chehov-kashtanka.jpg").build();

        List<Book> actual = repository.findAll();
        Assert.assertEquals(13, actual.size());

        //when
        boolean isDeleted = repository.softDelete(book.getId());
        actual.remove(0);

        //then
        Assert.assertTrue(isDeleted);
        Assert.assertEquals(actual, repository.findAll());
    }
}