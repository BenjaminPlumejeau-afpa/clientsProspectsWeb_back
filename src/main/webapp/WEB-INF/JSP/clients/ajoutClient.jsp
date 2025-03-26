<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/JSP/components/taglibs.jsp" %>

<!doctype html>
<html lang="fr">
<head>
  <%@include file="/WEB-INF/JSP/components/commonHead.jsp" %>
  <title>Ajouter client</title>
</head>

<body>

<%@include file="/WEB-INF/JSP/components/header.jsp" %>

<main>
  <article>

    <form action="?cmd=submitAjouterClient" method="post">
      <h2>Ajouter un client</h2>

      <%-- Affichage des erreurs de saisie --%>
      <c:if test="${!empty validationClient}">
        <div class="alert alert-danger mt-1" role="alert">
            ${validationClient}
        </div>
      </c:if>

      <div class="row">
        <div class="col-lg-1"></div>

        <div class="col-lg-4 gy-3">
          <section class="card">
            <div class="card-body">

              <h3 class="card-title">Informations</h3>

              <div class="mb-3">
                <label for="inputRaisonSociale" class="form-label">Raison sociale</label>
                <input type="text" class="form-control" value="${raisonSociale}"
                       id="inputRaisonSociale" name="raisonSociale">
              </div>

              <div class="mb-3">
                <label for="inputTel" class="form-label">Téléphone</label>
                <input type="tel" class="form-control" value="${telephone}"
                       id="inputTel" name="telephone">
              </div>

              <div class="mb-3">
                <label for="inputMail" class="form-label">Adresse mail</label>
                <input type="email" class="form-control" value="${mail}"
                       id="inputMail" name="mail">
              </div>

              <div class="mb-3">
                <label for="inputCA" class="form-label">Chiffre d'affaires</label>
                <input type="number" class="form-control" value="${chiffreAffaires}"
                       id="inputCA" name="chiffreAffaires">
              </div>

              <div class="mb-3">
                <label for="inputNbEmployes" class="form-label">Nombre d'employés</label>
                <input type="number" class="form-control" value="${nbEmployes}"
                       id="inputNbEmployes" name="nbEmployes">
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
                <input type="text" class="form-control" value="${numRue}"
                       id="inputNumRue" name="numRue">
              </div>

              <div class="mb-3">
                <label for="inputNomRue" class="form-label">Rue</label>
                <input type="text" class="form-control" value="${nomRue}"
                       id="inputNomRue" name="nomRue">
              </div>

              <div class="mb-3">
                <label for="inputCP" class="form-label">Code Postal</label>
                <input type="text" class="form-control" pattern="[0-9]{5}"
                       value="${codePostal}"
                       id="inputCP" name="codePostal">
              </div>

              <div class="mb-3">
                <label for="inputVille" class="form-label">Ville</label>
                <input type="text" class="form-control" value="${ville}"
                       id="inputVille" name="ville">
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
								<textarea class="form-control" rows="4" placeholder="Commentaire facultatif"
                          id="areaComment" name="commentaire">
                  ${commentaire}
                </textarea>
                </div>

              </div>
            </section>

            <div class="align-self-center mt-4 mt-lg-auto mb-5">
              <button type="submit" class="btn btn-primary" id="btnValider">Enregistrer</button>
            </div>
          </div>

        </div>
      </div>

    </form>
  </article>
</main>

<%@include file="/WEB-INF/JSP/components/footer.jsp" %>

<%--<script src="assets/js/client/ajoutClient.js"></script>--%>
</body>

</html>
