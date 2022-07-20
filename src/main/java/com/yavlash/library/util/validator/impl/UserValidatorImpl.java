package com.yavlash.library.util.validator.impl;

import com.yavlash.library.util.validator.UserValidator;

import java.util.Map;

import static com.yavlash.library.controller.command.RequestParameter.*;
import static com.yavlash.library.controller.command.RequestParameter.EMAIL;

public class UserValidatorImpl implements UserValidator {
    private static final String INVALID_VALUE_PARAMETER = "";
    private static final String NAME_REGEX = "[А-Я\\p{Upper}][а-я\\p{Lower}]{2,64}";
    private static final String EMAIL_REGEX = "^\\S+@\\S+\\.\\S+$";
    private static final String PASSWORD_REGEX =  "[\\p{Alpha}\\d\\p{Punct}&&[^<>/{}()\\[\\]]]{6,30}";

    private static UserValidatorImpl instance;

    private UserValidatorImpl(){
    }

    public static UserValidatorImpl getInstance() {
        if (instance == null){
            instance = new UserValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean isFormSignUpValid(Map<String, String> parameters) {
        boolean result = true;
        if (!isNameValid(parameters.get(FIRST_NAME))) {
            parameters.put(FIRST_NAME, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isNameValid(parameters.get(LAST_NAME))) {
            parameters.put(LAST_NAME, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isPasswordValid(parameters.get(PASSWORD))) {
            parameters.put(PASSWORD, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isEmailValid(parameters.get(EMAIL))) {
            parameters.put(EMAIL, INVALID_VALUE_PARAMETER);
            result = false;
        }
        return result;
    }

    @Override
    public boolean isFormSignInValid(Map<String, String> parameters) {
        boolean result = true;
        if (!isEmailValid(parameters.get(EMAIL))) {
            parameters.put(EMAIL, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isPasswordValid(parameters.get(PASSWORD))) {
            parameters.put(PASSWORD, INVALID_VALUE_PARAMETER);
            result = false;
        }
        return result;
    }

    @Override
    public boolean isNameValid(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    @Override
    public boolean isEmailValid(String email) {
        return email != null && email.length() <= 40 && email.matches(EMAIL_REGEX);
    }

    @Override
    public boolean isPasswordValid(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }
}