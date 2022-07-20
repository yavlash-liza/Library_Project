package com.yavlash.library.controller.listener;

import com.yavlash.library.controller.command.PagePath;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import static com.yavlash.library.controller.command.SessionAttribute.*;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "en_US";
    private static final String DEFAULT_LANGUAGE = "EN";

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(CURRENT_PAGE, PagePath.HOME);
        session.setAttribute(LOCALE, DEFAULT_LOCALE);
        session.setAttribute(LANGUAGE, DEFAULT_LANGUAGE);
    }
}