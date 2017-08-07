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

import model.Fournisseur;
import model.Produit;

/***
 * 
 * Cette classe permet de définir le model de la table composé de fournisseurs.
 * (C'est à dire quelles colonnes à afficher, quel type d'objet à retourner lors de la selection d'une ligne etc...)
 *
 */
public class FournisseursTableModel extends AbstractTableModel{

	private final String[] entetes = { "Id", "Nom", "Adresse", "Mail", "Téléphone"};

	public Class[] colTypes = { Integer.class, String.class, String.class, String.class, String.class};

	private List<Fournisseur> listeFournisseurs = new ArrayList<>();
	
	public FournisseursTableModel(List<Fournisseur> listeFournisseurs) {  
		this.listeFournisseurs = listeFournisseurs;
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public int getRowCount() {
		return listeFournisseurs.size();
	}

	public void setValueAt(Object value, int row, int col) {
		Fournisseur f = (Fournisseur) (listeFournisseurs.get(row));

	  switch (col) {
	  	case 0:
		    f.setId((Integer) value);
		    break;
	  	case 1:
		    f.setNom((String) value);
		    break;	  	
		case 2:
		    f.setAdresse((String) value);
		    break;
		case 3:
		    f.setMail((String) value);
		    break;
		case 4:
		    f.setTel((String) value);
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
		Fournisseur f = (Fournisseur) (listeFournisseurs.get(row));
	
	  switch (col) {
	  case 0:
	    return f.getId();
	  case 1:
	    return f.getNom();
	  case 2:
		return f.getAdresse();
	  case 3:
		return f.getMail();
	  case 4:
		return f.getTel();
		
	  }
	
	  return new String();
	}
	
	
	public Fournisseur getRowAt(int index) {
	
		Fournisseur f = (Fournisseur) (listeFournisseurs.get(index));
		return f;
		
	}
	
}
