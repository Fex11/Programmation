<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <p><strong>Erreur:</strong> <%= request.getAttribute("message") %></p>
    
    <a href="index2.html"><p>Retour</p></a>
</body>
</html>
