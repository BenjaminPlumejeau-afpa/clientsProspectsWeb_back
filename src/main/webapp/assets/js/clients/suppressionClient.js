document.addEventListener("DOMContentLoaded", () => {
    const btnValider = document.getElementById("btnValider");
    btnValider.addEventListener("click", confirmSuppression);
});


function confirmSuppression(event) {

    event.preventDefault();
    let formulaire = document.querySelector("form");

    // On submit le formulaire uniquement si l'utilisateur valide la boîte de dialogue
    if (window.confirm("Voulez-vous vraiment supprimer ce client ?")) {
        formulaire.submit();
    }

}