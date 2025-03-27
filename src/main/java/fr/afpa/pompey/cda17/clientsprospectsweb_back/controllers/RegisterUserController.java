package fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.AbstractDAOFactory;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.DAO;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.DAOException;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.TypeDB;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.models.User;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.servlets.FrontController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Logger;

/**
 * Controller enregistrant un utilisateur dans la base de données
 */
public class RegisterUserController implements ICommand {

    private final Logger LOGGER = Logger.getLogger(RegisterUserController.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Informations de l'utilisateur
        String username = "admin";
        String pwd = "admin";

        // Cryptage du mot de passe
        Argon2 argon2 = Argon2Factory.create();
        char[] password = pwd.toCharArray();
        try {
            // Hash password
            String hash = argon2.hash(10, 65536, 1, password);

            // Verify password
            if (argon2.verify(hash, password)) {
                // Hash matches password
                User utilisateur = new User(username, hash);

                // Enregistrement dans la base de données
                AbstractDAOFactory factory = AbstractDAOFactory.getDAOFactory(TypeDB.MYSQL);
                DAO<User> userDAO = factory.getUser();

                userDAO.save(utilisateur);

            } else {
                // Hash doesn't match password
                LOGGER.severe("Erreur de hashage de mot de passe");
                return "WEB-INF/JSP/erreur.jsp";
            }
        } catch (DAOException daoe) {
            LOGGER.severe(daoe.getMessage());
        } finally {
            // Wipe confidential data
            argon2.wipeArray(password);
        }

        return "WEB-INF/JSP/index.jsp";
    }
}
