package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FournisseurModel {

	/**
	 * Liste les fournisseurs et stock dans un array les donnees concernant le fournisseur
	 * @return Un array contenant des fournisseurs
	 */
	public List<Fournisseur> findAll() {
		
		try{
					
					List<Fournisseur> listeFournisseurs = new ArrayList<>();
					PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("SELECT * FROM fournisseurs");
					
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()){
						Fournisseur f = new Fournisseur();
		
						f.setId(rs.getInt("id"));
						f.setNom(rs.getString("nom_entreprise"));
						f.setAdresse(rs.getString("adresse"));
						f.setMail(rs.getString("mail"));
						f.setTel(rs.getString("tel"));
						
						listeFournisseurs.add(f);
						
					}
					return listeFournisseurs;
					
				}catch (Exception err){
					System.out.println(err.getMessage());
					return null;
				}
	}

	/**
	 * Creer un nouveau fournisseur dans la table Fournisseurs d'après un objet Fournisseur
	 * @param Fournisseur à créer
	 * @return True si la requete aboutie, false sinon
	 */
	public boolean create(Fournisseur f){
		
		try{
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("INSERT into fournisseurs(nom_entreprise, adresse, mail, tel) values (?,?,?,?)");
			ps.setString(1,f.getNom());
			ps.setString(2,f.getAdresse());
			ps.setString(3,f.getMail());
			ps.setString(4,f.getTel());
			
			return ps.executeUpdate()>0;
			
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
	}
	
	/**
	 * Mettre à jour les informations de le fournisseur dans la base de donnée
	 * @param Fournisseur à mettre à jour
	 * @return True si la requete aboutie, false sinon
	 */
	public boolean update(Fournisseur f){
		try{
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("UPDATE fournisseurs SET nom_entreprise = ?, adresse = ?, mail = ?, tel = ? WHERE id = ?");
			ps.setString(1,f.getNom());
			ps.setString(2,f.getAdresse());
			ps.setString(3,f.getMail());
			ps.setString(4,f.getTel());
			ps.setInt(5,f.getId());
			
			return ps.executeUpdate()>0;
		
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
	}
	
	/**
	 * Supprimer le fournisseur de la base de donnée d'après son id
	 * @param Fournisseur à supprimer
	 * @return True si la requete aboutie, false sinon
	 */
	public boolean delete(Fournisseur f){
		try{
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("DELETE FROM fournisseurs WHERE id = ?");
			ps.setInt(1,f.getId());
			
			ps.executeUpdate();
			
			return true;
		
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
	}
}
