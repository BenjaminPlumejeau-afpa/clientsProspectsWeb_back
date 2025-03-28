/**
 * Renvoie la date courante au format 'AAAA-MM-JJ hh:00:00' ; Les heures sont arrondies par créneau de 3h pour
 * correspondre aux données de l'API infoclimat.
 * @returns {string} La date courante formatée
 */
export function formattedCurrentDate() {

    let dateCourante = new Date();

    let jour = dateCourante.getDate();
    if (jour < 10) {
        jour = "0" + jour;
    }

    let mois = dateCourante.getMonth() + 1;
    if (mois < 10) {
        mois = "0" + mois;
    }

    let annee = dateCourante.getFullYear();

    let heure = dateCourante.getHours();

    // Arrondi de l'heure (sans tenir compte des secondes) au plus proche pour correspondre à un
    // créneau de 3h à partir de 01h00 (10h, 13h, 16h...)
    if (heure <= 2) {
        heure = 1;
    } else if (heure <= 5) {
        heure = 4;
    } else if (heure <= 8) {
        heure = 7;
    } else if (heure <= 11) {
        heure = 10
    } else if (heure <= 14) {
        heure = 13;
    } else if (heure <= 17) {
        heure = 16;
    } else if (heure <= 20) {
        heure = 19
    } else {
        heure = 22;
    }

    // formattage de l'heure
    if (heure < 10) {
        heure = "0" + heure;
    }

    return annee + "-" + mois + "-" + jour + " " + heure + ":00:00";
}