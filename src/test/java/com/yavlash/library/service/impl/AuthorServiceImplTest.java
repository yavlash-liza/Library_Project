package com.yavlash.library.service.impl;

import com.yavlash.library.entity.Author;
import com.yavlash.library.entity.dto.AuthorDto;
import com.yavlash.library.entity.dto.converter.AuthorConverter;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.exception.ServiceException;
import com.yavlash.library.model.dao.AuthorDao;
import com.yavlash.library.service.AuthorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceImplTest {
    private final AuthorDao authorDao;
    private final AuthorService authorService;

    public AuthorServiceImplTest() {
        authorDao = Mockito.mock(AuthorDao.class);
        authorService = new AuthorServiceImpl(authorDao);
    }

    @Test
    public void findAllTest_shouldFindAllAuthors() throws DaoException, ServiceException {
        //given
        List<Author> authors = new ArrayList<>() {{
            add(Author.builder().id(1L).firstName("Александр").lastName("Пушкин").build());
            add(Author.builder().id(2L).firstName("Николай").lastName("Гоголь").build());
            add(Author.builder().id(3L).firstName("Лев").lastName("Толстой").build());
        }};

        List<AuthorDto> expected = new ArrayList<>() {{
            add(AuthorDto.builder().id(1L).firstName("Александр").lastName("Пушкин").build());
            add(AuthorDto.builder().id(2L).firstName("Николай").lastName("Гоголь").build());
            add(AuthorDto.builder().id(3L).firstName("Лев").lastName("Толстой").build());
        }};

        //when
        Mockito.when(authorDao.findAll()).thenReturn(authors);
        List<AuthorDto> actual = authorService.findAll();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void saveAuthorTest_shouldAddNewAuthor() throws DaoException, ServiceException {
        //given
        Author newAuthor = Author.builder().firstName("Александр").lastName("Пушкин").build();
        AuthorDto authorSaveDto = AuthorConverter.toDto(newAuthor);

        //when
        Mockito.when(authorDao.save(newAuthor)).thenReturn(true);
        boolean isSaved = authorService.save(authorSaveDto);

        //then
        Assert.assertTrue(isSaved);
    }
}