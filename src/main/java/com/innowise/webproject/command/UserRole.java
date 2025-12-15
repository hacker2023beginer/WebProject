package com.innowise.webproject.command;

import com.innowise.webproject.command.impl.AdminUserRoleCommand;
import com.innowise.webproject.command.impl.BookmakerUserRoleCommand;
import com.innowise.webproject.command.impl.ClientUserRoleCommand;

public enum UserRole {
    CLIENT(new ClientUserRoleCommand()),
    ADMIN(new AdminUserRoleCommand()),
    BOOKMAKER(new BookmakerUserRoleCommand());

    private final UserRoleCommand command;

    UserRole(UserRoleCommand command) {
        this.command = command;
    }

    public UserRoleCommand getCommand() {
        return command;
    }
}
