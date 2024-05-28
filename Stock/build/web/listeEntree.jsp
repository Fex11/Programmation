<%-- 
    Document   : listeSortie
    Created on : 23 nov. 2023, 01:29:35
    Author     : fex
--%>

<%@page import="model.Entree"%>
<%@page import="model.Sortie"%>
<%
    Entree[] e=(Entree[]) request.getAttribute("e");
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
            <% for(int i=0;i<e.length;i++) { %>
                <tr>
                    <td><%= e[i].getIdEntree() %></td>
                    <td><%= e[i].getDate() %></td>
                    <td><%= e[i].getMagasin().getNom() %></td>
                    <td><%= e[i].getArticle().getNom() %></td>
                    <td><%= e[i].getQte() %></td>
                    <td><%= e[i].getPu() %></td>
                    <td><%= e[i].getValeur() %></td>
                    <td><%= e[i].getUnite() %></td>
                    <td><a href="Init_validerEntree?id=<%= e[i].getIdEntree() %>"><button>Valider</button></a></td>
                </tr>
            <% } %>
        </table>
        </div>
    </body>
</html>
