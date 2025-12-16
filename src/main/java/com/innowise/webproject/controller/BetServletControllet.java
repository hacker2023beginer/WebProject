package com.innowise.webproject.controller;

import com.innowise.webproject.entity.impl.Bet;
import com.innowise.webproject.entity.impl.User;
import com.innowise.webproject.entity.impl.BetType;
import com.innowise.webproject.exception.ServiceException;
import com.innowise.webproject.service.impl.BetServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/bet")
public class BetServletControllet extends HttpServlet implements WebParameter{
    private BetServiceImpl betService;

    @Override
    public void init() throws ServletException {
        betService = new BetServiceImpl(); // твоя реализация
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(USER_ATTRIBUTE);
        try {
            req.setAttribute(BETS_ATTRIBUTE, betService.getBetsByUser(user.getId()));
        } catch (ServiceException e) {
            req.setAttribute(ERROR_ATTRIBUTE, "Cannot load bets");
        }
        req.getRequestDispatcher(REDIRECT_TO_BET_LIST).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(USER_ATTRIBUTE);
        int competitionId = Integer.parseInt(req.getParameter(COMPETITION_ID_PARAMETER));
        double amount = Double.parseDouble(req.getParameter(AMOUNT_PARAMETER));
        String type = req.getParameter(BET_TYPE_PARAMETER);

        Bet bet = new Bet();
        bet.setUserId(user.getId());
        bet.setCompetitionId(competitionId);
        bet.setAmount(BigDecimal.valueOf(amount));
        bet.setBetType(BetType.valueOf(type));

        try {
            betService.placeBet(bet);
            resp.sendRedirect(req.getContextPath() + REDIRECT_TO_BET);
        } catch (ServiceException e) {
            req.setAttribute(ERROR_ATTRIBUTE, "Cannot place bet");
            req.getRequestDispatcher(REDIRECT_TO_BET_CREATE).forward(req, resp);
        }
    }
}
