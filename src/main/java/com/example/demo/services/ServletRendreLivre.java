package com.example.demo.services;

import com.example.demo.persistance.MediathequeData;
import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "retour", value = "/retour")
public class ServletRendreLivre extends HttpServlet {

    Mediatheque mdt = Mediatheque.getInstance();

    public ServletRendreLivre() {
        try {
            Class.forName(MediathequeData.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utilisateur user  = (Utilisateur)req.getSession().getAttribute("user");
        int n = Integer.parseInt(req.getParameter("num"));
        mediatek2022.Document doc= (Document) req.getSession().getAttribute(String.valueOf(n));
        resp.getOutputStream().print("Vous souhaitez louer le document " + doc);
        try {
            doc.retour();
            resp.sendRedirect("Home.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
