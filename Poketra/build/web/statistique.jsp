<%@page import="model.Statistiqu"%>
<%@page import="java.util.Vector"%>
<%
    Vector<Statistiqu> v=(Vector<Statistiqu>) request.getAttribute("s");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="tableau.css" rel="stylesheet">
        <link href="style.css" rel="stylesheet">
        <script  src="chart.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <main>
            <div><a href="InitChoix"><button >Retour</button></a></div>
            <div id="corps">
                <div class="info_source">
                    <p></p>
                </div>
                <div class="detail">
                    <table id="t">
                        <tr>
                            <th>Poketra</th>
                            <th>Homme</th>
                            <th>Femme</th>
                        </tr>
                        <% for(int i=0;i<v.size();i++) { %>
                            <tr>
                                <td><%= v.get(i).getPoketra().getNom() %></td>
                                <td><%= v.get(i).getNbHomme() %></td>
                                <td><%= v.get(i).getNbFemme() %></td>
                                <td><a href="Statistique?idp=<%= v.get(i).getPoketra().getIdPoketra() %>&chart=1">Voir chart</a></td>
                            </tr>
                        <% } %>
                    </table>
                </div>
            </div>    
        </main>                       
    </body>
</html>
