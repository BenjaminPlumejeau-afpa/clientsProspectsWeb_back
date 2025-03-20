<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/JSP/components/taglibs.jsp" %>

<!doctype html>
<html lang="fr">
<head>
  <head>
    <%@include file="/WEB-INF/JSP/components/commonHead.jsp" %>
    <title>Supprimer client</title>
  </head>

<body>

<%@include file="/WEB-INF/JSP/components/header.jsp" %>

<main>
  <article>
    <form action="./selectionClient.html" method="post">
      <h2>Supprimer un client</h2>

      <div class="row">
        <div class="col-lg-1"></div>

        <div class="col-lg-4 gy-3">
          <section class="card">
            <div class="card-body">

              <h3 class="card-title">Informations</h3>

              <div class="mb-3">
                <label for="inputId" class="form-label">Identifiant</label>
                <input type="text" class="form-control" id="inputId" name="identifiant"
                       value="1" readonly>
              </div>

              <div class="mb-3">
                <label for="inputRaisonSociale" class="form-label">Raison sociale</label>
                <input type="text" class="form-control" id="inputRaisonSociale" name="raisonSociale"
                       value="Google" readonly>
              </div>

              <div class="mb-3">
                <label for="inputTel" class="form-label">Téléphone</label>
                <input type="tel" class="form-control" id="inputTel" name="telephone"
                       value="0102030405" readonly>
              </div>

              <div class="mb-3">
                <label for="inputMail" class="form-label">Adresse mail</label>
                <input type="email" class="form-control" id="inputMail" name="mail"
                       value="google@google.com" readonly>
              </div>

              <div class="mb-3">
                <label for="inputCA" class="form-label">Chiffre d'affaires</label>
                <input type="number" class="form-control" min="201" id="inputCA" name="chiffreAffaires"
                       value="1700000" readonly>
              </div>

              <div class="mb-3">
                <label for="inputNbEmployes" class="form-label">Nombre d'employés</label>
                <input type="number" class="form-control" min="1" id="inputNbEmployes" name="nbEmployes"
                       value="2357" readonly>
              </div>

            </div>
          </section>
        </div>

        <div class="col-lg-3 gy-3">
          <section class="card">
            <div class="card-body">

              <h3 class="card-title">Adresse</h3>
              <div class="mb-3">
                <label for="inputNumRue" class="form-label">Numéro</label>
                <input type="text" class="form-control" id="inputNumRue" name="numRue"
                       value="17" readonly>
              </div>

              <div class="mb-3">
                <label for="inputNomRue" class="form-label">Rue</label>
                <input type="text" class="form-control" id="inputNomRue" name="nomRue"
                       value="rue des Rose" readonly>
              </div>

              <div class="mb-3">
                <label for="inputCP" class="form-label">Code Postal</label>
                <input type="text" class="form-control" pattern="[0-9]{5}" id="inputCP" name="codePostal"
                       value="75000" readonly>
              </div>

              <div class="mb-3">
                <label for="inputVille" class="form-label">Ville</label>
                <input type="text" class="form-control" id="inputVille" name="ville"
                       value="Paris" readonly>
              </div>

            </div>

          </section>
        </div>

        <div class="col-lg-3 gy-3 d-flex">

          <div class="d-flex flex-column justify-content-start align-content-center flex-grow-1">
            <section class="card">
              <div class="card-body">
                <h3 class="card-title"><label for="areaComment">Commentaire</label></h3>

                <div class="m-4">
								<textarea class="form-control" rows="4" placeholder="Aucun commentaire"
                          id="areaComment" name="commentaire" readonly></textarea>
                </div>

              </div>
            </section>

            <div class="align-self-center mt-4 mt-lg-auto mb-5">
              <button type="submit" class="btn btn-danger" id="btnValider">Supprimer</button>
            </div>
          </div>

        </div>
      </div>

    </form>
  </article>
</main>

<%@include file="/WEB-INF/JSP/components/footer.jsp" %>

<script src="assets/js/client/suppressionClient.js"></script>

</body>

</html>
