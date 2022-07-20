package com.yavlash.library.controller.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestAttribute {
    public static final String BOOK_LIST = "bookListDto";
    public static final String BOOK_COPY_LIST = "bookCopyListDto";
    public static final String BOOK_DTO = "bookDto";
    public static final String BOOK_ID = "bookId";
    public static final String BOOK_COPY_DTO = "bookCopyDto";
    public static final String BOOK_COPIES_ID= "bookCopiesId";
    public static final String BOOK_COPY_ID = "bookCopyId";
    public static final String USER_ID = "userId";
    public static final String ORDER_ID = "orderId";
    public static final String ORDERS_WITH_USER_LIST = "orderLibrarianListDto";
    public static final String GENRE_LIST = "genreList";
    public static final String AUTHOR_LIST = "authorList";
    public static final String READER_ROLE = "reader";
    public static final String USER = "user";
    public static final String USERS = "userListDto";
    public static final String ORDER_DTO = "orderDto";
}