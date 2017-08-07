package model;

/**
 * Personne est la classe représentant l'objet Personne
 *
 */

public class Personne {
	private String nom;
	private String prenom;
	private String adresse;
	private String pays;
	
	/**
	 * Constructeur de l'objet Personne </br>
	 * nom	Nom de la personne </br>
	 * prenom	Prenom de la personne </br>
	 * adresse	Adresse de la personne </br>
	 * pays	Pays de la personne
	 */
	
	public Personne() {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.pays = pays;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", pays=" + pays + "]";
	}
	
	
}
