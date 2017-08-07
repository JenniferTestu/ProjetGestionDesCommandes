package model;
import java.sql.Timestamp ;
import java.util.ArrayList;
import java.util.List;

/**
 * Commande est la classe représentant l'objet Commande
 *
 */

public class Commande {

	private int id;
	private Client client;
	private Personne destinataire;
	private Personne payeur;
	private String paiement;
	private Timestamp date;
	private boolean traite;
	private List<Produit> listeProduits = new ArrayList<>();
	
	/**
	 * Constructeur de l'objet Commande </br>
	 * id	Id de la commande </br>
	 * client	Client qui a passé la commande </br>
	 * destinataire	Destinataire de la commande </br>
	 * payeur	Payeur de la commande </br>
	 * paiement	Moyen de paiement de la commande </br>
	 * date	Date de la commande </br>
	 * traite 	Traitement de la commande </br>
	 * listeProduits	Liste des produits de la commande </br>
	 */
	
	public Commande() {
		super();
		this.id = id;
		this.client = client;
		this.destinataire = destinataire;
		this.payeur = payeur;
		this.paiement = paiement;
		this.traite = traite;
		this.listeProduits = listeProduits;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPaiement() {
		return paiement;
	}

	public void setPaiement(String paiement) {
		this.paiement = paiement;
	}

	public boolean isTraite() {
		return traite;
	}

	public void setTraite(boolean traite) {
		this.traite = traite;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Personne getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(Personne destinataire) {
		this.destinataire = destinataire;
	}

	public Personne getPayeur() {
		return payeur;
	}

	public void setPayeur(Personne payeur) {
		this.payeur = payeur;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", client=" + client + ", destinataire=" + destinataire + ", payeur=" + payeur
				+ ", paiement=" + paiement + ", date=" + date + ", traite=" + traite + "]";
	}
	
	
}
