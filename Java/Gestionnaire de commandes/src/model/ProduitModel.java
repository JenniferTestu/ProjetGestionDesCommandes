package model;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class ProduitModel {

	private String host = "http://localhost";
	private int port = 8080;
	private String user = "root";
	private String pass = "";
	private String folder = "/ADLM%20Website/images_inventaire/";
	
	private String dir = "C:\\wamp64\\www\\ADLM Website\\images_inventaire\\";
	/**
	 * Liste les produits et stock dans un array les donnees concernant le produit
	 * @return Un array contenant des Produit
	 */
	public List<Produit> findAll(){
			
			try{
				
				List<Produit> listeProduits = new ArrayList<>();
				PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("SELECT * FROM produits");
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					Produit produit = new Produit();
	
					produit.setId(rs.getInt("id"));
					produit.setNom(rs.getString("nom_produit"));
					produit.setPrixUni(rs.getDouble("prix"));
					produit.setDetails(rs.getString("details"));
					produit.setCategorie(rs.getString("categorie"));
					produit.setSousCategorie(rs.getString("souscategorie"));
					produit.setDate(rs.getTimestamp("date_ajout"));
					produit.setImage(findImage(rs.getInt("id")));
					produit.setListeFournisseurs(findAllFournisseurs(rs.getInt("id")));
					
					listeProduits.add(produit);
					
				}
				return listeProduits;
				
			}catch (Exception err){
				System.out.println(err.getMessage());
				return null;
			}
	}
		
	/**
	 * Liste les produits d'une commande et stock dans un array les donnees concernant le produit
	 * @param Id de la commande
	 * @return Un array contenant des Produit
	 */
	public List<Produit> findAllByCommande(int idCommande){
			
			try{
				
				List<Produit> listeProduits = new ArrayList<>();
				PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("SELECT * FROM produits_commandes ,produits WHERE id_commande = ? AND id_produit = id");
				
				ps.setInt(1,idCommande);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					Produit produit = new Produit();
	
					produit.setId(rs.getInt("id_produit"));
					produit.setNom(rs.getString("nom_produit"));
					produit.setTaille(rs.getString("taille_produit"));
					produit.setQuantite(rs.getInt("quantite"));
					produit.setPrixUni(rs.getDouble("prix"));
					produit.setImage(findImage(rs.getInt("id")));
					
					listeProduits.add(produit);
					
				}
				return listeProduits;
				
			}catch (Exception err){
				System.out.println(err.getMessage());
				return null;
			}
		}
	
	/**
	 * Creer un nouveau produit dans la table Produit d'après un objet Produit
	 * @param Produit à créer
	 * @return True si la requete aboutie, false sinon
	 */
	public boolean create(Produit p){
		
		try{
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("INSERT into produits(nom_produit, prix, details, categorie, souscategorie, date_ajout) values (?,?,?,?,?,?)");
			ps.setString(1,p.getNom());
			ps.setDouble(2,p.getPrixUni());
			ps.setString(3,p.getDetails());
			ps.setString(4,p.getCategorie());
			ps.setString(5,p.getSousCategorie());
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
			ps.setTimestamp(6,timestamp);
			
			return ps.executeUpdate()>0;
			
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
	}
	
	/**
	 * Mettre à jour les informations du produit dans la base de donnée
	 * @param Produit à mettre à jour
	 * @return True si la requete aboutie, false sinon
	 */
	public boolean update(Produit p){
		try{
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("UPDATE produits SET nom_produit = ?, prix = ?, details = ?, categorie = ?, souscategorie = ? WHERE id = ?");
			ps.setString(1,p.getNom());
			ps.setDouble(2,p.getPrixUni());
			ps.setString(3,p.getDetails());
			ps.setString(4,p.getCategorie());
			ps.setString(5,p.getSousCategorie());
			ps.setInt(6,p.getId());
			
			return ps.executeUpdate()>0;
		
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
	}
	
	/**
	 * Supprimer le produit de la base de donnée d'après son id
	 * @param Produit à supprimer
	 * @return True si la requete aboutie, false sinon
	 */
	public boolean delete(Produit p){
		try{
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("DELETE FROM produits WHERE id = ?");
			ps.setInt(1,p.getId());		
			ps.executeUpdate();
			
			ps = ConnectionMySQL.getConnection().prepareStatement("DELETE FROM stocks WHERE id_produit = ?");
			ps.setInt(1,p.getId());		
			ps.executeUpdate();
			
			return true;
		
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
	}
	
	/**
	 * Chercher l'image d'un produit 
	 * @param Id du produit
	 * @return L'image du produit
	 */
	public ImageIcon findImage(int id) {
		try {

			//BufferedImage img = ImageIO.read(new URL(url+String.valueOf(p.getId())+".jpg"));
			ImageIcon imgicon = new ImageIcon(new URL(host+":"+String.valueOf(port)+folder+String.valueOf(id)+".jpg"));
			Image newImg = imgicon.getImage();
			Image resizeImg = newImg.getScaledInstance(-1, -1, Image.SCALE_SMOOTH);
			imgicon = new ImageIcon(resizeImg);
			return imgicon;
		  } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
		return null;
	}
	
	/**
	 * Liste les stocks d'un produit et stock dans un array les donnees concernant les stocks
	 * @param Id du produit
	 * @return Un array contenant des Produit
	 */
	public List<Produit> findStock(int id){
		
		try{
			
			List<Produit> listeProduits = new ArrayList<>();
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("SELECT * FROM stocks WHERE id_produit = ?");
			
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){

				Produit p = new Produit();
				
				p.setTaille(rs.getString("taille"));
				p.setQuantite(rs.getInt("nombre"));
				
				listeProduits.add(p);
				
			}
			
			for(Produit pr : listeProduits){
				System.out.println(pr.getTaille()+" "+pr.getQuantite());
			}
			
			return listeProduits;
			
		}catch (Exception err){
			System.out.println(err.getMessage());
			return null;
		}
	}
	
	/**
	 * Mettre à jour un stock d'un produit d'après son id et la taille concernée
	 * @param Produit à mettre à jour
	 * @return True si la requete aboutie, false sinon
	 */
	public boolean updateStock(Produit p){
		try{
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("UPDATE stocks SET taille = ?, nombre = ? WHERE id_produit = ?");
			ps.setString(1,p.getTaille());
			ps.setInt(2,p.getQuantite());
			ps.setInt(3,p.getId());
			
			return ps.executeUpdate()>0;
		
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
	}
	
	/**
	 * Supprimer un stock d'un produit d'après son id et la taille concernée
	 * @param Produit à supprimer
	 * @return True si la requete aboutie, false sinon
	 */
	public boolean deleteStock(Produit p){
		try{
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("DELETE FROM stocks WHERE id_produit = ? AND taille = ?");
			ps.setInt(1,p.getId());
			ps.setString(1,p.getTaille());
			
			ps.executeUpdate();
			
			return true;
		
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
	}
	
	/**
	 * Liste les fournisseurs d'un produit et stock dans un array les donnees concernant le fournisseur
	 * @param Id du produit
	 * @return Un array contenant des Fournisseur
	 */
	private List<Fournisseur> findAllFournisseurs(int id){
		
		try{
			
			List<Fournisseur> listeFournisseurs = new ArrayList<>();
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("SELECT * FROM reappro, fournisseurs WHERE id_produit = ? AND id_fourni = id");
			
			ps.setInt(1,id);
			
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
	 * Supprimer l'association d'un fournisseur a un produit
	 * @param Produit
	 * @param Fournisseur à supprimer
	 * @return True si la requete aboutie, false sinon
	 */
	public boolean deleteFournisseur(Produit p, Fournisseur f){
		try{
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("DELETE FROM reappro WHERE id_produit = ? AND id_fourni = ?");
			ps.setInt(1,p.getId());
			ps.setInt(2,f.getId());	
			ps.executeUpdate();
			
			return true;
		
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
	}
	
	/**
	 * Creer l'association d'un fournisseur à un produit
	 * @param Produit
	 * @param Fournisseur à associer
	 * @return True si la requete aboutie, false sinon
	 */
	public boolean createFournisseur(Produit p, Fournisseur f){
		try{
			PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("INSERT into reappro(id_produit, id_fourni) values (?,?)");
			ps.setInt(1,p.getId());
			ps.setInt(2,f.getId());	
			ps.executeUpdate();
			
			return true;
		
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
	}
	
	/**
	 * Creer l'image d'un produit sur le serveur
	 * @param Image à créer
	 * @param Id du produit
	 * @throws IOException 
	 * @throws SocketException 
	 * @throws SQLException 
	 */
	public void createImage(String path) throws SocketException, IOException, SQLException {
		/*
		FTPClient ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect(host,port);
        System.out.println("FTP URL is:"+ftp.getDefaultPort());
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            try {
				throw new Exception("Exception in connecting to FTP Server");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        ftp.login(user, pass);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();   
        
        try {
            InputStream input = new FileInputStream(new File(path));

            ftp.storeFile(host + folder + String.valueOf(id) +".jpg", input);
            }
            catch(Exception e){

            }
        ftp.logout();
        ftp.disconnect();
        */

		InputStream is = null;
    	OutputStream os = null;
    	int id = 0;    	
    	
	    	PreparedStatement ps = ConnectionMySQL.getConnection().prepareStatement("SELECT MAX(id) as maxid FROM produits");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				id = rs.getInt("maxid");
			}
	    	System.out.println(id);
	    	is = new FileInputStream(new File(path));
	    	os = new FileOutputStream(new File(dir+String.valueOf(id)+".jpg"));
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }

	        is.close();
	        os.close();

	}
	
	/**
	 * Supprimer l'image d'un produit sur le serveur
	 * @param Id du produit
	 * @return True si la requete aboutie, false sinon
	 * @throws IOException 
	 */
	public void deleteImage(int id) throws IOException {
		
		File f = null;
	    boolean bool = false;
	      
	    // create new file
	    f = new File(dir+String.valueOf(id)+".jpg");
	    
	    // tries to delete a non-existing file
	    bool = f.delete();

		/*
		FTPClient ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect(host,port);
        System.out.println("FTP URL is:"+ftp.getDefaultPort());
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            try {
				throw new Exception("Exception in connecting to FTP Server");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        ftp.login(user, pass);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();   
        
        try {

            boolean deleted = ftp.deleteFile(host + folder + String.valueOf(id) +".jpg");
            if (deleted) {
                System.out.println("The file was deleted successfully.");
            } else {
                System.out.println("Could not delete the  file, it may not exist.");
            }
            }
            catch(Exception e){

            }
        ftp.logout();
        ftp.disconnect();
        */
	}
}
