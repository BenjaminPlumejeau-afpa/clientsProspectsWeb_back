package fr.afpa.pompey.cda17.clientsprospectsweb_back.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.ICommand;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.PageAccueilController;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.clients.*;
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
    public static DataSource dataSource;
    public Connection connection;


    /**
     * Initialise la collection des commandes de routage.
     */
    public void init() {
        commands.put(null, new PageAccueilController());
        commands.put("accueil", new PageAccueilController());
        commands.put("ajouterClient", new AjoutClientController());
        commands.put("listerClient", new ListeClientController());
        commands.put("choisirClient", new SelectionClientController());
        commands.put("afficherClient", new AffichageClientController());
        commands.put("modifierClient", new ModificationClientController());
        commands.put("supprimerClient", new SuppressionClientController());
        commands.put("submitAjouterClient", new AjoutClientController());

        // Ouverture de la connexion à la base de données
        try {
            connection = dataSource.getConnection();
            LOGGER.info("\n    ==== CONNECTION OK ====");
        } catch (SQLException sqle) {
            //TODO Gestion de l'exception - ServletException ?
//            throw new ServletException("Problème d'ouverture de connection à la base de données", sqle);
            LOGGER.warning("\n    SQLException : " + sqle.getMessage());
            LOGGER.info("\n    ==== CONNECTION ECHEC ====");
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
            LOGGER.info("\n    ==== FERMETURE OK ====");
        } catch (SQLException sqle) {
            //TODO Gestion de l'exception - ServletException ?
//            throw new ServletException("Problème de fermeture de connection à la base de données", sqle);
            LOGGER.warning("\n    SQLException : " + sqle.getMessage());
            LOGGER.info("\n    ==== FERMETURE ECHEC ====");
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
