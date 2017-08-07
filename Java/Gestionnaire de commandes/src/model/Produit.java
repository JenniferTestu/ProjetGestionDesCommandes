package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Produit est la classe représentant l'objet Produit
 *
 */

public class Produit {

	private int id;
	private String nom;
	private String details;
	private String categorie;
	private String sousCategorie;
	private Timestamp date;
	private String taille;
	private int quantite;
	private double prixUni;
	private ImageIcon image;
	private List<Fournisseur> listeFournisseurs = new ArrayList<>();
	
	/**
	 * Constructeur de l'objet Produit </br>
	 * id	Id du produit </br>
	 * nom	Nom du produit </br>
	 * details	Description du produit </br>
	 * categorie	Categorie du produit </br>
	 * sousCategorie	Sous categorie du produit </br>
	 * date	Date d'ajout du produit </br>
	 * taille	Taille du produit </br>
	 * quantite	Quantite du produit </br>
	 * prixUni	Prix unitaire du produit </br>
	 * image	Image du produit </br>
	 * listeFournisseurs	Liste des fournisseurs du produit </br>
	 */
	
	public Produit() {
		super();
		this.id = id;
		this.nom = nom;
		this.details = details;
		this.categorie = categorie;
		this.sousCategorie = sousCategorie;
		this.date = date;
		this.taille = taille;
		this.quantite = quantite;
		this.prixUni = prixUni;
		this.image = image;
		this.listeFournisseurs = listeFournisseurs;
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

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrixUni() {
		return prixUni;
	}

	public void setPrixUni(double prixUni) {
		this.prixUni = prixUni;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getSousCategorie() {
		return sousCategorie;
	}

	public void setSousCategorie(String sousCategorie) {
		this.sousCategorie = sousCategorie;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

	public List<Fournisseur> getListeFournisseurs() {
		return listeFournisseurs;
	}

	public void setListeFournisseurs(List<Fournisseur> listeFournisseurs) {
		this.listeFournisseurs = listeFournisseurs;
	}
	
	
	
}
