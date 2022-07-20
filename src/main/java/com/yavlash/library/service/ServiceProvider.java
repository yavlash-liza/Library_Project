package com.yavlash.library.service;

import com.yavlash.library.service.impl.AuthorServiceImpl;
import com.yavlash.library.service.impl.BookCopyServiceImpl;
import com.yavlash.library.service.impl.BookServiceImpl;
import com.yavlash.library.service.impl.GenreServiceImpl;
import com.yavlash.library.service.impl.OrderServiceImpl;
import com.yavlash.library.service.impl.UserServiceImpl;
import lombok.Getter;

@Getter
public class ServiceProvider {
    private static final FactoryDependencyService factory = FactoryDependencyService.getInstance();
    private static ServiceProvider instance;

    private static final UserService userService = new UserServiceImpl(factory.getPasswordEncryptor(), factory.getUserDao(), factory.getOrderDao());
    private static final BookService bookService = new BookServiceImpl(factory.getBookDao(), factory.getBookCopyDao(), factory.getGenreDao(), factory.getAuthorDao());
    private static final BookCopyService bookCopyService = new BookCopyServiceImpl(factory.getBookCopyDao(), factory.getBookDao());

    private static final OrderService orderService = new OrderServiceImpl(factory.getBookCopyDao(), factory.getOrderDao(), factory.getUserDao());
    private static final GenreService genreService = new GenreServiceImpl(factory.getGenreDao());
    private static final AuthorService authorService = new AuthorServiceImpl(factory.getAuthorDao());

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public BookCopyService getBookCopyService() {
        return bookCopyService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public GenreService getGenreService() {
        return genreService;
    }

    public AuthorService getAuthorService() {
        return authorService;
    }
}