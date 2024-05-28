<%@page import="model.Poketra"%>
<%@page import="java.util.Vector"%>
<%
    Vector<Poketra> poketras=(Vector<Poketra>) request.getAttribute("poketras");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet">
        <link href="tableau.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <main>
            <div><a href="uuuu.jsp"><button >Retour</button></a></div>
            <div id="corps">
            <div class="detail">
                <table id="t">
                    <tr>
                        <th>Poketra</th>
                        <th>Taille</th>
                        <th>Benefice</th>
                    </tr>

                    <% for(int i=0;i< poketras.size();i++){ %>
                        <tr>
                            <td> <%= poketras.get(i).getNom() %>(<%= poketras.get(i).getCategorie().getCategorie() %> <%= poketras.get(i).getLook().getLook() %>) </td>
                            <td> <%= poketras.get(i).getTaille()[0].getTaille() %> </td>
                            <td> <%= poketras.get(i).getTaille()[0].getBenefice() %> </td>
                        </tr>
                    <% }%>
                </table>
            </div>
            </div>
        </main>
    </body>
</html>
