package com.yavlash.library.controller.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestParameter {
    public static final String COMMAND = "command";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String LAST_NAME = "last_name";
    public static final String FIRST_NAME = "first_name";
    public static final String PASSPORT_NUMBER = "passport_number";
    public static final String BIRTH_DATE = "birth_date";
    public static final String ADDRESS = "address";
    public static final String ROLE_ID = "roleId";
    public static final String TITLE = "title";
    public static final String RELEASE_YEAR = "release_year";
    public static final String GENRE = "genre";
    public static final String AUTHOR = "author";
    public static final String PAGES = "pages";
    public static final String BOOK_PHOTO_PATH = "book_photo_path";
    public static final String PUBLISHED_YEAR = "published_year";
    public static final String BOOK_COPY_COUNT = "count";
    public static final String PRICE_PER_DAY = "price_per_day";

    public static Map<String, String> extractParameters(HttpServletRequest request){
        Map<String, String> parameters = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        parameterNames.asIterator().forEachRemaining(parameter -> parameters.put(parameter, request.getParameter(parameter)));
        return parameters;
    }
}