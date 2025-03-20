package fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interface permettant l'execution des controllers l'implémentant
 */
public interface ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
