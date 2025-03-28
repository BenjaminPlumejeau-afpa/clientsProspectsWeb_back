<%@ page pageEncoding="UTF-8" %>

<header>
  <div class="container text-center">
    <h1>Gestion des clients et prospects</h1>
  </div>
  <nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
      <button class="navbar-toggler ms-auto" type="button" data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>


      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active currentPage" aria-current="page" href="?cmd=accueil">Accueil</a>
          </li>

          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Gestion des clients
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="?cmd=listerClient">Liste des clients</a></li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <li><a class="dropdown-item" href="?cmd=ajouterClient">Saisir un client</a></li>
              <li><a class="dropdown-item" href="?cmd=choisirClient">Afficher/Modifier/Supprimer</a></li>
            </ul>

          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Gestion des prospects
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="?cmd=listerProspect">Liste des prospects</a></li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <li><a class="dropdown-item" href="?cmd=ajouterProspect">Saisir un prospect</a></li>
              <li><a class="dropdown-item" href="?cmd=choisirProspect">Afficher/Modifier/Supprimer</a></li>
            </ul>
          </li>

          <li class="nav-item">
            <a class="nav-link active" href="?cmd=contacter">Contact</a>
          </li>

          <li class="nav-item dropdown menuConnexion">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              <c:choose>
                <c:when test="${sessionScope.utilisateur == null}">
                  Connexion
                </c:when>
                <c:otherwise>
                  ${sessionScope.utilisateur}
                </c:otherwise>
              </c:choose>
            </a>
            <ul class="dropdown-menu">
              <c:choose>
                <c:when test="${sessionScope.utilisateur == null}">
                  <li><a class="dropdown-item" href="?cmd=connecter">Se connecter</a></li>
                </c:when>
                <c:otherwise>
                  <li><a class="dropdown-item" href="?cmd=deconnecter">Se déconnecter</a></li>
                </c:otherwise>
              </c:choose>
            </ul>
          </li>

        </ul>
      </div>
    </div>
  </nav>
</header>