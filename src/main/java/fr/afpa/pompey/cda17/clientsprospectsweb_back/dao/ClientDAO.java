package fr.afpa.pompey.cda17.clientsprospectsweb_back.dao;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.models.Adresse;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.models.Client;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.servlets.FrontController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Classe gérant la DAO de la Classe Client.
 */
public class ClientDAO implements DAO<Client> {

    /**
     * Recherche dans la base de données et renvoie le client
     * ayant la raison sociale passée en paramètre.
     *
     * @param nom La raison sociale recherchée
     * @return Client - Le client recherché, ou Null si aucun n'est trouvé
     * @throws DAOException
     */
    public Client find(String nom) throws DAOException {

        Client client = null;

        // préparation de l'instruction paramétrée
        PreparedStatement prepStmt = null;
        String requete = "SELECT c.idClient, c.raisonSociale, c.telephone, c.mail, c.chiffreDAffaires," +
            " c.nombreEmployes, c.commentaire, a.idAdresse, a.numRue, a.nomRue, a.codePostal, a.ville "
            + "FROM CLIENT c "
            + "INNER JOIN ADRESSE a ON c.idAdresse = a.idAdresse  "
            + "WHERE c.raisonSociale = ?";

        try {
            // Execution de la requête et récupération des résultats
            prepStmt = FrontController.getConnection().prepareStatement(requete);
            prepStmt.setString(1, nom);
            ResultSet rs = prepStmt.executeQuery();
            // Si la requête renvoie un résultat, on instancie un client à renvoyer
            if (rs.next()) {
                client = new Client(
                    rs.getInt("c.idClient"),
                    rs.getString("c.raisonSociale"),
                    rs.getString("c.telephone"),
                    new Adresse(
                        rs.getInt("a.idAdresse"),
                        rs.getString("a.numRue"),
                        rs.getString("a.nomRue"),
                        rs.getString("a.codePostal"),
                        rs.getString("a.ville")
                    ),
                    rs.getString("c.mail"),
                    rs.getString("c.commentaire"),
                    rs.getLong("c.chiffreDAffaires"),
                    rs.getInt("c.nombreEmployes")
                );
            }
        } catch (SQLException sqle) {
            switch (sqle.getErrorCode()) {
                case 1054: {
                    throw new DAOException("Erreur dans les instructions SQL\n" +
                        "Erreur " + sqle.getErrorCode(), sqle, 5);
                }
                default: {
                    throw new DAOException("Erreur de connexion à la base de données\n" +
                        "Erreur " + sqle.getErrorCode(), sqle, 5);
                }
            }
        } finally {
            // Tentative de fermeture du statement
            if (prepStmt != null) {
                try {
                    prepStmt.close();
                } catch (SQLException sqle) {
                    throw new DAOException("Erreur de connexion à la base de données\n" +
                        "Erreur " + sqle.getErrorCode(), sqle, 5);
                }
            }
        }

        return client;
    }


    /**
     * Recherche dans la base de données et renvoie le client
     * ayant l'identifiant passé en paramètre.
     *
     * @param id L'identifiant du client recherché
     * @return Client - Le client recherché, ou Null si aucun n'est trouvé
     * @throws DAOException
     */
    public Client findById(int id) throws DAOException {

        Client client = null;

        // préparation de l'instruction paramétrée
        PreparedStatement prepStmt = null;
        String requete = "SELECT c.idClient, c.raisonSociale, c.telephone, c.mail, c.chiffreDAffaires," +
            " c.nombreEmployes, c.commentaire, a.idAdresse, a.numRue, a.nomRue, a.codePostal, a.ville "
            + "FROM CLIENT c "
            + "INNER JOIN ADRESSE a ON c.idAdresse = a.idAdresse  "
            + "WHERE c.idClient = ?";

        try {
            // Execution de la requête et récupération des résultats
            prepStmt = FrontController.getConnection().prepareStatement(requete);
            prepStmt.setInt(1, id);
            ResultSet rs = prepStmt.executeQuery();
            // Si la requête renvoie un résultat, on instancie un client à renvoyer
            if (rs.next()) {
                client = new Client(
                    rs.getInt("c.idClient"),
                    rs.getString("c.raisonSociale"),
                    rs.getString("c.telephone"),
                    new Adresse(
                        rs.getInt("a.idAdresse"),
                        rs.getString("a.numRue"),
                        rs.getString("a.nomRue"),
                        rs.getString("a.codePostal"),
                        rs.getString("a.ville")
                    ),
                    rs.getString("c.mail"),
                    rs.getString("c.commentaire"),
                    rs.getLong("c.chiffreDAffaires"),
                    rs.getInt("c.nombreEmployes")
                );
            }
        } catch (SQLException sqle) {
            switch (sqle.getErrorCode()) {
                case 1054: {
                    throw new DAOException("Erreur dans les instructions SQL\n" +
                        "Erreur " + sqle.getErrorCode(), sqle, 5);
                }
                default: {
                    throw new DAOException("Erreur de connexion à la base de données\n" +
                        "Erreur " + sqle.getErrorCode(), sqle, 5);
                }
            }
        } finally {
            // Tentative de fermeture du statement
            if (prepStmt != null) {
                try {
                    prepStmt.close();
                } catch (SQLException sqle) {
                    throw new DAOException("Erreur de connexion à la base de données\n" +
                        "Erreur " + sqle.getErrorCode(), sqle, 5);
                }
            }
        }

        return client;
    }


    /**
     * Renvoie l'ensemble de tous les clients enregistrés
     * dans la base de données.
     *
     * @return ArrayList de Client - La liste des clients,
     * ou Null si aucun n'est trouvé
     * @throws DAOException
     */
    public ArrayList<Client> findAll() throws DAOException {

        ArrayList<Client> listeClients = new ArrayList<>();

        // Préparation de la requête
        Statement stmt = null;
        String requete = "SELECT c.idClient, c.raisonSociale, c.telephone, c.mail, c.chiffreDAffaires, "
            + "c.nombreEmployes, c.commentaire, a.idAdresse, a.numRue, a.nomRue, a.codePostal, a.ville " +
            "FROM CLIENT c " +
            "INNER JOIN ADRESSE a ON c.idAdresse = a.idAdresse  " +
            "ORDER BY c.raisonSociale";

        try {
            // Execution de la requête et récupération du résultat
            stmt = FrontController.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(requete);

            // Parcours du résultat et construction de la liste à retourner
            while (rs.next()) {
                Client client = new Client(
                    rs.getInt("c.idClient"),
                    rs.getString("c.raisonSociale"),
                    rs.getString("c.telephone"),
                    new Adresse(
                        rs.getInt("a.idAdresse"),
                        rs.getString("a.numRue"),
                        rs.getString("a.nomRue"),
                        rs.getString("a.codePostal"),
                        rs.getString("a.ville")
                    ),
                    rs.getString("c.mail"),
                    rs.getString("c.commentaire"),
                    rs.getLong("c.chiffreDAffaires"),
                    rs.getInt("c.nombreEmployes")
                );

                listeClients.add(client);
            }

        } catch (SQLException sqle) {
            switch (sqle.getErrorCode()) {
                case 1054: {
                    throw new DAOException("Erreur dans les instructions SQL\n" +
                        "Erreur " + sqle.getErrorCode(), sqle, 5);
                }
                default: {
                    throw new DAOException("Erreur de connexion à la base de données\n" +
                        "Erreur " + sqle.getErrorCode(), sqle, 5);
                }
            }
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqle) {
                    throw new DAOException("Erreur de connexion à la base de données\n" +
                        "Erreur " + sqle.getErrorCode(), sqle, 5);
                }
            }
        }

        return listeClients;
    }


    /**
     * Enregistre le client passé en paramètre dans la base de données ;
     * si le client y est déja présent il sera modifié,
     * sinon un nouveau client sera inséré.
     *
     * @param obj Client : Le client à enregistrer
     * @throws DAOException
     */
    public void save(Client obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Le client en paramètre ne peut être nul", 3);
        }

        // Si l'indentifiant du client en paramètre est nul, il s'agit d'un nouveau client à insérer dans la base de
        // données ; sinon il s'agit d'un client existant à modifier
        if (obj.getIdentifiant() == null) {
            // INSERTION

            // Préparation des instructions paramétrées
            PreparedStatement stmtClient = null;
            PreparedStatement stmtAdresse = null;
            String insertionAdresse = "INSERT INTO ADRESSE (numRue, nomRue, codePostal, ville)" +
                "VALUES (?, ?, ?, ?)";
            String insertionClient = "INSERT INTO CLIENT (raisonSociale, telephone, mail, commentaire, chiffreDAffaires,"
                + " nombreEmployes, idAdresse)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try {
                // Vérification de la disponibilité de la raison sociale Dans les prospects
//                AbstractDAOFactory factory = AbstractDAOFactory.getDAOFactory(TypeDB.MYSQL);
//                DAO<Prospect> prospectDAO = factory.getProspect();
//                if (prospectDAO.find(obj.getRaisonSociale()) != null) {
//                    throw new DAOException("Cette société est déja enregistrée comme prospect", 0);
//                }

                // Début de la "transaction"
                FrontController.getConnection().setAutoCommit(false);
                stmtAdresse = FrontController.getConnection().prepareStatement(insertionAdresse,
                    Statement.RETURN_GENERATED_KEYS);
                stmtClient = FrontController.getConnection().prepareStatement(insertionClient);

                // Insertion de l'adresse
                stmtAdresse.setString(1, obj.getAdresse().getNumeroRue());
                stmtAdresse.setString(2, obj.getAdresse().getNomRue());
                stmtAdresse.setString(3, obj.getAdresse().getCodePostal());
                stmtAdresse.setString(4, obj.getAdresse().getVille());
                stmtAdresse.execute();
                // Récupération de l'id de l'adresse insérée
                ResultSet rs = stmtAdresse.getGeneratedKeys();

                // Insertion du client si l'adresse a bien été insérée
                if (rs.next()) {
                    stmtClient.setString(1, obj.getRaisonSociale());
                    stmtClient.setString(2, obj.getTelephone());
                    stmtClient.setString(3, obj.getMail());
                    stmtClient.setString(4, obj.getCommentaire());
                    stmtClient.setLong(5, obj.getChiffreDAffaire());
                    stmtClient.setInt(6, obj.getNombreEmployes());
                    stmtClient.setInt(7, rs.getInt(1));
                    stmtClient.execute();

                    // On commit la transaction si tout s'est bien déroulé
                    FrontController.getConnection().commit();
                } else {
                    // Sinon on rollback en renvoyant une exception
                    FrontController.getConnection().rollback();
                    throw new DAOException("Impossible d'insérer l'adresse ; opération annulée", 3);
                }

            } catch (SQLException sqle) {
                // Si une exception est attrapée, on s'assure que la transaction est rollback
                try {
                    FrontController.getConnection().rollback();
                } catch (SQLException transacte) {
                    throw new DAOException("Erreur d'exécution de la transaction", transacte, 5);
                }

                // On revoie une DAOException dépendant du message d'erreur SQL récupéré
                switch (sqle.getErrorCode()) {
                    case 1054: {
                        throw new DAOException("Erreur dans les instructions SQL\n" +
                            "Erreur " + sqle.getErrorCode(), sqle, 5);
                    }
                    case 1062: {
                        throw new DAOException("Cette société est déja enregistrée comme client", 0);
                    }
                    default: {
                        throw new DAOException("Erreur de connexion à la base de données\n" +
                            "Erreur " + sqle.getErrorCode(), sqle, 5);
                    }
                }
            } finally {
                // Dans tous les cas on réactive le fonctionnement normal des commit de la connection et on libère
                // les PreparedStatement utilisés
                try {
                    FrontController.getConnection().setAutoCommit(true);
                    if (stmtClient != null) {
                        stmtClient.close();
                    }
                    if (stmtAdresse != null) {
                        stmtAdresse.close();
                    }
                } catch (SQLException sqle) {
                    throw new DAOException("Erreur de connexion à la base de données\n" +
                        "Erreur " + sqle.getErrorCode(), sqle, 5);
                }
            }

        } else {
            // MODIFICATION

            // Préparation des instructions paramétrées
            PreparedStatement stmtClient = null;
            PreparedStatement stmtAdresse = null;
            String modifClient = "UPDATE CLIENT " +
                "SET raisonSociale = ?, telephone = ?, mail = ?, commentaire = ?," +
                " chiffreDAffaires = ?, nombreEmployes = ? " +
                "WHERE idClient= ?";
            String modifAdresse = "UPDATE ADRESSE " +
                "SET numRue = ?, nomRue = ?, codePostal = ?, ville = ? " +
                "WHERE idAdresse = ?";

            try {
                // Début de la "transaction"
                FrontController.getConnection().setAutoCommit(false);
                stmtClient = FrontController.getConnection().prepareStatement(modifClient);
                stmtAdresse = FrontController.getConnection().prepareStatement(modifAdresse);

                // Modification du client
                stmtClient.setString(1, obj.getRaisonSociale());
                stmtClient.setString(2, obj.getTelephone());
                stmtClient.setString(3, obj.getMail());
                stmtClient.setString(4, obj.getCommentaire());
                stmtClient.setLong(5, obj.getChiffreDAffaire());
                stmtClient.setInt(6, obj.getNombreEmployes());
                stmtClient.setInt(7, obj.getIdentifiant());
                stmtClient.executeUpdate();

                // Modification de l'adresse
                stmtAdresse.setString(1, obj.getAdresse().getNumeroRue());
                stmtAdresse.setString(2, obj.getAdresse().getNomRue());
                stmtAdresse.setString(3, obj.getAdresse().getCodePostal());
                stmtAdresse.setString(4, obj.getAdresse().getVille());
                stmtAdresse.setInt(5, obj.getAdresse().getIdAdresse());
                stmtAdresse.executeUpdate();

                FrontController.getConnection().commit();

            } catch (SQLException sqle) {
                // Si une exception est attrapée, on s'assure que la transaction est rollback
                try {
                    FrontController.getConnection().rollback();
                } catch (SQLException transacte) {
                    throw new DAOException("Erreur d'exécution de la transaction", transacte, 5);
                }

                // On revoie une DAOException dépendant du message d'erreur SQL récupéré
                switch (sqle.getErrorCode()) {
                    case 1054: {
                        throw new DAOException("Erreur dans les instructions SQL\n" +
                            "Erreur " + sqle.getErrorCode(), sqle, 5);
                    }
                    case 1062: {
                        throw new DAOException("Cette société est déja enregistrée comme client", 0);
                    }
                    default: {
                        throw new DAOException("Erreur de connexion à la base de données\n" +
                            "Erreur " + sqle.getErrorCode(), sqle, 5);
                    }
                }
            } finally {
                // Dans tous les cas on réactive le fonctionnement normal des commit de la connection et on libère
                // les PreparedStatement utilisés
                try {
                    FrontController.getConnection().setAutoCommit(true);
                    if (stmtClient != null) {
                        stmtClient.close();
                    }
                    if (stmtAdresse != null) {
                        stmtAdresse.close();
                    }
                } catch (SQLException sqle) {
                    throw new DAOException("Erreur de connexion à la base de données\n" +
                        "Erreur " + sqle.getErrorCode(), sqle, 5);
                }
            }
        }
    }


    /**
     * Supprime le client en paramètre de la base de données.
     *
     * @param obj Client : Le client à supprimer
     * @throws DAOException
     */
    public void delete(Client obj) throws DAOException {

        // Préparation des instructions paramétrées
        PreparedStatement stmtClient = null;
        PreparedStatement stmtAdresse = null;
        String suppressionClient = "DELETE FROM CLIENT WHERE idClient = ?";
        String suppressionAdresse = "DELETE FROM ADRESSE WHERE idAdresse = ?";

        try {
            // Début de la "transaction"
            FrontController.getConnection().setAutoCommit(false);
            stmtClient = FrontController.getConnection().prepareStatement(suppressionClient);
            stmtAdresse = FrontController.getConnection().prepareStatement(suppressionAdresse);
            stmtClient.setInt(1, obj.getIdentifiant());
            stmtAdresse.setInt(1, obj.getAdresse().getIdAdresse());
            stmtClient.executeUpdate();
            stmtAdresse.executeUpdate();

            FrontController.getConnection().commit();

        } catch (SQLException sqle) {
            // Si une exception est attrapée, on s'assure que la transaction est rollback
            try {
                FrontController.getConnection().rollback();
            } catch (SQLException transacte) {
                throw new DAOException("Erreur d'exécution de la transaction", transacte, 5);
            }

            // On revoie une DAOException dépendant du message d'erreur SQL récupéré
            switch (sqle.getErrorCode()) {
                case 1054: {
                    throw new DAOException("Erreur dans les instructions SQL\n" +
                        "Erreur " + sqle.getErrorCode(), sqle, 5);
                }
                default: {
                    throw new DAOException("Erreur de connexion à la base de données\n" +
                        "Erreur " + sqle.getErrorCode(), sqle, 5);
                }
            }
        } finally {
            // Dans tous les cas on réactive le fonctionnement normal des commit de la connection et on libère
            // les PreparedStatement utilisés
            try {
                FrontController.getConnection().setAutoCommit(true);
                if (stmtClient != null) {
                    stmtClient.close();
                }
                if (stmtAdresse != null) {
                    stmtAdresse.close();
                }
            } catch (SQLException sqle) {
                throw new DAOException("Erreur de connexion à la base de données\n" +
                    "Erreur " + sqle.getErrorCode(), sqle, 5);
            }
        }

    }
}
