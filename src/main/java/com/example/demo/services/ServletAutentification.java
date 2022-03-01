package com.example.demo.services;

import com.example.demo.persistance.MediathequeData;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import java.io.*;
import java.sql.*;
import javax.inject.Inject;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Auth", value = "/auth")
public class ServletAutentification extends HttpServlet {

    //@Inject
    //MediathequeData MD;

    Mediatheque mdt = Mediatheque.getInstance();

    public ServletAutentification() {
        try {
            Class.forName(MediathequeData.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Utilisateur usr = mdt.getUser(request.getParameter("fname"),request.getParameter("fmdp"));
        if( usr !=null){
            HttpSession session=request.getSession();
            session.setAttribute("user",usr);
            if ((usr.isBibliothecaire())) {
                response.sendRedirect("blibli.jsp");
            } else {
                response.sendRedirect("Home.jsp");
            }
        }
        //out.println("</body></html>");
    }

    public void destroy() {
    }
}