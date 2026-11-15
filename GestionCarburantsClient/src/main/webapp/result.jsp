
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Résultat SOAP</title>
    <style>
        body {
            font-family: Arial;
            background: #f2f4f8;
            margin: 30px;
        }
        .resultat {
            background: white;
            padding: 25px;
            border-radius: 10px;
        }
        pre {
            background: #f1f3f5;
            padding: 15px;
            white-space: pre-wrap;
            overflow-wrap: anywhere;
        }
        a {
            color: #24527a;
        }
    </style>
</head>
<body>

<div class="resultat">
    <h1>Résultat de l'appel Web Service</h1>

    <p><strong>Opération :</strong>
        <c:out value="${operation}"/>
    </p>

    <p><strong>Statut HTTP :</strong>
        <c:out value="${status}"/>
    </p>

    <h3>Réponse du serveur :</h3>

    <pre><c:out value="${resultat}"/></pre>

    <a href="index.jsp">Retour à l'accueil</a>
</div>

</body>
</html>
