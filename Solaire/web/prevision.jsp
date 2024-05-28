<%-- 
    Document   : prevision
    Created on : 13 déc. 2023, 03:00:44
    Author     : fex
--%>
<%@page import="model.Journee"%>
<%
    Journee journee= (Journee) request.getAttribute("journee");
%>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f8f8f8;
        margin: 0;
        padding: 0;
    }

    #corps {
        max-width: 800px;
        margin: 50px auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
    }

    h1 {
        color: #333;
        text-align: center;
    }

    .source {
        margin-top: 20px;
        padding: 20px;
        border: solid 1px #333;
        
    }

    h2 {
        color: #007bff;
    }

    .info_source p {
        margin: 5px 0;
        color: #555;
    }

    .detail {
        margin-top: 20px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 10px;
    }

    table, th, td {
        border: 1px solid #ddd;
    }

    th, td {
        padding: 10px;
        text-align: left;
    }

    th {
        background-color: #007bff;
        color: #fff;
    }

</style>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="corps">
            <h1><%= journee.getDate() %> </h1>
            <% for(int i=0;i<journee.getSources().length;i++) { %>
                <div class="source">
                    <h2><%= journee.getSources()[i].getNomSource() %></h2>
                    <div class="info_source">
                        <p> Puissance maximum panneau : <%= journee.getSources()[i].getPuissanceMaxPanneau() %> W</p>
                        <p> Capacite batterie : <%= journee.getSources()[i].getCapaciteBatterie() %> W</p>
                        <p> Nb etudiants AM : <%= journee.getSources()[i].getNbEtudiantAM() %></p>
                        <p> Nb etudiants PM : <%= journee.getSources()[i].getNbEtudiantPM() %></p>
                        <p> Conso moyenne par etudiant : <%= journee.getSources()[i].getConso() %> W</p>
                        <p> Estimation heure de coupure : <%= journee.getSources()[i].getHeureCoupure() %></p>
                    </div>
                    <div class="detail">
                        <table id="t">
                            <tr>
                                <th>Heure</th>
                                <th>Luminosite</th>
                                <th>Consomation</th>
                                <th>Puissance panneau</th>
                                <th>Batterie restant</th>
                            </tr>
                            <% for(int j=0;j<journee.getSources()[i].getDetails().length;j++) { %>
                                <tr>
                                    <td><%= journee.getSources()[i].getDetails()[j].getHeure() %></td>
                                    <td><%= journee.getSources()[i].getDetails()[j].getLuminosite() %></td>
                                    <td><%= journee.getSources()[i].getDetails()[j].getConsoParheure() %> W</td>
                                    <td><%= journee.getSources()[i].getDetails()[j].getPuissancePanneau() %> W</td>
                                    <td><%= journee.getSources()[i].getDetails()[j].getCapaciteBatterieRestant() %> W</td>
                                </tr>
                            <% } %>
                        </table>
                    </div>
                </div>
            <% } %>
        </div>
    </body>
</html>
