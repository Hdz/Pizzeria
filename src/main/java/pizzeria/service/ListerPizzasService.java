package pizzeria.service;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.IPizzaDao;
import dao.Pizzabdd;
import pizzeria.model.Pizza;

public class ListerPizzasService extends MenuService{

	private static final Logger LOG = LoggerFactory.getLogger(Pizzabdd.class);
	
	@Override
	public void executeUC(Scanner scanner, IPizzaDao Pizzabdd) {

		LOG.info("Liste pizzas");
		List<Pizza> pizzas = Pizzabdd.findAllNewPizzas();
		affiche(pizzas);
	}

	private static void affiche(List<Pizza> pizzas) {
		for (Pizza pi : pizzas) {
			if (pi != null) {
				LOG.info(pi.toString());

			}

		}
	}

}