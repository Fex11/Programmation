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
        <div><a href="index.jsp"><button >Index</button></a></div>
        <div id="liste-container">
            <h2>Liste poketra</h2>
            <ul>
                <% for(int i=0;i<poketras.length;i++) { %>
                    <li>
                        <p><%= poketras[i].getNom() %> (<%= poketras[i].getCategorie().getCategorie() %> <%= poketras[i].getLook().getLook() %>)</p>
                        <% if(poketras[i].getEtat()==0) { %>
                            <a href="InitAjout?idPoketra=<%= poketras[i].getIdPoketra()%>"><button class="voir_mp">Ajouter MP</button></a>
                        <% } %>
                        <% if(poketras[i].getEtat()==1) { %>
                            <a href="InitTaille?idPoketra=<%= poketras[i].getIdPoketra()%>"><button class="voir_mp">Configurer taille</button></a>
                            <a href="DetailPoketra?idPoketra=<%= poketras[i].getIdPoketra()%>"><button class="voir_mp">Details</button></a>
                        <% } %>
                        <a href="InitDetailFabrication?idPoketra=<%= poketras[i].getIdPoketra()%>"><button class="voir_mp">Fabrication</button></a>
                        <a href="InitVente?idPoketra=<%= poketras[i].getIdPoketra()%>"><button class="voir_mp">Prix de Vente</button></a>
                    </li>
                <% } %>
            </ul>
        </div>

        <div id="formulaire-container">
            <h2>Creation poketra</h2>
            <form action="InsertPoketra" method="get">
                <label for="categorie">Categorie :</label>
                <select name="categorie">
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
                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" required>
                <br><br>
                <button type="submit">Envoyer</button>
            </form>
        </div>
    </main>
    </body>
</html>
