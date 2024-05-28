<%-- 
    Document   : choixDate
    Created on : 13 déc. 2023, 02:43:45
    Author     : fex
--%>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
     }

    #formulaire {
        max-width: 600px;
        margin: 50px auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h2 {
        color: #333;
    }

    form {
        display: flex;
        flex-direction: column;
    }

    input[type="date"] {
        padding: 10px;
        margin-bottom: 15px;
    }

    input[type="submit"] {
        background-color: #007bff;
        color: #fff;
        padding: 10px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
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
        <div id="formulaire">
            <h2>Choisissez une date</h2>
            <form method="GET" action="Prevision">
                <input type="date" name="daty">
                <input type="submit" value="valider">
            </form>
        </div>
    </body>
</html>
