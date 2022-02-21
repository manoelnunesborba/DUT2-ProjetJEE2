<%@ page import="mediatek2022.Mediatheque" %><%--
  Created by IntelliJ IDEA.
  User: Carpentier
  Date: 21/02/2022
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%= Mediatheque.getInstance().tousLesDocumentsDisponibles() %>
</body>
</html>
