<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="style.css" rel="stylesheet">
    <title>JSP Page</title>
</head>
<%@page import='model.*' %>
<%
    Metier [] lesMetiers= (Metier[])request.getAttribute("metiers");
 %>
<body>
    <main>
        <div><a href="index.jsp"><button >Index</button></a></div>
        <h2>Ajouter employe</h2>
        <div id="formulaire-container">
	<form action="InsertEmploye">
            <input type="text" name="nom" id="nom" placeholder="nom"> <br>
            <input type="date" name="dateEmbauche" placeholder="la date d'embauche">
            <select name="idMetier">
                <% for(int i=0; i< lesMetiers.length; i++){ %>
                    <option value="<%= lesMetiers[i].getIdMetier() %>"><%= lesMetiers[i].getNomMetier() %></option>
                <% } %>
            </select>
		<input type="submit" name="Valider" value="Valider">
	</form>
        </div>
     </main>
</body>
</html>