import {getLocation} from "../utilitaire/geolocalisation.js";

document.addEventListener("DOMContentLoaded", () => {

    const ligneAdresse = document.getElementById("adresse");
    ligneAdresse.innerText = clientTest.adresse.numRue + " "
        + clientTest.adresse.nomRue + ", "
        + clientTest.adresse.codePostal + " "
        + clientTest.adresse.ville;

    // Récupération des coordonnées de l'adresse, affichage de la localisation et affichage des données météo
    getLocation(clientTest.adresse);
});


const clientTest = {
    id: "1",
    raisonSociale: "Google",
    telephone: "0102030405",
    mail: "google@google.com",
    commentaire: "",
    chiffresAffaires: 1700000,
    nbEmployes: 2357,
    adresse: {
        numRue: "56",
        nomRue: "Square Eugène Herzog",
        codePostal: "54390",
        ville: "Frouard"
    }
};