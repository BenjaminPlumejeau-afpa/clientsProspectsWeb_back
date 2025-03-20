package fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.clients;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AffichageClientController implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "WEB-INF/JSP/clients/affichageClient.jsp";
    }
}
