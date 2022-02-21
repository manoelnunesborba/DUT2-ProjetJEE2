<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Se connecter!" %>
</h1>
<br/>
<form action="auth" method="POST">
    <label for="fname">Nom</label><br>
    <input name="fname" type="text" id="fname" value=""><br><br>
    <label for="lmdp">Mot de passe:</label><br>
    <input name="fmdp" type="text" id="lmdp" value=""><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>