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
 * Controller de la page d'affichage de la liste des clients.
 */
public class ListeClientController implements ICommand {

    private final Logger LOGGER = Logger.getLogger(ListeClientController.class.getName());

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response)
        throws Exception {

        // Récupération des clients
        ArrayList<Client> listeClients = chargerClients();

        // Test du passage d'une liste vide
//        request.setAttribute("listeClients", new ArrayList<Client>());

        request.setAttribute("listeClients", listeClients);

        return "WEB-INF/JSP/clients/listeClient.jsp";
    }

    /**
     * Méthode renvoyant une ArrayList contenant tous les clients à afficher.
     *
     * @return ArrayList : collection des clients
     */
    private ArrayList<Client> chargerClients() {
        ArrayList<Client> clients = new ArrayList<>();

        try {
            AbstractDAOFactory factory = AbstractDAOFactory.getDAOFactory(TypeDB.MYSQL);
            DAO<Client> clientDAO = factory.getClient();
            clients = clientDAO.findAll();
        } catch (DAOException daoe) {
            LOGGER.severe(daoe.getMessage());
        }

        return clients;
    }
}
