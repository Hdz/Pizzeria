package pizzeria.service;
import java.util.Scanner;

import dao.IPizzaDao;
import dao.PizzaMemDao;
import pizzeria.exception.StockageException;
import pizzeria.exception.UpdatePizzaException;
import pizzeria.model.CategoriePizza;
import pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao memPizza) throws StockageException{
		System.out.println("Mise � jour d'une pizza");
		ListerPizzasService l = new ListerPizzasService();
		l.executeUC(scanner, memPizza);
		System.out.println("Veuillez choisir le code de la pizza � modifier.");
		String code = scanner.nextLine();
		System.out.println("Veuillez saisir le nouveau code");
		String newCode = scanner.nextLine();
		System.out.println("Veuillez saisir le nouveau nom (sans espace)");
		String newNom = scanner.nextLine();
		String newPrixStr = scanner.nextLine();
		Double newPrix = Double.parseDouble(newPrixStr);
		if (memPizza.pizzaExists(code)) {
			throw new UpdatePizzaException("Le code rentr� ne corresponde � aucune pizza !");
		}
		System.out.println("Veuillez saisir la nouvelle categorie !");
		String newCat = scanner.nextLine();
		CategoriePizza cat = CategoriePizza.valueOf(newCat);
		memPizza.updatePizza(code, new Pizza(newCode, newNom, newPrix, cat));
	}

}
