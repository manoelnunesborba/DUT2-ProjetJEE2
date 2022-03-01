package com.example.demo.persistance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mediatek2022.*;
import org.json.JSONException;
import org.json.JSONObject;

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
				if(tableResultat.getInt("idUser")<0){
					Utilisateur usr= null;
					if(tableResultat.getInt("idDoc")>0){
						usr = getUser(tableResultat.getInt("idDoc"));
					}

					dispo.add(new Document(tableResultat.getInt("idDoc"), tableResultat.getString("Titre"), tableResultat.getString("Description"), tableResultat.getString("Auteur"), usr, tableResultat.getString("options")));
				}

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

		Document doc = null;
		try {
			this.c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","");

			Statement requeteStatique = c.createStatement();
			ResultSet tableResultat = requeteStatique.executeQuery("SELECT * FROM document WHERE idDoc= " + numDocument);
			if (!tableResultat.next())
				System.out.println("Aucun document");
			else{
				Utilisateur usr= null;
				if(tableResultat.getInt("idDoc")>0){
					usr = getUser(tableResultat.getInt("idDoc"));
				}
				doc = new Document(tableResultat.getInt("idDoc"), tableResultat.getString("Titre"), tableResultat.getString("Description"), tableResultat.getString("Auteur"), usr, tableResultat.getString("options"));
			}
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doc;
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		JSONObject jo = new JSONObject();
		for (int i = 3; i < args.length; i++) {
			String op = "option" + (3-i);
			try {
				jo.put(op,args[i]);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		try {
			this.c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","");

			String requette = "INSERT INTO `document`(`Titre`, `Description`, `Auteur`, `idUser`, `options`) VALUES ( '" + args[0] + "','" + args[1] + "','" + args[2] + "',-1 , '" +  jo + "' )";
			System.out.println(requette);
			Statement st = c.createStatement();
			st.executeUpdate(requette);
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}



	}

}
