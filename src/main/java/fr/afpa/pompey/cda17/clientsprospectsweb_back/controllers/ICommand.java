package fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interface permettant l'execution des controllers l'implémentant.
 */
public interface ICommand {

    /**
     * Renvoie le lien vers la page que le controller doit afficher.
     * @param request
     * @param response
     * @return String - L'URL à contacter
     * @throws Exception
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
