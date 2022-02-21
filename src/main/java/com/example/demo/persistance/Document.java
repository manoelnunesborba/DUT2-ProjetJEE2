package com.example.demo.persistance;

import mediatek2022.Utilisateur;

public class Document implements mediatek2022.Document {
    private int id;
    private String Titre;
    private String Descr;
    private String auteur;
    private Utilisateur user;
    private String options;

    public Document(int id, String titre, String descr, String auteur, Utilisateur user, String options) {
        this.id = id;
        Titre = titre;
        Descr = descr;
        this.auteur = auteur;
        this.user = user;
        this.options = options;
    }

    @Override
    public boolean disponible() {
        return user==null;
    }

    @Override
    public void emprunt(Utilisateur utilisateur) throws Exception {
        this.user = (User) utilisateur;
    }

    @Override
    public void retour() {
        user=null;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", Titre='" + Titre + '\'' +
                ", Descr='" + Descr + '\'' +
                ", auteur='" + auteur + '\'' +
                ", user=" + user +
                ", options='" + options + '\'' +
                '}';
    }
}
