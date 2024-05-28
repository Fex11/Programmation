<%-- 
    Document   : formu
    Created on : 9 janv. 2024, 12:43:05
    Author     : fex
--%>

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
            <h2>Recherche par prix</h2>
            <form action="Prix" method="get">
                <label for="nom">prix 1 :</label>
                <input type="text" id="nom" name="p1" required>
                <label for="nom">prix 2 :</label>
                <input type="text" id="nom" name="p2" required>
                <br><br>
                <button type="submit">Envoyer</button>
            </form>
        </div>
        </main>
    </body>
</html>
