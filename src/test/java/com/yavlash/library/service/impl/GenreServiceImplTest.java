package com.yavlash.library.service.impl;

import com.yavlash.library.entity.Genre;
import com.yavlash.library.entity.dto.GenreDto;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.exception.ServiceException;
import com.yavlash.library.model.dao.GenreDao;
import com.yavlash.library.service.GenreService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GenreServiceImplTest {
    private final GenreDao genreDao;
    private final GenreService genreService;

    public GenreServiceImplTest() {
        genreDao = Mockito.mock(GenreDao.class);
        genreService = new GenreServiceImpl(genreDao);
    }
    @Test
    public void findAllTest_shouldFindAllGenres() throws DaoException, ServiceException {
        //given
        List<Genre> orders = new ArrayList<>() {{
            add(Genre.builder().id(1L).bookGenre("DETECTIVE").build());
            add(Genre.builder().id(2L).bookGenre("FANTASY").build());
            add(Genre.builder().id(3L).bookGenre("HORROR").build());
        }};

        List<GenreDto> expected = new ArrayList<>() {{
            add(GenreDto.builder().id(1L).genreName("DETECTIVE").build());
            add(GenreDto.builder().id(2L).genreName("FANTASY").build());
            add(GenreDto.builder().id(3L).genreName("HORROR").build());
        }};

        //when
        Mockito.when(genreDao.findAll()).thenReturn(orders);
        List<GenreDto> actual = genreService.findAll();

        //then
        Assert.assertEquals(expected, actual);
    }
}