<%@page import="model.StockMp"%>
<%@page import="model.Poketra"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    StockMp [] listeStock=(StockMp []) request.getAttribute("listeStock");
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
        <div><a href="index.jsp"><button >Index</button></a></div>
        <div id="corps">
        <div class="detail">
            <table id="t">
                <tr>
                    <th>idMp</th>
                    <th>nom</th>
                    <th>quantite</th>
                </tr>

                <% for(int i=0;i< listeStock.length;i++){ %>
                    <tr>
                        <td> <%= listeStock[i].getIdMp() %> </td>
                        <td> <%= listeStock[i].getNom () %> </td>
                        <td> <%= listeStock[i].getQte () %> </td>
                    </tr>
                <% }%>
            </table>
        </div>
        </div>
        </main>
    </body>
</html>
