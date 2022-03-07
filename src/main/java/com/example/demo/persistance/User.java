package com.example.demo.persistance;

import mediatek2022.Utilisateur;

import java.sql.*;
import java.util.ArrayList;

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
        synchronized (this){
            ArrayList<Document> livresUser = new ArrayList<>();
            try {
                Connection c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","");
                PreparedStatement stmt = c.prepareStatement("SELECT * FROM document WHERE idUser = ?");
                stmt.setInt(1, this.id);
                ResultSet tableResultat = stmt.executeQuery();
                if (!tableResultat.next())
                System.out.println("Aucun document");
				else do {
                    livresUser.add(new Document(tableResultat.getInt("idDoc"), tableResultat.getString("Titre"), tableResultat.getString("Description"), tableResultat.getString("Auteur"), this, tableResultat.getString("options")));
                }while (tableResultat.next());
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return livresUser.toArray();
        }

    }
}
