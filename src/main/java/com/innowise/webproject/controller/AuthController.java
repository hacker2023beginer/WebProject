package com.innowise.webproject.controller;

import com.innowise.webproject.entity.impl.User;
import com.innowise.webproject.exception.ServiceException;
import com.innowise.webproject.service.impl.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app")
public class AuthController extends HttpServlet implements WebParameter{
    private AuthService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION_PARAMETER);
        String view;

        switch (action) {
            case LOGIN_ACTION:
                view = LOGIN_PAGE;
                break;
            case REGISTER_ACTION:
                view = REGISTER_PAGE;
                break;
            case HOME_ACTION:
                view = HOME_PAGE;
                break;
            case LOGOUT_ACTION:
                req.getSession().invalidate();
                resp.sendRedirect(req.getContextPath() + REDIRECT_TO_LOGIN_PAGE);
                return;
            case MENU_ACTION:
                view = MENU_PAGE;
                break;
            default:
                view = HOME_PAGE;
        }

        req.getRequestDispatcher(view).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION_PARAMETER);
        String username = req.getParameter(USERNAME_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);
        switch (action) {
            case LOGIN_ACTION:
                User user;
                try {
                    user = authService.login(username, password);
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }
                if (user != null) {
                    req.getSession().setAttribute(USER_ATTRIBUTE, user);
                    resp.sendRedirect(req.getContextPath() + REDIRECT_TO_MENU_PAGE);
                } else {
                    req.setAttribute(ERROR_ATTRIBUTE, "error");
                    req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
                }
                break;
            case REGISTER_ACTION:
                boolean success;
                try {
                    success = authService.register(username, password);
                } catch (ServiceException e) {
                    //переход на страницу ошибок и lock
                    throw new RuntimeException(e);
                }
                if (success) {
                    resp.sendRedirect(req.getContextPath() + REDIRECT_TO_LOGIN_PAGE);
                } else {
                    req.setAttribute(ERROR_ATTRIBUTE, "Registration failed. Username or email already exists.");
                    req.getRequestDispatcher(REDIRECT_TO_REGISTER_PAGE).forward(req, resp);
                }
        }
    }
}