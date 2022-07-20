package com.yavlash.library.controller.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SessionAttribute {
    public static final String USER = "user";
    public static final String CURRENT_PAGE = "current_page";
    public static final String LOCALE = "locale";
    public static final String LANGUAGE = "language";
    public static final String ROLE = "role";
}