<%-- 
    Document   : valiny
    Created on : 9 janv. 2024, 12:59:50
    Author     : fex
--%>

<%@page import="model.Poketra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Poketra[] poketras=(Poketra[]) request.getAttribute("poketras");
%>
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
            <% for(int i=0;i<poketras.length;i++){ %>
            <p> </p>
            <% } %>
            <div><a href="formu.jsp"><button >Retour</button></a></div>
            <div id="corps">
                <div class="detail">
                    <table id="t">
                        <tr>
                            <th>Poketra</th>
                            <th>Prix</th>
                        </tr>
                        <% for(int i=0;i<poketras.length;i++){ %>
                            <tr>
                                <td><%= poketras[i].getNom() %> <%= poketras[i].getTaille()[0].getTaille() %> (<%= poketras[i].getCategorie().getCategorie() %> <%= poketras[i].getLook().getLook() %>)</td>
                                <td><%= poketras[i].getTaille()[0].getPrix() %> Ar</td>
                            </tr>
                        <% } %>
                    </table>
                </div>
            </div>
        </main>
    </body>
</html>
