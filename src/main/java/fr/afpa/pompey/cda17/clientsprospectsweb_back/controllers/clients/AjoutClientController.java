package fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.clients;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.ICommand;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.PageAccueilController;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.models.Adresse;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.models.Client;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

/**
 * Controller de la page de création d'un client.
 */
public class AjoutClientController implements ICommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        // Si on reçoit un formulaire à traiter, on valide la saisie et on enregistre les changements s'il n'y a
        // pas d'erreur
        if (request.getParameter("cmd").equals("submitAjouterClient")) {

            // On valorise les attributs pour afficher les paramètres dans les champs
            request.setAttribute("raisonSociale", request.getParameter("raisonSociale"));
            request.setAttribute("telephone", request.getParameter("telephone"));
            request.setAttribute("mail", request.getParameter("mail"));
            request.setAttribute("commentaire", request.getParameter("commentaire"));
            request.setAttribute("chiffreAffaires", request.getParameter("chiffreAffaires"));
            request.setAttribute("nbEmployes", request.getParameter("nbEmployes"));
            request.setAttribute("numRue", request.getParameter("numRue"));
            request.setAttribute("nomRue", request.getParameter("nomRue"));
            request.setAttribute("codePostal", request.getParameter("codePostal"));
            request.setAttribute("ville", request.getParameter("ville"));

            try {
                // Instanciation d'un client après réception du formulaire
                Client client = new Client(
                    request.getParameter("raisonSociale"),
                    request.getParameter("telephone"),
                    new Adresse(
                        request.getParameter("numRue"),
                        request.getParameter("nomRue"),
                        request.getParameter("codePostal"),
                        request.getParameter("ville")
                    ),
                    request.getParameter("mail"),
                    request.getParameter("commentaire"),
                    //TODO   V V V V    RENVOIE UNE EXCEPTION QUAND LE CHAMP EST NUL - A GERER    V V V V
                    Integer.parseInt(request.getParameter("chiffreAffaires")),
                    Integer.parseInt(request.getParameter("nbEmployes"))
                );

                // vérification des données saisies

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
                String validation = msg.toString();

                if (validation.isEmpty()) {
                    // Si la saisie ne contient aucune erreur, elle est enregistrée dans la base de données et on
                    // affiche la page d'accueil
//TODO                --- ENREGISTREMENT DANS LA BDD ---
                    return new PageAccueilController().execute(request, response);
                } else {
                    // Si les saisies ne sont pas valides, on affiche les corrections à effectuer
                    request.setAttribute("validationClient", validation);
                }

            } catch (NumberFormatException e) {
                request.setAttribute("validationClient", "Le chiffre d'affaires et le nombre d'employés doivent" +
                    " être saisis et doivent être des nombres entiers et positifs");
            }

        }
        return "WEB-INF/JSP/clients/ajoutClient.jsp";
    }
}
