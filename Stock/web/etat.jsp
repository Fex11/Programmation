<%@page import="java.util.Vector"%>
<%@page import="connexion.Connexion"%>
<%@page import="java.sql.Connection"%>
<%@page import="model.Article"%>
<%@page import="model.Magasin"%>
<%
    Article[] a=(Article[]) request.getAttribute("a");
    Magasin[] m=(Magasin[]) request.getAttribute("m");
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
            <h1>Etat</h1>
            <form action="EtatStock" method="get">
                <div class="row">
                    <label class="" for="article">Date debut : </label>
                    <input type="datetime-local" name="date1"  required />
                </div>
                <div class="row">
                    <label class="" for="article">Date fin : </label>
                    <input type="datetime-local" name="date2" required  />
                </div>
                <div class="row">
                    <label class="" for="article">Article : </label>
                    <select class="" id="" name="article">
                      <option value="%">Tous</option>
                      <option value="B%">Boissons</option>
                      <option value="S%">Snack</option>
                      <option value="L%">Laitier</option>
                      <% for(int i=0;i<a.length;i++) { %>
                          <option value=<%= a[i].getCode() %>><%= a[i].getNom() %></option>
                      <% } %>
                    </select>
                </div>
                <div class="row">
                    <label class="" for="magasin">Magasin : </label>
                    <select class="" id="" name="magasin">
                      <option value="%">Tous</option>
                      <% for(int i=0;i<m.length;i++) { %>
                          <option value=<%= m[i].getNom() %>><%= m[i].getNom() %></option>
                      <% } %>
                    </select>
                </div>
                <div class="row">
                    <input type="submit" value="Valider" />
                </div>
            </form>
        </div>
        </div>
    </body>
</html>
