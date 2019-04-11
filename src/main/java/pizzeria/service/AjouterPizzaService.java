package pizzeria.service;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.IPizzaDao;
import dao.Pizzabdd;
import pizzeria.model.CategoriePizza;
import pizzeria.model.Pizza;
import pizzeria.exception.*;

public class AjouterPizzaService extends MenuService{
	
	private static final Logger LOG = LoggerFactory.getLogger(Pizzabdd.class);

	@Override
	public void executeUC(Scanner scanner, IPizzaDao memPizza) throws StockageException {


		LOG.info("Ajout d'une nouvelle pizza");
		boolean except = false;
		String code = "";
		while (!except) {
			LOG.info("Veuillez saisir le code :");

			code = scanner.nextLine();
			if (code.length() != 3) {
				LOG.debug("Le code doit etre de 3 lettres !");

				continue;
			} else if (memPizza.pizzaExists(code)) {
				LOG.debug("Le code saisie existe deja ! ");

				continue;
			} else {
				except = true;
			}

			LOG.info("Veuillez saisir le nom (sans espace) :");

			String nom = scanner.nextLine();
			LOG.info("Veuillez saisir le prix :");
			String prixstr = scanner.nextLine();
			double prix = Double.parseDouble(prixstr);
			if (prix < 0 || prix > 30) {
				throw new SavePizzaException("Donner un prix positif et inférieur à 30");
			}
			
			LOG.info("Veuillez choisir le categorie de pizza : Viande, Poisson ou Sans Viande");
			String cat = scanner.nextLine();
			CategoriePizza cate = CategoriePizza.recupCat(cat);
			Pizza nouv = new Pizza(code, nom, prix, cate);

			memPizza.saveNewPizza(nouv);


		}
	}
}
