package com.innowise.webproject.service;

import com.innowise.webproject.entity.impl.Bet;
import com.innowise.webproject.exception.ServiceException;

import java.util.List;

public interface BetService {
    void placeBet(Bet bet) throws ServiceException;
    List<Bet> getBetsByUser(int userId) throws ServiceException;
}
