package com.yavlash.library.controller.command.impl.bookcopy;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.controller.command.Router.RouterType;
import com.yavlash.library.entity.dto.BookCopySaveDto;
import com.yavlash.library.service.BookCopyService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

import static com.yavlash.library.controller.command.RequestAttribute.BOOK_ID;
import static com.yavlash.library.controller.command.RequestParameter.*;

public class AddBookCopyPageCommand implements Command {
    private final BookCopyService bookCopyService = serviceProvider.getBookCopyService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isLibrarian(request)) {
            try {
                Map<String, String> parameters = extractParameters(request);
                bookCopyService.save(build(request, parameters));
            } catch (Exception e) {
                logger.error("Book copies weren't saved", e);
            }
            return new Router(PagePath.DEFAULT, RouterType.REDIRECT);
        }
        return sendForbidden();
    }

    private BookCopySaveDto build(HttpServletRequest request, Map<String, String> parameters) {
        return BookCopySaveDto.builder()
                .publishedYear(Integer.parseInt(parameters.get(PUBLISHED_YEAR)))
                .pricePerDay(BigDecimal.valueOf(Double.parseDouble(parameters.get(PRICE_PER_DAY))))
                .count(Integer.parseInt(parameters.get(BOOK_COPY_COUNT)))
                .bookId((Long) request.getSession().getAttribute(BOOK_ID))
                .build();
    }
}