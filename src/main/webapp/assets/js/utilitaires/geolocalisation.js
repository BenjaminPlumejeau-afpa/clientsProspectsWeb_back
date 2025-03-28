import {formattedCurrentDate} from "./dateUtil.js"

/**
 * Récupère les coordonnées GPS de l'adresse en paramètre via l'API  de la Base Adresse Nationale, puis utilise ces
 * dernières pour afficher une carte localisant l'adresse et les conditions météo..
 * @param adresse Objet adresse {numéro, rue, code postal, ville}
 */
export function getLocation(adresse) {

    // Code de base copié depuis le site de l'API puis adapté
    const url = new URL('http://api-adresse.data.gouv.fr/search');
    const params = {
        q: adresse.numRue.trim().replace(" ", "+") + "+"
            + adresse.nomRue.trim().replace(" ", "+") + "+"
            + adresse.ville.trim().replace(" ", "+"),
        postcode: adresse.codePostal.trim().replace(" ", "+"),
        limit: '1'
    };
    Object.keys(params).forEach(
        key => url.searchParams.append(key, params[key])
    );

    fetch(url)
        .then(response => {
            if (response.status >= 200 && response.status < 300) {
                return response;
            } else {
                const error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        })
        .then(response => response.json())
        .then(data => {

            const longitude = data.features[0].geometry.coordinates[0];
            const latitude = data.features[0].geometry.coordinates[1];

            // Affichage de la carte avec les coordonnées récupérées
            setMap(latitude, longitude);

            // Récupération et affichage des données météo
            getWeather(latitude, longitude);

        })
        .catch(error => {
            // Si une erreur est rencontrée, on affiche une carte par défaut
            setMap();
            console.log('Impossible de récupérer les coordonnées ; ', error);
        });
}


/**
 * Initialise et affiche une carte Leaflet plaçant un marqueur sur les coordonnées en paramètre. Si la latitude et la
 * longitude sont à 0, on affiche à la place une carte de France par défaut, sans marqueur.
 * @param latitude Latitude des coordonnées
 * @param longitude Longitude des coordonnées
 */
function setMap(latitude = 0, longitude = 0) {

    if ((latitude === 0) && (longitude === 0)) {
        let map = L.map('map').setView([46.2, 2.1], 5);

        L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
        }).addTo(map);

    } else {
        let map = L.map('map').setView([latitude, longitude], 14);

        L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
        }).addTo(map);

        let marker = L.marker([latitude, longitude]).addTo(map);
    }
}


/**
 * Récupère les données météo aux coordonnées en paramètre, et construit le DOM de l'affichage de la météo.
 * @param latitude
 * @param longitude
 * @returns {Promise<void>}
 */
async function getWeather(latitude, longitude) {

    const zoneMeteo = document.getElementById("zoneMeteo");
    const url = `https://www.infoclimat.fr/public-api/gfs/json?_ll=${latitude},${longitude}&_auth=AhgFEgB%2BASMFKAcwUCYHLgRsV2IBdwEmUCwDYAxpBXgGbVIzBWUBZwRqUi8AL1FnUn9VNgkyAzMKYVcvXy0FZAJoBWkAawFmBWoHYlB%2FBywEKlc2ASEBJlAyA2wMaQV4BmdSNQVnAX0EbVI1AC5RZFJhVT0JKQMkCmhXN182BWcCZgVpAGYBYQVrB2RQfwcsBDFXMAFqAThQOwNsDGYFNAZmUjAFYgFiBGtSNQAuUWJSaVU0CTQDOgpsVzBfNQV5An4FGAAQAX4FKgcnUDUHdQQqV2IBYAFt&_c=d0a36ba3e5be34cc553d259e07db765f`;

    try {
        // Récupération des données météo
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error("Erreur d'accès à l'API - code " + response.status);
        }
        const data = await response.json();

        const meteoCourante = data[formattedCurrentDate()];
        let temperature = meteoCourante.temperature["2m"] - 273.15;
        let humidite = meteoCourante.humidite["2m"];
        let vent = meteoCourante.vent_moyen["10m"];

        // Construction du DOM
        zoneMeteo.innerHTML = `
		<p>Température : ${Math.round(temperature)} °C</p>
		<p>Humidité : ${humidite} %</p>
		<p>Vent : ${vent} km/h</p>
		<p class="source">Données <a href="https://www.infoclimat.fr">www.infoclimat.fr</a></p>
	`;

    } catch (error) {
        console.log(error);
        zoneMeteo.innerHTML = `
			<p>Impossible de récupérer les données météo </p>
		`;
    }

}