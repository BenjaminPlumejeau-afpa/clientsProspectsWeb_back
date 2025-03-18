# Gestion de clients-prospects pour une entreprise

## ECF Web - partie Back

## Modification de la partie Front pour le déploiement sur un serveur Web et implémentation de la partie Back

Cette application permet la gestion des sociétés partenaires, clients ou prospects.
Les fonctionnalités disponibles sont l'affichage, l'ajout, la modification et la suppression de clients et de prospects
ainsi que l'affichage de la liste de ces derniers.

L'écran d'affichage d'un client/prospect affiche également la localisation de ce dernier ainsi que la météo à ces
coordonnées.

### Interface graphique

L'interface utilisateur de l'application est réalisée en html/css en utilisant bootstrap 5.3.2.

### Gestion des dépendances

La gestion des dépendances est effectuée avec NPM.

### APIs utilisées

- [API Adresse de la Base Adresse Nationale](https://adresse.data.gouv.fr/outils/api-doc/adresse) pour récupérer les
  coordonnées en fonction de l'adresse
- [Leaflet](https://leafletjs.com/) pour afficher une carte avec un marqueur représentant l'adresse
- [API Infoclimat](https://www.infoclimat.fr/api-previsions-meteo.html?id=2988507&cntry=FR) pour les données
  météorologiques localisées

### Serveur Web et serveur d'application
Utilisation de TomCat 10.1