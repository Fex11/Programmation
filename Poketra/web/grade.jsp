<%@page import="model.*"%>
<%
    Grade[] grades=(Grade[]) request.getAttribute("grades");
    String message=(String) request.getAttribute("message");
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
       <header>
        <h1>Grade</h1>
    </header>

    <main>
        <div><a href="index.jsp"><button >Index</button></a></div>
        <div class="source">
            <h2>Liste Grade</h2>
            <div class="info_source">
                <p></p>
            </div>
            <div class="detail">
                <table id="t">
                    <tr>
                        <th>Grade</th>
                        <th>Experience</th>
                        <th>Augmentation</th>
                    </tr>
                    <% for(int i=0;i<grades.length;i++) { %>
                        <tr>
                            <td><%= grades[i].getDesignation() %></td>
                            <td><%= grades[i].getExperience() %></td>
                            <td><%= grades[i].getAugmentation() %></td>
                        </tr>
                    <% } %>
                </table>
            </div>
        </div>
        </div>
        <div id="formulaire-container">
            <div><p><%= message %></p></div>
            <h2>Ajouter grade</h2>
            <form action="InsertGrade" method="get">
                <label for="designation">Designation :</label>
                <input type="text" id="designation" name="designation" required>
                <label for="experience">Experience :</label>
                <input type="number" name="experience" required>
                <label for="augmentation">Augmentation :</label>
                <input type="number" name="augmentation" required>
                <br><br>
                <button type="submit">Envoyer</button>
            </form>
        </div>
    </main>
    </body>
</html>
