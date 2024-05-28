<%@page import="model.Article"%>
<%@page import="model.*"%>
<%@page import="java.util.Vector"%>
<%@page import="connexion.Connexion"%>
<%@page import="java.sql.Connection"%>
<%
    Connection c=Connexion.getConnection();
    Etat etat= (Etat) request.getAttribute("etat");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <link href="style.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="navbar">
            <a href="Init_sortie"><p>Sortie</p></a>
            <a href="Init_entree"><p>Entrée</p></a>
            <a href="Init_sortieNv"><p>Liste sortie NV</p></a>
            <a href="Init_entreeNv"><p>Liste entree NV</p></a>
            <a href="Init_etat"><p>Etat</p></a>
        </div>
        <div id="corp">
        <h1>Etat de stock</h1>
        <div>
            <div id="debut">
            <h3><%= etat.getDebut() %></h3>
            </div >
            <div id="fin">
                <h3><%= etat.getFin() %></h3></h3>
            </div>
        </div>
        <table id="t">
            <tr>
                <th>Magasin</th>
                <th>Article</th>
                <th>Quantite initial</th>
                <th>Montant initial</th>
                <th>PU initial</th>
                <th>Quantite final</th>
                <th>Montant final</th>
                <th>PU final</th>
            </tr>
            <% for(int i=0;i<etat.getStocks().size();i++) { %>
                <tr>
                    <td><%= etat.getStocks().get(i).getMagasin() %></td>
                    <td><%= etat.getStocks().get(i).getNom() %></td>
                    <td><%= etat.getStocks().get(i).getQteInitial() %> <%= etat.getStocks().get(i).getUnite() %></td>
                    <td><%= (int) etat.getStocks().get(i).getMontantInitial() %> Ar</td>
                    <td><%= (int)etat.getStocks().get(i).getPuInitial() %> Ar</td>
                    <td><%= etat.getStocks().get(i).getQteFinal() %> <%= etat.getStocks().get(i).getUnite() %></td>
                    <td><%= (int) etat.getStocks().get(i).getMontantFinal() %> Ar</td>
                    <td><%= (int)etat.getStocks().get(i).getPuFinal() %> Ar</td>
                </tr>
            <% } %>
        </table>
        </div>
    </body>
</html>
