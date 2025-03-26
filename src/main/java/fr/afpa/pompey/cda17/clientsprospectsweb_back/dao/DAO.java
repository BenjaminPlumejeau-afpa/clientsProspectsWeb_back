package fr.afpa.pompey.cda17.clientsprospectsweb_back.dao;

import java.util.ArrayList;

/**
 * Interface pour les classes de DAO des classes métiers.
 * @param <T>
 */
public interface DAO<T> {

    /**
     * Recherche un élément T selon son nom, libellé, raison sociale... dans une base de données.
     * @param nom Le nom à rechercher
     * @return T - Un élément correspondant, ou Null si aucun n'a été trouvé
     * @throws DAOException
     */
    public T find(String nom) throws DAOException;

    /**
     * Renvoie tous les éléments T présents dans une base de données.
     * @return ArrayList de T - Liste des éléments récupérés, ou Null su aucun n'est trouvé
     * @throws DAOException
     */
    public ArrayList<T> findAll() throws DAOException;

    /**
     * Enregistre l'élément T en paramètre dans une base de données.
     * @param obj l'élément T à enregistrer
     * @throws DAOException
     */
    public void save(T obj) throws DAOException;

    /**
     * Supprime l'élément T en paramètre dans une base de données.
     * @param obj l'élément T à supprimer
     * @throws DAOException
     */
    public void delete(T obj) throws DAOException;

}
