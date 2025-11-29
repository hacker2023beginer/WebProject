package com.innowise.webproject.command;

import com.innowise.webproject.entity.Bet;
import com.innowise.webproject.entity.Competition;
import com.innowise.webproject.entity.User;

public interface UserRole {
    void placeBet(User user, Competition competition, Bet bet);
    void manageCompetition(User user, Competition competition);
    void manageUsers(User user, User target);
    void setOdds(User user, Competition competition, double odds);
}
