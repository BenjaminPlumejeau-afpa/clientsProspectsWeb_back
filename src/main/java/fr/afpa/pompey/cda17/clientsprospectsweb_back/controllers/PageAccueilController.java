package fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller de la page d'accueil.
 */
public class PageAccueilController implements ICommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        return "WEB-INF/JSP/index.jsp";
    }
}
