package com.yavlash.library.controller.command.impl.order;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.entity.dto.OrderDto;
import com.yavlash.library.exception.CommandException;
import com.yavlash.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import static com.yavlash.library.controller.command.RequestAttribute.ORDER_DTO;
import static com.yavlash.library.controller.command.RequestAttribute.ORDER_ID;

public class OrderPageCommand implements Command {
    private final OrderService orderService = serviceProvider.getOrderService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isLibrarian(request) || isReader(request)) {
            try {
                findOrder(request);
            } catch (Exception e) {
                logger.error("Exception in find order command", e);
            }
            return new Router(PagePath.ORDER, Router.RouterType.FORWARD);
        }
        return sendForbidden();
    }

    public void findOrder(HttpServletRequest request) throws CommandException {
        try {
            Long orderId = Long.parseLong(request.getParameter(ORDER_ID));
            OrderDto orderDto = orderService.findById(orderId);
            request.setAttribute(ORDER_DTO, orderDto);
        } catch (Exception e) {
            logger.error("Exception in find order command", e);
            throw new CommandException("Exception in find order command", e);
        }
    }
}