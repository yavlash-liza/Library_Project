package com.yavlash.library.controller.command.impl.user;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.RequestAttribute;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.entity.dto.ReaderDto;
import com.yavlash.library.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static com.yavlash.library.controller.command.RequestAttribute.USER_ID;

public class UserPageCommand implements Command {
    private final UserService userService = serviceProvider.getUserService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isAdmin(request) || isReader(request) || isLibrarian(request)) {
            try {
                Long readerId = Long.parseLong(request.getParameter(USER_ID));
                ReaderDto reader = userService.findReaderById(readerId);
                request.setAttribute(RequestAttribute.USER, reader);
            } catch (Exception e) {
                logger.error("Exception in find reader command", e);
            }
            return new Router(PagePath.USER_PAGE, Router.RouterType.FORWARD);
        }
        return sendForbidden();
    }
}
