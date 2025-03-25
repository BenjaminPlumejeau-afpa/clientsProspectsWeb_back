package fr.afpa.pompey.cda17.clientsprospectsweb_back.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

/**
 * Classe abstraite représentant une société
 */
public abstract class Societe {

    private Integer identifiant;

    @NotBlank
    private String raisonSociale;

    @Pattern(regexp = "^(0|([+]|00)[0-9]{2,3})[0-9]{9}$")
    private String telephone;

    @Valid
    private Adresse adresse;

    @Pattern(regexp = "^([^ @.-]([.-][^ @.-])?)+@([^ @.-]([.-][^ @.-])?)+[.][a-zA-Z]+$")
    private String mail;

    @NotNull
    private String commentaire;


    /**
     * Constructeur d'insertion ; instancie une société à insérer dans la base de données n'ayant donc pas encore
     * d'identifiant
     *
     * @param raisonSociale String
     * @param telephone     String
     * @param adresse       Adresse
     * @param mail          String
     * @param commentaire   String
     */
    public Societe(String raisonSociale, String telephone, Adresse adresse, String mail, String commentaire) {
        // l'identifiant est initialisé dans le contructeur de la classe fille
        setRaisonSociale(raisonSociale);
        setTelephone(telephone);
        setAdresse(adresse);
        setMail(mail);
        setCommentaire(commentaire);
    }

    /**
     * Constructeur de chargement ; instancie une société chargée depuis la base de données, incluant son identifiant
     *
     * @param identifiant   int
     * @param raisonSociale String
     * @param telephone     String
     * @param adresse       Adresse
     * @param mail          String
     * @param commentaire   String
     */
    public Societe(int identifiant, String raisonSociale, String telephone, Adresse adresse, String mail,
                   String commentaire) {
        setIdentifiant(identifiant);
        setRaisonSociale(raisonSociale);
        setTelephone(telephone);
        setAdresse(adresse);
        setMail(mail);
        setCommentaire(commentaire);
    }

    /**
     * Constructeur implicite
     */
    public Societe() {
        // l'identifiant est initialisé dans le contructeur de la classe fille
    }


    public Integer getIdentifiant() {
        return identifiant;
    }

    protected void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone.trim();
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


    @Override
    public String toString() {
        // On ne retourne ici que la raison sociale pour avoir un affichage propre dans une comboBox
        return this.raisonSociale;
    }
}
