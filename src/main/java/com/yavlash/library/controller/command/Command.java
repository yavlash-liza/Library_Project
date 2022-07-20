package com.yavlash.library.controller.command;

import com.yavlash.library.entity.Role;
import com.yavlash.library.entity.User;
import com.yavlash.library.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.yavlash.library.controller.command.SessionAttribute.USER;

@FunctionalInterface
public interface Command {
    Logger logger = LogManager.getLogger();
    ServiceProvider serviceProvider = ServiceProvider.getInstance();

    default String getParameterToCheck(String name, HttpServletRequest request) {
        final String parameter = request.getParameter(name);
        if (parameter == null) {
            logger.error("Request have no parameter with name: {}", name);
        }
        return parameter;
    }

    default Router sendForbidden() {
        return new Router(PagePath.ERROR_403, Router.RouterType.REDIRECT);
    }

    default boolean isAdmin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER);
        return isAuthenticated(user) && hasRole(user.getRole(), 3L, "admin");
    }

    default boolean isLibrarian(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER);
        return isAuthenticated(user) && hasRole(user.getRole(), 2L, "librarian");
    }

    default boolean isReader(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER);
        return isAuthenticated(user) && hasRole(user.getRole(), 1L, "reader");
    }

    private boolean isAuthenticated(User user) {
        if(user == null) {
            return false;
        }
        Role role = user.getRole();
        return role != null && (role.getId() != null || role.getRoleName() != null);
    }
    private boolean hasRole(Role actual, Long expectedId, String expectedRoleName) {
        return actual.getId() != null && actual.getId().equals(expectedId)
            || actual.getRoleName() != null && actual.getRoleName().equals(expectedRoleName);
    }

    Router execute(HttpServletRequest request);
}