package fr.afpa.pompey.cda17.clientsprospectsweb_back.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Classe héritant de Societe représentant une société cliente
 */
public class Client extends Societe {


    @NotNull
    @Min(201)
    private long chiffreDAffaire;

    @NotNull
    @Min(1)
    private int nombreEmployes;


    /**
     * Constructeur d'insertion ; instancie un client à insérer dans la base de données n'ayant donc pas encore
     * d'identifiant
     *
     * @param raisonSociale   String
     * @param telephone       String
     * @param adresse         Adresse
     * @param mail            String
     * @param commentaire     String
     * @param chiffreDAffaire long
     * @param nombreEmployes  int
     */
    public Client(String raisonSociale, String telephone, Adresse adresse, String mail, String commentaire,
                  long chiffreDAffaire, int nombreEmployes) {
        super(raisonSociale, telephone, adresse, mail, commentaire);
        setChiffreDAffaire(chiffreDAffaire);
        setNombreEmployes(nombreEmployes);
    }

    /**
     * Constructeur de chargement ; instancie un client chargé depuis la base de données, incluant son identifiant
     *
     * @param identifiant     int
     * @param raisonSociale   String
     * @param telephone       String
     * @param adresse         Adresse
     * @param mail            String
     * @param commentaire     String
     * @param chiffreDAffaire long
     * @param nombreEmployes  int
     */
    public Client(int identifiant, String raisonSociale, String telephone, Adresse adresse, String mail,
                  String commentaire, long chiffreDAffaire, int nombreEmployes) {
        super(identifiant, raisonSociale, telephone, adresse, mail, commentaire);
        setChiffreDAffaire(chiffreDAffaire);
        setNombreEmployes(nombreEmployes);
    }

    /**
     * Constructeur implicite
     */
    public Client() {
        super();
    }

    public long getChiffreDAffaire() {
        return chiffreDAffaire;
    }

    public void setChiffreDAffaire(long chiffreDAffaire) {
        this.chiffreDAffaire = chiffreDAffaire;
    }

    public int getNombreEmployes() {
        return nombreEmployes;
    }

    public void setNombreEmployes(int nombreEmployes) {
        this.nombreEmployes = nombreEmployes;
    }

}
