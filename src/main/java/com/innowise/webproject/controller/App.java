package com.innowise.webproject.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app")
public class App extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String view;

        if ("login".equals(action)) {
            view = "/WEB-INF/view/login.jsp";
        } else if ("menu".equals(action)) {
            view = "/WEB-INF/view/menu.jsp";
        } else {
            view = "/WEB-INF/view/home.jsp";
        }

        req.getRequestDispatcher(view).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("login".equals(action)) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
        }
    }
}