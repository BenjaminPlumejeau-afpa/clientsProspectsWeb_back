package fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.contact;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller de la page de contact.
 */
public class ContactController implements ICommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response)
        throws Exception {
        return "WEB-INF/JSP/contact/contact.jsp";
    }
}
