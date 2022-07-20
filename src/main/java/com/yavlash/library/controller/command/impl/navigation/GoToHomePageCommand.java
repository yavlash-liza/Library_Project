package com.yavlash.library.controller.command.impl.navigation;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.entity.dto.BookListDto;
import com.yavlash.library.service.BookService;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.yavlash.library.controller.command.RequestAttribute.BOOK_LIST;

public class GoToHomePageCommand implements Command {
    private final BookService bookService = serviceProvider.getBookService();

    @Override
    public Router execute(HttpServletRequest request) {
        List<BookListDto> bookList;
        try {
            bookList = bookService.findAll();
            request.setAttribute(BOOK_LIST, bookList);
        } catch (Exception e) {
            logger.log(Level.ERROR,"Exception in users or books or readers", e);
        }
        return new Router(PagePath.HOME, Router.RouterType.FORWARD);
    }
}