package com.yavlash.library.controller.command.impl.user;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.entity.dto.UserListDto;
import com.yavlash.library.service.UserService;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.yavlash.library.controller.command.RequestAttribute.READER_ROLE;
import static com.yavlash.library.controller.command.RequestAttribute.USERS;

public class ReadersPageCommand implements Command {
    private final UserService userService = serviceProvider.getUserService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isLibrarian(request)) {
            List<UserListDto> readers;
            try {
                readers = userService.findAllReaders(READER_ROLE);
                request.setAttribute(USERS, readers);
            } catch (Exception e) {
                logger.log(Level.ERROR, "Exception in all readers command", e);
            }
            return new Router(PagePath.READERS, Router.RouterType.FORWARD);
        }
        return sendForbidden();
    }
}