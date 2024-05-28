<%@page import="model.Poketra"%>
<%@page import="java.sql.Connection"%>
<%
    Poketra[] p=(Poketra[]) request.getAttribute("p");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
       <main>
        <div><a href="index.jsp"><button >Index</button></a></div>
        <div id="formulaire-container">
             <h2>Choix poketra</h2>
             <form action="Statistique" method="get">
                 <select name="idp">
                     <option value="0">Tous</option>
                     <% for(int i=0;i<p.length;i++) { %>
                         <option value="<%= p[i].getIdPoketra() %>"><%= p[i].getNom() %></option>
                     <% } %>
                 </select>
                 <br><br>
                 <button type="submit">Envoyer</button>
             </form>
         </div>
        </main>
    </body>
</html>
