package com.innowise.webproject.entity.impl;

import com.innowise.webproject.entity.AppBet;

public class Bet implements AppBet {
    private int id;
    private int userId;
    private int betId;
    private BetType betType;
    private double amount;
    private double coefficient;

    public Bet() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int getBetId() {
        return betId;
    }

    @Override
    public void setBetId(int betId) {
        this.betId = betId;
    }

    @Override
    public BetType getBetType() {
        return betType;
    }

    @Override
    public void setBetType(BetType betType) {
        this.betType = betType;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
