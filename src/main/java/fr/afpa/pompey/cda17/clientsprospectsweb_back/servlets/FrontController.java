package fr.afpa.pompey.cda17.clientsprospectsweb_back.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.ICommand;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.PageAccueilController;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.RegisterUserController;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.clients.*;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.connexion.ConnexionController;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.connexion.DeconnexionController;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.contact.ContactController;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

/**
 * Routeur pour les pages de l'application.
 */
@WebServlet(value = "/frontController")
public class FrontController extends HttpServlet {
    private Map commands = new HashMap();
    private final Logger LOGGER = Logger.getLogger(FrontController.class.getName());

    @Resource(name = "jdbc/mysql")
    private static DataSource dataSource;
    public static Connection connection;


    /**
     * Initialise la collection des commandes de routage.
     *
     * @throws ServletException
     */
    public void init() throws ServletException {
        commands.put(null, new PageAccueilController());
        commands.put("accueil", new PageAccueilController());
        commands.put("contacter", new ContactController());
        commands.put("connecter", new ConnexionController());
        commands.put("submitConnecter", new ConnexionController());
        commands.put("deconnecter", new DeconnexionController());
        commands.put("listerClient", new ListeClientController());
        commands.put("choisirClient", new SelectionClientController());
        commands.put("afficherClient", new AffichageClientController());
        commands.put("ajouterClient", new AjoutClientController());
        commands.put("submitAjouterClient", new AjoutClientController());
        commands.put("modifierClient", new ModificationClientController());
        commands.put("submitModifierClient", new ModificationClientController());
        commands.put("supprimerClient", new SuppressionClientController());
        commands.put("sbmitSupprimerClient", new SuppressionClientController());

        // Création de l'utilisateur admin au lancement de la page - à commenter
//        commands.put(null, new RegisterUserController());


        // Ouverture de la connexion à la base de données
        try {
            connection = dataSource.getConnection();
        } catch (SQLException sqle) {
            throw new ServletException("Problème d'ouverture de connection à la base de données", sqle);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    public void destroy() {
        try {
            connection.close();
        } catch (SQLException sqle) {
            LOGGER.warning("Problème de fermeture de connection à la base de données" + sqle.getMessage());
        }
    }


    /**
     * Effectue le routage en fonction de la commande associée.
     *
     * @param request
     * @param response
     */
    public void processRequest(final HttpServletRequest request, final HttpServletResponse response) {
        String urlSuite = "";

        try {
            String cmd = request.getParameter("cmd");
            ICommand command = (ICommand) commands.get(cmd);
            urlSuite = command.execute(request, response);
        } catch (Exception e) {
            urlSuite = "WEB-INF/JSP/erreur.jsp";
        } finally {
            try {
                request.getRequestDispatcher(urlSuite).forward(request, response);
            } catch (ServletException | IOException e) {
                LOGGER.severe("pb forward" + e.getMessage());
            }
        }

    }
}
