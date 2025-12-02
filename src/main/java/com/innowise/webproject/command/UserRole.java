package com.innowise.webproject.command;

import com.innowise.webproject.command.impl.AdminUserRole;
import com.innowise.webproject.command.impl.BookmakerUserRole;
import com.innowise.webproject.command.impl.ClientUserRole;
import com.innowise.webproject.entity.Bet;
import com.innowise.webproject.entity.Competition;
import com.innowise.webproject.entity.User;

public interface UserRole {
    void placeBet(User user, Competition competition, Bet bet);
    void manageCompetition(User user, Competition competition);
    void manageUsers(User user, User target);
    void setOdds(User user, Competition competition, double odds);
    static UserRole valueOf(String role) {
        switch (role) {
            case "ClientUserRole":
                return new ClientUserRole();
            case "AdminUserRole":
                return new AdminUserRole();
            case "BookmakerUserRole":
                return new BookmakerUserRole();
            default:
                return null;
        }
    }
}
