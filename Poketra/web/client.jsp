<%@page import="model.Client"%>
<%@page import="connexion.Connexion"%>
<%@page import="java.sql.Connection"%>
<%
   Connection c=Connexion.getConnection();
   Client[] client=Client.getAll(c);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet">
        <link href="tableau.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <main>
            <div><a href="index.jsp"><button >Index</button></a></div>
            <div class="source">
                <h2>Liste client</h2>
                <div class="info_source">
                    <p></p>
                </div>
                <div class="detail">
                    <table id="t">
                        <tr>
                            <th>Client</th>
                            <th>Sexe</th>
                        </tr>
                        <% for(int i=0;i<client.length;i++) { %>
                            <tr>
                                <td><%= client[i].getNom() %></td>
                                <td><%= client[i].getSexe() %></td>
                            </tr>
                        <% } %>
                    </table>
                </div>
            </div>
            <div id="formulaire-container">
                <h2>Ajouter Client</h2>
                <form action="InitClient" method="get">
                    <label for="nom">Nom :</label>
                    <input type="text" id="nom" name="nom" required>
                    <select name="genre">
                        <option value="0">Homme</option>
                        <option value="1">Femme</option>
                    </select>
                    <button type="submit">Envoyer</button>
                </form>
            </div>
        </main>
    </body>
</html>
