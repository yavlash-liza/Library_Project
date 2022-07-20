package com.yavlash.library.controller.command.impl.bookcopy;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

import static com.yavlash.library.controller.command.RequestAttribute.BOOK_ID;

public class GoToAddBookCopyPage implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        if (isLibrarian(request)) {
            try {
                Long bookId = Long.parseLong(request.getParameter(BOOK_ID));
                request.getSession().setAttribute(BOOK_ID, bookId);
            } catch (Exception e) {
                logger.error("Exception in find reader command", e);
            }
            return new Router(PagePath.ADD_COPIES, Router.RouterType.FORWARD);
        }
        return sendForbidden();
    }
}