<%@ page pageEncoding="UTF-8" %>

<!doctype html>
<html lang="fr">
<head>
  <%@include file="/WEB-INF/JSP/components/commonHead.jsp" %>
  <title>Afficher client</title>
  <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"
        integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY="
        crossorigin=""/>
</head>

<body>

<%@include file="/WEB-INF/JSP/components/header.jsp" %>

<main>
  <article>

    <h2>Affichage d'un client</h2>

    <div class="row">

      <div class="col-lg-4 gy-3">
        <section class="card">
          <div class="card-body">

            <h3 class="card-title">Informations</h3>

            <div class="mb-3">
              <label for="inputId" class="form-label">Identifiant</label>
              <input type="text" class="form-control" value="<c:out value="${identifiant}"/>"
                     id="inputId" name="identifiant" readonly>
            </div>

            <div class="mb-3">
              <label for="inputRaisonSociale" class="form-label">Raison sociale</label>
              <input type="text" class="form-control" value="<c:out value="${raisonSociale}"/>"
                     id="inputRaisonSociale" name="raisonSociale" readonly>
            </div>

            <div class="mb-3">
              <label for="inputTel" class="form-label">Téléphone</label>
              <input type="tel" class="form-control" value="<c:out value="${telephone}"/>"
                     id="inputTel" name="telephone" readonly>
            </div>

            <div class="mb-3">
              <label for="inputMail" class="form-label">Adresse mail</label>
              <input type="email" class="form-control" value="<c:out value="${mail}"/>"
                     id="inputMail" name="mail" readonly>
            </div>

            <div class="mb-3">
              <label for="inputCA" class="form-label">Chiffre d'affaires</label>
              <input type="number" class="form-control" value="<c:out value="${chiffreAffaires}"/>"
                     id="inputCA" name="chiffreAffaires" readonly>
            </div>

            <div class="mb-3">
              <label for="inputNbEmployes" class="form-label">Nombre d'employés</label>
              <input type="number" class="form-control" value="<c:out value="${nbEmployes}"/>"
                     id="inputNbEmployes" name="nbEmployes" readonly>
            </div>

          </div>
        </section>
      </div>

      <div class="col-lg-5 gy-3">
        <section class="card">
          <div class="card-body">

            <h3 class="card-title">Adresse</h3>
            <div class="mb-3">
              <p id="adresse">
                <span
                  id="numRue"><c:out value="${numRue}"/></span> <span
                id="nomRue"><c:out value="${nomRue}"/></span>,<span
                id="codePostal"><c:out value="${codePostal}"/></span> <span
                id="ville"><c:out value="${ville}"/></span>
              </p>
            </div>

            <!-- Carte de géolocalisation -->
            <div class="mb-3">
              <div id="map"></div>
            </div>

            <!-- Données météo relatives à la géolocalisation -->
            <div class="mb-3 meteo">
              <h4>Météo</h4>
              <div id="zoneMeteo"></div>
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
								<textarea class="form-control" rows="4" placeholder="Aucun commentaire" id="areaComment"
                          readonly><c:out value="${commentaire}"/></textarea>
              </div>

            </div>
          </section>

          <div class="align-self-center mt-4 mt-lg-auto mb-5">
            <a href="?cmd=choisirClient">
              <button type="button" class="btn btn-primary">Retour</button>
            </a>
          </div>
        </div>

      </div>

    </div>
  </article>
</main>

<%@include file="/WEB-INF/JSP/components/footer.jsp" %>

<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"
        integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo="
        crossorigin=""></script>

<%--<script type="module" src="/assets/js/clients/affichageClient.js"></script>--%>
</body>

</html>
