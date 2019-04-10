package pizzeria.service;
import java.util.Scanner;

import dao.IPizzaDao;
import pizzeria.exception.*;


public abstract class MenuService {
public abstract	void executeUC(Scanner scanner, IPizzaDao memPizza)throws StockageException;

}
