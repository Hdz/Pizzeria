package pizzeria.service;
import java.util.Scanner;

import dao.IPizzaDao;
import dao.PizzaMemDao;
import pizzeria.model.CategoriePizza;
import pizzeria.model.Pizza;
import pizzeria.exception.*;

public class AjouterPizzaService extends MenuService{
	@Override
	public void executeUC(Scanner scanner, IPizzaDao memPizza) throws StockageException {


		System.out.println("Ajout d'une nouvelle pizza");

		boolean except = false;
		String code = "";
		while (except == false) {
			System.out.println("Veuillez saisir le code :");	
			code = scanner.nextLine();
			if (code.length() != 3) {
				System.out.println("Le code doit etre de 3 lettres !");
				continue;
			} else if (memPizza.pizzaExists(code) != false) {
				System.out.println("Le code saisie existe déjà ! ");
				continue;
			} else {
				except = true;
			}

			System.out.println("Veuillez saisir le nom (sans espace) :");
			String nom = scanner.nextLine();
			System.out.println("Veuillez saisir le prix :");
			String prixstr = scanner.nextLine();
			double prix = Double.parseDouble(prixstr);
			if (prix < 0 || prix > 30) {
				throw new SavePizzaException("Donner un prix positif et inférieur à 30");
			}

			System.out.println("Veuillez choisir le categorie de pizza : Viande, Poisson ou Sans Viande");
			String cat = scanner.nextLine();
			CategoriePizza cate = CategoriePizza.recupCat(cat);
			Pizza nouv = new Pizza(code, nom, prix, cate);

			memPizza.saveNewPizza(nouv);


		}
	}
}
