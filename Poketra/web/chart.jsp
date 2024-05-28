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
        <script  src="chart.js"></script>
        <link href="style.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <h2 style="text-align: center"><%= v.get(0).getPoketra().getNom() %></h2>
        <div><canvas id="myChart" width="400" height="400"></canvas></div>
    </body>
</html>
<script>
        var ctx = document.getElementById('myChart').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: ['Femmes','Hommes'],
                datasets: [{
                    label: 'Répartition Hommes/Femmes',
                    data: [<%= v.get(0).getPourcFemme() %>,<%= v.get(0).getPourcHomme() %>],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.5)',
                        'rgba(54, 162, 235, 0.5)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false
            }
        });
</script>
