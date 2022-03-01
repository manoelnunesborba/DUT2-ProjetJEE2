package com.example.demo.persistance;

import mediatek2022.Utilisateur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Document implements mediatek2022.Document {
    private int id;
    private String Titre;
    private String Descr;
    private String auteur;
    private User user;
    private String options;

    public Document(int id, String titre, String descr, String auteur, Utilisateur user, String options) {
        this.id = id;
        Titre = titre;
        Descr = descr;
        this.auteur = auteur;
        this.user = (User) user;
        this.options = options;
    }

    public Document(String titre, String descr, String auteur, Utilisateur user, String options) {
        Titre = titre;
        Descr = descr;
        this.auteur = auteur;
        this.user = (User) user;
        this.options = options;
    }
    public int getId(){
        return this.id;
    }
    @Override
    public boolean disponible() {
        return user==null;
    }

    @Override
    public void emprunt(Utilisateur utilisateur) throws Exception {
        this.user = (User) utilisateur;
        try {
            Connection c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","");
            String requette = "UPDATE document SET idUser= " + this.user.getId() + " WHERE idDoc = " + this.getId();
            System.out.println(requette);
            Statement st = c.createStatement();
            st.executeUpdate(requette);
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void retour() {
        user=null;
    }

    @Override
    public String toString() {
        return "Titre: '" + Titre +
                ", Description: '" + Descr +
                ", auteur: '" + auteur +
                ", options: '" + options;
    }
}
