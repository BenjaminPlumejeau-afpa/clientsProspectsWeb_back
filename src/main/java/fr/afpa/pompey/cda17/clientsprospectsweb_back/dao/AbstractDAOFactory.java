package fr.afpa.pompey.cda17.clientsprospectsweb_back.dao;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.models.Client;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.models.User;

/**
 * Abstract Factory gérant l'instanciation des Factory de DAO.
 */
public abstract class AbstractDAOFactory {

    /**
     * Instancie et renvoie un objet de la classe de DAO pour la classe Client.
     *
     * @return L'objet instancié
     */
    public abstract DAO<Client> getClient();

//    /**
//     * Instancie et renvoie un objet de la classe de DAO pour la classe Prospect.
//     * @return L'objet instancié
//     */
//    public abstract DAO<Prospect> getProspect();
//
//    /**
//     * Instancie et renvoie un objet de la classe de DAO pour la classe Contrat.
//     * @return L'objet instancié
//     */
//    public abstract DAO<Contrat> getContrat();

    /**
     * Instancie et renvoie un objet de la classe de DAO pour la classe User.
     *
     * @return L'objet instancié
     */
    public abstract DAO<User> getUser();


    /**
     * Renvoie une nouvelle instance de la Factory de DAO spécifique à la base de données en paramètre.
     *
     * @param database Le type de base de données utilisé
     * @return La Factory d'objet DAO spécifique pour cette base de données
     */
    public static AbstractDAOFactory getDAOFactory(TypeDB database) {
        switch (database) {
            case TypeDB.MYSQL: {
                return new MySQLDAOFactory();
            }
            case TypeDB.MANGODB: {
                return null;
            }
        }
        return null;
    }
}
