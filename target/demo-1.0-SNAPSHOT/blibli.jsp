<%@ page import="mediatek2022.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>Nouveau document</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body>

  <div class="container">

    <div class="row mt-5">

      <form class="form-vertical col-10 col-lg-6 col-xl-4 mx-auto border rounded mt-5" action="addLivre" method="post">
        <fieldset>
          <legend class="font-weight-bold mt-3">Nouveau document</legend>

          <div class="form-group">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="type" id="livre" value="0" checked>
                <label class="form-check-label" for="livre">
                    Livre
                </label>
            </div>

            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="type" id="CD" value="1">
                <label class="form-check-label" for="CD">
                    CD
                </label>
            </div>
          </div>

          <div class="form-group">
            <label for="titre">Titre</label>
            <input name="titre" type="text" class="form-control" value="" placeholder="Ex : Harry Potter" required autofocus />
          </div>

          <div class="form-group">
            <label for="desc">Description</label>
            <input name="desc" type="text" class="form-control" value="" placeholder="Ex : Format poche" required />
          </div>

          <div class="form-group">
            <label for="aut">Auteur</label>
            <input name="aut" type="text" class="form-control" value="" placeholder="Ex : J. K. Rowling" required />
          </div>

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
                      out.print("<div class='form-group'><label for='option" + i +"'>Option " + i + "</label><br>");
                      out.print("<input class='form-control' id='option" + i + "' name='option" + i +"'/></div>");
                  }
                  out.print("<input style='display:none;' name='qte' id='qte' type=' number' value=" + nbOpt + "><br>");
                  out.print("<div class='form-group'><a href='./blibli.jsp?opt=" +  (nbOpt+1) + "' class='btn btn-outline-secondary col-12'/>Ajouter une option<a></div>");
                  out.print("<div class='form-group'><a href='./blibli.jsp?opt=" +  (nbOpt-1) + "' class='btn btn-outline-secondary col-12'/>Supprimer la dernière option</a></div>");


              } catch(NullPointerException e){
                  response.sendRedirect("./index.jsp");
              }

          %>

          <div class="form-group text-center my-4">
            <input type="submit" class="btn btn-success col-3" value="Ajouter" />
            <a href="./deco" class="btn btn-outline-danger col-5">Se déconnecter</a>
          </div>

        </fieldset>
      </form>

    </div>
  </div>
</body>

</html>
