package fr.afpa.pompey.cda17.clientsprospectsweb_back.dao;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.models.User;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.servlets.FrontController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Classe gérant la DAO de la Classe User.
 */
public class UserDAO implements DAO<User> {

    /**
     * Recherche dans la base de données et renvoie l'utilisateur ayant l'username passé en paramètre.
     *
     * @param username Le nom d'utilisateur recherché
     * @return User - L'utilisateur recherché, ou Null si aucun n'est trouvé
     * @throws DAOException
     */
    @Override
    public User find(String username) throws DAOException {

        User user = null;

        // préparation de l'instruction paramétrée
        PreparedStatement prepStmt = null;
        String requete = "SELECT identifiant, username, password"
            + "FROM UTILISATEUR "
            + "WHERE username = ?";

        try {
            // Execution de la requête et récupération des résultats
            prepStmt = FrontController.connection.prepareStatement(requete);
            prepStmt.setString(1, username);
            ResultSet rs = prepStmt.executeQuery();
            // Si la requête renvoie un résultat, on instancie un utilisateur à renvoyer
            if (rs.next()) {
                user = new User(
                    rs.getInt("identifiant"),
                    rs.getString("username"),
                    rs.getString("password")
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

        return user;
    }

    /**
     * Not yet implemented
     *
     * @param id L'identifiant à rechercher
     * @return Null
     * @throws DAOException
     */
    @Override
    public User findById(int id) throws DAOException {
        return null;
    }

    /**
     * Not yet implemented
     *
     * @return Null
     * @throws DAOException
     */
    @Override
    public ArrayList<User> findAll() throws DAOException {
        return null;
    }

    /**
     * Enregistre l'utilisateur passé en paramètre dans la base de données ;
     * si l'utilisateur y est déja présent il sera modifié,
     * sinon un nouvel utilisateur sera inséré.
     *
     * @param obj User : L'utilisateur à enregistrer
     * @throws DAOException
     */
    @Override
    public void save(User obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("L'utilisateur en paramètre ne peut être nul", 3);
        }


        // Si l'indentifiant de l'utilisateur vaut 0 il n'est pas initialisé, il s'agit d'une insertion ;
        // sinon il s'agit d'une modification
        if (obj.getIdentifiant() == 0) {
            // INSERTION

            // Préparation des instructions paramétrées
            PreparedStatement prepStmt = null;
            String requete = "INSERT INTO UTILISATEUR (username, password) " +
                "VALUES (?, ?) ";

            try {

                // Début de la "transaction"
                FrontController.connection.setAutoCommit(false);
                prepStmt = FrontController.connection.prepareStatement(requete);

                // Insertion de l'adresse
                prepStmt.setString(1, obj.getName());
                prepStmt.setString(2, obj.getPwd());
                prepStmt.execute();

            } catch (SQLException sqle) {
                // Si une exception est attrapée, on s'assure que la transaction est rollback
                try {
                    FrontController.connection.rollback();
                } catch (SQLException transacte) {
                    throw new DAOException("Erreur d'exécution de la transaction", transacte, 5);
                }

                // On renvoie une DAOException dépendant du message d'erreur SQL récupéré
                switch (sqle.getErrorCode()) {
                    case 1054: {
                        throw new DAOException("Erreur dans les instructions SQL\n" +
                            "Erreur " + sqle.getErrorCode(), sqle, 5);
                    }
                    case 1062: {
                        throw new DAOException("Ce nom d'utilisateur est déja utilisé", 0);
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
                    FrontController.connection.setAutoCommit(true);
                    if (prepStmt != null) {
                        prepStmt.close();
                    }
                } catch (SQLException sqle) {
                    throw new DAOException("Erreur de connexion à la base de données\n" +
                        "Erreur " + sqle.getErrorCode(), sqle, 5);
                }
            }

        } else {
            // MODIFICATION

            // Préparation des instructions paramétrées
            PreparedStatement prepStmt = null;
            String requete = "UPDATE UTILISATEUR " +
                "SET username = ?, password = ? " +
                "WHERE identifiant= ?";

            try {
                // Début de la "transaction"
                FrontController.connection.setAutoCommit(false);
                prepStmt = FrontController.connection.prepareStatement(requete);

                // Modification de l'utilisateur
                prepStmt.setString(1, obj.getName());
                prepStmt.setString(2, obj.getPwd());
                prepStmt.setInt(3, obj.getIdentifiant());
                prepStmt.executeUpdate();

                FrontController.connection.commit();

            } catch (SQLException sqle) {
                // Si une exception est attrapée, on s'assure que la transaction est rollback
                try {
                    FrontController.connection.rollback();
                } catch (SQLException transacte) {
                    throw new DAOException("Erreur d'exécution de la transaction", transacte, 5);
                }

                // On renvoie une DAOException dépendant du message d'erreur SQL récupéré
                switch (sqle.getErrorCode()) {
                    case 1054: {
                        throw new DAOException("Erreur dans les instructions SQL\n" +
                            "Erreur " + sqle.getErrorCode(), sqle, 5);
                    }
                    case 1062: {
                        throw new DAOException("Ce nom d'utilisateur est déja utilisé", 0);
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
                    FrontController.connection.setAutoCommit(true);
                    if (prepStmt != null) {
                        prepStmt.close();
                    }
                } catch (SQLException sqle) {
                    throw new DAOException("Erreur de connexion à la base de données\n" +
                        "Erreur " + sqle.getErrorCode(), sqle, 5);
                }
            }
        }

    }


    /**
     * Not yet implemented
     *
     * @param obj l'élément T à supprimer
     * @throws DAOException
     */
    @Override
    public void delete(User obj) throws DAOException {

    }
}
