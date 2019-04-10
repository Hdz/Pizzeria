package pizzeria.service;

public class MenuFactory {
	public MenuService create(String typeMenu) {
		if(typeMenu.equals("Lister")) {
			return new ListerPizzasService();
		}
		else if(typeMenu.equals("Ajouter")) {
			return new AjouterPizzaService();
		}
		else if(typeMenu.equals("Modifier")) {
			return new ModifierPizzaService();
		}
		else if(typeMenu.equals("Supprimer")) {
			return new SupprimerPizzaService();
		}
		return null;
}
}
