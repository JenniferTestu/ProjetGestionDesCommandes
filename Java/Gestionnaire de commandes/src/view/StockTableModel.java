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
 * Cette classe permet de définir le model de la table composé des stocks.
 * (C'est à dire quelles colonnes à afficher, quel type d'objet à retourner lors de la selection d'une ligne etc...)
 *
 */
public class StockTableModel extends AbstractTableModel{

	private final String[] entetes = { "Taille", "Quantité"};

	public Class[] colTypes = { String.class, Integer.class};

	private List<Produit> listeProduits = new ArrayList<>();

	
	public StockTableModel(List<Produit> listeProduits) {  
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
		    p.setTaille((String) value);
		    break;
	  	case 1:
		    p.setQuantite((Integer) value);
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
	    return p.getTaille();
	  case 1:
	    return p.getQuantite();
	  }
	
	  return new String();
	}
	
	
	public Produit getRowAt(int index) {
	
		Produit p = (Produit) (listeProduits.get(index));
		return p;
		
	}
	
}
