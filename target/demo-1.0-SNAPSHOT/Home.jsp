<%@ page import="mediatek2022.Mediatheque" %>
<%@ page import="mediatek2022.Document" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.persistance.MediathequeData" %>
<%@ page import="mediatek2022.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Accueil</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body>
    <div class="container col-10 col-lg-8 col-xl-6">

        <div class="row my-5">
            <h1 class="h1">Accueil</h1>
        </div>

        <div class="row text-justify">
            <a href="Home.jsp?mode=voir" class="btn btn-outline-secondary col-3">Voir tous les livres disponibles</a>
            <a href="Home.jsp?mode=rendre" type="" class="btn btn-outline-secondary col-3">Rendre un livre</a>
            <a href="./deco" class="btn btn-outline-danger col-3">Se déconnecter</a>
        </div>

        <div class="row">

            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Titre</th>
                        <th scope="col">Type</th>
                        <th scope="col">Description</th>
                        <th scope="col">Options</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                            try {
                                List<Document> doc = Mediatheque.getInstance().tousLesDocumentsDisponibles();
                                String param = request.getParameter("mode");
                                if(param!= null && param.equals("voir")){
                                    for (int i = 0; i < doc.size(); i++) {

                                        if(doc.get(i).disponible()){
                                            out.print("<tr>");
                                            out.print(doc.get(i));
                                                request.getSession().setAttribute(String.valueOf(i), doc.get(i));
                                                out.println("<td class='col-2'><a href='./location?num=" + i + "' class='btn btn-outline-secondary'>Louez-moi</a></td>");
                                                out.print("</tr>");
                                        }
                                    }
                                }else if(param!= null && param.equals("rendre")) {
                                    Utilisateur user = (Utilisateur)request.getSession().getAttribute("user");
                                    for (int i = 0; i < user.data().length; i++) {
                                            Document docu = (Document) user.data()[i];
                                            out.print("<tr>");
                                                    out.print(docu);
                                                    request.getSession().setAttribute(String.valueOf(i), docu);
                                                    out.println("<td class='col-2'><a href='./retour?num=" + i + "' class='btn btn-outline-secondary'>Retourner</a></td>");
                                                    out.print("</tr>");
                                    }

                                }
                            }catch (NullPointerException e){
                                response.sendRedirect("./index.jsp");
                            }


                        %>
                </tbody>
            </table>
        </div>
    </div>
</body>

</html>

