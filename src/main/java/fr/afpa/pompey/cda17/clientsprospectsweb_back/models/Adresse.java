package fr.afpa.pompey.cda17.clientsprospectsweb_back.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Classe représentant une adresse physique.
 */
public class Adresse {

    /**
     * Identifiant de l'adresse.
     */
    private Integer idAdresse;

    /**
     * Numéro dans la rue.
     */
    @NotBlank
    private String numeroRue;

    /**
     * Nom de la rue de l'adresse.
     */
    @NotBlank
    private String nomRue;

    /**
     * Code postal de l'adresse.
     */
    @Pattern(regexp = "^[0-9]{5}$")
    private String codePostal;

    /**
     * Ville de l'adresse.
     */
    @NotBlank
    private String ville;

    /**
     * Constructeur d'insertion ; instancie une adresse à insérer dans la base de
     * données n'ayant donc pas encore d'identifiant.
     *
     * @param numeroRue  String
     * @param nomRue     String
     * @param codePostal String
     * @param ville      String
     */
    public Adresse(String numeroRue, String nomRue, String codePostal, String ville) {
        setNumeroRue(numeroRue);
        setNomRue(nomRue);
        setCodePostal(codePostal);
        setVille(ville);
    }

    /**
     * Constructeur de chargement ; instancie une adresse chargée depuis
     * la base de données, incluant son identifiant.
     *
     * @param idAdresse  Integer
     * @param numeroRue  String
     * @param nomRue     String
     * @param codePostal String
     * @param ville      String
     */
    public Adresse(Integer idAdresse, String numeroRue, String nomRue, String codePostal, String ville) {
        this.idAdresse = idAdresse;
        this.numeroRue = numeroRue;
        this.nomRue = nomRue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    /**
     * Constructeur implicite.
     */
    public Adresse() {
        this.numeroRue = "";
        this.nomRue = "";
        this.codePostal = "";
        this.ville = "";
    }

    public Integer getIdAdresse() {
        return idAdresse;
    }

    public String getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(String numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return this.numeroRue + " " + this.nomRue
            + ", " + this.codePostal + " " + this.ville;
    }
}
