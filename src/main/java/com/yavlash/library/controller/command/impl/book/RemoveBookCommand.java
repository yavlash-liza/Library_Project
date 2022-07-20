package com.yavlash.library.controller.command.impl.book;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.exception.CommandException;
import com.yavlash.library.service.BookService;

import javax.servlet.http.HttpServletRequest;

import static com.yavlash.library.controller.command.RequestAttribute.BOOK_ID;

public class RemoveBookCommand implements Command {
    private final BookService bookService = serviceProvider.getBookService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isLibrarian(request)) {
            try {
                removeBook(request);
            } catch (Exception e) {
                logger.error("Exception in remove book command", e);
            }
            BooksPageCommand booksPageCommand = new BooksPageCommand();
            return booksPageCommand.execute(request);
        }
        return sendForbidden();
    }

    private void removeBook(HttpServletRequest request) throws CommandException {
        Long bookId = Long.parseLong(request.getParameter(BOOK_ID));
        try {
            bookService.softDelete(bookId);
        } catch (Exception e) {
            logger.error("Exception in remove book command", e);
            throw new CommandException("Exception in remove book command", e);
        }
    }
}