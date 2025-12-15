package com.innowise.webproject.entity;

import com.innowise.webproject.command.UserRole;
import com.innowise.webproject.entity.impl.Bet;
import com.innowise.webproject.entity.impl.Competition;
import com.innowise.webproject.entity.impl.User;

public interface AppUser {
    void placeBet(Competition competition, Bet bet);
    void manageCompetition(Competition competition);
    void manageUsers(User target);
    void setOdds(Competition competition, double odds);
    String getPassword();
    void setId(int id);
    int getId();
    String getUsername();
    UserRole getRole();
}
