package view;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import model.Produit;

/***
 * 
 * Cette classe permet de définir le model de la table composé de produits qui ont été commandé.
 * (C'est à dire quelles colonnes à afficher, quel type d'objet à retourner lors de la selection d'une ligne etc...)
 *
 */
public class ProduitsComTableModel extends AbstractTableModel{

	private final String[] entetes = { "Id", "Nom", "Taille",
    "Quantité", "Prix Unitaire", "Image"};

	public Class[] colTypes = { Integer.class, String.class, String.class, Integer.class, Double.class, ImageIcon.class};

	private List<Produit> listeProduits = new ArrayList<>();
	
	
	public ProduitsComTableModel(List<Produit> listeProduits) {  
		this.listeProduits = listeProduits;
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public int getRowCount() {
		return listeProduits.size();
	}

	public void setValueAt(Object value, int row, int col) {
		Produit p = (Produit) (listeProduits.get(row));

	  switch (col) {
	  	case 0:
		    p.setId((Integer) value);
		    break;
	  	case 1:
		    p.setNom((String) value);
		    break;
	  	case 2:
		    p.setTaille((String) value);
		    break;
	  	case 3:
		    p.setQuantite((Integer) value);
		    break;
		case 4:
		    p.setPrixUni((Double) value);
		    break;
		    
	  }
	}

	public String getColumnName(int col) {
	  return entetes[col];
	}
	
	public Class getColumnClass(int col) {
	  return colTypes[col];
	}
	
	public Object getValueAt(int row, int col) {
		Produit p = (Produit) (listeProduits.get(row));
	
	  switch (col) {
	  case 0:
	    return p.getId();
	  case 1:
	    return p.getNom();
	  case 2:
	    return p.getTaille();
	  case 3:
	    return p.getQuantite();
	  case 4:
		return p.getPrixUni();
	  case 5:
		return ResizeImage(p.getImage());		  
	  }
	
	  return new String();
	}
	
	
	public Produit getRowAt(int index) {
	
		Produit p = (Produit) (listeProduits.get(index));
		return p;
		
	}
	
	private ImageIcon ResizeImage(ImageIcon MyImage){ 
		Image img = MyImage.getImage(); 
		Image newImage = img.getScaledInstance(-1, 60,Image.SCALE_SMOOTH); 
		ImageIcon image = new ImageIcon(newImage); 
		return image; 
	}
	
}
