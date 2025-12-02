package com.innowise.webproject.command.impl;

import com.innowise.webproject.command.UserRole;
import com.innowise.webproject.entity.Bet;
import com.innowise.webproject.entity.Competition;
import com.innowise.webproject.entity.User;

public class ClientUserRole implements UserRole {

    @Override
    public void manageCompetition(User user, Competition competition) {
        throw new UnsupportedOperationException("Client cannot manage competitions");
    }

    @Override
    public void manageUsers(User user, User target) {
        throw new UnsupportedOperationException("Client cannot manage users");
    }

    @Override
    public void setOdds(User user, Competition competition, double odds) {
        throw new UnsupportedOperationException("Client cannot set odds");
    }

    @Override
    public void placeBet(User user, Competition competition, Bet bet) {

    }

    @Override
    public String toString() {
        return "ClientUserRole";
    }
}

