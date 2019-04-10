package pizzeria.model;

import pizzeria.model.CategoriePizza;

public enum CategoriePizza {

	VIANDE("VIANDE"),
	POISSON("POISSON"),
	SANS_VIANDE("SANS_VIANDE");
	
	private String nom;
	
	
	private CategoriePizza(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public static CategoriePizza recupCat(String name) {
		CategoriePizza [] cat = values();
		
		for(CategoriePizza p : cat) {
			if(p.getNom().equalsIgnoreCase(name)) {
				return p;
			}
		}
		return null;
	}
	
	
}