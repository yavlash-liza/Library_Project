package com.yavlash.library.controller.command;

import com.yavlash.library.controller.command.impl.author.AddAuthorCommand;
import com.yavlash.library.controller.command.impl.author.GoToAddAuthorPageCommand;
import com.yavlash.library.controller.command.impl.book.BookPageCommand;
import com.yavlash.library.controller.command.impl.book.BookRegistrationCommand;
import com.yavlash.library.controller.command.impl.book.BooksPageCommand;
import com.yavlash.library.controller.command.impl.book.GoToBookRegistrationPageCommand;
import com.yavlash.library.controller.command.impl.book.GoToUpdateBookPageCommand;
import com.yavlash.library.controller.command.impl.book.RemoveBookCommand;
import com.yavlash.library.controller.command.impl.book.UpdateBookCommand;
import com.yavlash.library.controller.command.impl.bookcopy.AddBookCopyPageCommand;
import com.yavlash.library.controller.command.impl.bookcopy.BookCopyPageCommand;
import com.yavlash.library.controller.command.impl.bookcopy.GoToAddBookCopyPage;
import com.yavlash.library.controller.command.impl.bookcopy.GoToUpdateBookCopyPageCommand;
import com.yavlash.library.controller.command.impl.bookcopy.UpdateBookCopyCommand;
import com.yavlash.library.controller.command.impl.navigation.ChangeLocaleCommand;
import com.yavlash.library.controller.command.impl.navigation.DefaultCommand;
import com.yavlash.library.controller.command.impl.navigation.GoToHomePageCommand;
import com.yavlash.library.controller.command.impl.navigation.GoToSignInPageCommand;
import com.yavlash.library.controller.command.impl.navigation.GoToSignUpPageCommand;
import com.yavlash.library.controller.command.impl.navigation.SignInCommand;
import com.yavlash.library.controller.command.impl.navigation.SignOutCommand;
import com.yavlash.library.controller.command.impl.navigation.SignUpCommand;
import com.yavlash.library.controller.command.impl.order.AddBookCopyToOrderCommand;
import com.yavlash.library.controller.command.impl.order.AddOrderPageCommand;
import com.yavlash.library.controller.command.impl.order.GoToCartPageCommand;
import com.yavlash.library.controller.command.impl.order.OrderPageCommand;
import com.yavlash.library.controller.command.impl.order.OrdersPageCommand;
import com.yavlash.library.controller.command.impl.order.RemoveOrderCommand;
import com.yavlash.library.controller.command.impl.user.ActivateUserCommand;
import com.yavlash.library.controller.command.impl.user.AllUsersPageCommand;
import com.yavlash.library.controller.command.impl.user.ChangeRoleToLibrarianCommand;
import com.yavlash.library.controller.command.impl.user.ChangeRoleToReaderCommand;
import com.yavlash.library.controller.command.impl.user.GoToUpdateUserPageCommand;
import com.yavlash.library.controller.command.impl.user.ReadersPageCommand;
import com.yavlash.library.controller.command.impl.user.RemoveReaderCommand;
import com.yavlash.library.controller.command.impl.user.RemoveUserCommand;
import com.yavlash.library.controller.command.impl.user.UpdateUserCommand;
import com.yavlash.library.controller.command.impl.user.UserPageCommand;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CommandType {

    BOOKS_PAGE(new BooksPageCommand()),
    BOOK_PAGE(new BookPageCommand()),
    REMOVE_BOOK(new RemoveBookCommand()),
    GO_TO_BOOK_REGISTRATION_PAGE(new GoToBookRegistrationPageCommand()),
    BOOK_REGISTRATION(new BookRegistrationCommand()),
    ADD_AUTHOR(new AddAuthorCommand()),
    GO_TO_ADD_AUTHOR_PAGE(new GoToAddAuthorPageCommand()),
    BOOK_COPY_PAGE(new BookCopyPageCommand()),
    ADD_BOOK_COPY_TO_ORDER_COMMAND(new AddBookCopyToOrderCommand()),
    READERS_PAGE(new ReadersPageCommand()),
    USER_PAGE(new UserPageCommand()),
    REMOVE_READER(new RemoveReaderCommand()),
    ORDERS_PAGE(new OrdersPageCommand()),
    ORDER_PAGE(new OrderPageCommand()),
    REMOVE_ORDER(new RemoveOrderCommand()),
    GO_TO_CART_PAGE(new GoToCartPageCommand()),
    ADD_ORDER(new AddOrderPageCommand()),
    GO_TO_SIGN_IN_PAGE(new GoToSignInPageCommand()),
    GO_TO_SIGN_UP_PAGE(new GoToSignUpPageCommand()),
    SIGN_OUT(new SignOutCommand()),
    SIGN_IN(new SignInCommand()),
    SIGN_UP(new SignUpCommand()),
    UPDATE_USER(new GoToUpdateUserPageCommand()),
    SAVE_UPDATED_USER_COMMAND(new UpdateUserCommand()),
    UPDATE_BOOK_COPY_PAGE(new GoToUpdateBookCopyPageCommand()),
    SAVE_UPDATED_BOOK_COPY_COMMAND(new UpdateBookCopyCommand()),
    UPDATE_BOOK_PAGE(new GoToUpdateBookPageCommand()),
    UPDATE_BOOK(new UpdateBookCommand()),
    ADD_COPIES(new AddBookCopyPageCommand()),
    ADD_COPIES_COMMAND(new GoToAddBookCopyPage()),
    USERS_PAGE(new AllUsersPageCommand()),
    REMOVE_USER(new RemoveUserCommand()),
    ACTIVATE_USER(new ActivateUserCommand()),
    CHANGE_TO_LIBRARIAN(new ChangeRoleToLibrarianCommand()),
    CHANGE_TO_READER(new ChangeRoleToReaderCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    GO_TO_HOME_PAGE(new GoToHomePageCommand()),
    DEFAULT(new DefaultCommand());

    private final Command command;
    private static final Logger logger = LogManager.getLogger();

    public static Command defineCommand(String commandType) {
        if (commandType == null || commandType.isEmpty()) {
            return CommandType.DEFAULT.getCommand();
        }
        try {
            return CommandType.valueOf(commandType.toUpperCase()).getCommand();
        } catch (IllegalArgumentException exception) {
            logger.error("Error has occurred while defining command: ", exception);
            return CommandType.DEFAULT.getCommand();
        }
    }
}