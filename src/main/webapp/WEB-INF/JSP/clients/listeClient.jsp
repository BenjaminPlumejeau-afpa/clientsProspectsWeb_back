<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/JSP/components/taglibs.jsp" %>

<!doctype html>
<html lang="fr">
<head>
  <%@include file="/WEB-INF/JSP/components/commonHead.jsp"%>
  <title>Liste Clients</title>
</head>

<body>

<%@include file="/WEB-INF/JSP/components/header.jsp"%>

<main>
  <article>
    <h2>Liste des clients</h2>

    <div class="container text-center">
      <div class="card mt-4 p-2">
        <div class="table-responsive">
          <table class="table table-striped table-bordered">
            <thead>
            <tr>
              <th scope="col">Identifiant</th>
              <th scope="col">Raison sociale</th>
              <th scope="col">N°</th>
              <th scope="col">Rue</th>
              <th scope="col">Code postal</th>
              <th scope="col">Ville</th>
              <th scope="col">Téléphone</th>
              <th scope="col">Mail</th>
              <th scope="col">Chiffre d'affaires</th>
              <th scope="col">Employés</th>
            </tr>
            </thead>
            <tbody id="tableBody">

            <!-- Contenu généré par le javascript -->

            </tbody>
          </table>
        </div>

      </div>
    </div>

  </article>
</main>

<%@include file="/WEB-INF/JSP/components/footer.jsp"%>

<script src="../assets/js/client/listeClients.js"></script>
</body>

</html>
