package com.innowise.webproject.entity;

import com.innowise.webproject.entity.impl.BetType;

public interface AppBet {
    int getId();
    void setId(int id);
    int getUserId();
    void setUserId(int userId);
    int getBetId();
    void setBetId(int betId);
    BetType getBetType();
    void setBetType(BetType betType);
    double getAmount();
    void setAmount(double amount);
    double getCoefficient();
    void setCoefficient(double coefficient);
}
