package com.yavlash.library.controller.command.impl.navigation;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

public class GoToSignUpPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.SIGN_UP, Router.RouterType.FORWARD);
    }
}