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

public class AdminUserRoleCommand implements UserRoleCommand {
    private static final Logger logger = LogManager.getLogger();
    private final CompetitionDao competitionDAO = new CompetitionDao();
    private final UserDao userDAO = new UserDao();

    @Override
    public void placeBet(User user, Competition competition, Bet bet) {
        throw new UnsupportedOperationException("Admin cannot place bets");
    }

    @Override
    public void manageCompetition(User user, Competition competition) {
        try {
            if (competition.getId() == 0) {
                competitionDAO.addCompetition(competition);
                logger.info("Admin {} created competition {}", user.getUsername(), competition);
            } else {
                competitionDAO.updateCompetition(competition);
                logger.info("Admin {} updated competition {}", user.getUsername(), competition);
            }
        } catch (DaoException e) {
            logger.error("Error managing competition", e);
        }
    }

    @Override
    public void manageUsers(User user, User target) {
        try {
            userDAO.updateUser(target); // например, смена роли или блокировка
            logger.info("Admin {} updated user {}", user.getUsername(), target.getUsername());
        } catch (DaoException e) {
            logger.error("Error managing user", e);
        }
    }

    @Override
    public void setOdds(User user, Competition competition, double homeWin, double draw, double awayWin) {
        throw new UnsupportedOperationException("Admin cannot set odds");
    }

    @Override
    public String toString() {
        return "AdminUserRole";
    }
}
