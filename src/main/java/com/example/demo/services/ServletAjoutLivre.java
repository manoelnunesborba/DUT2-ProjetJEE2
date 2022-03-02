package com.example.demo.services;

import com.example.demo.persistance.MediathequeData;
import mediatek2022.Mediatheque;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addLivre", value = "/addLivre")
public class ServletAjoutLivre extends HttpServlet {
    Mediatheque mdt = Mediatheque.getInstance();

    public ServletAjoutLivre() {
        try {
            Class.forName(MediathequeData.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("titre")!=null && req.getParameter("desc")!=null && req.getParameter("aut")!=null && req.getParameter("option")!=null){
            mdt.ajoutDocument(0,req.getParameter("titre"),req.getParameter("desc"),req.getParameter("aut"),req.getParameter("option"));
            resp.sendRedirect("blibli.jsp");
        }
    }
}