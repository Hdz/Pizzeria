package pizzeria.service;
import java.util.ArrayList;
import java.util.Scanner;

import dao.IPizzaDao;
import pizzeria.model.Pizza;

public class ListerPizzasService extends MenuService{

	@Override
	public void executeUC(Scanner scanner, IPizzaDao Pizzabdd) {

		System.out.println("Liste pizzas"); 	
		ArrayList<Pizza> pizzas = Pizzabdd.findAllNewPizzas();
		affiche(pizzas);
	}

	private static void affiche(ArrayList<Pizza> pizzas) {
		for (Pizza pi : pizzas) {
			if (pi != null) {
				System.out.println(pi);
			}

		}
	}

}