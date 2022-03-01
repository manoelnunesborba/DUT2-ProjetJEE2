package com.example.demo.persistance;

import mediatek2022.Utilisateur;

public class User implements Utilisateur {
    private int id;
    private String nom;
    private boolean estBlibli;

    public User(int i,String nom, boolean estBlibli) {
        id=i;
        this.nom = nom;
        this.estBlibli = estBlibli;
    }
    public int getId(){
        return this.id;
    }
    @Override
    public String name() {
        return this.nom;
    }

    @Override
    public boolean isBibliothecaire() {
        return this.estBlibli;
    }

    @Override
    public Object[] data() {
        return new Object[0];
    }
}
