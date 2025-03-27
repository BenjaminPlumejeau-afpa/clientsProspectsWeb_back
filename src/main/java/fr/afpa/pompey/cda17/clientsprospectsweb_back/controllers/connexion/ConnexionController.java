package fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.connexion;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller de la page de connexion.
 */
public class ConnexionController implements ICommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response)
        throws Exception {
        return "WEB-INF/JSP/connexion/connexion.jsp";
    }
}
