package fr.afpa.pompey.cda17.clientsprospectsweb_back.dao;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.models.Client;

/**
 * Factory instanciant des objets de DAO pour la base de données MySQL
 */
public class MySQLDAOFactory extends AbstractDAOFactory {

    /**
     * Instancie et renvoie un objet de la classe de DAO pour Client, spécifique à MySQL.
     * @return Une instance de la classe de DAO Client pour MySQL
     */
    public DAO<Client> getClient() {
        return new ClientDAO();
    }

//    /**
//     * Instancie et renvoie un objet de la classe de DAO pour Prospect, spécifique à MySQL.
//     * @return Une instance de la classe de DAO Propsect pour MySQL
//     */
//    public DAO<Prospect> getProspect() {
//        return new ProspectDAO();
//    }
//
//
//    public DAO<Contrat> getContrat() {
//        return new ContratDAO();
//    }
}
