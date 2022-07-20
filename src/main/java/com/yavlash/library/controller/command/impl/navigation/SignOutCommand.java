package com.yavlash.library.controller.command.impl.navigation;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return new Router(PagePath.SIGN_IN, Router.RouterType.REDIRECT);
    }
}