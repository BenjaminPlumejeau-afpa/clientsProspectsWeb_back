<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/JSP/components/taglibs.jsp" %>

<!doctype html>
<html lang="fr">
<head>
  <%@include file="/WEB-INF/JSP/components/commonHead.jsp"%>
  <title>Sélection client</title>
</head>

<body>

<%@include file="/WEB-INF/JSP/components/header.jsp"%>

<main>
  <article>
    <form method="post">
      <h2>Choisir un client et l'opération à effectuer</h2>

      <div class="row">
        <div class="col-lg-3"></div>

        <div class="col-lg-6">
          <section class="card mx-auto mt-5">
            <div class="card-body">

              <div class="m-1">
                <label for="selectClient" class="form-label">Raison sociale</label>

                <select class="form-select" aria-label="Choix du client"
                        id="selectClient" name="choixClient" required>
                  <option value="" disabled selected>Choisir un client...</option>
                  <c:forEach var="client" begin="0" items="${listeClients}">
                    <option value="${client.getIdentifiant()}">${client.getRaisonSociale()}</option>
                  </c:forEach>

                </select>

              </div>

              <div class="container-fluid mt-3">
                <div class="d-md-flex justify-content-center">
                  <div class="text-center">
                    <button type="submit" formaction="?cmd=afficherClient" class="btn btn-primary m-2">Afficher
                    </button>
                  </div>
                  <div class="text-center">
                    <button type="submit" formaction="?cmd=modifierClient" class="btn btn-primary m-2">Modifier
                    </button>
                  </div>
                  <div class="text-center">
                    <button type="submit" formaction="?cmd=supprimerClient" class="btn btn-primary m-2">Supprimer
                    </button>
                  </div>
                </div>
              </div>

            </div>
          </section>
        </div>

        <div class="col-lg-3"></div>
      </div>

    </form>
  </article>
</main>

<%@include file="/WEB-INF/JSP/components/footer.jsp"%>

</body>

</html>
