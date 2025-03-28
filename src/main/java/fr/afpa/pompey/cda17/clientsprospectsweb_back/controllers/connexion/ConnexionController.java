package fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.connexion;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.ICommand;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.PageAccueilController;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.clients.AjoutClientController;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.clients.SelectionClientController;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.AbstractDAOFactory;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.DAO;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.DAOException;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.TypeDB;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.models.Client;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;
import java.util.logging.Logger;

/**
 * Controller de la page de connexion.
 */
public class ConnexionController implements ICommand {

    private final Logger LOGGER = Logger.getLogger(ConnexionController.class.getName());

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response)
        throws Exception {

        if (request.getParameter("cmd").equals("submitConnecter")) {

            // Valorisation des champs
            request.setAttribute("utilisateur", request.getParameter("utilisateur"));
            request.setAttribute("motDePasse", request.getParameter("motDePasse"));

            // Instanciation de la DAO
            AbstractDAOFactory factory = AbstractDAOFactory.getDAOFactory(TypeDB.MYSQL);
            DAO<User> userDAO = factory.getUser();

            try {
                // Instanciation d'un utilisateur
                User userData = new User(
                    request.getParameter("utilisateur"),
                    request.getParameter("motDePasse")
                );

                // vérification des données saisies
                String validation = validation(userData);
                if (validation.isEmpty()) {
                    // Si la saisie ne contient aucune erreur, on recherche l'utilisateur dans la base de données

                    // Recherche de l'utilisateur
                    User dbUser = userDAO.find(userData.getName());

                    // Si la recherche renvoit un User null l'utilisateur n'est pas dans la base de données
                    if (dbUser == null) {
                        request.setAttribute("validation", "Informations de connexion incorrectes");
                    } else {
                        // Si l'utilisateur existe, on vérifie son mot de passe
                        Argon2 argon2 = Argon2Factory.create();
                        char[] password = userData.getPwd().toCharArray();
                        try {
                            // Verify password
                            if (argon2.verify(dbUser.getPwd(), password)) {
                                // Hash matches password
//                                  TODO : Connexion -> Session etc
                                request.setAttribute("validation", "Connexion réussie !");
                                return new PageAccueilController().execute(request, response);

                            } else {
                                // Hash doesn't match password
                                request.setAttribute("validation", "Informations de connexion incorrectes");
                            }
                        } finally {
                            // Wipe confidential data
                            argon2.wipeArray(password);
                        }

                    }

                } else {
                    // Si les saisies ne sont pas valides, on affiche les corrections à effectuer
                    request.setAttribute("validation", validation);
                }

            } catch (DAOException daoe) {
                LOGGER.severe("Accès à la base de données : " + daoe.getMessage());
                return "WEB-INF/JSP/erreur.JSP";
            }

        }


        return "WEB-INF/JSP/connexion/connexion.jsp";
    }


    /**
     * Méthode vérifiant la validité des attribut d'un utilisateur
     * et renvoyant une chaine de caractères contenant toutes les erreurs.
     * Si la chaine retournée est vide, l'utilisateur est valide.
     *
     * @param user L'utilisateur à valider
     * @return String - Les erreurs de validations
     */
    private String validation(User user) {

        // Instanciation du validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Validation de l'utilisateur
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Construction du message
        StringBuilder msg = new StringBuilder();
        for (ConstraintViolation<User> violation : violations) {
            switch (violation.getPropertyPath().toString()) {
                case "raisonSociale": {
                    msg.append("- La raison sociale ").append(violation.getMessage());
                    break;
                }
                case "telephone": {
                    msg.append("- Le numéro de téléphone saisi est invalide");
                    break;
                }
                case "mail": {
                    msg.append("- Le format de l'adresse mail est invalide");
                    break;
                }
                case "chiffreDAffaire": {
                    msg.append("- Le chiffres d'affaires ").append(violation.getMessage());
                    break;
                }
                case "nombreEmployes": {
                    msg.append("- Le nombre d'employés ").append(violation.getMessage());
                    break;
                }
                case "commentaire": {
                    msg.append("- Le commentaire ").append(violation.getMessage());
                    break;
                }
                case "adresse": {
                    msg.append("- Veuillez renseigner une adresse valide");
                    break;
                }
                case "adresse.numeroRue": {
                    msg.append("- Le numéro de l'adresse ").append(violation.getMessage());
                    break;
                }
                case "adresse.nomRue": {
                    msg.append("- Le nom de rue ").append(violation.getMessage());
                    break;
                }
                case "adresse.codePostal": {
                    msg.append("- Le code postal doit être composé de 5 chiffres");
                    break;
                }
                case "adresse.ville": {
                    msg.append("- La ville ").append(violation.getMessage());
                    break;
                }
                default: {
                    msg.append(violation.toString());
                }
            }
            msg.append("<br>");
        }

        return msg.toString();
    }
}
