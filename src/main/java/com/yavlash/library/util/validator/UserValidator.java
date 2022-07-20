package com.yavlash.library.util.validator;

import java.util.Map;

public interface UserValidator {
    boolean isFormSignUpValid(Map<String, String> parameters);
    boolean isFormSignInValid(Map<String, String> parameters);
    boolean isPasswordValid(String password);
    boolean isNameValid(String name);
    boolean isEmailValid(String email);
}