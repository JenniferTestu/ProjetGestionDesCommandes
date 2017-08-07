package model;

/**
 * Client est la classe représentant l'objet Client qui derive de la classe Personne
 *
 */

public class Client extends Personne{

	private int id;
	private String mail;
	private String tel;
	
	/**
	 * Constructeur de l'objet Client </br>
	 * id	Id du client </br>
	 * mail	Mail du client </br>
	 * tel	Tel du client </br>
	 */
	
	public Client() {
		super();
		this.id = id;
		this.mail = mail;
		this.tel = tel;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return super.toString()+" Client [id=" + id + ", mail=" + mail + ", tel=" + tel + "]";
	}
	
	
}
