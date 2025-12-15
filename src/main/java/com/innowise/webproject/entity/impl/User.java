package com.innowise.webproject.entity.impl;

import com.innowise.webproject.command.UserRole;
import com.innowise.webproject.entity.AppUser;

import java.util.Objects;

public class User implements AppUser {
    private int id;
    private String username;
    private String password;
    private UserRole role;

    public User(UserRole role) {
        this.role = role;
    }

    @Override
    public void placeBet(Competition competition, Bet bet) {
        role.getCommand().placeBet(this, competition, bet);
    }

    @Override
    public void manageCompetition(Competition competition) {
        role.getCommand().manageCompetition(this, competition);
    }

    @Override
    public void manageUsers(User target) {
        role.getCommand().manageUsers(this, target);
    }

    @Override
    public void setOdds(Competition competition, double odds) {
        role.getCommand().setOdds(this, competition, odds);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public UserRole getRole() {
        return role;
    }

    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(username);
        result = 31 * result + Objects.hashCode(password);
        result = 31 * result + Objects.hashCode(role);
        return result;
    }
}
