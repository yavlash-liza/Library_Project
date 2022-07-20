package com.yavlash.library.controller.command.impl.user;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.exception.CommandException;
import com.yavlash.library.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static com.yavlash.library.controller.command.RequestAttribute.USER_ID;

public class RemoveUserCommand implements Command {
    private final UserService userService = serviceProvider.getUserService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isAdmin(request)) {
            try {
                removeUser(request);
            } catch (Exception e) {
                logger.error("Exception in remove user command", e);
            }
            AllUsersPageCommand allUsersPageCommand = new AllUsersPageCommand();
            return allUsersPageCommand.execute(request);
        }
        return sendForbidden();
    }

    private void removeUser(HttpServletRequest request) throws CommandException {
        Long userId = Long.parseLong(request.getParameter(USER_ID));
        try {
            userService.softDelete(userId);
        } catch (Exception e) {
            logger.error("Exception in remove user command", e);
            throw new CommandException("Exception in remove user command", e);
        }
    }
}