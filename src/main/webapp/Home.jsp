<%@ page import="mediatek2022.Mediatheque" %>
<%@ page import="mediatek2022.Document" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.persistance.MediathequeData" %>
<%@ page import="com.example.demo.persistance.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
                com.example.demo.persistance.Document test = (com.example.demo.persistance.Document) doc.get(i);

                if(doc.get(i).disponible()){
                    out.print("<p></p>");
                    out.print(doc.get(i));
                    out.println("<a href='./location?num=" +  test.getId() + "'>Louez moi</a>");
                }
            }
        }else if(param!= null && param.equals("rendre")) {
            for (int i = 0; i < doc.size(); i++) {
                com.example.demo.persistance.Document test = (com.example.demo.persistance.Document) doc.get(i);
                User aa = (User)session.getAttribute("user");
                if( test.getUserLocationCours()!=null && test.getUserLocationCours().getId()  == aa.getId()){
                    out.print("<p></p>");
                    out.print(doc.get(i));
                    out.println("<a href='./retour?num=" +  test.getId() + "'>retourner</a>");
                }
            }

        }
    }catch (NullPointerException e){
        response.sendRedirect("./index.jsp");
    }


%>
</body>
</html>
