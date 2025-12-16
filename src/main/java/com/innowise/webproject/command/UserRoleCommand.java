package com.innowise.webproject.command;

import com.innowise.webproject.entity.impl.Bet;
import com.innowise.webproject.entity.impl.Competition;
import com.innowise.webproject.entity.impl.User;

public interface UserRoleCommand {
    void placeBet(User user, Competition competition, Bet bet);
    void manageCompetition(User user, Competition competition);
    void manageUsers(User user, User target);
    void setOdds(User user, Competition competition, double homeWin, double draw, double awayWin);
}
