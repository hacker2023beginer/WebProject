package com.innowise.webproject.entity;

import com.innowise.webproject.entity.impl.BetType;

import java.math.BigDecimal;

public interface AppBet {
    int getId();
    void setId(int id);
    int getUserId();
    void setUserId(int userId);
    BetType getBetType();
    void setBetType(BetType betType);
    BigDecimal getAmount();
    void setAmount(BigDecimal amount);
    double getCoefficient();
    void setCoefficient(double coefficient);
    String getStringBetType();
}
