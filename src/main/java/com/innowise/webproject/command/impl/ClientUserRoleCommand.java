package com.innowise.webproject.command.impl;

import com.innowise.webproject.command.UserRoleCommand;
import com.innowise.webproject.dao.impl.BetDao;
import com.innowise.webproject.entity.impl.Bet;
import com.innowise.webproject.entity.impl.Competition;
import com.innowise.webproject.entity.impl.User;
import com.innowise.webproject.exception.CommandException;
import com.innowise.webproject.exception.DaoException;
import com.innowise.webproject.exception.ServiceException;
import com.innowise.webproject.service.BetService;
import com.innowise.webproject.service.impl.BetServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientUserRoleCommand implements UserRoleCommand {
    private static final Logger logger = LogManager.getLogger();
    private final BetService betService;

    public ClientUserRoleCommand(BetService betService) {
        this.betService = betService;
    }

    public ClientUserRoleCommand() {
        betService = new BetServiceImpl();
    }

    @Override
    public void placeBet(User client, Competition competition, Bet bet) {
        try {
            bet.setUserId(client.getId());
            bet.setCompetitionId(competition.getId());
            betService.placeBet(bet);
            logger.info("Client {} placed bet {}", client.getUsername(), bet.getId());
        } catch (ServiceException e) {
            logger.error("placeBet failed", e);
        }
    }

    @Override
    public void manageCompetition(User u, Competition c) {
        throw new UnsupportedOperationException("Client cannot manage competitions");
    }

    @Override
    public void manageUsers(User u, User t) {
        throw new UnsupportedOperationException("Client cannot manage users");
    }

    @Override
    public void setOdds(User user, Competition competition, double homeWin, double draw, double awayWin) {
        throw new UnsupportedOperationException("Client cannot set odds");
    }

}

