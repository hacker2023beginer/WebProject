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
            }
        } catch (DaoException e) {}
    }

    @Override
    public void manageUsers(User user, User target) {
        // Логика: админ управляет пользователями
    }

    @Override
    public void setOdds(User user, Competition competition, double odds) {
        throw new UnsupportedOperationException("Admin cannot set odds");
    }

    @Override
    public String toString() {
        return "AdminUserRole";
    }
}
