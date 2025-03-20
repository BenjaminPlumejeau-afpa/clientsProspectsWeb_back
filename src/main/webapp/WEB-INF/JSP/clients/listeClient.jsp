<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/JSP/components/taglibs.jsp" %>

<!doctype html>
<html lang="fr">
<head>
  <%@include file="/WEB-INF/JSP/components/commonHead.jsp" %>
  <title>Liste Clients</title>
</head>

<body>

<%@include file="/WEB-INF/JSP/components/header.jsp" %>

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

            <!-- Affichage des clients -->
            <c:choose>
              <c:when test="${empty listeClients}">
                <p>Aucun client à afficher</p>
              </c:when>
              <c:otherwise>
                <c:forEach var="client" begin="0" items="${listeClients}">
                  <tr>
                    <td><c:out value="${client.identifiant}"/></td>
                    <td><c:out value="${client.raisonSociale}"/></td>
                      <%--                <td><c:out value="${client.adresse.numRue}"/></td>--%>
                      <%--                <td><c:out value="${client.adresse.nomRue}"/></td>--%>
                      <%--                <td><c:out value="${client.adresse.codePostal}"/></td>--%>
                      <%--                <td><c:out value="${client.adresse.ville}"/></td>--%>
                    <td><c:out value="${client.telephone}"/></td>
                    <td><c:out value="${client.mail}"/></td>
                    <td><c:out value="${client.chiffreDAffaire}"/></td>
                    <td><c:out value="${client.nombreEmployes}"/></td>
                  </tr>
                </c:forEach>
              </c:otherwise>
            </c:choose>


            </tbody>
          </table>
        </div>

      </div>
    </div>

  </article>
</main>

<%@include file="/WEB-INF/JSP/components/footer.jsp" %>

<script src="../assets/js/client/listeClients.js"></script>
</body>

</html>
