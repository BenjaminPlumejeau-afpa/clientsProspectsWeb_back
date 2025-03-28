document.addEventListener("DOMContentLoaded", () => {
    const btnValider = document.getElementById("btnValider");
    btnValider.addEventListener("click", controleFormulaire);
});


function controleFormulaire(event) {

    let formulaireValide = true;
    let messageInvalide = "";

    let inputChiffreAffaires = document.getElementById("inputCA");
    let inputNbEmployes = document.getElementById("inputNbEmployes");

    // Test de la validité du contenu des champs de saisie soumis à des règles métier

    // parseInt() renvoie NaN si la chaine de caractères ne peut être convertie en nombre ; cette comparaison suffit
    // car NaN rend toute comparaison false
    if (parseInt(inputChiffreAffaires.value.trim()) <= 200) {
        inputChiffreAffaires.classList.add("isInvalid");
        formulaireValide = false;
        messageInvalide += "Le chiffre d'affaire doit être un nombre supérieur à 200 \n";
    } else {
        inputChiffreAffaires.classList.remove("isInvalid");
    }

    if (parseInt(inputNbEmployes.value.trim()) <= 0) {
        inputNbEmployes.classList.add("isInvalid");
        formulaireValide = false;
        messageInvalide += "Le nombre d'employés doit être un nombre supérieur à 0 \n";
    } else {
        inputNbEmployes.classList.remove("isInvalid");
    }

    /* Si les tous les éléments métiers sont valides, on laisse le bouton fonctionner normalement et effectuer les
     contrôles HTML avant de submit ;
     Sinon on désactive le bouton pour l'empécher de submit */
    if (!formulaireValide) {
        event.preventDefault();
        if (messageInvalide !== "") {
            alert(messageInvalide);
        }
    }

}