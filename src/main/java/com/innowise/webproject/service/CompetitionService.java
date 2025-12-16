package com.innowise.webproject.service;

import com.innowise.webproject.entity.impl.Competition;
import com.innowise.webproject.exception.ServiceException;

import java.util.List;

public interface CompetitionService {
    void create(Competition competition) throws ServiceException;
    void update(Competition competition) throws ServiceException;
    void setResult(int competitionId, String result) throws ServiceException;
    void updateOdds(int competitionId, double homeWin, double draw, double awayWin) throws ServiceException;
    Competition getCompetitionById(int id) throws ServiceException;
    List<Competition> getAllCompetitions() throws ServiceException;
    void deleteCompetition(int id) throws ServiceException;
}