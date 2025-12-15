package com.innowise.webproject.entity.impl;

import com.innowise.webproject.entity.AppCompetition;

import java.time.LocalDateTime;

public class Competition implements AppCompetition {
    private int id;
    private String teamHome;
    private String teamAway;
    private LocalDateTime date;
    private String status;
    private String result;
    private double oddsHomeWin;
    private double oddsDraw;
    private double oddsAwayWin;

    public Competition() {}
    public Competition(String teamHome, String teamAway, LocalDateTime date) {
        this.teamHome = teamHome;
        this.teamAway = teamAway;
        this.date = date;
        this.status = "PLANNED";
    }

    @Override
    public int getId() { return id; }

    @Override
    public void setId(int id) { this.id = id; }

    @Override
    public String getTeamHome() { return teamHome; }

    @Override
    public void setTeamHome(String teamHome) { this.teamHome = teamHome; }

    @Override
    public String getTeamAway() { return teamAway; }

    @Override
    public void setTeamAway(String teamAway) { this.teamAway = teamAway; }

    @Override
    public LocalDateTime getDate() { return date; }

    @Override
    public void setDate(LocalDateTime date) { this.date = date; }

    @Override
    public String getStatus() { return status; }

    @Override
    public void setStatus(String status) { this.status = status; }

    @Override
    public String getResult() { return result; }

    @Override
    public void setResult(String result) { this.result = result; }

    @Override
    public double getOddsHomeWin() { return oddsHomeWin; }

    @Override
    public void setOddsHomeWin(double oddsHomeWin) { this.oddsHomeWin = oddsHomeWin; }

    @Override
    public double getOddsDraw() { return oddsDraw; }

    @Override
    public void setOddsDraw(double oddsDraw) { this.oddsDraw = oddsDraw; }

    @Override
    public double getOddsAwayWin() { return oddsAwayWin; }

    @Override
    public void setOddsAwayWin(double oddsAwayWin) { this.oddsAwayWin = oddsAwayWin; }

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", " + teamHome + " vs " + teamAway +
                ", date=" + date +
                ", status=" + status +
                ", result=" + result +
                '}';
    }
}
