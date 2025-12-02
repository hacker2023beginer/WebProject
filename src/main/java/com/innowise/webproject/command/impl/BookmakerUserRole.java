package com.innowise.webproject.command.impl;

import com.innowise.webproject.command.UserRole;
import com.innowise.webproject.entity.Bet;
import com.innowise.webproject.entity.Competition;
import com.innowise.webproject.entity.User;

public class BookmakerUserRole implements UserRole {
    @Override
    public void placeBet(User user, Competition competition, Bet bet) {
        throw new UnsupportedOperationException("Bookmaker cannot place bets");
    }

    @Override
    public void manageCompetition(User user, Competition competition) {
        throw new UnsupportedOperationException("Bookmaker cannot manage competitions");
    }

    @Override
    public void manageUsers(User user, User target) {
        throw new UnsupportedOperationException("Bookmaker cannot manage users");
    }

    @Override
    public void setOdds(User user, Competition competition, double odds) {
        // Логика: букмекер устанавливает коэффициенты
    }

    @Override
    public String toString() {
        return "BookmakerUserRole";
    }
}
