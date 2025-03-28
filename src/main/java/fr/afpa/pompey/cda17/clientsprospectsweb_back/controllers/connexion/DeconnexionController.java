package fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.connexion;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.ICommand;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.PageAccueilController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeconnexionController implements ICommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response)
        throws Exception {

        // Fermeture de la session
        HttpSession session = request.getSession();
        session.invalidate();

        // Redirection vers la page d'accueil
        return new PageAccueilController().execute(request, response);
    }
}
