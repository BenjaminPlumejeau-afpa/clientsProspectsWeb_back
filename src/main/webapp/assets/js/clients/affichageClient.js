import {getLocation} from "../utilitaire/geolocalisation.js";

document.addEventListener("DOMContentLoaded", () => {

    // Construction de l'adresse
    const adresse = {
        "numRue": document.getElementById("numRue"),
        "nomRue": document.getElementById("nomRue"),
        "codePostal": document.getElementById("codePostal"),
        "ville": document.getElementById("ville")
    }

    // Récupération des coordonnées de l'adresse, affichage de la localisation et affichage des données météo
    getLocation(adresse);

});