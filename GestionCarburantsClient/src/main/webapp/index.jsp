
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des carburants</title>
    <style>
        body {
            font-family: Arial;
            background: #f2f4f8;
            margin: 30px;
        }
        h1 { color: #17365d; }
        h2 { color: #24527a; }
        .section {
            background: white;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 10px;
        }
        form {
            display: inline-block;
            background: #f8f9fb;
            padding: 12px;
            margin: 8px;
            border: 1px solid #ddd;
            border-radius: 8px;
            vertical-align: top;
        }
        input {
            display: block;
            margin: 7px 0;
            padding: 8px;
            width: 190px;
        }
        button {
            background: #24527a;
            color: white;
            border: 0;
            padding: 9px 15px;
            cursor: pointer;
            border-radius: 5px;
        }
    </style>
</head>

<body>

<h1>Application de gestion des carburants</h1>
<p>Client Web JEE - Appels des Web Services JAX-WS</p>

<div class="section">
    <h2>1. Gestion des stations</h2>

    <form action="soap" method="post">
        <h3>Ajouter une station</h3>
        <input type="hidden" name="action" value="ajouterStation">
        <input name="nom" placeholder="Nom" required>
        <input name="ville" placeholder="Ville" required>
        <input name="adresse" placeholder="Adresse" required>
        <button>Ajouter</button>
    </form>

    <form action="soap" method="post">
        <h3>Afficher les stations</h3>
        <input type="hidden" name="action" value="afficherStations">
        <button>Afficher</button>
    </form>

    <form action="soap" method="post">
        <h3>Rechercher une station</h3>
        <input type="hidden" name="action" value="rechercherStation">
        <input name="id" type="number" placeholder="ID station" required>
        <button>Rechercher</button>
    </form>

    <form action="soap" method="post">
        <h3>Modifier une station</h3>
        <input type="hidden" name="action" value="modifierStation">
        <input name="id" type="number" placeholder="ID station" required>
        <input name="nom" placeholder="Nouveau nom" required>
        <input name="ville" placeholder="Nouvelle ville" required>
        <input name="adresse" placeholder="Nouvelle adresse" required>
        <button>Modifier</button>
    </form>

    <form action="soap" method="post">
        <h3>Supprimer une station</h3>
        <input type="hidden" name="action" value="supprimerStation">
        <input name="id" type="number" placeholder="ID station" required>
        <button>Supprimer</button>
    </form>
</div>

<div class="section">
    <h2>2. Gestion des carburants</h2>

    <form action="soap" method="post">
        <h3>Ajouter un carburant</h3>
        <input type="hidden" name="action" value="ajouterCarburant">
        <input name="nom" placeholder="Nom du carburant" required>
        <input name="description" placeholder="Description" required>
        <button>Ajouter</button>
    </form>

    <form action="soap" method="post">
        <h3>Afficher les carburants</h3>
        <input type="hidden" name="action" value="afficherCarburants">
        <button>Afficher</button>
    </form>

    <form action="soap" method="post">
        <h3>Rechercher un carburant</h3>
        <input type="hidden" name="action" value="rechercherCarburant">
        <input name="id" type="number" placeholder="ID carburant" required>
        <button>Rechercher</button>
    </form>

    <form action="soap" method="post">
        <h3>Modifier un carburant</h3>
        <input type="hidden" name="action" value="modifierCarburant">
        <input name="id" type="number" placeholder="ID carburant" required>
        <input name="nom" placeholder="Nouveau nom" required>
        <input name="description" placeholder="Nouvelle description" required>
        <button>Modifier</button>
    </form>

    <form action="soap" method="post">
        <h3>Supprimer un carburant</h3>
        <input type="hidden" name="action" value="supprimerCarburant">
        <input name="id" type="number" placeholder="ID carburant" required>
        <button>Supprimer</button>
    </form>
</div>

<div class="section">
    <h2>3. Gestion des historiques de prix</h2>

    <form action="soap" method="post">
        <h3>Ajouter un historique</h3>
        <input type="hidden" name="action" value="ajouterHistorique">
        <input name="date" type="date" required>
        <input name="prix" type="number" step="0.01" placeholder="Prix" required>
        <input name="idStation" type="number" placeholder="ID station" required>
        <input name="idCarburant" type="number" placeholder="ID carburant" required>
        <button>Ajouter</button>
    </form>

    <form action="soap" method="post">
        <h3>Afficher les historiques</h3>
        <input type="hidden" name="action" value="afficherHistoriques">
        <button>Afficher</button>
    </form>

    <form action="soap" method="post">
        <h3>Rechercher un historique</h3>
        <input type="hidden" name="action" value="rechercherHistorique">
        <input name="id" type="number" placeholder="ID historique" required>
        <button>Rechercher</button>
    </form>

    <form action="soap" method="post">
        <h3>Modifier un historique</h3>
        <input type="hidden" name="action" value="modifierHistorique">
        <input name="id" type="number" placeholder="ID historique" required>
        <input name="date" type="date" required>
        <input name="prix" type="number" step="0.01" placeholder="Prix" required>
        <input name="idStation" type="number" placeholder="ID station" required>
        <input name="idCarburant" type="number" placeholder="ID carburant" required>
        <button>Modifier</button>
    </form>

    <form action="soap" method="post">
        <h3>Supprimer un historique</h3>
        <input type="hidden" name="action" value="supprimerHistorique">
        <input name="id" type="number" placeholder="ID historique" required>
        <button>Supprimer</button>
    </form>
</div>

<div class="section">
    <h2>4. Consulter les prix journaliers (JAX-WS)</h2>

    <form action="soap" method="post">
        <h3>Consulter les prix</h3>
        <input type="hidden" name="action" value="consulterPrix">
        <input name="nomStation" placeholder="Nom de la station" required>
        <input name="date" type="date" required>
        <button>Consulter les prix</button>
    </form>
</div>

</body>
</html>
