<%@page import="model.*"%>
<%
    Metier[] m=(Metier[]) request.getAttribute("metier");
    Poketra poketra=(Poketra) request.getAttribute("poketra");
   
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
        <div><a href="InitPoketra"><button>Retour</button></a></div>
        <div class="detail">
            <table id="t">
                <tr>
                    <th>Metier</th>
                    <th>temp</th>
                    <th>nb</th>
                </tr>
                <% for(int j=0;j<poketra.getMetier().length;j++) { %>
                    <tr>
                        <td><%=poketra.getMetier()[j].getNomMetier() %></td>
                        <td><%= poketra.getMetier()[j].getTemp() %></td>
                        <td><%= poketra.getMetier()[j].getNb()%></td>
                    </tr>
                <% } %>
            </table>
        </div>

        <div id="formulaire-container">
            <h2>Ajouter Metier pour le sac</h2>
            <form action="InsertDetailFabrication" method="get">
                <input type="hidden" name="idPoketra" value="<%= poketra.getIdPoketra() %>" required>
                <label for="unite">Metier :</label>
                <select name="metier">
                    <% for(int i=0;i<m.length;i++) { %>
                        <option value="<%= m[i].getIdMetier() %>"><%= m[i].getNomMetier() %></option>
                    <% } %>
                </select>
                <label for="temp">Temp pour chaque individu :</label>
                <input type="text" id="temp" name="temp" required>
                <label for="nb">Nb :</label>
                <input type="text" name="nb" required>
                <br><br>
                <button type="submit">Envoyer</button>
            </form>
        </div>
    </main>
    </body>
</html>
