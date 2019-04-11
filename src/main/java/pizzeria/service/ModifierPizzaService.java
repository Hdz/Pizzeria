package pizzeria.service;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.IPizzaDao;
import dao.Pizzabdd;
import pizzeria.exception.StockageException;
import pizzeria.exception.UpdatePizzaException;
import pizzeria.model.CategoriePizza;
import pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService {

	private static final Logger LOG = LoggerFactory.getLogger(Pizzabdd.class);

	@Override
	public void executeUC(Scanner scanner, IPizzaDao memPizza) throws StockageException{
		LOG.info("Mise a Jour d'une Pizza");
		ListerPizzasService l = new ListerPizzasService();
		l.executeUC(scanner, memPizza);
		LOG.info("Veuillez choisir le code de la pizza a modifier.");

		String code = scanner.nextLine();
		LOG.info("Veuillez saisir le nouveau code");

		String newCode = scanner.nextLine();
		LOG.info("Veuillez saisir le nouveau nom (sans espace)");

		String newNom = scanner.nextLine();
		String newPrixStr = scanner.nextLine();
		Double newPrix = Double.parseDouble(newPrixStr);
		if (memPizza.pizzaExists(code)) {
			throw new UpdatePizzaException("Le code rentre ne corresponde a aucune pizza !");
		}
		LOG.info("Veuillez saisir la nouvelle categorie !");
		String newCat = scanner.nextLine();
		CategoriePizza cat = CategoriePizza.valueOf(newCat);
		memPizza.updatePizza(code, new Pizza(newCode, newNom, newPrix, cat));
	}

}
