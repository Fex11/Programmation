<%@page import="model.Taille"%>
<%@page import="model.Poketra"%>
<%@page import="model.Client"%>
<%
    Client[] c=(Client[]) request.getAttribute("c");
    Poketra[] p=(Poketra[]) request.getAttribute("p");
    Taille[] t=(Taille[]) request.getAttribute("t");
    String message=(String) request.getAttribute("m");
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
            
            <div><a href="index.jsp"><button >Index</button></a></div>
            <div><p><%= message %></p></div>
            <div id="formulaire-container">
                <h2>Vente</h2>
                <form action="InsertVentePoketra" method="get">
                    <label for="date">Date :</label>
                    <input type="date" name="date">
                    <label for="mp">Client:</label>
                    <select name="client">
                        <% for(int i=0;i<c.length;i++) { %>
                            <option value="<%= c[i].getIdClient() %>"><%= c[i].getNom() %></option>
                        <% } %>
                    </select>
                     <label for="mp">Poketra :</label>
                    <select name="poketra">
                        <% for(int i=0;i<p.length;i++) { %>
                            <option value="<%= p[i].getIdPoketra() %>"><%= p[i].getNom() %></option>
                        <% } %>
                    </select>
                    <br>
                    <label for="mp">Taille:</label>
                    <select name="taille">
                        <% for(int i=0;i<t.length;i++) { %>
                            <option value="<%= t[i].getIdTaille() %>"><%= t[i].getTaille() %></option>
                        <% } %>
                    </select>
                     <label for="nb">Nombre :</label>
                    <input type="number" name="nb" id="nom" placeholder="nombre">
                    <br><br>
                    <button type="submit">Envoyer</button>
                </form>
            </div>
        </main>
    </body>
</html>
