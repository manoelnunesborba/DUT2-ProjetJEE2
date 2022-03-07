<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="./style.css">
    <title>JSP - Hello World</title>
</head>
<body>

<br/>
<form action="auth" method="POST">
    <h1><%= "Se connecter!" %></h1>
    <label for="fname">Nom</label><br>
    <input name="fname" type="text" id="fname" value=""><br><br>
    <label for="lmdp">Mot de passe:</label><br>
    <input name="fmdp" type="text" id="lmdp" value=""><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>