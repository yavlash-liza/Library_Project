package com.yavlash.library.controller.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PagePath {
    public static final String HOME = "/pages/main/home.jsp";
    public static final String ERROR_403 = "/pages/error/error403.jsp";
    public static final String ERROR_404 = "/pages/error/error404.jsp";
    public static final String DEFAULT = "/pages/main/defaultpage.jsp";
    public static final String BOOKS = "/pages/booklist.jsp";
    public static final String BOOK = "/pages/book.jsp";
    public static final String BOOK_REGISTRATION = "/pages/bookregistration.jsp";
    public static final String ADD_AUTHOR = "/pages/addauthor.jsp";
    public static final String BOOK_COPY = "/pages/bookcopy.jsp";
    public static final String ADD_COPIES = "/pages/addbookcopy.jsp";

    public static final String UPDATE_CABINET = "/pages/updateuser.jsp";
    public static final String UPDATE_BOOK_COPY = "/pages/updatebookcopy.jsp";
    public static final String UPDATE_BOOK = "/pages/updatebook.jsp";

    public static final String USER_PAGE = "/pages/userpage.jsp";
    public static final String USERS = "/pages/userlist.jsp";
    public static final String READERS = "/pages/readerlist.jsp";

    public static final String SAVE_ORDER = "/pages/cart.jsp";
    public static final String ORDERS = "/pages/orderlist.jsp";
    public static final String ORDER = "/pages/order.jsp";

    public static final String SIGN_IN = "/pages/signin.jsp";
    public static final String SIGN_UP = "/pages/signup.jsp";
}