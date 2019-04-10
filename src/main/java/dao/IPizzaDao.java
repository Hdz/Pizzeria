package dao;

import java.util.ArrayList;

import pizzeria.exception.SavePizzaException;
import pizzeria.model.Pizza;

public interface IPizzaDao {
	
	ArrayList<Pizza> findAllNewPizzas();
	void saveNewPizza(Pizza pizza) throws SavePizzaException;
	void updatePizza(String codePizza, Pizza pizza);
	void deletePizza(String codePizza);
	Pizza findPizzaByCode(String codePizza);
	boolean pizzaExists(String codePizza);
	


}
