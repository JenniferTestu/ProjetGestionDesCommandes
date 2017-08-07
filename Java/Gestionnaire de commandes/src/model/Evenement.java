package model;

/**
 * Evenement est la classe représentant l'objet Evenement
 *
 */

public class Evenement {

	private int id;
	private String titre;
	private String texte;
	
	/**
	 * Constructeur de l'objet Evenement </br>
	 * id	Id de l'evenement </br>
	 * titre	Titre de l'evenement </br>
	 * texte	Texte de l'evenement </br>
	 */
	
	public Evenement() {
		super();
		this.id = id;
		this.titre = titre;
		this.texte = texte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	
}
