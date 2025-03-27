package fr.afpa.pompey.cda17.clientsprospectsweb_back.utilities;

import java.time.format.DateTimeFormatter;

/**
 * Classe de formattage des dates.
 */
public class FormattageDate {

    /**
     * Format de date français standard.
     */
    public static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

}
