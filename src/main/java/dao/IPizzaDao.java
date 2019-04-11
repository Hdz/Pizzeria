package dao;

import java.util.List;

import pizzeria.exception.SavePizzaException;
import pizzeria.model.Pizza;

public interface IPizzaDao {
	
	List<Pizza> findAllNewPizzas();
	void saveNewPizza(Pizza pizza) throws SavePizzaException;
	void updatePizza(String codePizza, Pizza pizza);
	void deletePizza(String codePizza);
	Pizza findPizzaByCode(String codePizza);
	boolean pizzaExists(String codePizza);
	void initialiserBdd();
	void destroyEmFactory();
	


}
