package com.yavlash.library.controller.command.impl.book;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.entity.dto.AuthorDto;
import com.yavlash.library.entity.dto.GenreDto;
import com.yavlash.library.service.AuthorService;
import com.yavlash.library.service.GenreService;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.yavlash.library.controller.command.RequestAttribute.AUTHOR_LIST;
import static com.yavlash.library.controller.command.RequestAttribute.GENRE_LIST;

public class GoToBookRegistrationPageCommand implements Command {
    private final GenreService genreService = serviceProvider.getGenreService();
    private final AuthorService authorService = serviceProvider.getAuthorService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isLibrarian(request)) {
            try {
                List<GenreDto> genreList = genreService.findAll();
                List<AuthorDto> authorsList = authorService.findAll();
                request.setAttribute(AUTHOR_LIST, authorsList);
                request.setAttribute(GENRE_LIST, genreList);
                return new Router(PagePath.BOOK_REGISTRATION, Router.RouterType.FORWARD);
            } catch (Exception e) {
                logger.log(Level.ERROR, "Exception has occurred while redirect to book registration page: " + e);
                return new Router(PagePath.ERROR_404, Router.RouterType.REDIRECT);
            }
        }
        return sendForbidden();
    }
}