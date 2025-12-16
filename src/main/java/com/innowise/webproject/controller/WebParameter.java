package com.innowise.webproject.controller;

public interface WebParameter {
    //Action
    String LOGIN_ACTION = "login";
    String MENU_ACTION = "menu";
    String REGISTER_ACTION = "register";
    String LOGOUT_ACTION = "logout";
    String HOME_ACTION = "home";
    String LIST_ACTION = "list";
    String EDIT_ACTION = "edit";
    String CREATE_ACTION = "create";
    String UPDATE_ACTION = "update";
    String DELETE_ACTION = "delete";

    //Pages
    String LOGIN_PAGE = "/WEB-INF/view/login.jsp";
    String REGISTER_PAGE = "/WEB-INF/view/register.jsp";
    String HOME_PAGE = "/WEB-INF/view/home.jsp";
    String MENU_PAGE = "/WEB-INF/view/menu.jsp";
    String REDIRECT_TO_MENU_PAGE = "/app?action=menu";
    String REDIRECT_TO_LOGIN_PAGE = "/app?action=login";
    String REDIRECT_TO_LIST_PAGE = "/competition?action=list";
    String REDIRECT_TO_REGISTER_PAGE = "/WEB-INF/view/register.jsp";
    String REDIRECT_TO_COMPETITION_LIST_PAGE = "/WEB-INF/view/competitionList.jsp";
    String REDIRECT_TO_COMPETITION_EDIT_PAGE = "/WEB-INF/view/competitionEdit.jsp";

    //Attribute
    String ERROR_ATTRIBUTE = "error";
    String USER_ATTRIBUTE = "user";
    String COMPETITIONS_ATTRIBUTE = "competitions";
    String COMPETITION_ATTRIBUTE = "competition";

    //Parameter
    String USERNAME_PARAMETER = "username";
    String ACTION_PARAMETER= "action";
    String PASSWORD_PARAMETER = "password";
    String ID_PARAMETER = "id";
    String TEAM_HOME_PARAMETER = "teamHome";
    String TEAM_AWAY_PARAMETER = "teamAway";
}
