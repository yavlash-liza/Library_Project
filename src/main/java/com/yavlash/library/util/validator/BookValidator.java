package com.yavlash.library.util.validator;

import java.util.Map;

public interface BookValidator {
    boolean isFormBookRegistrationValid(Map<String, String> parameters);
    boolean isFormAddAuthorValid(Map<String, String> parameters);
    boolean isNameValid(String name);
    boolean isBookTitleValid(String name);
}