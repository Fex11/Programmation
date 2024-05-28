<%@page import="model.*"%>
<%
    Taille[] taille=(Taille[]) request.getAttribute("taille");
    String message=(String) request.getAttribute("message");
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
        <h1>Taille</h1>
    </header>

    <main>
        <div><a href="index.jsp"><button >Index</button></a></div>
        <div class="source">
            <h2>Liste taille</h2>
            <div class="info_source">
                <p></p>
            </div>
            <div class="detail">
                <table id="t">
                    <tr>
                        <th>Taille</th>
                        <th>Niveau</th>
                    </tr>
                    <% for(int i=0;i<taille.length;i++) { %>
                        <tr>
                            <td><%= taille[i].getTaille() %></td>
                            <td><%= taille[i].getNiveau() %></td>
                        </tr>
                    <% } %>
                </table>
            </div>
        </div>
        </div>
        <div id="formulaire-container">
            <div><p><%= message %></p></div>
            <h2>Ajouter taille</h2>
            <form action="InsertTaill" method="get">
                <label for="taille">Taille :</label>
                <input type="text" id="taille" name="taille" required>
                <br><br>
                <button type="submit">Envoyer</button>
            </form>
        </div>
    </main>
    </body>
</html>
