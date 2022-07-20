package com.yavlash.library.controller.command.impl.order;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.entity.dto.OrderLibrarianListDto;
import com.yavlash.library.service.OrderService;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.yavlash.library.controller.command.RequestAttribute.ORDERS_WITH_USER_LIST;

public class OrdersPageCommand implements Command {
    private final OrderService orderService = serviceProvider.getOrderService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isLibrarian(request)) {
            try {
                List<OrderLibrarianListDto> orderLibrarianListDto = orderService.findAll();
                request.setAttribute(ORDERS_WITH_USER_LIST, orderLibrarianListDto);
            } catch (Exception e) {
                logger.log(Level.ERROR, "Exception in all orders command", e);
            }
            Router router = new Router();
            router.setPagePath(PagePath.ORDERS);
            return router;
        }
        return sendForbidden();
    }
}
