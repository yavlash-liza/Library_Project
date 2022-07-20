package com.yavlash.library.controller.command.impl.book;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.entity.dto.AuthorDto;
import com.yavlash.library.entity.dto.BookListDto;
import com.yavlash.library.entity.dto.BookSaveDto;
import com.yavlash.library.entity.dto.GenreDto;
import com.yavlash.library.service.BookService;
import com.yavlash.library.util.validator.BookValidator;
import com.yavlash.library.util.validator.impl.BookValidatorImpl;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.yavlash.library.controller.command.RequestAttribute.BOOK_LIST;
import static com.yavlash.library.controller.command.RequestParameter.*;

public class BookRegistrationCommand implements Command {
    private final BookService bookService = serviceProvider.getBookService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isLibrarian(request)) {
            Map<String, String> parameters = extractParameters(request);
            BookValidator validator = BookValidatorImpl.getInstance();
            List<BookListDto> bookList;
            if (validator.isFormBookRegistrationValid(parameters)) {
                try {
                    BookSaveDto bookSaveDto = buildBook(request);
                    logger.log(Level.DEBUG, "book from form is get", bookSaveDto.toString());
                    request.getSession(true).setAttribute("book from form", bookSaveDto);
                    bookService.save(bookSaveDto);
                    bookList = bookService.findAll();
                    request.setAttribute(BOOK_LIST, bookList);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
            return new Router(PagePath.DEFAULT, Router.RouterType.REDIRECT);
        }
        return sendForbidden();
    }

    private BookSaveDto buildBook(HttpServletRequest request) {
        return BookSaveDto.builder()
                .title(getParameterToCheck(TITLE, request))
                .releaseYear(Integer.parseInt(getParameterToCheck(RELEASE_YEAR, request)))
                .pages(Integer.parseInt(getParameterToCheck(PAGES, request)))
                .bookPhotoPath(getParameterToCheck(BOOK_PHOTO_PATH, request))
                .genres(convertToGenreList(request))
                .authors(convertToAuthorsList(request))
                .build();
    }

    private List<GenreDto> convertToGenreList(HttpServletRequest request) {
        return new ArrayList<>() {{
            add(GenreDto.builder()
                    .id(Long.valueOf(getParameterToCheck(GENRE, request)))
                    .build());
        }};
    }

    private List<AuthorDto> convertToAuthorsList(HttpServletRequest request) {
        return new ArrayList<>() {{
            add(AuthorDto.builder()
                    .id(Long.valueOf(getParameterToCheck(AUTHOR, request)))
                    .build());
        }};
    }

}