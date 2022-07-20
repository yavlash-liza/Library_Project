package com.yavlash.library.controller.command.impl.user;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.entity.dto.UserListDto;
import com.yavlash.library.service.UserService;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.yavlash.library.controller.command.RequestAttribute.USERS;

public class AllUsersPageCommand implements Command {
    private final UserService userService = serviceProvider.getUserService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isAdmin(request)) {
            List<UserListDto> userList;
            try {
                userList = userService.findAll();
                request.setAttribute(USERS, userList);
            } catch (Exception e) {
                logger.log(Level.ERROR, "Exception in find all users command", e);
            }
            Router router = new Router();
            router.setPagePath(PagePath.USERS);
            return router;
        }
        return sendForbidden();
    }
}