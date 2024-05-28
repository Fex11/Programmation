<%-- 
    Document   : insertMpPoketra
    Created on : 19 déc. 2023, 12:53:02
    Author     : fex
--%>

<%@page import="model.Poketra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Poketra poketra=(Poketra) request.getAttribute("poketra");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            button {
                background-color: #333;
                color: #fff;
                padding: 10px;
                border: none;
                cursor: pointer;
            }
            /* Style de base pour la case à cocher */
            input[type="checkbox"] {
              /* Style de la case à cocher elle-même */
              margin-right: 5px;
            }

            /* Style de la case à cocher lorsqu'elle est cochée */
            input[type="checkbox"]:checked {
              color: #3CB371; /* Couleur de la case cochée */
            }
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            header {
                background-color: #333;
                color: #fff;
                padding: 10px;
                text-align: center;
            }

            main {
                max-width: 800px;
                margin: 20px auto;
                padding: 20px;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            #formulaire-container {
                border: 1px solid #ddd;
                padding: 15px;
                margin-top: 20px;
            }
        </style>
        <title>JSP Page</title>
    </head>
    <body>
        <main>
        <div><a href="InitPoketra"><button >Retour</button></a></div>
        <div id="formulaire-container">
            <h2>Matiere premiere utilise</h2>
            <form action="AjouterMpPoketra" method="get">
                    <input name="poketra" type="hidden" value="<%= poketra.getIdPoketra() %>" >
                    <% for(int i=0;i<poketra.getLook().getMps().length;i++) { %>
                        <label>
                            <%= poketra.getLook().getMps()[i].getNom() %>
                            <input type="checkbox" class="checkbox-custom" value="<%= poketra.getLook().getMps()[i].getIdMp() %>" name="mp">
                        </label>
                        <br>
                    <% } %>
                <br><br>
                <button type="submit">Envoyer</button>
            </form>
        </div>
        </main>
    </body>
</html>
