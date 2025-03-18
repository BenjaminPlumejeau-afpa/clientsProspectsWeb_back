<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Gestion Clients/Prospects</title>
  <%@include file="/WEB-INF/JSP/components/commonStyles.jsp"%>
  <link rel="stylesheet" href="/webapp/assets/css/index.css">
</head>

<body>

<%@include file="/WEB-INF/JSP/components/header.jsp"%>

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
          <a class="btn btn-primary btn-lg" href="connexion/connexion.html">Se connecter</a>
        </div>
      </div>

    </div>
  </article>
</main>

<%@include file="/WEB-INF/JSP/components/footer.jsp"%>

</body>

</html>
