package com.example.demo.persistance;

import mediatek2022.Utilisateur;

import java.sql.*;

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
        synchronized (this){
            this.user = (User) utilisateur;
            try {
                Connection c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","");
                PreparedStatement stmt = c.prepareStatement("UPDATE document SET idUser= ? WHERE idDoc = ?");
                stmt.setInt(1,this.user.getId());
                stmt.setInt(2,this.getId());
                stmt.executeUpdate();
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void retour() {
        synchronized (this){
            user=null;
            try {
                Connection c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","");
                PreparedStatement stmt = c.prepareStatement("UPDATE document SET idUser= ? WHERE idDoc = ?");
                stmt.setInt(1,-1);
                stmt.setInt(2,this.getId());
                stmt.executeUpdate();
                c.close();
                this.user=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public User getUserLocationCours(){
        return this.user;
    }
    @Override
    public String toString() {
        return "Titre: '" + Titre +
                ", Description: '" + Descr +
                ", auteur: '" + auteur +
                ", options: '" + options;
    }
}
