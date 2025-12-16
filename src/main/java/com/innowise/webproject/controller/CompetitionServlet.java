package com.innowise.webproject.controller;

import com.innowise.webproject.dao.impl.CompetitionDao;
import com.innowise.webproject.entity.impl.Competition;
import com.innowise.webproject.exception.ServiceException;
import com.innowise.webproject.service.CompetitionService;
import com.innowise.webproject.service.impl.CompetitionServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/competition")
public class CompetitionServlet extends HttpServlet implements WebParameter{
    private CompetitionService competitionService;

    @Override
    public void init() throws ServletException {
        competitionService = new CompetitionServiceImpl(new CompetitionDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION_PARAMETER);
        if (action == null) {
            resp.sendRedirect(req.getContextPath() + MENU_PAGE);
            return;
        }

        switch (action) {
            case LIST_ACTION:
                try {
                    req.setAttribute(COMPETITIONS_ATTRIBUTE, competitionService.getAllCompetitions());
                } catch (ServiceException e) {
                    req.setAttribute("error", "Cannot load competitions");
                }
                req.getRequestDispatcher(REDIRECT_TO_COMPETITION_LIST_PAGE).forward(req, resp);
                break;

            case EDIT_ACTION:
                int id = Integer.parseInt(req.getParameter(ID_PARAMETER));
                try {
                    req.setAttribute(COMPETITION_ATTRIBUTE, competitionService.getCompetitionById(id));
                } catch (ServiceException e) {
                    req.setAttribute("error", "Cannot load competition");
                }
                req.getRequestDispatcher(REDIRECT_TO_COMPETITION_EDIT_PAGE).forward(req, resp);
                break;

            default:
                resp.sendRedirect(req.getContextPath() + MENU_PAGE);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION_PARAMETER);
        if (action == null) {
            resp.sendRedirect(req.getContextPath() + MENU_PAGE);
            return;
        }

        switch (action) {
            case CREATE_ACTION:
                Competition competition = new Competition();
                competition.setTeamHome(req.getParameter(TEAM_HOME_PARAMETER));
                competition.setTeamAway(req.getParameter(TEAM_AWAY_PARAMETER));
                // дата и коэффициенты тоже из параметров
                try {
                    competitionService.create(competition);
                    resp.sendRedirect(req.getContextPath() + REDIRECT_TO_LIST_PAGE);
                } catch (ServiceException e) {
                    req.setAttribute("error", "Cannot create competition");
                    req.getRequestDispatcher(REDIRECT_TO_COMPETITION_EDIT_PAGE).forward(req, resp);
                }
                break;

            case UPDATE_ACTION:

                break;

            case DELETE_ACTION:
                int id = Integer.parseInt(req.getParameter(ID_PARAMETER));
                try {
                    competitionService.deleteCompetition(id);
                    resp.sendRedirect(req.getContextPath() + REDIRECT_TO_LIST_PAGE);
                } catch (ServiceException e) {
                    req.setAttribute("error", "Cannot delete competition");
                    req.getRequestDispatcher(REDIRECT_TO_COMPETITION_LIST_PAGE).forward(req, resp);
                }
                break;
        }
    }
}
