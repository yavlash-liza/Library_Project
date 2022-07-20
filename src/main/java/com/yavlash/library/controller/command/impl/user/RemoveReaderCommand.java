package com.yavlash.library.controller.command.impl.user;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.exception.CommandException;
import com.yavlash.library.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static com.yavlash.library.controller.command.RequestAttribute.USER_ID;

public class RemoveReaderCommand implements Command {
    private final UserService userService = serviceProvider.getUserService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isLibrarian(request)) {
            try {
                removeReader(request);
            } catch (Exception e) {
                logger.error("Exception in remove reader command", e);
            }
            ReadersPageCommand readersPageCommand = new ReadersPageCommand();
            return readersPageCommand.execute(request);
        }
        return sendForbidden();
    }

    private void removeReader(HttpServletRequest request) throws CommandException {
        Long readerId = Long.parseLong(request.getParameter(USER_ID));
        try {
            userService.softDelete(readerId);
        } catch (Exception e) {
            logger.error("Exception in remove reader command", e);
            throw new CommandException("Exception in remove reader command", e);
        }
    }
}