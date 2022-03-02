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

<%
    int nbOpt;
    try{
        nbOpt = Integer.parseInt(request.getParameter("opt"));
        if(nbOpt <= 1){
            throw new NumberFormatException();
        }
    }catch (NumberFormatException e){
        nbOpt=1;
    };
    for (int i = 0; i < nbOpt; i++) {
        out.print("<label for='option" + i +"'>Option</label>");
        out.print("<input id='option" + i + "' name='option" + i+"'>");
    }
    out.print("<input style='display:none;' name='qte' id='qte' type=' number' value=" + nbOpt + ">");
    out.print("<a href='./blibli.jsp?opt=" +  (nbOpt+1) + "'>Ajouter option</a>");
    out.print("<a href='./blibli.jsp?opt=" +  (nbOpt-1) + "'>Suprimer dernière option</a>");


%>
    <input type="submit" value="Submit">
</form>
</body>
</html>
