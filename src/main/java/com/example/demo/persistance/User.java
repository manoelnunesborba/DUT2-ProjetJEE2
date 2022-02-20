package com.example.demo.persistance;

import mediatek2022.Utilisateur;

public class User implements Utilisateur {
    private String nom;
    private boolean estBlibli;

    public User(String nom, boolean estBlibli) {
        this.nom = nom;
        this.estBlibli = estBlibli;
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
