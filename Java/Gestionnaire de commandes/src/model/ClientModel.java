package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientModel {

	/**
	 * Cherche un client et stock les donnees concernant le client
	 * @param Id du client cherché
	 * @return Le client
	 */
	public Client findById(int id){
		
		Client c = new Client();
		
		try{
			
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("SELECT * FROM clients WHERE id = ?");
			ps.setInt(1,id);			
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next()){
				return null;
			}			
			else{
				c.setId(rs.getInt(1));
				c.setNom(rs.getString(2));
				c.setPrenom(rs.getString(3));
				c.setMail(rs.getString(4));
				c.setAdresse(rs.getString(6));
				c.setPays(rs.getString(7));
				c.setTel(rs.getString(8));
				
			}			
			return c;
			
		}catch (Exception err){
			System.out.println(err.getMessage());
			return null;
		}
		
	}

	/**
	 * Liste les clients et stock dans un array les donnees concernant le client
	 * @return Un array contenant des Client
	 */
	public List<Client> findAll(){
		
		try{
			
			List<Client> listeClients = new ArrayList<>();
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("SELECT * FROM clients");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Client c = new Client();
				
				c.setNom(rs.getString("nom"));
				c.setPrenom(rs.getString("prenom"));
				c.setMail(rs.getString("mail"));
				c.setAdresse(rs.getString("adresse"));
				c.setPays(rs.getString("pays"));
				c.setTel(rs.getString("tel"));
				
				listeClients.add(c);
				
			}
			return listeClients;
			
		}catch (Exception err){
			System.out.println(err.getMessage());
			return null;
		}
	}
	
	
}
