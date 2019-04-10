package dev.pizzeria;

import java.util.Scanner;

import dao.Pizzabdd;
import pizzeria.exception.SavePizzaException;
import pizzeria.exception.StockageException;
import pizzeria.service.MenuFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	public static void main(String[] args) {
		Pizzabdd pizza = new Pizzabdd();
		MenuFactory mf = new MenuFactory();
		Scanner sc = new Scanner(System.in);
		String n = "0";
		LOG.warn("L'application vient de démarrer.");
		LOG.error("error :");

		while (!n.equals("5")) {
			try {
				System.out.println(
						"***PIZZA ADMINISTRATION****\n 1. Lister les pizzas\n 2. Ajouter une nouvelle pizza\n 3. Mettre à jour une pizza\n 4. Supprimer une Pizza\n 5. Sortir");
				n = sc.nextLine();
				switch (n) {

				case "1":
					mf.create("Lister").executeUC(sc, pizza);
					break;

				case"2":
					mf.create("Ajouter").executeUC(sc, pizza);
					break;

				case "3":
					mf.create("Modifier").executeUC(sc, pizza);
					break;

				case "4":
					mf.create("Supprimer").executeUC(sc, pizza);				
					break;

				case "5":
					System.out.println("5. Sortir");
					break;
				default:
					System.out.println("Merci de choisir dans le menu");
				}

			}
		
		catch(NumberFormatException e) {
			n = "0";
			LOG.error(e.getMessage());

		}
		catch(SavePizzaException e) {
			System.out.println(e.getMessage());
			LOG.error(e.getMessage());

		}
		catch(StockageException e) {
			System.out.println(e.getMessage());
			LOG.error(e.getMessage());

		}
	}
		sc.close();
	}
}
