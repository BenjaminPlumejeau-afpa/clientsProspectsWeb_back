<%@ page pageEncoding="UTF-8" %>

<!doctype html>
<html lang="fr">
<head>
  <%@include file="/WEB-INF/JSP/components/commonHead.jsp" %>
  <title>Gestion Clients/Prospects</title>
</head>

<body>

<%@include file="/WEB-INF/JSP/components/header.jsp" %>

<main>
  <article>
    <form action="#" method="post">

      <h2>Contact</h2>

      <div class="row">
        <div class="col-md-1"></div>

        <div class="col-md-5 gy-3">
          <section class="card">
            <div class="card-body">

              <h3 class="card-title">Coordonnées</h3>
              <div class="mb-3">
                <label for="inputNom" class="form-label">Nom</label>
                <input type="text" class="form-control" id="inputNom" name="nom" required>
              </div>

              <div class="mb-3">
                <label for="inputPrenom" class="form-label">Prénom</label>
                <input type="text" class="form-control" id="inputPrenom" name="prenom" required>
              </div>

              <div class="mb-3">
                <label for="inputMail" class="form-label">Adresse mail</label>
                <input type="email" class="form-control" id="inputMail" name="mail" required>
              </div>

              <div class="mb-3">
                <label for="inputTel" class="form-label">Téléphone</label>
                <input type="tel" class="form-control" id="inputTel" name="telephone" required>
              </div>

            </div>
          </section>
        </div>

        <div class="col-md-5 gy-3">
          <section class="card">
            <div class="card-body">

              <h3 class="card-title">Votre message</h3>
              <div class="mb-3">
                <label for="selectMotif" class="form-label">Motif</label>
                <select class="form-select" id="selectMotif" name="motif" required>
                  <option value="" disabled selected>Choisir un motif...</option>
                  <option value="1">Erreur rencontrée</option>
                  <option value="2">Données personnelles</option>
                  <option value="3">Autre</option>

                </select>
              </div>

              <div class="mb-3">
                <label for="areaMessage" class="form-label">Message</label>
                <textarea class="form-control" rows="8" id="areaMessage" name="message"
                          placeholder="Saisir votre message ici" required></textarea>
              </div>

            </div>

          </section>
        </div>

        <div class="col-md-1"></div>
      </div>

      <div class="row">
        <div class="col-sm gy-4 text-center">
          <button type="submit" class="btn btn-primary">Envoyer</button>
        </div>
      </div>

    </form>
  </article>
</main>


<%@include file="/WEB-INF/JSP/components/footer.jsp" %>
</body>

</html>
