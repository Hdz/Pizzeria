package pizzeria.model;
import java.lang.reflect.*;
import pizzeria.utils.*;
/**
 * @author DROUAUD
 *
 */
public class Pizza {
	private static int nbPizza = 0;
	private int id;
	@ToString(upper = true)
	private String code;
	@ToString(upper = true)
	private String libelle;
	@ToString(upper = false)
	private double prix;
	private static int cpt = 1;
	@ToString(upper = true)
private CategoriePizza catPizza;

	/**
	 * @param code
	 * @param libelle
	 * @param prix
	 * @param catPizza 
	 */
	public Pizza(String code, String libelle, double prix, CategoriePizza catPizza) {
		Pizza.cpt++;
		this.id = Pizza.cpt;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.catPizza = catPizza;
	}

	/**
	 * @param id
	 * @param code
	 * @param libelle
	 * @param prix
	 */
	public Pizza(int id, String code, String libelle, double prix, CategoriePizza catPizza) {
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.catPizza = catPizza;
	}

	
	public String toString() {
		String chaine = "";
		Class classe = getClass();
		Field[] fields = classe.getDeclaredFields();

		for (Field attribut: fields){

			if (attribut.isAnnotationPresent(ToString.class)){
			

				ToString annotation = attribut.getAnnotation(ToString.class);

				

				boolean uppercase = annotation.upper();

				String before = annotation.before();

				String after = annotation.after();

				

				try {

					chaine+=before;
					if (uppercase && attribut.get(this) != null){
						chaine+=attribut.get(this).toString().toUpperCase();
					}
					else {
						chaine+=attribut.get(this);
					}
					chaine+=after;

				} catch (IllegalArgumentException | IllegalAccessException e) {



					e.printStackTrace();

				} 

			}

		}

		return chaine;
}
	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param libelle
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @param prix
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @return prix
	 */
	public double getPrix() {
		return prix;

	}
	
	public static int getNbPizza()
	{
		return nbPizza;
	}

	/**
	 * @param nbPizza2
	 */
	public static void setNbPizza(int nbPizza)
	{
		Pizza.nbPizza = nbPizza;
	}

	public CategoriePizza getcP()
	{
		return catPizza;
	}

	public void setcP(CategoriePizza cP)
	{
		this.catPizza = cP;
}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catPizza == null) ? 0 : catPizza.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		long temp;
		temp = Double.doubleToLongBits(prix);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (catPizza != other.catPizza)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (Double.doubleToLongBits(prix) != Double.doubleToLongBits(other.prix))
			return false;
		return true;
	}




	
}