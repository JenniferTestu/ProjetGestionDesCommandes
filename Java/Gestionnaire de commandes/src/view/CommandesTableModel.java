package view;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Client;
import model.Commande;

/***
 * 
 * Cette classe permet de définir le model de la table composé de commandes.
 * (C'est à dire quelles colonnes à afficher, quel type d'objet à retourner lors de la selection d'une ligne etc...)
 *
 */
public class CommandesTableModel extends AbstractTableModel {

	private final String[] entetes = { "Id de la commande", "Id du client qui a passé la commande", "Mode de paiement",
	        "Traité ?"};

	public Class[] m_colTypes = { Integer.class, Integer.class, String.class, Boolean.class};

	private List<Commande> listeCommandes = new ArrayList<>();

	    public CommandesTableModel(List<Commande> listeCommandes) {
	      
	    	this.listeCommandes = listeCommandes;
	    }
	    
	    public int getColumnCount() {
	      return entetes.length;
	    }
	    public int getRowCount() {
	      return listeCommandes.size();
	    }
	    public void setValueAt(Object value, int row, int col) {
	      Commande c = (Commande) (listeCommandes.get(row));

	      switch (col) {
	      case 0:
	        c.setId((Integer) value);
	        break;
	      case 1:
	        c.setClient((Client) value);
	        break;
	      case 2:
	        c.setPaiement((String) value);
	        break;
	      case 3:
	        c.setTraite((Boolean) value);
	        break;
	      }
	    }

	    public String getColumnName(int col) {
	      return entetes[col];
	    }

	    public Class getColumnClass(int col) {
	      return m_colTypes[col];
	    }
	    public Object getValueAt(int row, int col) {
	    	Commande c = (Commande) (listeCommandes.get(row));

	      switch (col) {
	      case 0:
	        return c.getId();
	      case 1:
	        return c.getClient().getId();
	      case 2:
	        return c.getPaiement();
	      case 3:
	        return c.isTraite();
	      }

	      return new String();
	    }
	  

	    public Commande getRowAt(int index) {

	    	Commande c = listeCommandes.get(index);
	    	return c;
	    	
	    }

		public void removeRow(int index) {
		  	listeCommandes.remove(index);
		  	this.fireTableDataChanged();
		}
		
	}