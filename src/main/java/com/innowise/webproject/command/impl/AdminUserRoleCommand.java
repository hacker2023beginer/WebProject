package com.innowise.webproject.command.impl;

import com.innowise.webproject.command.UserRoleCommand;
import com.innowise.webproject.dao.impl.CompetitionDao;
import com.innowise.webproject.dao.impl.UserDao;
import com.innowise.webproject.entity.impl.Bet;
import com.innowise.webproject.entity.impl.Competition;
import com.innowise.webproject.entity.impl.User;
import com.innowise.webproject.exception.CommandException;
import com.innowise.webproject.exception.ServiceException;
import com.innowise.webproject.service.CompetitionService;
import com.innowise.webproject.service.UserService;
import com.innowise.webproject.service.impl.CompetitionServiceImpl;
import com.innowise.webproject.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminUserRoleCommand implements UserRoleCommand {
    private static final Logger logger = LogManager.getLogger();
    private final CompetitionService competitionService;
    private final UserService userService;

    public AdminUserRoleCommand(CompetitionService competitionService, UserService userService) {
        this.competitionService = competitionService;
        this.userService = userService;
    }

    public AdminUserRoleCommand() {
        competitionService = new CompetitionServiceImpl(new CompetitionDao());
        userService = new UserServiceImpl(new UserDao());
    }

    @Override
    public void placeBet(User user, Competition competition, Bet bet) {
        throw new UnsupportedOperationException("Admin cannot place bets");
    }

    @Override
    public void manageCompetition(User admin, Competition competition){
        try {
            if (competition.getId() == 0) {
                competitionService.create(competition);
                logger.info("Admin {} created competition {}", admin.getUsername(), competition.getId());
            } else {
                competitionService.update(competition);
                logger.info("Admin {} updated competition {}", admin.getUsername(), competition.getId());
            }
        } catch (ServiceException e) {
            logger.error("manageCompetition failed", e);
        }
    }

    @Override
    public void manageUsers(User admin, User target){
        try {
            userService.update(target);
            logger.info("Admin {} updated user {}", admin.getUsername(), target.getUsername());
        } catch (ServiceException e) {
            logger.error("manageUsers failed", e);
        }
    }

    @Override
    public void setOdds(User user, Competition competition, double homeWin, double draw, double awayWin) {
        throw new UnsupportedOperationException("Admin cannot set odds");
    }

    @Override
    public String toString() { return "AdminUserRole"; }
}
