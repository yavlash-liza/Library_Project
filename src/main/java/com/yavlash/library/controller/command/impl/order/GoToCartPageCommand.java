package com.yavlash.library.controller.command.impl.order;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.entity.dto.BookCopyListDto;
import com.yavlash.library.service.BookCopyService;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.yavlash.library.controller.command.RequestAttribute.BOOK_COPIES_ID;
import static com.yavlash.library.controller.command.RequestAttribute.BOOK_COPY_LIST;

public class GoToCartPageCommand implements Command {
    private final BookCopyService bookService = serviceProvider.getBookCopyService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isReader(request)) {
            try {
                List<Long> bookCopiesId = (List<Long>) request.getSession().getAttribute(BOOK_COPIES_ID);
                List<BookCopyListDto> bookCopyListDto = bookService.findAll(bookCopiesId == null ? new ArrayList<>() : bookCopiesId);
                request.setAttribute(BOOK_COPY_LIST, bookCopyListDto);
                return new Router(PagePath.SAVE_ORDER, Router.RouterType.FORWARD);
            } catch (Exception e) {
                logger.log(Level.ERROR, "Error has occurred while redirect to save order page: " + e);
                return new Router(PagePath.ERROR_404, Router.RouterType.REDIRECT);
            }
        }
        return sendForbidden();
    }
}