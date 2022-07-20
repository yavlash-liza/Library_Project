package com.yavlash.library.util.validator.impl;

import com.yavlash.library.util.validator.BookValidator;

import java.util.Map;

import static com.yavlash.library.controller.command.RequestParameter.*;

public class BookValidatorImpl implements BookValidator {
    private static final String INVALID_VALUE_PARAMETER = "";
    private static final String NAME_REGEX = "[А-Я\\p{Upper}][а-я\\p{Lower}]{2,64}";
    private static final String BOOK_TITLE_REGEX = "^[А-ЯA-Zа-яa-z0-9]+([-_\\s]{1}[А-ЯA-Zа-яa-z0-9]+)*$";

    private static BookValidatorImpl instance;

    private BookValidatorImpl() {
    }

    public static BookValidatorImpl getInstance() {
        if (instance == null) {
            instance = new BookValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean isFormAddAuthorValid(Map<String, String> parameters) {
        boolean result = true;
        if (!isNameValid(parameters.get(FIRST_NAME))) {
            parameters.put(FIRST_NAME, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isNameValid(parameters.get(LAST_NAME))) {
            parameters.put(LAST_NAME, INVALID_VALUE_PARAMETER);
            result = false;
        }
        return result;
    }

    @Override
    public boolean isFormBookRegistrationValid(Map<String, String> parameters) {
        boolean result = true;
        if (!isBookTitleValid(parameters.get(TITLE))) {
            parameters.put(TITLE, INVALID_VALUE_PARAMETER);
            result = false;
        }
        return result;
    }

    @Override
    public boolean isNameValid(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    @Override
    public boolean isBookTitleValid(String name) {
        return name != null && name.matches(BOOK_TITLE_REGEX);
    }
}