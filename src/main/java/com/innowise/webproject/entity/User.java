package com.innowise.webproject.entity;

import com.innowise.webproject.command.UserRole;

public class User {
    private int id;
    private String username;
    private String password;
    private UserRole role;

    public User(UserRole role) {
        this.role = role;
    }

    public void placeBet(Competition competition, Bet bet) {
        role.placeBet(this, competition, bet);
    }

    public void manageCompetition(Competition competition) {
        role.manageCompetition(this, competition);
    }

    public void manageUsers(User target) {
        role.manageUsers(this, target);
    }

    public void setOdds(Competition competition, double odds) {
        role.setOdds(this, competition, odds);
    }

    public String getPassword() {
        return password;
    }

    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
