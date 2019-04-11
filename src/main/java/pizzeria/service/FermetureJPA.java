package pizzeria.service;

import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.IPizzaDao;
import dao.PizzaJpaDao;
import dao.Pizzabdd;
import pizzeria.exception.StockageException;

public class FermetureJPA extends MenuService {

	private static final Logger LOG = LoggerFactory.getLogger(Pizzabdd.class);

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws StockageException, SQLException {
		
		if(dao instanceof PizzaJpaDao){
			
			dao.destroyEmFactory();

		} else {
			
			LOG.info("PizzaJpaDao activé, EntityManagerFactory n'est pas fermable");
			
		}

	}

}