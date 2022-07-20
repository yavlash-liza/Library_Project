package com.yavlash.library.model.dao.impl;

import com.yavlash.library.entity.Genre;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.model.dao.BaseDaoTest;
import com.yavlash.library.model.dao.GenreDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenreDaoImplTest extends BaseDaoTest {
    private GenreDao genreRepository;
    private final List<Genre> genres;

    public GenreDaoImplTest() {
        genreRepository = new GenreDaoImpl(getConnectionPool());
        genres = new ArrayList<>() {{
            add(Genre.builder().id(1L).bookGenre("Detective").build());
            add(Genre.builder().id(2L).bookGenre("Fantasy").build());
            add(Genre.builder().id(3L).bookGenre("Adventure").build());
            add(Genre.builder().id(4L).bookGenre("Mystery").build());
            add(Genre.builder().id(5L).bookGenre("Horror").build());
            add(Genre.builder().id(6L).bookGenre("Romance").build());
            add(Genre.builder().id(7L).bookGenre("Western").build());
            add(Genre.builder().id(8L).bookGenre("Children").build());
            add(Genre.builder().id(9L).bookGenre("Documentary").build());
        }};
    }

    @Test
    public void findByIdTest_shouldFindGenreById() throws DaoException {
        //given
        Genre expected = genres.get(0);

        //when
        Genre actual = genreRepository.findById(expected.getId());

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findAllTest_shouldFindAllValuesInTable() throws DaoException {
        //given & when
        List<Genre> actual = genreRepository.findAll();

        //then
        Assert.assertEquals(genres, actual);
    }

    @Test
    public void updateTest_shouldUpdateTable() throws DaoException {
        //given
        Genre expected = Genre.builder().id(3L).bookGenre("Adventure").build();
        Genre actual = genreRepository.findById(expected.getId());

        Assert.assertEquals(expected, actual);

        //when
        actual.setBookGenre("Romance");
        boolean isUpdated = genreRepository.update(actual);
        expected.setBookGenre("Romance");

        //then
        Assert.assertTrue(isUpdated);
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(actual, genreRepository.findById(actual.getId()));
    }

    @Test
    public void deleteTest_shouldSetDeleteStatusToStringInTable() throws DaoException {
        //given
        Genre genre = Genre.builder().id(1L).bookGenre("Detective").build();

        List<Genre> actual = genreRepository.findAll();
        Assert.assertEquals(9, actual.size());

        //when
        boolean isDeleted = genreRepository.softDelete(genre.getId());
        actual.remove(0);

        //then
        Assert.assertTrue(isDeleted);
        Assert.assertEquals(actual, genreRepository.findAll());
    }
}