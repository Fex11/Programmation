<%@page import="model.*"%>
<%
    Categorie[] categories=(Categorie[]) request.getAttribute("categories");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
       <header>
        <h1>Matiere premiere</h1>
    </header>

    <main>
        <div><a href="index.jsp"><button >Index</button></a></div>
        <div id="liste-container">
            <h2>Liste type</h2>
            <ul>
                <% for(int i=0;i<categories.length;i++) { %>
                    <li><%= categories[i].getCategorie() %></li>
                <% } %>
            </ul>
        </div>

        <div id="formulaire-container">
            <h2>Ajouter type</h2>
            <form action="InsertCategorie" method="get">
                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" required>
                <br><br>
                <button type="submit">Envoyer</button>
            </form>
        </div>
    </main>
    </body>
</html>
