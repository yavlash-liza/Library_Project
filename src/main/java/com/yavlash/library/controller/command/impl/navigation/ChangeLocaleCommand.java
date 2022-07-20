package com.yavlash.library.controller.command.impl.navigation;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.yavlash.library.controller.command.SessionAttribute.*;

public class ChangeLocaleCommand implements Command {
    private static final String LANGUAGE_ENGLISH = "RU";
    private static final String LANGUAGE_RUSSIAN = "EN";
    private static final String LOCALE_ENGLISH = "en_US";
    private static final String LOCALE_RUSSIAN = "ru_RU";

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        String language = request.getParameter(LANGUAGE);
        switch (language) {
            case LANGUAGE_ENGLISH:
                session.setAttribute(LOCALE, LOCALE_ENGLISH);
                break;
            case LANGUAGE_RUSSIAN:
                session.setAttribute(LOCALE, LOCALE_RUSSIAN);
                break;
        }
        session.setAttribute(LANGUAGE, language);
        return new Router(currentPage, Router.RouterType.REDIRECT);
    }
}