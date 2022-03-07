<%@ page import="mediatek2022.Mediatheque" %>
<%@ page import="mediatek2022.Document" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.persistance.MediathequeData" %>
<%@ page import="mediatek2022.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="./styleHome.css">
    <title>Home</title>
</head>
<body>

<h1>Nous proposons les services suivants :</h1>
<a href="Home.jsp?mode=voir">Voir tous les livres disponibles</a>
<a href="Home.jsp?mode=rendre">Rendre un livre</a>
<%
    try {
        List<Document> doc = Mediatheque.getInstance().tousLesDocumentsDisponibles();
        String param = request.getParameter("mode");
        if(param!= null && param.equals("voir")){
            out.println("voir");
            for (int i = 0; i < doc.size(); i++) {

                if(doc.get(i).disponible()){
                    out.print("<p></p>");
                    out.print(doc.get(i));
                    request.getSession().setAttribute(String.valueOf(i), doc.get(i));
                    out.println("<a href='./location?num=" +  i + "'>Louez moi</a>");
                }
            }
        }else if(param!= null && param.equals("rendre")) {
            Utilisateur user = (Utilisateur)request.getSession().getAttribute("user");
            for (int i = 0; i < user.data().length; i++) {
                    Document docu = (Document) user.data()[i];
                    out.print("<p></p>");
                    out.print(docu);
                    request.getSession().setAttribute(String.valueOf(i), docu);
                    out.println("<a href='./retour?num=" +  i + "'>retourner</a>");

            }

        }
    }catch (NullPointerException e){
        response.sendRedirect("./index.jsp");
    }


%>
<a href="./deco">Se deconnecter</a>
</body>
</html>
