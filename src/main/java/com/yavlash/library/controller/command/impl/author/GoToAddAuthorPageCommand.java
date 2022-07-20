package com.yavlash.library.controller.command.impl.author;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

public class GoToAddAuthorPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        if (isLibrarian(request)) {
        return new Router(PagePath.ADD_AUTHOR, Router.RouterType.FORWARD);
        }
        return sendForbidden();
    }
}