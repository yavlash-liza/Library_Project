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

import static com.yavlash.library.controller.command.RequestAttribute.BOOK_COPY_ID;
import static com.yavlash.library.controller.command.RequestParameter.*;

public class UpdateBookCopyCommand implements Command {
    private final BookCopyService bookCopyService = serviceProvider.getBookCopyService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isLibrarian(request)) {
            try {
                Map<String, String> parameters = extractParameters(request);
                bookCopyService.update(buildUserSaveDto(parameters));
            } catch (Exception e) {
                logger.error("Book wasn't updated", e);
            }
            return new Router(PagePath.DEFAULT, RouterType.REDIRECT);
        }
        return sendForbidden();
    }

    private BookCopySaveDto buildUserSaveDto(Map<String, String> parameters) {
        return BookCopySaveDto.builder()
                .id(Long.parseLong(parameters.get(BOOK_COPY_ID)))
                .pricePerDay(BigDecimal.valueOf(Double.parseDouble(parameters.get(PRICE_PER_DAY))))
                .publishedYear(Integer.parseInt(parameters.get(PUBLISHED_YEAR)))
                .build();
    }
}