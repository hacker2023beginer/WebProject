package com.innowise.webproject.entity;

import java.time.LocalDateTime;

public interface AppCompetition {
    int getId();
    void setId(int id);
    String getTeamHome();
    void setTeamHome(String teamHome);
    String getTeamAway();
    void setTeamAway(String teamAway);
    LocalDateTime getDate();
    void setDate(LocalDateTime date);
    String getStatus();
    void setStatus(String status);
    String getResult();
    void setResult(String result);
    double getOddsHomeWin();
    void setOddsHomeWin(double oddsHomeWin);
    double getOddsDraw();
    void setOddsDraw(double oddsDraw);
    double getOddsAwayWin();
    void setOddsAwayWin(double oddsAwayWin);
}
