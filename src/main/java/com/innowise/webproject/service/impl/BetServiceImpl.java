package com.innowise.webproject.service.impl;

import com.innowise.webproject.dao.impl.BetDao;
import com.innowise.webproject.dao.impl.CompetitionDao;
import com.innowise.webproject.dao.impl.UserDao;
import com.innowise.webproject.entity.impl.Bet;
import com.innowise.webproject.entity.impl.User;
import com.innowise.webproject.exception.DaoException;
import com.innowise.webproject.exception.ServiceException;
import com.innowise.webproject.service.BetService;

import java.util.List;
import java.util.Optional;

public class BetServiceImpl implements BetService {
    private final String ACTIVE_COMPETITION = "ACTIVE";
    private final String PENDING_COMPETITION = "PENDING";
    private final BetDao betDao;
    private final UserDao userDao;
    private final CompetitionDao competitionDao;

    public BetServiceImpl(BetDao betDao, UserDao userDao, CompetitionDao competitionDao) {
        this.betDao = betDao; this.userDao = userDao; this.competitionDao = competitionDao;
    }

    public BetServiceImpl() {
        this.betDao = new BetDao();
        this.userDao = new UserDao();
        this.competitionDao = new CompetitionDao();
    }

    public void placeBet(Bet bet) throws ServiceException {
        try {
            var competitionOpt = competitionDao.getCompetitionById(bet.getCompetitionId());
            if (competitionOpt.isEmpty() || !ACTIVE_COMPETITION.equalsIgnoreCase(competitionOpt.get().getStatus())) {
                throw new ServiceException("Competition not active");
            }
            Optional<User> userOpt = userDao.authenticationById(bet.getUserId());
            if (userOpt.isEmpty()) {
                throw new ServiceException("User not found");
            }
            User user = userOpt.get();
            bet.setStatus(PENDING_COMPETITION);
            betDao.addBet(bet);
            userDao.updateUser(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Bet> getBetsByUser(int userId) throws ServiceException {
        try {
            return betDao.getBetsByUser(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
