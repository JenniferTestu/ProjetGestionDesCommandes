package model;

/**
 * Fournisseur est la classe représentant l'objet Fournisseur
 *
 */

public class Fournisseur {

	private int id;
	private String nom;
	private String adresse;
	private String mail;
	private String tel;
	
	/**
	 * Constructeur de l'objet Fournisseur </br>
	 * id	Id du fournisseur </br>
	 * nom	Nom du fournisseur </br>
	 * adresse	Adresse du fournisseur </br>
	 * mail	Mail du fournisseur </br>
	 * tel	Numero de telephone de l'entreprise
	 */
	
	public Fournisseur() {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.mail = mail;
		this.tel = tel;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
