package com.yavlash.library.controller.command.impl.order;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.controller.command.Router.RouterType;
import com.yavlash.library.entity.dto.BookCopyListDto;
import com.yavlash.library.entity.dto.OrderSaveDto;
import com.yavlash.library.exception.ServiceException;
import com.yavlash.library.service.BookCopyService;
import com.yavlash.library.service.OrderService;
import lombok.SneakyThrows;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.yavlash.library.controller.command.RequestAttribute.*;

public class AddOrderPageCommand implements Command {
    private final BookCopyService bookCopyService = serviceProvider.getBookCopyService();
    private final OrderService orderService = serviceProvider.getOrderService();

    @Override
    @SneakyThrows
    public Router execute(HttpServletRequest request) {
        if (isReader(request)) {
            try {
                HttpSession session = request.getSession();
                OrderSaveDto orderSaveDto = build(request);
                logger.log(Level.DEBUG, "Book to order from form is get", orderSaveDto.toString());
                orderService.save(orderSaveDto);
                session.removeAttribute(BOOK_COPIES_ID);
                session.removeAttribute(BOOK_COPY_LIST);
            } catch (Exception e) {
                logger.error("Order wasn't created", e);
            }
            return new Router(PagePath.DEFAULT, RouterType.REDIRECT);
        }
        return sendForbidden();
    }

    private OrderSaveDto build(HttpServletRequest request) throws ServiceException {
        return OrderSaveDto.builder()
                .userId(Long.valueOf(getParameterToCheck(USER_ID, request)))
                .expirationDate(LocalDate.now().plusDays(extractRentDays(request)))
                .bookCopiesId(extractBookCopiesId(request))
                .finalSum(calculateFinalSum(request))
                .build();
    }

    private List<Long> extractBookCopiesId(HttpServletRequest request) {
        return (List<Long>) request.getSession().getAttribute(BOOK_COPIES_ID);
    }

    private long extractRentDays(HttpServletRequest request) {
        return Long.parseLong(getParameterToCheck("rent_days", request));
    }

    private BigDecimal calculateFinalSum(HttpServletRequest request) throws ServiceException {
        List<BookCopyListDto> bookCopyListDto = bookCopyService.findAll(extractBookCopiesId(request));
        BigDecimal rentDays = BigDecimal.valueOf(extractRentDays(request));
        return bookCopyListDto.stream()
                .map(BookCopyListDto::getPricePerDay)
                .map(pricePerDay -> pricePerDay.multiply(rentDays))
                .reduce((current, sum) -> sum.add(current))
                .orElse(BigDecimal.ZERO);
    }
}