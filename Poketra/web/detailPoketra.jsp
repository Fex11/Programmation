<%@page import="model.Poketra"%>
<%
    Poketra p= (Poketra) request.getAttribute("poketra");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="tableau.css" rel="stylesheet">
        <link href="style.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <main>
        <div><a href="InitPoketra"><button >Retour</button></a></div>
        <div id="corps">
            <h1><%= p.getNom() %> (<%= p.getCategorie().getCategorie() %> <%= p.getLook().getLook() %>)</h1>
            <% for(int i=0;i<p.getTaille().length;i++) { %>
                <div class="source">
                    <h2>Taille <%= p.getTaille()[i].getTaille() %> ==> stock : <%= p.getTaille()[i].getStockk() %></h2>
                    <div class="info_source">
                        <p></p>
                    </div>
                    <div class="detail">
                        <table id="t">
                            <tr>
                                <th>Matiere Premiere</th>
                                <th>Qte</th>
                            </tr>
                            <% for(int j=0;j<p.getTaille()[i].getMpPoketra().length;j++) { %>
                                <tr>
                                    <td><%= p.getTaille()[i].getMpPoketra()[j].getNom() %></td>
                                    <td><%= p.getTaille()[i].getMpPoketra()[j].getQte() %></td>
                                </tr>
                            <% } %>
                        </table>
                    </div>
                </div>
            <% } %>
        </div>
        </main>
    </body>
</html>
