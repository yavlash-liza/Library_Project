package com.yavlash.library.controller.command.impl.order;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.controller.command.impl.book.BooksPageCommand;
import com.yavlash.library.service.BookService;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.yavlash.library.controller.command.RequestAttribute.*;

public class AddBookCopyToOrderCommand implements Command {
    private final BookService bookService = serviceProvider.getBookService();

    @Override
    @SneakyThrows
    public Router execute(HttpServletRequest request) {
        if (isReader(request)) {
            try {
                List<Long> bookCopiesId = createOrGet(request);
                Long bookId = Long.valueOf(request.getParameter(BOOK_COPY_ID));
                bookCopiesId.add(bookId);
            } catch (Exception e) {
                logger.error("Book copies wasn't added", e);
            }
            request.setAttribute(BOOK_LIST, bookService.findAll());
            BooksPageCommand booksPageCommand = new BooksPageCommand();
            return booksPageCommand.execute(request);
        }
        return sendForbidden();
    }

    private List<Long> createOrGet(HttpServletRequest request) {
        List<Long> bookCopiesId;
        if (request.getSession().getAttribute(BOOK_COPIES_ID) == null) {
            bookCopiesId = new ArrayList<>();
            request.getSession().setAttribute(BOOK_COPIES_ID, bookCopiesId);
        } else {
            bookCopiesId = (List<Long>) request.getSession().getAttribute(BOOK_COPIES_ID);
        }
        return bookCopiesId;
    }
}