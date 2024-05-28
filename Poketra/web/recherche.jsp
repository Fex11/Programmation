<%@page import="connexion.Connexion"%>
<%@page import="java.sql.Connection"%>
<%@page import="model.Mp"%>
<%
    Connection c=Connexion.getConnection();
    Mp[] mp=Mp.getAll(c);
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
        <div><a href="index.jsp"><button >Retour</button></a></div>
       <div id="formulaire-container">
            <h2>Recherche par MP</h2>
            <form action="Recherche" method="get">
                <select name="mp">
                    <% for(int i=0;i<mp.length;i++) { %>
                        <option value="<%= mp[i].getNom() %>"><%= mp[i].getNom() %></option>
                    <% } %>
                </select>
                <br><br>
                <button type="submit">Envoyer</button>
            </form>
        </div>
        </main>
    </body>
</html>
