package com.innowise.webproject.command.impl;

import com.innowise.webproject.command.UserRoleCommand;
import com.innowise.webproject.dao.impl.BetDao;
import com.innowise.webproject.entity.impl.Bet;
import com.innowise.webproject.entity.impl.Competition;
import com.innowise.webproject.entity.impl.User;
import com.innowise.webproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientUserRoleCommand implements UserRoleCommand {
    private static final Logger logger = LogManager.getLogger();
    private final BetDao betDao = new BetDao();
    @Override
    public void manageCompetition(User user, Competition competition) {
        throw new UnsupportedOperationException("Client cannot manage competitions");
    }

    @Override
    public void manageUsers(User user, User target) {
        throw new UnsupportedOperationException("Client cannot manage users");
    }

    @Override
    public void setOdds(User user, Competition competition, double homeWin, double draw, double awayWin) {
        throw new UnsupportedOperationException("Client cannot set odds");
    }

    @Override
    public void placeBet(User user, Competition competition, Bet bet) {
        try {
            if (!"ACTIVE".equalsIgnoreCase(competition.getStatus())) {
                throw new IllegalStateException("Competition is not active");
            }

            bet.setUserId(user.getId());
            bet.setCompetitionId(competition.getId());
            bet.setStatus("PENDING");
            betDao.addBet(bet);

            logger.info("Client {} placed bet {} on competition {}", user.getUsername(), bet, competition.getId());
        } catch (DaoException e) {
            logger.error("Error placing bet", e);
        }
    }

    @Override
    public String toString() {
        return "ClientUserRole";
    }
}

