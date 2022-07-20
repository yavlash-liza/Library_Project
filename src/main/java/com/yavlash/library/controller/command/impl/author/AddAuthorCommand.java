package com.yavlash.library.controller.command.impl.author;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.entity.dto.AuthorDto;
import com.yavlash.library.entity.dto.BookListDto;
import com.yavlash.library.service.AuthorService;
import com.yavlash.library.service.BookService;
import com.yavlash.library.util.validator.BookValidator;
import com.yavlash.library.util.validator.impl.BookValidatorImpl;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.yavlash.library.controller.command.RequestAttribute.BOOK_LIST;
import static com.yavlash.library.controller.command.RequestParameter.*;

public class AddAuthorCommand implements Command {
    private final AuthorService service = serviceProvider.getAuthorService();
    private final BookService bookService = serviceProvider.getBookService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isLibrarian(request)) {
            Map<String, String> parameters = extractParameters(request);
            BookValidator validator = BookValidatorImpl.getInstance();
            if (validator.isFormAddAuthorValid(parameters)) {
                try {
                    AuthorDto author = buildAuthor(request);
                    logger.log(Level.DEBUG, "Author from form is get", author.toString());
                    service.save(author);
                    List<BookListDto> bookList = bookService.findAll();
                    request.setAttribute(BOOK_LIST, bookList);
                    return new Router(PagePath.DEFAULT, Router.RouterType.REDIRECT);
                } catch (Exception e) {
                    logger.error("Author wasn't created", e);
                }
            }
            return new Router(PagePath.ERROR_404, Router.RouterType.FORWARD);
        }
        return sendForbidden();
    }

    private AuthorDto buildAuthor(HttpServletRequest request) {
        return AuthorDto.builder()
                .firstName(getParameterToCheck(FIRST_NAME, request))
                .lastName(getParameterToCheck(LAST_NAME, request))
                .build();
    }
}