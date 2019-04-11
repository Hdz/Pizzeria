package dao;

import pizzeria.model.Pizza;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pizzeria.exception.SavePizzaException;
import pizzeria.model.*;

public class PizzaMemDao implements IPizzaDao {

	private ArrayList<Pizza> pizzas = new ArrayList<>();
	private static final Logger LOG = LoggerFactory.getLogger(Pizzabdd.class);


	// 1 Instanciation des pizzas, on créer un tableau avec 100 entr�ees, pour ajouter les pizzas que l'on veut

	public PizzaMemDao() {
		Pizza p1 = new Pizza(0,"PEP","Pepperoni",12.50,CategoriePizza.SANS_VIANDE);
		Pizza p2 = new Pizza(1,"MAR","Margherita",14.00,CategoriePizza.SANS_VIANDE);
		Pizza p3 = new Pizza(2,"REI","La Reine",11.50,CategoriePizza.VIANDE);
		Pizza p4 = new Pizza(3,"FRO","La 4 fromages",12.00,CategoriePizza.SANS_VIANDE);
		Pizza p5 = new Pizza(4,"CAN","La Cannibale",12.50,CategoriePizza.VIANDE);
		Pizza p6 = new Pizza(5,"SAV","La Savoyarde",13.00,CategoriePizza.VIANDE);
		Pizza p7 = new Pizza(6,"ORI","L'Orientale",13.50,CategoriePizza.VIANDE);
		Pizza p8 = new Pizza(7,"IND","L'Indienne",14.00,CategoriePizza.VIANDE); 
		pizzas.add(p1);
		pizzas.add(p2);
		pizzas.add(p3);
		pizzas.add(p4);
		pizzas.add(p5);
		pizzas.add(p6);
		pizzas.add(p7);
		pizzas.add(p8);

	}
	//2 Méthode qui va retourner la liste de pizzas
	@Override
	public ArrayList<Pizza> findAllNewPizzas() {
		return this.pizzas;
	}

	//3 Méthode qui va sauvegarder une nouvelle pizza

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		if (pizza.getCode().length() < 3) {
			pizzas.add(pizza);
		} else {
			throw new SavePizzaException("Le code ne doit pas être inférieur à 3");
		}
	}


	// 4 Méthode qui va mettre � jour une pizza dans la liste.
	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		for(Pizza pi : pizzas) {
			if(pi != null && pi.getCode().equals(codePizza)) {
				pi = pizza;
			}
		}
	} 

	// 5 M�thode qui va supprimer la pizza
	@Override
	public void deletePizza(String codePizza) {
		for(Pizza pi : pizzas) {
			if(pi != null && pi.equals(findPizzaByCode(codePizza))){
				pi=null;
			}
		}
		
		LOG.info("pizza supprimé !");
		}
	

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		for(Pizza pi : pizzas) {
			if(pi != null && pi.getCode().equals(codePizza)) {
				return pi;
			}
		}
		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		boolean b =false;
		for(Pizza pi : pizzas) {
			if(pi != null && pi.getCode().equals(codePizza)) {
				b = true;
				break;
			}


		}
		return b;
	}
	@Override
	public void initialiserBdd() {
		
	}
	@Override
	public void destroyEmFactory() {
		
	}

}