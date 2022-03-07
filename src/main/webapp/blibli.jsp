<%@ page import="mediatek2022.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="./style.css">
    <title>Connecté as blibliotecaire</title>
</head>
<body>
<br>
<form action="addLivre" method="POST">
    <h1>Ajouter des livres</h1>
    <label for="titre">Titre:</label><br>
    <input type="text" id="titre" name="titre"><br><br>
    <label for="desc">Description</label><br>
    <input type="desc" id="desc" name="desc"><br><br>
    <label for="aut">Auteur:</label><br>
    <input type="aut" id="aut" name="aut"><br><br>

<%
    try{
        Utilisateur user= (Utilisateur) request.getSession().getAttribute("user");
        user.isBibliothecaire();  // Juste pour soulever l'execption s'iln'est pas connecté
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
            out.print("<label for='option" + i +"'>Option</label><br>");
            out.print("<input id='option" + i + "' name='option" + i+"'><br><br>");
        }
        out.print("<input style='display:none;' name='qte' id='qte' type=' number' value=" + nbOpt + "><br>");
        out.print("<a href='./blibli.jsp?opt=" +  (nbOpt+1) + "'>Ajouter option</a>");
        out.print("<a href='./blibli.jsp?opt=" +  (nbOpt-1) + "'>Suprimer dernière option</a>");


    }catch(NullPointerException e){
        response.sendRedirect("./index.jsp");
    }

%>
    <input type="submit" value="Submit">
    <a href="./deco">Se deconnecter</a>
</form>

</body>
</html>
