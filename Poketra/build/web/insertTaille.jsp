<%@page import="model.Taille"%>
<%@page import="model.Poketra"%>
<%@page import="model.Look"%>
<%@page import="model.Categorie"%>
<%
    Taille[] taille= (Taille[]) request.getAttribute("taille");
    Poketra poketra = (Poketra) request.getAttribute("poketra");
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
        <h1>Look</h1>
    </header>

    <main>
        <div><a href="InitPoketra"><button >Retour</button></a></div>
        <div id="liste-container">
            <h3>Taille existant</h3>
            <% for(int i=0;i<poketra.getTaille().length;i++) { %>
                <p><%= poketra.getTaille()[i].getTaille() %><p>
            <% } %>
            <!--<h1>Taille existant</h1>
            <% for(int i=0;i<poketra.getTaille().length;i++) { %>
                <div class="source">
                    <h2>Taille <%= poketra.getTaille()[i].getTaille() %></h2>
                    <div class="info_source">
                        <p>So de ilaina</p>
                    </div>
                    <div class="detail">
                        <table id="t">
                            <tr>
                                <th>Matiere Premiere</th>
                                <th>Qte</th>
                            </tr>
                            <% for(int j=0;j<poketra.getTaille()[i].getMpPoketra().length;j++) { %>
                                <tr>
                                    <td><%= poketra.getTaille()[i].getMpPoketra()[j].getNom() %></td>
                                    <td><%= poketra.getTaille()[i].getMpPoketra()[j].getQte() %></td>
                                </tr>
                            <% } %>
                        </table>
                    </div>
                </div>
            <% } %>-->
        </div>

        <div id="formulaire-container">
            <h3>Configurer nouveau taille</h3>
            <form action="InsertTaille" method="get">
                <input type="hidden" name="poketra" value="<%= poketra.getIdPoketra() %>">
                <label for="taille">Taille :</label>
                <select name="taille">
                    <% for(int i=0;i<taille.length;i++) { %>
                        <option value="<%= taille[i].getIdTaille()%>"><%= taille[i].getTaille() %></option>
                    <% } %>
                </select>
                <% for(int i=0;i<poketra.getMp().length;i++) { %>
                    <label for="mp"><%= poketra.getMp()[i].getNom() %>:</label>
                    <input type="text" name="qte" required>
                <% } %>
                <br><br>
                <button type="submit">Envoyer</button>
            </form>
        </div>
    </main>
    </body>
</html>
