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

import model.Client;
import model.Fournisseur;
import model.Produit;

public class ClientsTableModel extends AbstractTableModel{

	private final String[] entetes = { "Id", "Nom", "Prenom", "Mail", "Adresse", "Pays", "Téléphone"};

	public Class[] colTypes = { Integer.class, String.class, String.class, String.class, String.class, String.class, String.class};

	private List<Client> listeClients = new ArrayList<>();
	
	public ClientsTableModel(List<Client> listeClients) {  
		this.listeClients = listeClients;
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public int getRowCount() {
		return listeClients.size();
	}

	public void setValueAt(Object value, int row, int col) {
		Client c = (Client) (listeClients.get(row));

	  switch (col) {
	  	case 0:
		    c.setId((Integer) value);
		    break;
	  	case 1:
		    c.setNom((String) value);
		    break;
	  	case 2:
		    c.setPrenom((String) value);
		    break;	
		case 3:
		    c.setAdresse((String) value);
		    break;
		case 4:
		    c.setPays((String) value);
		    break;
		case 5:
		    c.setMail((String) value);
		    break;
		case 6:
		    c.setTel((String) value);
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
		Client c = (Client) (listeClients.get(row));
	
	  switch (col) {
	  case 0:
	    return c.getId();
	  case 1:
	    return c.getNom();
	  case 2:
		return c.getPrenom();
	  case 3:
		return c.getAdresse();
	  case 4:
		return c.getPays();
	  case 5:
		return c.getMail();
	  case 6:
		return c.getTel();
		
	  }
	
	  return new String();
	}
	
	
	public Client getRowAt(int index) {
	
		Client c = (Client) (listeClients.get(index));
		return c;
		
	}
	
}
