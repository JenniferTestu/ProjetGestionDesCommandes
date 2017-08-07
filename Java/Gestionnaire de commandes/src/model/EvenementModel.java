package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EvenementModel {

	/**
	 * Liste les evenements et stock dans un array les donnees concernant l'evenement
	 * @return Un array contenant des Evenements
	 */
	public List<Evenement> findAll() {
		
		try{
					
					List<Evenement> listeEvenements = new ArrayList<>();
					PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("SELECT * FROM evenements");
					
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()){
						Evenement e = new Evenement();
		
						e.setId(rs.getInt("id"));
						e.setTitre(rs.getString("titre"));
						e.setTexte(rs.getString("texte"));
						
						listeEvenements.add(e);
						
					}
					return listeEvenements;
					
				}catch (Exception err){
					System.out.println(err.getMessage());
					return null;
				}
	}

	/**
	 * Creer un nouvel evenement dans la table Evenement d'après un objet Evenement
	 * @param Evenement à créer
	 * @return True si la requete aboutie, false sinon
	 */
	public boolean create(Evenement e){
		
		try{
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("INSERT into evenements(titre, texte) values (?,?)");
			ps.setString(1,e.getTitre());
			ps.setString(2,e.getTexte());
			
			return ps.executeUpdate()>0;
			
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
	}
	
	/**
	 * Mettre à jour les informations de l'evenement dans la base de donnée
	 * @param Evenement à mettre à jour
	 * @return True si la requete aboutie, false sinon
	 */
	public boolean update(Evenement e){
		try{
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("UPDATE evenements SET titre = ?, texte = ? WHERE id = ?");
			ps.setString(1,e.getTitre());
			ps.setString(2,e.getTexte());
			ps.setInt(3,e.getId());
			
			return ps.executeUpdate()>0;
		
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
	}
	
	/**
	 * Supprimer l'evenement de la base de donnée d'après son id
	 * @param Evenement à supprimer
	 * @return True si la requete aboutie, false sinon
	 */
	public boolean delete(Evenement e){
		try{
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("DELETE FROM evenements WHERE id = ?");
			ps.setInt(1,e.getId());
			
			ps.executeUpdate();
			
			return true;
		
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
	}
}
