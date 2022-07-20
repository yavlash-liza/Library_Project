package com.yavlash.library.controller.command.impl.order;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.exception.CommandException;
import com.yavlash.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import static com.yavlash.library.controller.command.RequestAttribute.ORDER_ID;

public class RemoveOrderCommand implements Command {
    private final OrderService orderService = serviceProvider.getOrderService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isLibrarian(request)) {
            try {
                softDelete(request);
            } catch (Exception e) {
                logger.error("Exception in remove order command", e);
            }
            OrdersPageCommand ordersPageCommand = new OrdersPageCommand();
            return ordersPageCommand.execute(request);
        }
        return sendForbidden();
    }

    private void softDelete(HttpServletRequest request) throws CommandException {
        Long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        try {
            orderService.softDelete(orderId);
        } catch (Exception e) {
            logger.error("Exception in remove order command", e);
            throw new CommandException("Exception in remove order command", e);
        }
    }
}
