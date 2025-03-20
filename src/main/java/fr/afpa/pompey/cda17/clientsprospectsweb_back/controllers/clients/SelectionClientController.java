package fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.clients;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller de la page de sélection d'un client.
 */
public class SelectionClientController implements ICommand {
    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        return "WEB-INF/JSP/clients/selectionClient.jsp";
    }
}
