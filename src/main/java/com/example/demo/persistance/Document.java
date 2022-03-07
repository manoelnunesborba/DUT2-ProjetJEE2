package com.example.demo.persistance;

import mediatek2022.Utilisateur;

import java.sql.*;

public class Document implements mediatek2022.Document {
    private int id;
    private String Titre;
    private int type;
    private String Descr;
    private String auteur;
    private Utilisateur user;
    private String options;

    public Document(String titre, int type, String descr, String auteur, Utilisateur user, String options) {
        Titre = titre;
        this.type = type;
        Descr = descr;
        this.auteur = auteur;
        this.user = user;
        this.options = options;
    }

    public Document(int id, String titre, int type, String descr, String auteur, Utilisateur user, String options) {
        this.id = id;
        Titre = titre;
        this.type = type;
        Descr = descr;
        this.auteur = auteur;
        this.user = user;
        this.options = options;
    }

    private int getId(){
        return this.id;
    }
    @Override
    public boolean disponible() {
        return user==null;
    }

    @Override
    public void emprunt(Utilisateur utilisateur) throws Exception {
        synchronized (this){
            this.user = utilisateur;
            User tmp = (User) this.user;
            try {
                Connection c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","root");
                PreparedStatement stmt = c.prepareStatement("UPDATE document SET idUser= ? WHERE idDoc = ?");
                stmt.setInt(1,tmp.getId());
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
                Connection c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","root");
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

    @Override
    public String toString() {
        String type;

        if(this.type==0){
            type="Livre";
        }else{
            type="DVD";
        }
        return "<td>" + Titre + "</td>" +
                "<td>" + type + "</td>" +
                "<td>" + Descr + "</td>" +
                "<td>" + auteur + "</td>" +
                "<td>" + user + "</td>" +
                "<td>" + options + "</td>";
    }
}
