package com.innowise.webproject.entity;

import com.innowise.webproject.command.UserRole;

import java.util.Objects;

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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

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
