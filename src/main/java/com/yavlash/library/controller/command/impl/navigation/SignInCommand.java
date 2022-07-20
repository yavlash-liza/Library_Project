package com.yavlash.library.controller.command.impl.navigation;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.entity.User;
import com.yavlash.library.entity.dto.BookListDto;
import com.yavlash.library.service.BookService;
import com.yavlash.library.service.UserService;
import com.yavlash.library.util.validator.UserValidator;
import com.yavlash.library.util.validator.impl.UserValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.yavlash.library.controller.command.RequestAttribute.BOOK_LIST;
import static com.yavlash.library.controller.command.RequestParameter.*;
import static com.yavlash.library.controller.command.SessionAttribute.ROLE;
import static com.yavlash.library.controller.command.SessionAttribute.USER;

public class SignInCommand implements Command {
    private final UserService userService = serviceProvider.getUserService();
    private final BookService bookService = serviceProvider.getBookService();

    @Override
    public Router execute(HttpServletRequest request) {
        List<BookListDto> bookList;
        Map<String, String> parameters = extractParameters(request);
        UserValidator validator = UserValidatorImpl.getInstance();
        if (validator.isFormSignInValid(parameters)) {
            HttpSession session = request.getSession();
            try {
                Optional<User> optional = userService.checkUser(parameters.get(EMAIL), parameters.get(PASSWORD));
                if (optional.isPresent()) {
                    User user = optional.get();
                    session.setAttribute(USER, user);
                    session.setAttribute(ROLE, user.getRole().getRoleName());
                    bookList = bookService.findAll();
                    request.setAttribute(BOOK_LIST, bookList);
                    return new Router(PagePath.HOME, Router.RouterType.FORWARD);
                }
            } catch (Exception e) {
                logger.error("Error has occurred while signing in: " + e);
            }
        }
        return new Router(PagePath.ERROR_404, Router.RouterType.REDIRECT);
    }
}