package com.innowise.webproject.service.impl;

import com.innowise.webproject.dao.impl.CompetitionDao;
import com.innowise.webproject.entity.impl.Competition;
import com.innowise.webproject.exception.DaoException;
import com.innowise.webproject.exception.ServiceException;
import com.innowise.webproject.service.CompetitionService;

import java.util.List;

public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionDao competitionDao;

    public CompetitionServiceImpl(CompetitionDao competitionDao) {
        this.competitionDao = competitionDao;
    }

    @Override
    public void create(Competition c) throws ServiceException {
        try {
            competitionDao.addCompetition(c);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Competition c) throws ServiceException {
        try {
            competitionDao.updateCompetition(c);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setResult(int id, String result) throws ServiceException {
        try {
            competitionDao.setResult(id, result);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateOdds(int id, double h, double d, double a) throws ServiceException {
        try {
            competitionDao.updateOdds(id, h, d, a);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Competition getCompetitionById(int id) throws ServiceException {
        try {
            return competitionDao.getCompetitionById(id).orElseThrow(() -> new ServiceException("Competition not found: " + id));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Competition> getAllCompetitions() throws ServiceException {
        try {
            return competitionDao.getAllCompetitions();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCompetition(int id) throws ServiceException {
        try {
            competitionDao.deleteCompetition(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
