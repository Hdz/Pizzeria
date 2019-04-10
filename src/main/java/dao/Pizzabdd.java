package dao;

import pizzeria.exception.Mysqlexception;
import pizzeria.exception.PersonnalSqlException;
import pizzeria.model.CategoriePizza;
import pizzeria.model.Pizza;
import java.io.FileInputStream;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

public class Pizzabdd implements IPizzaDao {


	String jdbcUrl = null;
	String userName = null;
	String password = null;
	Connection connexionBDD = null;
	PreparedStatement st = null;
	FileInputStream fis = null;
	private static final Logger LOG = LoggerFactory.getLogger(Pizzabdd.class);

	
	private void beginConnexionBdd ()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			GestionFichier file = new GestionFichier ("jdbc.properties");

			List <String> listString = file.lecture();

			jdbcUrl = listString.get(0).split(";")[1];
			userName = listString.get(1).split(";")[1];
			password = listString.get(2).split(";")[1];


			connexionBDD = DriverManager.getConnection(jdbcUrl, userName, password);
			connexionBDD.setAutoCommit(false);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			LOG.error(e.getMessage());

		}
		catch (SQLException e)
		{
			throw new Mysqlexception ("La connexion à la base de donnée ne s'est pas déroulé correctement", e);

		}



	}

	private void closeConnexionBdd ()
	{
		try
		{
			st.close ();
			connexionBDD.close ();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override	
	public ArrayList<Pizza> findAllNewPizzas()
	{
		ArrayList <Pizza> tabPizza = new ArrayList <> ();

		try
		{
			beginConnexionBdd ();

			st = connexionBDD.prepareStatement("SELECT * FROM pizzas");
			ResultSet result = st.executeQuery();
			System.out.println(result.getFetchSize());

			while(result.next()) 
			{		
				int id = result.getInt("id");
				String code = result.getString("code");
				String libelle = result.getString("libelle");
				double prix = result.getDouble("prix");
				String categorie = result.getString("categorie");

				tabPizza.add(new Pizza (id, code, libelle, prix, CategoriePizza.valueOf(categorie.toUpperCase())));	

				Pizza.setNbPizza(id);
			}

			closeConnexionBdd ();
		}
		catch (SQLException e)
		{
			throw new Mysqlexception ("La récupération de la liste de pizza dans la base de donnée ne s'est pas déroulé correctement", e);

		}

		return tabPizza;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		try
		{
			beginConnexionBdd ();

			st = connexionBDD.prepareStatement("INSERT INTO pizzas (code, libelle, prix, categorie) values (?, ?, ?, ?);");
			st.setString(1, pizza.getCode());
			st.setString(2, pizza.getLibelle());
			st.setDouble(3, pizza.getPrix());
			st.setString(4, pizza.getcP().getNom());
			st.executeUpdate();

			connexionBDD.commit();

			closeConnexionBdd ();
		}
		catch (SQLException e)
		{
			throw new PersonnalSqlException ("Votre ajout d'une pizza dans la base de donnée ne s'est pas déroulé correctement", e);
		}
		finally
		{
			closeConnexionBdd ();
			
		}
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		if (pizzaExists (codePizza))
		{
			beginConnexionBdd ();

			try
			{				
				st = connexionBDD.prepareStatement("UPDATE pizzas SET code = ?, libelle = ?, prix = ?, categorie = ? WHERE code = ?;");
				st.setString(1, pizza.getCode());
				st.setString(2, pizza.getLibelle());
				st.setDouble(3, pizza.getPrix());
				st.setString(4, pizza.getcP().getNom());
				st.setString(5, codePizza);
				st.executeUpdate();

				connexionBDD.commit();
			}
			catch (SQLException e)
			{
				throw new PersonnalSqlException ("Votre modification d'une pizza dans la base de donnée ne s'est pas déroulé correctement", e);
			}
			finally
			{
				closeConnexionBdd ();
			}

			closeConnexionBdd ();
		}
	}

	@Override
	public void deletePizza(String codePizza) {
		if (pizzaExists (codePizza))
		{
			beginConnexionBdd ();

			try
			{
				st = connexionBDD.prepareStatement("DELETE FROM pizzas WHERE code = ?;");
				st.setString(1, codePizza);
				st.executeUpdate();

				connexionBDD.commit();
			}
			catch (SQLException e)
			{
				throw new PersonnalSqlException ("Votre suppression d'une pizza dans la base de donnée ne s'est pas déroulé correctement", e);
			}
			finally
			{
				closeConnexionBdd ();
			}

			closeConnexionBdd ();
		}
	}


	@Override
	public Pizza findPizzaByCode(String codePizza) {
		Pizza pizza = null;

		beginConnexionBdd ();

		try
		{
			st = connexionBDD.prepareStatement("SELECT * FROM pizzas WHERE code = ?");
			st.setString(1, codePizza);
			ResultSet result = st.executeQuery();

			result.next();
			int id = result.getInt("id");
			String code = result.getString("code");
			String libelle = result.getString("libelle");
			double prix = result.getDouble("prix");
			String categorie = result.getString("categorie");

			pizza = new Pizza (id, code, libelle, prix, CategoriePizza.valueOf(categorie.toUpperCase()));
		}
		catch (SQLException e)
		{
			throw new PersonnalSqlException ("Votre recherche d'une pizza dans la base de donnée ne s'est pas déroulé correctement", e);
		}
		finally
		{
			closeConnexionBdd ();
		}

		closeConnexionBdd ();
		return pizza;
	}

	@Override
	public boolean pizzaExists(String codePizza) {

		boolean retour = false;

		beginConnexionBdd ();

		try
		{
			st = connexionBDD.prepareStatement("SELECT * FROM pizzas WHERE code = ?");
			st.setString(1, codePizza);
			ResultSet result = st.executeQuery();

			int compteur = 0;

			while(result.next()) 
			{
				compteur++;
			}

			if (compteur == 1)
			{
				retour = true;
			}

			connexionBDD.commit();
		}
		catch (SQLException e)
		{
			throw new PersonnalSqlException ("Votre recherche d'une pizza dans la base de donnée ne s'est pas déroulé correctement", e);
		}
		finally
		{
			closeConnexionBdd ();
		}

		closeConnexionBdd ();
		return retour;
	}

}
