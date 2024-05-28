<%@page import="model.*"%>
<%
    Mp[] mps=(Mp[]) request.getAttribute("mps");
    Look look= (Look) request.getAttribute("look");
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
       <header>
        <h1>Matiere premiere pour <%= look.getLook() %></h1>
    </header>

    <main>
        <div><a href="index.jsp"><button >Index</button></a></div>
        <div id="liste-container">
            <h2>Liste MP pour <%= look.getLook() %></h2>
            <ul>
                <% for(int i=0;i<look.getMps().length;i++) { %>
                    <li><%= look.getMps()[i].getNom() %></li>
                <% } %>
            </ul>
        </div>

        <div id="formulaire-container">
            <h2>Ajouter MP</h2>
            <form action="InsertMpLook" method="get">
                <label for="mp">Choisir Mp :</label>
                <input type="hidden" value="<%= look.getIdLook() %>" name="idLook">
                <select name="idMp">
                    <% for(int i=0;i<mps.length;i++) { %>
                        <option value="<%= mps[i].getIdMp() %>"><%= mps[i].getNom() %></option>
                    <% } %>
                </select>
                <br><br>
                <button type="submit">Envoyer</button>
            </form>
        </div>
    </main>
    </body>
</html>
