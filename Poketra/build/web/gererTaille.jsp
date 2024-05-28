<%@page import="model.Poketra"%>
<%@page import="model.Look"%>
<%@page import="model.Categorie"%>
<%
    Categorie[] categories=(Categorie[]) request.getAttribute("categories");
    Look[] looks= (Look[]) request.getAttribute("looks");
    Poketra[] poketras = (Poketra[]) request.getAttribute("poketras");
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
        <h1>Look</h1>
    </header>

    <main>
        <div><a href="index.html"><button >Index</button></a></div>
        <div id="liste-container">
            <h2>Liste taille</h2>
            <ul>
                <% for(int i=0;i<poketras.length;i++) { %>
                    <li>
                        <%= poketras[i].getNom() %>
                        <a href="InitAjout?idPoketra=<%= poketras[i].getIdPoketra()%>"><button class="voir_mp">Ajouter MP</button></a>
                        <a href="InitAjout?idPoketra=<%= poketras[i].getIdPoketra()%>"><button class="voir_mp">Configurer taille</button></a>
                    </li>
                <% } %>
            </ul>
        </div>

        <div id="formulaire-container">
            <h2>Ajouter taille</h2>
            <form action="InsertPoketra" method="get">
                <label for="taille">Taille :</label>
                <select name="taille">
                    <% for(int i=0;i<categories.length;i++) { %>
                        <option value="<%= categories[i].getIdCategorie() %>"><%= categories[i].getCategorie() %></option>
                    <% } %>
                </select>
                <label for="Look">Look :</label>
                <select name="look">
                    <% for(int i=0;i<looks.length;i++) { %>
                        <option value="<%= looks[i].getIdLook() %>"><%= looks[i].getLook() %></option>
                    <% } %>
                </select>
                <br><br>
                <button type="submit">Envoyer</button>
            </form>
        </div>
    </main>
    </body>
</html>
