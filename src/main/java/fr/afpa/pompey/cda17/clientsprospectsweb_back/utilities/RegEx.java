package fr.afpa.pompey.cda17.clientsprospectsweb_back.utilities;

import java.util.regex.Pattern;

/**
 * Classe contenant les RegEx de l'application.
 */
public class RegEx {

    /**
     * Pattern de code postal français.
     */
    public static final Pattern PATTERN_CODE_POSTAL = Pattern.compile("^[0-9]{5}$");

    /**
     * Pattern de numéro de téléphone permettant les numéros français
     * avec ou sans indicateur ainsi que certains numéros étrangers avec
     * leur indicateur international.
     */
    public static final Pattern PATTERN_TELEPHONE = Pattern.compile("^(0|([+]|00)[0-9]{2,3})[0-9]{9}$");

    /**
     * Pattern d'adresse mail vérifiant l'absence de répétition
     * de points et tirets et la validité de leur position.
     */
    public static final Pattern PATTERN_MAIL = Pattern.compile(
        "^([^ @.-]([.-][^ @.-])?)+@([^ @.-]([.-][^ @.-])?)+[.][a-zA-Z]+$");

}
