package fr.afpa.pompey.cda17.clientsprospectsweb_back.models;

import jakarta.validation.constraints.NotNull;

/**
 * Classe représentant un utilisateur dans la base de données
 */
public class User {

    /**
     * Identifiant de l'utillisateur
     */
    private int identifiant;

    /**
     * Nom de l'utilisateur
     */
    @NotNull
    private String name;

    /**
     * Mot de passe de l'utilisateur
     */
    @NotNull
    private String pwd;

    /**
     * Contructeur de création.
     *
     * @param name String : Le nom de l'utilisateur
     * @param pwd  String : Le mot de passe de l'utilisateur
     */
    public User(String name, String pwd) {
        setName(name);
        setPwd(pwd);
    }

    /**
     * Constructeur de chargement.
     *
     * @param identifiant int : L'identifiant de l'utilisateur
     * @param name        String : Le nom de l'utilisateur
     * @param pwd         String : Le mot de passe de l'utilisateur
     */
    public User(int identifiant, String name, String pwd) {
        this.identifiant = identifiant;
        this.name = name;
        this.pwd = pwd;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
