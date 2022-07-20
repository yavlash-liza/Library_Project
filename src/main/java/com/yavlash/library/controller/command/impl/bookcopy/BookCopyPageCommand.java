package com.yavlash.library.controller.command.impl.bookcopy;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.entity.dto.BookCopyDto;
import com.yavlash.library.service.BookCopyService;

import javax.servlet.http.HttpServletRequest;

import static com.yavlash.library.controller.command.RequestAttribute.BOOK_COPY_DTO;
import static com.yavlash.library.controller.command.RequestAttribute.BOOK_COPY_ID;

public class BookCopyPageCommand implements Command {
    private final BookCopyService bookCopyService = serviceProvider.getBookCopyService();

    @Override
    public Router execute(HttpServletRequest request) {
        Long bookCopyId = Long.parseLong(request.getParameter(BOOK_COPY_ID));
        try {
            BookCopyDto bookCopyDto = bookCopyService.findById(bookCopyId);
            request.setAttribute(BOOK_COPY_DTO, bookCopyDto);
        } catch (Exception e) {
            logger.error("Exception in find book copy command", e);
        }
        return new Router(PagePath.BOOK_COPY, Router.RouterType.FORWARD);
    }
}