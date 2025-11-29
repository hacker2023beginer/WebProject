package com.innowise.webproject.controller;

import com.innowise.webproject.entity.User;
import com.innowise.webproject.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app")
public class AuthController extends HttpServlet {
    private static final String LOGIN_ACTION = "login";
    private static final String REGISTER_ACTION = "register";
    private static final String LOGOUT_ACTION = "logout";
    private static final String HOME_ACTION = "home";
    private static final String ACTION_PARAMETER= "action";
    private static final String LOGIN_PAGE = "/WEB-INF/view/login.jsp";
    private static final String REGISTER_PAGE = "/WEB-INF/view/register.jsp";
    private static final String HOME_PAGE = "/WEB-INF/view/home.jsp";
    private static final String MENU_PAGE = "/WEB-INF/view/menu.jsp";
    private static final String REDIRECT_TO_MENU_PAGE = "/auth?action=menu";
    private static final String REDIRECT_TO_LOGIN_PAGE = "/auth?action=login";
    private static final String REDIRECT_TO_REGISTER_PAGE = "/WEB-INF/view/register.jsp";
    private static final String ERROR_ATTRIBUTE = "error";
    private static final String USER_ATTRIBUTE = "user";
    private static final String ERROR_LOGIN_MESSAGE = "error";
    private static final String USERNAME_PARAMETER = "username";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String ERROR_REGISTER_MESSAGE = "Registration failed. Username or email already exists.";

    private AuthService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthService(); // можно внедрять через DI, но для учебного проекта достаточно new
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
            default:
                view = HOME_PAGE; //anything else mb
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
                User user = authService.login(username, password);
                if (user != null) {
                    req.getSession().setAttribute(USER_ATTRIBUTE, user);
                    resp.sendRedirect(req.getContextPath() + REDIRECT_TO_MENU_PAGE);
                } else {
                    req.setAttribute(ERROR_ATTRIBUTE, ERROR_LOGIN_MESSAGE); //?
                    req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp); //?
                }
                break;
            case REGISTER_ACTION:
                boolean success = authService.register(username, password);
                if (success) {
                    resp.sendRedirect(req.getContextPath() + REDIRECT_TO_LOGIN_PAGE);
                } else {
                    req.setAttribute(ERROR_ATTRIBUTE, ERROR_REGISTER_MESSAGE);
                    req.getRequestDispatcher(REDIRECT_TO_REGISTER_PAGE).forward(req, resp);
                }
                break;
        }
    }
}