<%@page import="model.Poketra"%>
<%
    Poketra[] p=(Poketra[]) request.getAttribute("p");
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
        <main>
        <div><a href="recherche.jsp"><button >Retour</button></a></div>
        <div id="liste-container">
            <h2>Liste poketra</h2>
            <ul>
                <% for(int i=0;i<p.length;i++){ %>
                    <li><%= p[i].getNom() %> (<%= p[i].getCategorie().getCategorie() %> <%= p[i].getLook().getLook() %>)</li>
                    <a href="DetailPoketra?idPoketra=<%= p[i].getIdPoketra()%>"><button class="voir_mp">Details</button></a>
                <% } %>
            </ul>
        </div>
        </main>
    </body>
</html>
