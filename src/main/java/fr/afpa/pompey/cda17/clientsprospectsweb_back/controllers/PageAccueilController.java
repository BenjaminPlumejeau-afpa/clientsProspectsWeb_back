package fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PageAccueilController implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "WEB-INF/JSP/index.jsp";
    }
}
