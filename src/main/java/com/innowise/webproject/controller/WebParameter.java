package com.innowise.webproject.controller;

public interface WebParameter {
    //Action
    String LOGIN_ACTION = "login";
    String MENU_ACTION = "menu";
    String REGISTER_ACTION = "register";
    String LOGOUT_ACTION = "logout";
    String HOME_ACTION = "home";

    //Pages
    String LOGIN_PAGE = "/WEB-INF/view/login.jsp";
    String REGISTER_PAGE = "/WEB-INF/view/register.jsp";
    String HOME_PAGE = "/WEB-INF/view/home.jsp";
    String MENU_PAGE = "/WEB-INF/view/menu.jsp";
    String REDIRECT_TO_MENU_PAGE = "/app?action=menu";
    String REDIRECT_TO_LOGIN_PAGE = "/app?action=login";
    String REDIRECT_TO_REGISTER_PAGE = "/WEB-INF/view/register.jsp";

    //Attribute
    String ERROR_ATTRIBUTE = "error";
    String USER_ATTRIBUTE = "user";

    //Parameter
    String USERNAME_PARAMETER = "username";
    String ACTION_PARAMETER= "action";
    String PASSWORD_PARAMETER = "password";
}
