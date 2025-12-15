package com.innowise.webproject.command.impl;

import com.innowise.webproject.command.UserRoleCommand;
import com.innowise.webproject.dao.impl.CompetitionDao;
import com.innowise.webproject.dao.impl.UserDao;
import com.innowise.webproject.entity.impl.Bet;
import com.innowise.webproject.entity.impl.Competition;
import com.innowise.webproject.entity.impl.User;
import com.innowise.webproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookmakerUserRoleCommand implements UserRoleCommand {
    private static final Logger logger = LogManager.getLogger();
    private final CompetitionDao competitionDAO = new CompetitionDao();

    @Override
    public void placeBet(User user, Competition competition, Bet bet) {
        throw new UnsupportedOperationException("Bookmaker cannot place bets");
    }

    @Override
    public void manageCompetition(User user, Competition competition) {
        throw new UnsupportedOperationException("Bookmaker cannot manage competitions");
    }

    @Override
    public void manageUsers(User user, User target) {
        throw new UnsupportedOperationException("Bookmaker cannot manage users");
    }

    @Override
    public void setOdds(User user, Competition competition, double homeWin, double draw, double awayWin) {
        try {
            competitionDAO.updateOdds(
                    competition.getId(),
                    homeWin,
                    draw,
                    awayWin
            );
            logger.info("Bookmaker {} set odds home: {}, draw: {}, away: {} for competition {}", user.getUsername(), homeWin, draw, awayWin, competition.getId());
        } catch (DaoException e) {
            logger.error("Error setting odds", e);
        }
    }

    @Override
    public String toString() {
        return "BookmakerUserRole";
    }
}
