<%@page import="model.Mp"%>
<%@page import="model.Poketra"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    Mp[] lesMP=(Mp[])request.getAttribute("mps") ;
%>
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
            <h2>Achat Matiere premiere</h2>
            <form method="get" action="InsertAchat">
            Date : <input type="date" name="daty"> <br>
            Mp : 
            <select name="idmp">
                <% for(int i=0; i< lesMP.length; i++){ %>
                    <option value="<%=  lesMP[i].getIdMp() %>"><%= lesMP[i].getNom() %></option>
                <% } %>
            </select>
            Qte : 
            <input type="number" name="qte"> <br>
            PU :
            <input type="number" name="pu">
            <button type="submit">Envoyer</button>
            </form>
        </div>
        </main>
    </body>
</html>
