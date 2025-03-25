package fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.clients;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.ICommand;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.models.Adresse;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.models.Client;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;

/**
 * Controller de la page d'affichage de la liste des clients.
 */
public class ListeClientController implements ICommand {

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

        //TODO TEMP - Création d'un jeu d'essai - A remplacer par Datasource
        clients.add(new Client(1, "Google", "0102030405",
            new Adresse("24", "rue Pierre", "54000", "Nancy"),
            "google@google.com", "", 17500, 2200));
        clients.add(new Client(3, "Amazon", "0102030405",
            new Adresse("35", "rue Paul", "54000", "Nancy"),
            "amazon@amazon.com", "Mauvais payeur", 12500, 3000));
        clients.add(new Client(5, "Microsoft", "0102030405",
            new Adresse("666", "rue Jacques", "54000", "Nancy"),
            "microsoft@microsoft.com", "", 25000, 200));

        return clients;
    }
}
