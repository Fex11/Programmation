<%@page import="model.Taille"%>
<%@page import="model.Poketra"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    Poketra [] listePoketra=(Poketra []) request.getAttribute("listePoketra");
    Taille [] listeTaille=(Taille []) request.getAttribute("listeTaille");
    String message=(String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <main>
        <div><a href="index.jsp"><button >Index</button></a></div>
        <div>
            <% if(message.hashCode()!="".hashCode()) { %>
            <p><%= message %></p>
            <% } %>
        </div>
        <h2>Fabrication poketra</h2>
        <div class="formulaire-container">
            <form action="InsertFabrication" method="get">
                <label for="idPoketra">Poketra :</label>
                <select name="idPoketra">
                <%for(int i=0;i< listePoketra.length;i++) { %>
                      <option value="<%= listePoketra[i].getIdPoketra() %>"><%= listePoketra[i].getNom() %></option>  
                <% }%>
                </select>
                <label for="taille">Taille :</label>
                <select name="taille">
                <%for(int i=0;i< listeTaille.length;i++) { %>
                        <option value="<%= listeTaille[i].getIdTaille() %>"><%= listeTaille[i].getTaille() %></option>  
                <% }%>
                </select>
                <label for="date">Date :</label>
                <input type="date" name="date">
                <label for="nombre">Nombre :</label>
                <input type="number" name="nombre">
                <br>
                <input type="submit" name="Valider">
            </form>
        </div>
        </main>
    </body>
</html>
