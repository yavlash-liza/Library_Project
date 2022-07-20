package com.yavlash.library.controller.command.impl.user;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.controller.command.Router.RouterType;
import com.yavlash.library.entity.dto.UserSaveDto;
import com.yavlash.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Map;

import static com.yavlash.library.controller.command.RequestAttribute.USER_ID;
import static com.yavlash.library.controller.command.RequestParameter.*;

public class UpdateUserCommand implements Command {
    private final UserService userService = serviceProvider.getUserService();

    @Override
    public Router execute(HttpServletRequest request) {
        if (isAdmin(request) || isReader(request) || isLibrarian(request)) {
            try {
                Map<String, String> parameters = extractParameters(request);
                userService.update(buildUserSaveDto(parameters));
            } catch (Exception e) {
                logger.error("Reader wasn't updated", e);
            }
            return new Router(PagePath.DEFAULT, RouterType.REDIRECT);
        }
        return sendForbidden();
    }

    private UserSaveDto buildUserSaveDto(Map<String, String> parameters) {
        return UserSaveDto.builder()
                .id(Long.parseLong(parameters.get(USER_ID)))
                .birthDate(LocalDate.parse(parameters.get(BIRTH_DATE)))
                .lastName(parameters.get(LAST_NAME))
                .firstName(parameters.get(FIRST_NAME))
                .address(parameters.get(ADDRESS))
                .build();
    }
}