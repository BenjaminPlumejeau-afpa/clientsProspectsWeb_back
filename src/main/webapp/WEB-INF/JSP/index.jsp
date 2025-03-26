<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/JSP/components/taglibs.jsp" %>

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
          <a class="btn btn-primary btn-lg" href="?cmd=connecter">Se connecter</a>
        </div>
      </div>

    </div>
  </article>
</main>

<%@include file="/WEB-INF/JSP/components/footer.jsp" %>

</body>

</html>
