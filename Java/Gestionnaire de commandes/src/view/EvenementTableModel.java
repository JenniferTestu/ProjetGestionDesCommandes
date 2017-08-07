package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Evenement;
import model.Fournisseur;

/***
 * 
 * Cette classe permet de définir le model de la table composé d'evenements.
 * (C'est à dire quelles colonnes à afficher, quel type d'objet à retourner lors de la selection d'une ligne etc...)
 *
 */
public class EvenementTableModel extends AbstractTableModel{

	private final String[] entetes = { "Id", "Titre", "Texte"};

	public Class[] colTypes = { Integer.class, String.class, String.class};

	private List<Evenement> listeEvenements = new ArrayList<>();
	
	public EvenementTableModel(List<Evenement> listeEvenements) {  
		this.listeEvenements = listeEvenements;
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public int getRowCount() {
		return listeEvenements.size();
	}

	public void setValueAt(Object value, int row, int col) {
		Evenement e = (Evenement) (listeEvenements.get(row));

	  switch (col) {
	  	case 0:
		    e.setId((Integer) value);
		    break;
	  	case 1:
		    e.setTitre((String) value);
		    break;	  	
		case 2:
		    e.setTexte((String) value);
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
		Evenement e = (Evenement) (listeEvenements.get(row));
	
	  switch (col) {
	  case 0:
	    return e.getId();
	  case 1:
	    return e.getTitre();
	  case 2:
		return e.getTexte();
	  }
	
	  return new String();
	}
	
	
	public Evenement getRowAt(int index) {
	
		Evenement e = (Evenement) (listeEvenements.get(index));
		return e;
		
	}
	
}