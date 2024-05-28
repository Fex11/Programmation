<%@page import="model.Metier"%>
<%
    Metier[] metier=(Metier[]) request.getAttribute("metier");
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
            <div class="detail">
                <table id="t">
                    <tr>
                        <th>Metier</th>
                        <th>TH</th>
                    </tr>
                    <% for(int j=0;j<metier.length;j++) { %>
                        <tr>
                            <td><%= metier[j].getNomMetier() %></td>
                            <td><%= metier[j].getTh() %></td>
                        </tr>
                    <% } %>
                </table>
                    </div>
       <div id="formulaire-container">
            <h2>Ajouter Metier</h2>
            <form action="InsertMetier" method="get">
                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" required>
                <label for="th">th :</label>
                <input type="text" name="th" required>
                <br><br>
                <button type="submit">Envoyer</button>
            </form>
        </div>
        </main>
    </body>
</html>
