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

        if(req.getParameter("titre")!=null && req.getParameter("type")!=null && req.getParameter("desc")!=null && req.getParameter("aut")!=null && req.getParameter("qte")!=null){
            int nbOpt;
            try{
                nbOpt = Integer.parseInt(req.getParameter("qte"));
                if(nbOpt <= 1){
                    throw new NumberFormatException();
                }
            }catch (NumberFormatException e){
                nbOpt=1;
            };
            Object[] options = new Object[nbOpt];
            for (int i = 0; i < nbOpt; i++) {
                options[i] = req.getParameter("option" + i);
                System.out.println(req.getParameter("option" + i));
            }
            mdt.ajoutDocument(Integer.parseInt(req.getParameter("type")),req.getParameter("titre"),req.getParameter("desc"),req.getParameter("aut"),options);
            resp.sendRedirect("blibli.jsp");
        }
    }
}
