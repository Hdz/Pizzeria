package pizzeria.service;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.IPizzaDao;
import dao.Pizzabdd;
public class SupprimerPizzaService extends MenuService {

	private static final Logger LOG = LoggerFactory.getLogger(Pizzabdd.class);

	@Override
	public void executeUC(Scanner scanner, IPizzaDao memPizza) {
		Scanner sc = new Scanner(System.in);
		String coderecup;
		LOG.info("4. Supprimer une Pizza");
		LOG.info("Quel code souhaitez vous supprimer ?");
		coderecup = sc.nextLine();
		memPizza.deletePizza(coderecup);
	}

}
