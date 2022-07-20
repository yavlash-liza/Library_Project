package com.yavlash.library.model.dao.impl;

import com.yavlash.library.entity.Author;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.model.dao.AuthorDao;
import com.yavlash.library.model.dao.BaseDaoTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImplTest extends BaseDaoTest {
    private final AuthorDao authorRepository;
    private final List<Author> authors;

    public AuthorDaoImplTest() {
        authorRepository = new AuthorDaoImpl(getConnectionPool());
        authors = new ArrayList<>() {{
            add(Author.builder().id(1L).firstName("Антон").lastName("Чехов").build());
            add(Author.builder().id(2L).firstName("Михаил").lastName("Лермонтов").build());
            add(Author.builder().id(3L).firstName("Джоан").lastName("Роулинг").build());
            add(Author.builder().id(4L).firstName("F. Scott").lastName("Fitzgerald").build());
            add(Author.builder().id(5L).firstName("Herman").lastName("Melville").build());
            add(Author.builder().id(6L).firstName("William").lastName("Shakespeare").build());
        }};
    }

    @Test
    public void findByFirstAndLastNameTest_shouldFindBookById() throws DaoException {
        //given
        Author expected = authors.get(0);
        String firstName = "Антон";
        String lastName = "Чехов";

        //when
        Author actual = authorRepository.findByFirstAndLastName(firstName, lastName);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findByIdTest_shouldFindAuthorById() throws DaoException {
        //given
        Author expected = authors.get(0);

        //when
        Author actual = authorRepository.findById(expected.getId());

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findAllTest_shouldFindAllValuesInTable() throws DaoException {
        //given && when
        List<Author> actual = authorRepository.findAll();

        //then
        Assert.assertEquals(authors, actual);
    }

    @Test
    public void addTest_shouldAddNewString() throws DaoException {
        //given
        List<Author> expected = authorRepository.findAll();
        Assert.assertEquals(6, expected.size());

        //when
        Author newAuthorActual = Author.builder().id(7L).firstName("Стивен").lastName("Кинг").build();
        boolean isAdded = authorRepository.save(newAuthorActual);
        Author newAuthorExpected = Author.builder().id(7L).firstName("Стивен").lastName("Кинг").build();
        expected.add(newAuthorExpected);

        //then
        Assert.assertTrue(isAdded);
        Assert.assertEquals(newAuthorExpected, newAuthorActual);
        Assert.assertEquals(newAuthorExpected, authorRepository.findById(newAuthorActual.getId()));
    }

    @Test
    public void updateTest_shouldUpdateTable() throws DaoException {
        //given
        Author expected = Author.builder().id(2L).firstName("Михаил").lastName("Лермонтов").build();
        Author actual = authorRepository.findById(expected.getId());

        Assert.assertEquals(expected, actual);

        //when
        actual.setLastName("222р221");
        boolean isUpdated = authorRepository.update(actual);
        expected.setLastName("222р221");

        //then
        Assert.assertTrue(isUpdated);
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(actual, authorRepository.findById(actual.getId()));
    }

    @Test
    public void deleteTest_shouldSetDeleteStatusToStringInTable() throws DaoException {
        //given
        Author author = Author.builder().id(1L).firstName("Антон").lastName("Чехов").build();

        List<Author> actual = authorRepository.findAll();
        Assert.assertEquals(6, actual.size());

        //when
        boolean isDeleted = authorRepository.softDelete(author.getId());
        actual.remove(0);

        //then
        Assert.assertTrue(isDeleted);
        Assert.assertEquals(actual, authorRepository.findAll());
    }
}