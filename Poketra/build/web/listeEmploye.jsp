<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet">
        <link href="tableau.css" rel="stylesheet">
        <title>JSP Page</title>
</head>
<%@page import='model.*' %>
<%
    Employe [] lesEmployes=(Employe [])request.getAttribute("Employes");
%>
<body>
    <main>
    <div><a href="index.jsp"><button >Index</button></a></div>
    <div id="corps">
        <div class="detail">
    <table id="t">
        <tr>
            <th>nom employe</th>
            <th>date embauche</th>
            <th>metier</th>
            <th>grade</th>
            <th>salaire</th>
            <th>experience</th>
        </tr>
        <% for(int i=0; i< lesEmployes.length; i++){ %>
        <tr>
            <td><%= lesEmployes[i].getNom() %></td>
            <td><%= lesEmployes[i].getDaty() %></td>
            <td><%= lesEmployes[i].getMetier().getNomMetier() %></td>
            <td><%= lesEmployes[i].getGrade().getDesignation() %></td>
            <td><%= lesEmployes[i].getSalaire() %></td>
            <td><%= lesEmployes[i].getExperience() %></td>
        </tr>
        <% } %>
    </table>
    </div>
    </div>
    </main>
</body>
</html>