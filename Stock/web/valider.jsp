<%-- 
    Document   : valider
    Created on : 23 nov. 2023, 01:55:10
    Author     : fex
--%>
<%@page import="model.Sortie"%>
<%
    Sortie s=(Sortie) request.getAttribute("s");
    String message="";
    message = (String) request.getAttribute("message");
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
        <% if(message!=null) { %>
        <h3 style="text-align: center"><%= message %></h3>
        <% } %>
        <table>
            <tr>
                <th>Id</th>
                <th>date</th>
                <th>Magasin</th>
                <th>Article</th>
                <th>Qte</th>
                <th>Valeur</th>
                <th>Unite</th>
                <th></th>
            </tr>
            <tr>
                <td><%= s.getIdSortie() %></td>
                <td><%= s.getDate() %></td>
                <td><%= s.getMagasin().getNom() %></td>
                <td><%= s.getArticle().getNom() %></td>
                <td><%= s.getQte() %></td>
                <td><%= s.getValeur() %></td>
                <td><%= s.getUnite() %></td>
            </tr>
        </table>
        <div class="row" id="form">
            <h1>Choisir Date</h1>
            <form action="valider" method="get">
                <input type="hidden" name="id" value="<%= s.getIdSortie() %>" />
                <div class="row">
                    <label class="" for="article">Date : </label>
                    <input type="datetime-local" name="date" required />
                </div>
                <div class="row">
                    <input type="submit" value="Valider" />
                </div>
            </form>
        </div>
        </div>
    </body>
</html>
