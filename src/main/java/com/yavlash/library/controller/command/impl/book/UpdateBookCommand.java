package com.yavlash.library.controller.command.impl.book;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.controller.command.Router.RouterType;
import com.yavlash.library.entity.dto.BookSaveDto;
import com.yavlash.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.yavlash.library.controller.command.RequestAttribute.BOOK_ID;
import static com.yavlash.library.controller.command.RequestParameter.*;

public class UpdateBookCommand implements Command {
    private final BookService bookService = serviceProvider.getBookService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isLibrarian(request)) {
            try {
                Map<String, String> parameters = extractParameters(request);
                bookService.update(buildUserSaveDto(parameters));
            } catch (Exception e) {
                logger.error("Book wasn't updated", e);
            }
            return new Router(PagePath.DEFAULT, RouterType.REDIRECT);
        }
        return sendForbidden();
    }

    private BookSaveDto buildUserSaveDto(Map<String, String> parameters) {
        return BookSaveDto.builder()
                .id(Long.parseLong(parameters.get(BOOK_ID)))
                .title(parameters.get(TITLE))
                .releaseYear(Integer.parseInt(parameters.get(RELEASE_YEAR)))
                .pages(Integer.parseInt(parameters.get(PAGES)))
                .build();
    }
}