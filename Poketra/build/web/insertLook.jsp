<%@page import="model.Look"%>
<%
    Look[] looks=(Look[]) request.getAttribute("looks");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="style.css" rel="stylesheet">
    <title>Look</title>
</head>
<body>

    <header>
        <h1>Look</h1>
    </header>

    <main>
        <div><a href="index.jsp"><button >Index</button></a></div>
        <div id="liste-container">
            <h2>Liste look</h2>
            <ul>
                <% for(int i=0;i<looks.length;i++) { %>
                    <li>
                        <%= looks[i].getLook() %>
                        <a href="InitMpLook?idLook=<%= looks[i].getIdLook() %>"><button class="voir_mp">Voir Mp</button></a>
                    </li>
                <% } %>
            </ul>
        </div>

        <div id="formulaire-container">
            <h2>Ajouter look</h2>
            <form action="InsertLook" method="get">
                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" required>
                <button type="submit">Envoyer</button>
            </form>
        </div>
    </main>

</body>
</html>

