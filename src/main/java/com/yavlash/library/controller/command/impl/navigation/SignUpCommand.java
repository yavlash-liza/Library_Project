package com.yavlash.library.controller.command.impl.navigation;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.entity.dto.UserSaveDto;
import com.yavlash.library.service.UserService;
import com.yavlash.library.util.validator.UserValidator;
import com.yavlash.library.util.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Map;

import static com.yavlash.library.controller.command.RequestParameter.*;

public class SignUpCommand implements Command {

    private final UserService service = serviceProvider.getUserService();

    @Override
    public Router execute(HttpServletRequest request) {
        Map<String, String> parameters = extractParameters(request);
        UserValidator validator = UserValidatorImpl.getInstance();
        if (validator.isFormSignUpValid(parameters)) {
            try {
                UserSaveDto userSaveDto = build(request);
                logger.log(Level.DEBUG, "UserSaveDto from form is get {}", userSaveDto.toString());
                service.save(userSaveDto);
            } catch (Exception e) {
                logger.error("UserSaveDto wasn't created {}", e);
            }
        }
        return new Router(PagePath.SIGN_IN, Router.RouterType.FORWARD);
    }

    private UserSaveDto build(HttpServletRequest request) {
        String birthDate = getParameterToCheck(BIRTH_DATE, request);
        return UserSaveDto.builder()
            .firstName(getParameterToCheck(FIRST_NAME, request))
            .lastName(getParameterToCheck(LAST_NAME, request))
            .birthDate(birthDate == null ? null : LocalDate.parse(birthDate))
            .email(getParameterToCheck(EMAIL, request))
            .password(getParameterToCheck(PASSWORD, request))
            .passportNumber(getParameterToCheck(PASSPORT_NUMBER, request))
            .address(getParameterToCheck(ADDRESS, request))
            .roleId(Long.parseLong(getParameterToCheck(ROLE_ID, request)))
            .build();
    }
}