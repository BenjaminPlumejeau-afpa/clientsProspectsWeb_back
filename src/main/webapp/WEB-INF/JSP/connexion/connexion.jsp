<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/JSP/components/taglibs.jsp" %>

<!doctype html>
<html lang="fr">
<head>
  <%@include file="/WEB-INF/JSP/components/commonHead.jsp" %>
  <title>Connexion</title>
</head>

<body>

<%@include file="/WEB-INF/JSP/components/header.jsp" %>

<main>
  <article>
    <form action="?cmd=submitConnecter" method="post">

      <input type="hidden" name="csrfToken" value="${token}">

      <h2>Connexion</h2>

      <%-- Affichage des erreurs de saisie --%>
      <c:if test="${!empty validation}">
        <div class="alert alert-danger mt-1" role="alert">
            ${validation}
        </div>
      </c:if>

      <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
          <section class="card mx-auto mt-5">
            <div class="card-body">

              <div class="m-2">
                <label for="inputId" class="form-label">Identifiant</label>
                <input type="text" class="form-control" id="inputId" name="utilisateur" required>
              </div>

              <div class="m-2">
                <label for="inputPw" class="form-label">Mot de passe</label>
                <input type="password" class="form-control" id="inputPw" name="motDePasse" required>
              </div>

              <div class="mt-4 text-center">
                <button type="submit" class="btn btn-primary">Se connecter</button>
              </div>

            </div>
          </section>
        </div>
        <div class="col-lg-4"></div>
      </div>

    </form>
  </article>
</main>


<%@include file="/WEB-INF/JSP/components/footer.jsp" %>
</body>
</html>
