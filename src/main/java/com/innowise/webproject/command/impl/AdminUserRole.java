package com.innowise.webproject.command.impl;

import com.innowise.webproject.command.UserRole;
import com.innowise.webproject.entity.Bet;
import com.innowise.webproject.entity.Competition;
import com.innowise.webproject.entity.User;

public class AdminUserRole implements UserRole {
    @Override
    public void placeBet(User user, Competition competition, Bet bet) {
        throw new UnsupportedOperationException("Admin cannot place bets");
    }

    @Override
    public void manageCompetition(User user, Competition competition) {
        // Логика: админ создаёт/редактирует соревнования
    }

    @Override
    public void manageUsers(User user, User target) {
        // Логика: админ управляет пользователями
    }

    @Override
    public void setOdds(User user, Competition competition, double odds) {
        throw new UnsupportedOperationException("Admin cannot set odds");
    }
}
