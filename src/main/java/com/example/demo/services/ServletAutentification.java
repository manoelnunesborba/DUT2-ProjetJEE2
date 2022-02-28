package com.example.demo.services;

import com.example.demo.persistance.MediathequeData;
import mediatek2022.Mediatheque;

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

        if(mdt.getUser(request.getParameter("fname"),request.getParameter("fmdp")) !=null){
            response.sendRedirect("Home.jsp");
        }
        //out.println("</body></html>");
    }
    public boolean ExiteTil(String ps, String mdp){
        boolean hasacc=false;
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","");
            Statement requeteStatique = c.createStatement();
             ResultSet tableResultat = requeteStatique.executeQuery("SELECT pseudo, mdp FROM user");
            if (!tableResultat.next())
                System.out.println("aucun user");
            else do {
                if(tableResultat.getString("pseudo").equals(ps) && tableResultat.getString("mdp").equals(mdp))
                    hasacc = true;
            }

            while (tableResultat.next());
             c.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return hasacc;

    }
    public void destroy() {
    }
}