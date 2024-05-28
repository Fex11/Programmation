<%-- 
    Document   : valider
    Created on : 23 nov. 2023, 01:55:10
    Author     : fex
--%>
<%@page import="model.Entree"%>
<%@page import="model.Sortie"%>
<%
    Entree e=(Entree) request.getAttribute("e");
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
                <th>pu</th>
                <th>valeur</th>
                <th>unite</th>
                <th></th>
            </tr>
            <tr>
                <td><%= e.getIdEntree() %></td>
                <td><%= e.getDate() %></td>
                <td><%= e.getMagasin().getNom() %></td>
                <td><%= e.getArticle().getNom() %></td>
                <td><%= e.getQte() %></td>
                <td><%= e.getPu() %></td>
                <td><%= e.getValeur() %></td>
                <td><%= e.getUnite() %></td>
            </tr>
        </table>
        <div class="row" id="form">
            <h1>Choisir Date</h1>
            <form action="ValiderSortie" method="get">
                <input type="hidden" name="id" value="<%= e.getIdEntree() %>" />
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
