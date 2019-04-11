/**
 * 
 */
package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BddTest
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{

		// Etape 1 - Chargement du pilote
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// Etape 2 - Définition de la JDBC URL
		String jdbcUrl = "jdbc:mysql://localhost/";
		String userName = "root";
		String pw = "root";
		
		
		// Etape 3 - Création de la connexion
		Connection connexionBDD = DriverManager.getConnection(jdbcUrl, userName, pw);
		connexionBDD.setAutoCommit(false);
		
		Statement st = connexionBDD.createStatement();
		
		// Etape 4 - exécution  de la requête
		
		// Etape 5 - exploitation des résultats
		ResultSet rs = st.executeQuery("SELECT * FROM pizzas");
		while(rs.next()) {
			
			String titre = rs.getString("CODE_PAYS");
			String titre2 = rs.getString("nom_PAYS");
			
			System.out.println("CODE=" + titre + " NOM=" + titre2);
			
		}

		// Etape 6 - Fermeture des ressources
		rs.close();
		
		st.close();
		
		connexionBDD.close();

	}
}