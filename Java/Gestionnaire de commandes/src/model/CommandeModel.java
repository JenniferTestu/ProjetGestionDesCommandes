package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommandeModel {

	private ClientModel clMod = new ClientModel();
	
	/**
	 * Liste les commandes et stock dans un array les donnees concernant la commande
	 * @return Un array contenant des Commandes
	 */
	public List<Commande> findAll(){
		
		try{
			
			List<Commande> listeCommandes = new ArrayList<>();
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("SELECT * FROM info_commandes");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Commande commande = new Commande();
				Personne payeur = new Personne();
				Personne destinataire = new Personne();
				
				payeur.setNom(rs.getString("nom_fact"));
				payeur.setPrenom(rs.getString("prenom_fact"));
				payeur.setAdresse(rs.getString("adresse_fact"));
				payeur.setPays(rs.getString("pays_fact"));

				destinataire.setNom(rs.getString("nom_dest"));
				destinataire.setPrenom(rs.getString("prenom_dest"));
				destinataire.setAdresse(rs.getString("adresse_dest"));
				destinataire.setPays(rs.getString("pays_dest"));
				
				commande.setId(rs.getInt("id"));
				commande.setClient(clMod.findById(rs.getInt("id_client")));
				commande.setPayeur(payeur);
				commande.setDestinataire(destinataire);
				commande.setPaiement(rs.getString("paiement"));
				commande.setDate(rs.getTimestamp("date"));
				commande.setTraite(rs.getBoolean("traite"));
				
				listeCommandes.add(commande);
				
			}
			return listeCommandes;
			
		}catch (Exception err){
			System.out.println(err.getMessage());
			return null;
		}
	}

	/**
	 * Liste les commandes non traitées et stock dans un array les donnees concernant la commande
	 * @return Un array contenant des Commandes non traitées
	 */
	public List<Commande> findAllNonTraitee(){
		
		try{
			
			List<Commande> listeCommandes = new ArrayList<>();
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("SELECT * FROM info_commandes WHERE traite = false");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Commande commande = new Commande();
				Personne payeur = new Personne();
				Personne destinataire = new Personne();
				
				payeur.setNom(rs.getString("nom_fact"));
				payeur.setPrenom(rs.getString("prenom_fact"));
				payeur.setAdresse(rs.getString("adresse_fact"));
				payeur.setPays(rs.getString("pays_fact"));

				destinataire.setNom(rs.getString("nom_dest"));
				destinataire.setPrenom(rs.getString("prenom_dest"));
				destinataire.setAdresse(rs.getString("adresse_dest"));
				destinataire.setPays(rs.getString("pays_dest"));
				

				commande.setId(rs.getInt("id"));
				commande.setClient(clMod.findById(rs.getInt("id_client")));
				commande.setPayeur(payeur);
				commande.setDestinataire(destinataire);
				commande.setPaiement(rs.getString("paiement"));
				commande.setDate(rs.getTimestamp("date"));
				commande.setTraite(rs.getBoolean("traite"));
				
				listeCommandes.add(commande);
				
			}
			return listeCommandes;
			
		}catch (Exception err){
			System.out.println(err.getMessage());
			return null;
		}
	}
	
	/**
	 * Liste les commandes traitées et stock dans un array les donnees concernant la commande
	 * @return Un array contenant des Commandes traitées
	 */
	public List<Commande> findAllTraitee(){
			
			try{
				
				List<Commande> listeCommandes = new ArrayList<>();
				PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("SELECT * FROM info_commandes WHERE traite = true");
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					Commande commande = new Commande();
					Personne payeur = new Personne();
					Personne destinataire = new Personne();
					
					payeur.setNom(rs.getString("nom_fact"));
					payeur.setPrenom(rs.getString("prenom_fact"));
					payeur.setAdresse(rs.getString("adresse_fact"));
					payeur.setPays(rs.getString("pays_fact"));
	
					destinataire.setNom(rs.getString("nom_dest"));
					destinataire.setPrenom(rs.getString("prenom_dest"));
					destinataire.setAdresse(rs.getString("adresse_dest"));
					destinataire.setPays(rs.getString("pays_dest"));
					
	
					commande.setId(rs.getInt("id"));
					commande.setClient(clMod.findById(rs.getInt("id_client")));
					commande.setPayeur(payeur);
					commande.setDestinataire(destinataire);
					commande.setPaiement(rs.getString("paiement"));
					commande.setDate(rs.getTimestamp("date"));
					commande.setTraite(rs.getBoolean("traite"));
					
					listeCommandes.add(commande);
					
				}
				return listeCommandes;
				
			}catch (Exception err){
				System.out.println(err.getMessage());
				return null;
			}
	}
	
	/**
	 * Mettre à jour l'attribut traite de la commande dans la base de donnée
	 * @param Id de la commande
	 * @param Boolean a définir pour l'attribut traite
	 * @return true si la requete aboutie, false sinon
	 */
	public boolean setTraite(int idCommande, boolean t){
		
		try{
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("UPDATE info_commandes SET traite = ? WHERE id = ?");

			ps.setBoolean(1,t);
			ps.setInt(2,idCommande);
			
			return ps.executeUpdate()>0;
			
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
	}
	
	/**
	 * Supprimer la commande de la base de donnée d'après son id
	 * @param Commande à supprimer
	 * @return true si la requete aboutie, false sinon
	 */
	public boolean delete(Commande c){
		try{
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("DELETE FROM info_commandes WHERE id = ?");
			ps.setInt(1,c.getId());			
			ps.executeUpdate();
			
			ps = ConnectionMySQL.getConnection().prepareStatement("DELETE FROM produits_commandes WHERE id_commande = ?");
			ps.setInt(1,c.getId());			
			ps.executeUpdate();
			
			return true;
		
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
	}
}
