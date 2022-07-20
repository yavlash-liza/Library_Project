package com.yavlash.library.controller.command.impl.book;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.entity.dto.BookDto;
import com.yavlash.library.exception.CommandException;
import com.yavlash.library.service.BookService;

import javax.servlet.http.HttpServletRequest;

import static com.yavlash.library.controller.command.RequestAttribute.BOOK_DTO;
import static com.yavlash.library.controller.command.RequestAttribute.BOOK_ID;

public class BookPageCommand implements Command {
    private final BookService bookService = serviceProvider.getBookService();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            findBook(request);
        } catch (Exception e) {
            logger.error("Cannot find a book", e);
        }
        return new Router(PagePath.BOOK, Router.RouterType.FORWARD);
    }

    public void findBook(HttpServletRequest request) throws CommandException{
        Long bookId = Long.parseLong(request.getParameter(BOOK_ID));
        try {
            BookDto bookDto = bookService.findById(bookId);
            request.setAttribute(BOOK_DTO, bookDto);
        } catch (Exception e) {
            logger.error("Exception in find book command", e);
            throw new CommandException("Exception in find book command", e);
        }
    }
}