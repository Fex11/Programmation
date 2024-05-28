<%@page import="model.Poketra"%>
<% 
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
       <main>
        <div><a href="InitPoketra"><button >Retour</button></a></div>
        <div class="detail">
            <table id="t">
                <tr>
                    <th>Taille</th>
                    <th>Prix</th>
                </tr>
                <% for(int j=0;j<poketra.getTaille().length;j++) { %>
                    <tr>
                        <td><%=poketra.getTaille()[j].getTaille() %></td>
                        <td><%=poketra.getTaille()[j].getPrixVente() %></td>
                    </tr>
                <% } %>
            </table>
        </div>

        <div id="formulaire-container">
            <h2>Ajouter prix de vente</h2>
            <form action="InsertVente" method="get">
                <input type="hidden" name="idPoketra" value="<%= poketra.getIdPoketra() %>" required>
                <label for="unite">Taille :</label>
                <select name="taille">
                    <% for(int i=0;i<poketra.getTaille().length;i++) { %>
                        <option value="<%= poketra.getTaille()[i].getIdTaille() %>"><%= poketra.getTaille()[i].getTaille() %></option>
                    <% } %>
                </select>
                <label for="temp">Prix :</label>
                <input type="text" id="prix" name="prix" required>
                <br><br>
                <button type="submit">Envoyer</button>
            </form>
        </div>
    </main>
    </body>
</html>
