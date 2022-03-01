<%--
  Created by IntelliJ IDEA.
  User: papaj
  Date: 28/02/2022
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connecté as blibliotecaire</title>
</head>
<body>
<h1>Ajouter des livres</h1>
<form action="addLivre" method="POST">
    <label for="titre">Titre:</label>
    <input type="text" id="titre" name="titre">
    <label for="desc">Description</label>
    <input type="desc" id="desc" name="desc">
    <label for="aut">Auteur:</label>
    <input type="aut" id="aut" name="aut">
    <label for="option">Option</label>
    <input type="option" id="option" name="option">
    <input type="submit" value="Submit">
</form>
</body>
</html>
