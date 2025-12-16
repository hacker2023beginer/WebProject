package com.innowise.webproject.entity.impl;

import com.innowise.webproject.entity.AppBet;
import java.math.BigDecimal;

public class Bet implements AppBet {
    private int id;
    private int userId;
    private int competitionId;
    private BetType betType;
    private BigDecimal amount;
    private double coefficient;
    private String status;

    public Bet() {}

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

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    @Override
    public BetType getBetType() {
        return betType;
    }

    @Override
    public String getStringBetType() {
        BetType betType = this.getBetType();
        if (betType == BetType.DRAW) {
            return "DRAW";
        }
        if (betType == BetType.LOSE) {
            return "LOSE";
        }
        if (betType == BetType.WIN) {
            return "WIN";
        }
        return "EXACT_SCORE";
    }

    @Override
    public void setBetType(BetType betType) {
        this.betType = betType;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public void setAmount(BigDecimal amount) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", userId=" + userId +
                ", competitionId=" + competitionId +
                ", betType=" + betType +
                ", amount=" + amount +
                ", coefficient=" + coefficient +
                ", status='" + status + '\'' +
                '}';
    }
}
