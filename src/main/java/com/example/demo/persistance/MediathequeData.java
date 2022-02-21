package com.example.demo.persistance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mediatek2022.*;

// classe mono-instance  dont l'unique instance est connue de la m�diatheque
// via une auto-d�claration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
	static Connection c;



// Jean-Fran�ois Brette 01/01/2018
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
}

	private MediathequeData() {


	}

	// renvoie la liste de tous les documents disponibles de la m�diath�que
	@Override
	public List<mediatek2022.Document> tousLesDocumentsDisponibles() {
		ArrayList<mediatek2022.Document> dispo = new ArrayList<>();
		try {
			this.c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","");

			Statement requeteStatique = c.createStatement();
			ResultSet tableResultat = requeteStatique.executeQuery("SELECT * FROM document");
			if (!tableResultat.next())
				System.out.println("Aucun document");
			else do {
				Utilisateur usr= null;
				if(tableResultat.getInt("idDoc")>0){
					usr = getUser(tableResultat.getInt("idDoc"));
				}

				dispo.add(new Document(tableResultat.getInt("idDoc"), tableResultat.getString("Titre"), tableResultat.getString("Description"), tableResultat.getString("Auteur"), usr, tableResultat.getString("options")));
			}

			while (tableResultat.next());
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}





		return dispo;
	}
	private Utilisateur getUser(int id){
		User ts = null;
		if(id>0){
			boolean hasacc=false;
			try {
				this.c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","");

				Statement requeteStatique = c.createStatement();
				String querry ="SELECT * FROM utilisateur WHERE = " + String.valueOf(id);
				ResultSet tableResultat = requeteStatique.executeQuery("SELECT * FROM utilisateur");
				if (!tableResultat.next())
					System.out.println("aucun user");
				else do {
					ts = new User(tableResultat.getInt("idUser"), tableResultat.getString("pseudo"), tableResultat.getBoolean("estBlibli"));
				}

				while (tableResultat.next());
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


		return ts;
	}
	// va r�cup�rer le User dans la BD et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		User ts = null;
		boolean hasacc=false;
		try {
			this.c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","");

			Statement requeteStatique = c.createStatement();
			ResultSet tableResultat = requeteStatique.executeQuery("SELECT * FROM utilisateur");
			if (!tableResultat.next())
				System.out.println("aucun user");
			else do {
				if(tableResultat.getString("pseudo").equals(login) && tableResultat.getString("mdp").equals(password))
					ts = new User(tableResultat.getInt("idUser"), login, tableResultat.getBoolean("estBlibli"));
			}

			while (tableResultat.next());
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ts;

	}

	// va r�cup�rer le document de num�ro numDocument dans la BD
	// et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Document getDocument(int numDocument) {

		return null;
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc... variable suivant le type de document
	}

}
