package com.yavlash.library.controller.command.impl.book;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.entity.dto.BookListDto;
import com.yavlash.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.yavlash.library.controller.command.RequestAttribute.BOOK_LIST;

public class BooksPageCommand implements Command {
    private final BookService bookService = serviceProvider.getBookService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isLibrarian(request) || isReader(request)) {
            List<BookListDto> bookList;
            try {
                bookList = bookService.findAll();
                request.setAttribute(BOOK_LIST, bookList);
            } catch (Exception e) {
                logger.error("Exception in all books command", e);
            }
            Router router = new Router();
            router.setPagePath(PagePath.BOOKS);
            return router;
        }
        return sendForbidden();
    }
}