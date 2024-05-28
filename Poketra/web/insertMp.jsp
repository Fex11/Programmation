<%@page import="model.*"%>
<%
    Mp[] mps=(Mp[]) request.getAttribute("mps");
    Unite[] unites= (Unite[]) request.getAttribute("unites");
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
       <header>
        <h1>Matiere premiere</h1>
    </header>

    <main>
        <div><a href="index.jsp"><button >Index</button></a></div>
        <div class="source">
            <h2>Liste MP</h2>
            <div class="info_source">
                <p></p>
            </div>
            <div class="detail">
                <table id="t">
                    <tr>
                        <th>Matiere Premiere</th>
                        <th>PU</th>
                    </tr>
                    <% for(int i=0;i<mps.length;i++) { %>
                        <tr>
                            <td><%= mps[i].getNom() %></td>
                            <td><%= mps[i].getPu() %></td>
                        </tr>
                    <% } %>
                </table>
            </div>
        </div>
        </div>
        <div id="formulaire-container">
            <h2>Ajouter MP</h2>
            <form action="InsertMp" method="get">
                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" required>
                <label for="unite">Unite :</label>
                <select name="unite">
                    <% for(int i=0;i<unites.length;i++) { %>
                        <option value="<%= unites[i].getIdUnite() %>"><%= unites[i].getUnite() %></option>
                    <% } %>
                </select>
                <label for="Pu">Pu :</label>
                <input type="text" name="pu" required>
                <br><br>
                <button type="submit">Envoyer</button>
            </form>
        </div>
    </main>
    </body>
</html>
