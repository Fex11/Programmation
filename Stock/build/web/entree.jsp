<%@page import="model.Unite"%>
<%@page import="model.Article"%>
<%@page import="model.Magasin"%>
<%@page import="java.util.Vector"%>
<%@page import="connexion.Connexion"%>
<%@page import="java.sql.Connection"%>
<%
    Article[] a=(Article[]) request.getAttribute("a");
    Magasin[] m=(Magasin[]) request.getAttribute("m");
    Unite[] u=(Unite[]) request.getAttribute("u");
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
            <div class="row" id="form">
                <h1>Entrée</h1>
                <form action="EntreeStock" method="get">
                    <div class="row">
                        <label class="" for="article">Date : </label>
                        <input type="datetime-local" name="date" required />
                    </div>
                    <div class="row">
                        <label class="" for="article">Article : </label>
                        <select class="" id="" name="article">
                          <% for(int i=0;i<a.length;i++) { %>
                              <option value=<%= a[i].getIdArticle() %>><%= a[i].getNom() %></option>
                          <% } %>
                        </select>
                    </div>
                    <div class="row">
                        <label class="" for="magasin">Magasin : </label>
                        <select class="" id="" name="magasin">
                          <% for(int i=0;i<m.length;i++) { %>
                              <option value=<%= m[i].getIdMagasin() %>><%= m[i].getNom() %></option>
                          <% } %>
                        </select>
                    </div>
                    <div class="row">
                        <label class="" for="qte">Quantite : </label>
                        <input type="text" name="qte" required />
                    </div>
                    <div class="row">
                        <label class="" for="unite">Unite : </label>
                        <select class="" id="" name="unite">
                          <% for(int i=0;i<u.length;i++) { %>
                              <option value=<%= u[i].getIdUnite() %>><%= u[i].getNomUnite() %></option>
                          <% } %>
                        </select>
                    </div>
                    <div class="row">
                        <label class="" for="pu">PU : </label>
                        <input type="number" name="pu"  required />
                    </div>
                    <div class="row">
                        <input type="submit" value="Valider" />
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
