package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Client;
import model.ClientModel;
import model.Fournisseur;
import model.FournisseurModel;
import model.Produit;
import net.miginfocom.swing.MigLayout;
import java.awt.SystemColor;
import javax.swing.JTextField;

/***
 * 
 * Cette classe correspond à l'onglet de l'annuaire des clients.
 *
 */
public class PanelClient extends JPanel {
	
	private JTable table;
	private ClientModel cMod = new ClientModel();
	private Client cSelect;
	private List<Client> listeClients;
	private ClientsTableModel ctm;
	private int rowClients;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private JLabel lblNom;
	private JLabel lblPrnom;
	private JLabel lblAdresse;
	private JLabel lblPays;
	private JTextArea textMail;
	private JTextArea textNom;
	private JTextArea textAdresse;
	private JTextArea textTel;
	private JLabel lblQuiAPass;
	private JLabel lblPnom;
	private JTextArea textPrenom;
	private JLabel lblPays_1;
	private JTextArea textPays;
	private JTextField textRecherche;

	/**
	 * Create the panel.
	 */
	public PanelClient() {
		setLayout(new MigLayout("", "[142.00,grow][128.00,grow][grow]", "[25.00%][][8.00%][][8.00%][8.00%][8.00%][8.00%][8.00%][20.00%][][10.00%][][][]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0 3 1,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);

		listeClients = cMod.findAll();
		ClientsTableModel ctm = new ClientsTableModel(listeClients);
		table.setModel(ctm);
		
		btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		lblQuiAPass = new JLabel("Information du client s\u00E9lectionn\u00E9 :");
		add(lblQuiAPass, "cell 0 1,aligny center");
		
		lblNom = new JLabel("Nom : ");
		add(lblNom, "flowx,cell 0 3 3 1");
		
		lblPnom = new JLabel("P\u00E9nom : ");
		add(lblPnom, "flowx,cell 0 4 3 1");
		
		lblAdresse = new JLabel("Mail :");
		add(lblAdresse, "flowx,cell 0 5 3 1");
		
		lblPrnom = new JLabel("Adresse : ");
		add(lblPrnom, "flowx,cell 0 6 3 1");
		
		lblPays_1 = new JLabel("Pays : ");
		add(lblPays_1, "flowx,cell 0 7 3 1");
		
		lblPays = new JLabel("T\u00E9l\u00E9phone : ");
		add(lblPays, "flowx,cell 0 8 3 1");
		
		textRecherche = new JTextField();
		add(textRecherche, "cell 1 10,growx");
		textRecherche.setColumns(10);
		
		textNom = new JTextArea();
		textNom.setEditable(false);
		textNom.setWrapStyleWord(true);
		textNom.setRows(2);
		textNom.setLineWrap(true);
		textNom.setColumns(10);
		textNom.setBackground(SystemColor.menu);
		add(textNom, "flowx,cell 0 3 3 1");
		
		textPrenom = new JTextArea();
		textPrenom.setEditable(false);
		textPrenom.setWrapStyleWord(true);
		textPrenom.setRows(1);
		textPrenom.setLineWrap(true);
		textPrenom.setColumns(10);
		textPrenom.setBackground(SystemColor.menu);
		add(textPrenom, "cell 0 4 3 1");
		
		textMail = new JTextArea();
		textMail.setEditable(false);
		textMail.setWrapStyleWord(true);
		textMail.setLineWrap(true);
		textMail.setRows(1);
		textMail.setBackground(SystemColor.menu);
		textMail.setColumns(10);
		add(textMail, "cell 0 5 3 1");
		
		textAdresse = new JTextArea();
		textAdresse.setEditable(false);
		textAdresse.setWrapStyleWord(true);
		textAdresse.setRows(2);
		textAdresse.setLineWrap(true);
		textAdresse.setColumns(10);
		textAdresse.setBackground(SystemColor.menu);
		add(textAdresse, "cell 0 6 3 1");
		
		textPays = new JTextArea();
		textPays.setEditable(false);
		textPays.setWrapStyleWord(true);
		textPays.setRows(1);
		textPays.setLineWrap(true);
		textPays.setColumns(10);
		textPays.setBackground(SystemColor.menu);
		add(textPays, "cell 0 7 3 1");
		
		textTel = new JTextArea();
		textTel.setEditable(false);
		textTel.setWrapStyleWord(true);
		textTel.setRows(1);
		textTel.setLineWrap(true);
		textTel.setColumns(10);
		textTel.setBackground(SystemColor.menu);
		add(textTel, "cell 0 8 3 1");
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
			}
		});
		add(btnRechercher, "flowx,cell 1 12,alignx center,aligny center");
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {

	        	rowClients = table.getSelectedRow();
				
				if(rowClients<0){
					System.out.println("Ligne négative !");
				}else{
					System.out.println("Ligne : " + rowClients);
					rowClients = table.convertRowIndexToModel(rowClients);
					cSelect = ctm.getRowAt(rowClients);
					System.out.println(cSelect.toString());
				}
				remplirChamps(cSelect);
	        }
		});
		
	}

	public void remplirChamps(Client c){
		textNom.setText(c.getNom());
		textPrenom.setText(c.getPrenom());
		textAdresse.setText(c.getAdresse());
		textPays.setText(c.getPays());
		textMail.setText(c.getMail());
		textTel.setText(c.getTel());
		
	}
	
	public void actualiser(){
		listeClients=null;
		listeClients = cMod.findAll();
		ctm = new ClientsTableModel(listeClients);
		table.setModel(ctm);		
	}
}