package com.innowise.webproject.command.impl;

import com.innowise.webproject.command.UserRoleCommand;
import com.innowise.webproject.dao.impl.CompetitionDao;
import com.innowise.webproject.dao.impl.UserDao;
import com.innowise.webproject.entity.impl.Bet;
import com.innowise.webproject.entity.impl.Competition;
import com.innowise.webproject.entity.impl.User;
import com.innowise.webproject.exception.CommandException;
import com.innowise.webproject.exception.DaoException;
import com.innowise.webproject.exception.ServiceException;
import com.innowise.webproject.service.CompetitionService;
import com.innowise.webproject.service.impl.CompetitionServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookmakerUserRoleCommand implements UserRoleCommand {
    private static final Logger logger = LogManager.getLogger();
    private final CompetitionService competitionService;

    public BookmakerUserRoleCommand(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    public BookmakerUserRoleCommand() {
        competitionService = new CompetitionServiceImpl(new CompetitionDao());
    }

    @Override
    public void setOdds(User bookmaker, Competition competition, double homeWin, double draw, double awayWin) {
        try {
            // Лучше принимать три коэффициента, но если один — обновим homeWin
            competitionService.updateOdds(
                    competition.getId(),
                    homeWin,
                    draw,
                    awayWin
            );
            logger.info("Bookmaker {} set odds for competition {}", bookmaker.getUsername(), competition.getId());
        } catch (ServiceException e) {
            logger.error("setOdds failed", e);
        }
    }

    @Override
    public void placeBet(User u, Competition c, Bet b) {
        throw new UnsupportedOperationException("Bookmaker cannot place bets");
    }

    @Override
    public void manageCompetition(User u, Competition c){
        throw new UnsupportedOperationException("Bookmaker cannot manage competitions");
    }

    @Override
    public void manageUsers(User u, User t) {
        throw new UnsupportedOperationException("Bookmaker cannot manage users");
    }

    @Override public String toString() {
        return "BookmakerUserRole";
    }
}
