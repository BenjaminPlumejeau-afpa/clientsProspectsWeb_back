package fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.clients;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.ICommand;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.AbstractDAOFactory;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.DAO;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.DAOException;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.TypeDB;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.models.Client;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Controller de la page de sélection d'un client.
 */
public class SelectionClientController implements ICommand {

    private final Logger LOGGER = Logger.getLogger(SelectionClientController.class.getName());

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response)
        throws Exception {

        // Instanciation de la DAO
        AbstractDAOFactory factory = AbstractDAOFactory.getDAOFactory(TypeDB.MYSQL);
        DAO<Client> clientDAO = factory.getClient();

        // Récupération des clients
        ArrayList<Client> clients = new ArrayList<>();
        try {
            clients = clientDAO.findAll();
        } catch (DAOException daoe) {
            LOGGER.severe(daoe.getMessage());
        }

        request.setAttribute("listeClients", clients);

        return "WEB-INF/JSP/clients/selectionClient.jsp";
    }
}
