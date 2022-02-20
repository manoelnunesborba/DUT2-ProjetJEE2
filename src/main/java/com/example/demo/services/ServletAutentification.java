package com.example.demo.services;

import com.example.demo.persistance.MediathequeData;

import java.io.*;
import java.sql.*;
import javax.inject.Inject;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Auth", value = "/auth")
public class ServletAutentification extends HttpServlet {
    private final MediathequeData MD;
    @Inject
    public ServletAutentification(MediathequeData md) {
        MD = md;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if(MD.getUser(request.getParameter("fname"),request.getParameter("fmdp"))!=null){
            response.sendRedirect("http://localhost:8080/demo_war/Home.jsp");
            out.println("<h1>Acceuil</h1>");
        }else{
            out.println("<h1>ERREUR</h1>");
        }
        out.println("</body></html>");
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