<%@ page pageEncoding="UTF-8" %>

<!doctype html>
<html lang="fr">
<head>
  <%@include file="/WEB-INF/JSP/components/commonHead.jsp" %>
  <link rel="stylesheet" href="assets/css/index.css">
  <title>Gestion Clients/Prospects</title>
</head>

<body>

<%@include file="/WEB-INF/JSP/components/header.jsp" %>

<main>
  <article>
    <div class="container overflow-hidden text-center">
      <div class="row mt-3 gy-5">
        <div class="col-1 py-3"></div>

        <div class="col-10 py-3">
          <h1 class="accueil">Bienvenue dans l'application de gestion des sociétés partenaires</h1>
        </div>

        <div class="col-1 py-3"></div>
      </div>

      <div class="row">
        <div class="col mt-md-5 py-3">

          <c:choose>
            <c:when test="${sessionScope.utilisateur == null}">
              <a class="btn btn-primary btn-lg" href="?cmd=connecter">Se connecter</a>
            </c:when>
            <c:otherwise>
              <p>Connecté en tant que : ${sessionScope.utilisateur}</p>
            </c:otherwise>
          </c:choose>

        </div>
      </div>

    </div>
  </article>
</main>

<%@include file="/WEB-INF/JSP/components/footer.jsp" %>

</body>

</html>
