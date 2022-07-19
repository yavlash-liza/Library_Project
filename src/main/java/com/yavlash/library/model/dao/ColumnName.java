package com.yavlash.library.model.dao;

public final class ColumnName {
    /*Authors Table*/
    public static final String AUTHOR_FIRST_NAME_COLUMN = "first_name";
    public static final String AUTHOR_LAST_NAME_COLUMN = "last_name";

    /*Book_Copies Table*/
    public static final String PRICE_PER_DAY_COLUMN = "price_per_day";
    public static final String REGISTER_DATE_COLUMN = "register_date";
    public static final String PUBLISHED_YEAR_COLUMN = "published_year";
    public static final String COPY_STATUS_COLUMN = "copy_status";
    public static final String BOOK_ID_COLUMN = "book_id";

    /*Books Table*/
    public static final String TITLE_COLUMN = "title";
    public static final String RELEASE_COLUMN = "release_year";
    public static final String PAGES_COLUMN = "pages";
    public static final String BOOK_PHOTO_PATH_COLUMN = "book_photo_path";

    /*Genres table*/
    public static final String GENRE_NAME_COLUMN = "genre_name";

    /*Orders table*/
    public static final String CREATION_DATE_COLUMN = "creation_date";
    public static final String EXPIRATION_DATE_COLUMN = "expiration_date";
    public static final String FINAL_SUM_COLUMN = "fine_sum";
    public static final String ORDER_USER_ID_COLUMN = "user_id";

    /*Users Table*/
    public static final String FIRST_NAME_COLUMN = "first_name";
    public static final String LAST_NAME_COLUMN = "last_name";
    public static final String BIRTH_DATE_COLUMN = "birth_date";
    public static final String PASSWORD_COLUMN = "password";
    public static final String EMAIL_COLUMN = "email";
    public static final String PASSPORT_NUMBER_COLUMN = "passport_number";
    public static final String ADDRESS_COLUMN = "address";
    public static final String ROLE_ID_COLUMN = "role_id";
    public static final String USER_STATUS = "user_status";

    /*Roles Table*/
    public static final String ROLE_NAME_COLUMN = "role_name";

    private ColumnName() {
    }
}