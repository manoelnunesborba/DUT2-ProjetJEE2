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
		//Class.forName("com.mysql.cj.jdbc.Driver");
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
}

	private MediathequeData() {

	}

	// renvoie la liste de tous les documents disponibles de la m�diath�que
	@Override
	public List<mediatek2022.Document> tousLesDocumentsDisponibles() {
		synchronized (this){
			ArrayList<mediatek2022.Document> dispo = new ArrayList<>();
			try {
				this.c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","");

				PreparedStatement stmt = c.prepareStatement("SELECT * FROM document");
				ResultSet tableResultat = stmt.executeQuery();
				if (!tableResultat.next())
					System.out.println("Aucun document");
				else do {

					Utilisateur usr= null;
					if(tableResultat.getInt("idUser")>=0){
						usr = getUser(tableResultat.getInt("idUser"));
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

	}
	//Pas besoin de syncro car elle est deja utiisé dans une fonction syncro
	private Utilisateur getUser(int id){
			User ts = null;
			if(id>=0){
				boolean hasacc=false;
				try {
					this.c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","");

					PreparedStatement stmt = c.prepareStatement("SELECT * FROM utilisateur WHERE idUser=?");
					stmt.setInt(1, id);
					ResultSet tableResultat = stmt.executeQuery();
					if (!tableResultat.next())
						System.out.println("aucun user");
					else{
						ts = new User(tableResultat.getInt("idUser"), tableResultat.getString("pseudo"), tableResultat.getBoolean("estBlibli"));
					}


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
		synchronized (this){
			User ts = null;
			boolean hasacc=false;
			try {
				this.c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","");

				//Statement requeteStatique = c.createStatement();
				PreparedStatement stmt = c.prepareStatement("SELECT * FROM utilisateur");
				ResultSet tableResultat = stmt.executeQuery();
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
	}

	// va r�cup�rer le document de num�ro numDocument dans la BD
	// et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		synchronized (this){
			Document doc = null;
			try {
				this.c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","");

				PreparedStatement stmt = c.prepareStatement("SELECT * FROM document WHERE idDoc= ?");
				stmt.setInt(1, numDocument);
				ResultSet tableResultat = stmt.executeQuery();
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

	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		synchronized (this){

			JSONObject jo = new JSONObject();
			Object[] options = (Object[]) args[3];
			for (int i = 0; i < options.length; i++) {
				String op = "option" + i;
				try {
					jo.put(op,options[i].toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			try {
				this.c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jee" ,"root","");
				PreparedStatement stmt = c.prepareStatement("INSERT INTO `document`(`Titre`, `Description`, `Auteur`, `idUser`, `options`) VALUES ( ?,?,?,-1 ,? )");
				stmt.setString(1, String.valueOf(args[0]));
				stmt.setString(2, String.valueOf(args[1]));
				stmt.setString(3, String.valueOf(args[2]));
				stmt.setString(4, jo.toString());
				stmt.executeUpdate();
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}


		}


	}

}
