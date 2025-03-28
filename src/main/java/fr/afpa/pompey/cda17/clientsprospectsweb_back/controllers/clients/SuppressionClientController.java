package fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.clients;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.ICommand;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.connexion.ConnexionController;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.AbstractDAOFactory;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.DAO;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.DAOException;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.dao.TypeDB;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.models.Client;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.utilities.CSRF;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;
import java.util.logging.Logger;

/**
 * Controller de la page de suppression d'un client.
 */
public class SuppressionClientController implements ICommand {

    private final Logger LOGGER = Logger.getLogger(SuppressionClientController.class.getName());

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response)
        throws Exception {

        // Si le visiteur n'est pas connecté, renvoit vers la page de connexion
        HttpSession session = request.getSession();
        if (session.getAttribute("utilisateur") == null) {
            request.setAttribute("validation", "Connexion requise pour accéder à cette page");
            return new ConnexionController().execute(request, response);
        } else {

            // Instanciation de la DAO
            AbstractDAOFactory factory = AbstractDAOFactory.getDAOFactory(TypeDB.MYSQL);
            DAO<Client> clientDAO = factory.getClient();

            // Si on reçoit un formulaire à traiter, on supprime le client de la base de données
            if (request.getParameter("cmd").equals("sbmitSupprimerClient")) {

                // Vérification du token CSRF
                if ((session.getAttribute("csrf") == null)
                    ||
                    (!session.getAttribute("csrf").equals(request.getParameter("csrfToken")))) {
                    LOGGER.warning("Token CSRF non valide");
                    return "WEB-INF/JSP/erreur.jsp";
                }

                try {
                    // On charge le client depuis la base de données à partir de son identifiant
                    Client client = clientDAO.findById(Integer.parseInt(request.getParameter("identifiant")));

                    // Si le client est null, il n'existe pas et on renvoie une erreur
                    if (client == null) {
                        LOGGER.severe("Tentative de chargement d'un client inexistant");
                        return "WEB-INF/JSP/erreur.jsp";
                    }

                    // On supprime le client et on renvoie vers la page de sélection de client.
                    clientDAO.delete(client);
                    return new SelectionClientController().execute(request, response);

                } catch (NumberFormatException | DAOException e) {
                    LOGGER.severe(e.getMessage());
                    return "WEB-INF/JSP/erreur.jsp";
                }

                // Sinon si l'on a pas de commande spécifique, on charge les informations depuis la base de données
            } else {

                // Création du token CSRF si pas de retour de formulaire
                String token = CSRF.generateToken();
                request.setAttribute("token", token);
                session.setAttribute("csrf", token);

                // Récupération du client dans la base de données
                int identifiant = Integer.parseInt(request.getParameter("choixClient"));
                try {
                    Client client = clientDAO.findById(identifiant);

                    if (client == null) {
                        LOGGER.severe("Tentative de chargement d'un client inexistant - "
                            + ModificationClientController.class.getName());
                        return "WEB-INF/JSP/erreur.jsp";
                    }

                    // Si le client obtenu est valide on l'affiche dans les champs
                    String validation = validationClient(client);
                    if (validation.isEmpty()) {
                        request.setAttribute("identifiant", client.getIdentifiant());
                        request.setAttribute("raisonSociale", client.getRaisonSociale());
                        request.setAttribute("telephone", client.getTelephone());
                        request.setAttribute("mail", client.getMail());
                        request.setAttribute("commentaire", client.getCommentaire());
                        request.setAttribute("chiffreAffaires", client.getChiffreDAffaire());
                        request.setAttribute("nbEmployes", client.getNombreEmployes());
                        request.setAttribute("numRue", client.getAdresse().getNumeroRue());
                        request.setAttribute("nomRue", client.getAdresse().getNomRue());
                        request.setAttribute("codePostal", client.getAdresse().getCodePostal());
                        request.setAttribute("ville", client.getAdresse().getVille());
                    } else {
                        // Si les saisies ne sont pas valides, il y a incohérence dans la base de données
                        LOGGER.severe("Données de la base de données incohérentes - "
                            + ModificationClientController.class.getName());
                        return "WEB-INF/JSP/erreur.jsp";
                    }
                } catch (DAOException daoe) {
                    LOGGER.severe(daoe.getMessage());
                }
            }
            return "WEB-INF/JSP/clients/suppressionClient.jsp";
        }

    }


    /**
     * Méthode vérifiant la validité des attribut d'une instance de client
     * et renvoyant une chaine de caractères contenant toutes les erreurs.
     * Si la chaine retournée est vide, le client est valide.
     *
     * @param client Le client à valider
     * @return String - Les erreurs de validations
     */
    private String validationClient(Client client) {

        // Instanciation du validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Validation du client
        Set<ConstraintViolation<Client>> violations = validator.validate(client);

        // Construction du message
        StringBuilder msg = new StringBuilder();
        for (ConstraintViolation<Client> violation : violations) {
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
