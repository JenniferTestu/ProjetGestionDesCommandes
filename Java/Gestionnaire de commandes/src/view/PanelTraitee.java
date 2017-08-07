package view;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
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

import model.Commande;
import model.CommandeModel;
import model.Produit;
import model.ProduitModel;
import net.miginfocom.swing.MigLayout;

/***
 * 
 * Cette classe correspond à l'onglet des commandes à traiter.
 *
 */
public class PanelTraitee extends JPanel {
	private JTable table;
	private CommandeModel comMod = new CommandeModel();
	private JTable tableProduits;
	private ProduitModel prodMod = new ProduitModel();
	private Commande c;
	private List<Commande> listeCommandes;
	private CommandesTableModel ctm;
	private int rowCommandes;
	
	private JScrollPane scrollPane_1;
	private JButton btnImprimerLaCommande;
	private JButton btnNonTraite;
	private JButton btnSupprimer;
	private JButton btnActualiser;
	private JLabel lblNom;
	private JLabel lblPrnom;
	private JLabel lblAdresse;
	private JLabel lblMail;
	private JLabel lblPays;
	private JTextArea textAdresse;
	private JTextArea textNom;
	private JTextArea textPrenom;
	private JTextArea textPays;
	private JTextArea textMail;
	private JLabel lblQuiAPass;
	private JLabel lblPayPar;
	private JLabel lblDestin;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JTextArea textPNom;
	private JTextArea textPPrenom;
	private JTextArea textPAdresse;
	private JTextArea textPPays;
	private JTextArea textDNom;
	private JTextArea textDPrenom;
	private JTextArea textDAdresse;
	private JTextArea textDPays;

	/**
	 * Create the panel.
	 */
	public PanelTraitee() {
		setLayout(new MigLayout("", "[142.00,grow][grow][grow]", "[25.00%][][8.00%][][8.00%][8.00%][8.00%][8.00%][8.00%][20.00%][10.00%]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0 3 1,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);

		listeCommandes = comMod.findAllTraitee();
		CommandesTableModel ctm = new CommandesTableModel(listeCommandes);
		table.setModel(ctm);
		
		btnImprimerLaCommande = new JButton("Imprimer la commande");
		btnImprimerLaCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MessageFormat header = new MessageFormat("Facture de la commande");
			        MessageFormat footer = new MessageFormat("Autour De La Moto vous dit à bientôt !");
					tableProduits.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Un problème a été rencontré, impossible d'imprimer");
				}
			}
		});
		
		lblQuiAPass = new JLabel("Qui a pass\u00E9 commande : ");
		add(lblQuiAPass, "cell 0 1,aligny center");
		
		lblPayPar = new JLabel("Pay\u00E9 par : ");
		add(lblPayPar, "cell 1 1,aligny center");
		
		lblDestin = new JLabel("Destin\u00E9 \u00E0 : ");
		add(lblDestin, "cell 2 1,aligny center");
		
		lblNom = new JLabel("Nom : ");
		add(lblNom, "flowx,cell 0 3");
		
		scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 0 9 3 1,grow");
		
		tableProduits = new JTable();
		tableProduits.setRowHeight (60) ; 
		scrollPane_1.setViewportView(tableProduits);
		add(btnImprimerLaCommande, "flowx,cell 0 10 3 1,alignx center,aligny center");
		
		btnNonTraite = new JButton("Non trait\u00E9e");
		btnNonTraite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(c!=null){
					if(comMod.setTraite(c.getId(),false)){
						System.out.println("Traitement : ok");
						tableProduits.removeAll();
						actualiser();
					}
				}
				
			}
		});
		add(btnNonTraite, "cell 0 10 3 1,alignx center,aligny center");
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(c!=null){
					int response = JOptionPane.showConfirmDialog(null,"Vous êtes sur le point de supprimer cette commande de façon définitive car celle-ci n'a pas pu aboutir. \nEtes vous sur de vouloir continuer ?","Attention",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
					
					if (response == JOptionPane.YES_OPTION) {
						if(comMod.delete(c)){
							JOptionPane.showMessageDialog(null, "La commande a été supprimé");
							tableProduits.removeAll();
							actualiser();
						}
					}
					
				}
			}
		});
		add(btnSupprimer, "cell 0 10 3 1,alignx center,aligny center");
		
		btnActualiser = new JButton("Actualiser");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualiser();
			}
		});
		add(btnActualiser, "cell 0 10 3 1,alignx center,aligny center");
		
		lblMail = new JLabel("Mail : ");
		add(lblMail, "flowx,cell 0 7 3 1");
		
		lblPays = new JLabel("Pays : ");
		add(lblPays, "flowx,cell 0 6 3 1");
		
		lblAdresse = new JLabel("Adresse : ");
		add(lblAdresse, "flowx,cell 0 5 3 1");
		
		textAdresse = new JTextArea();
		textAdresse.setWrapStyleWord(true);
		textAdresse.setLineWrap(true);
		textAdresse.setRows(2);
		textAdresse.setBackground(SystemColor.menu);
		textAdresse.setEditable(false);
		textAdresse.setColumns(10);
		add(textAdresse, "cell 0 5 3 1");
		
		lblPrnom = new JLabel("Pr\u00E9nom : ");
		add(lblPrnom, "flowx,cell 0 4 3 1");
		
		textNom = new JTextArea();
		textNom.setWrapStyleWord(true);
		textNom.setRows(2);
		textNom.setLineWrap(true);
		textNom.setEditable(false);
		textNom.setColumns(10);
		textNom.setBackground(SystemColor.menu);
		add(textNom, "flowx,cell 0 3 3 1");
		
		textPrenom = new JTextArea();
		textPrenom.setWrapStyleWord(true);
		textPrenom.setRows(2);
		textPrenom.setLineWrap(true);
		textPrenom.setEditable(false);
		textPrenom.setColumns(10);
		textPrenom.setBackground(SystemColor.menu);
		add(textPrenom, "cell 0 4 3 1");
		
		textPays = new JTextArea();
		textPays.setWrapStyleWord(true);
		textPays.setRows(2);
		textPays.setLineWrap(true);
		textPays.setEditable(false);
		textPays.setColumns(10);
		textPays.setBackground(SystemColor.menu);
		add(textPays, "cell 0 6 3 1");
		
		textMail = new JTextArea();
		textMail.setWrapStyleWord(true);
		textMail.setRows(2);
		textMail.setLineWrap(true);
		textMail.setEditable(false);
		textMail.setColumns(10);
		textMail.setBackground(SystemColor.menu);
		add(textMail, "cell 0 7 3 1");
		
		label = new JLabel("Nom : ");
		add(label, "cell 1 3");
		
		label_1 = new JLabel("Pr\u00E9nom : ");
		add(label_1, "cell 1 4");
		
		label_2 = new JLabel("Adresse : ");
		add(label_2, "cell 1 5");
		
		label_3 = new JLabel("Pays : ");
		add(label_3, "cell 1 6");
		
		label_6 = new JLabel("Pays : ");
		add(label_6, "cell 2 6");
		
		label_7 = new JLabel("Adresse : ");
		add(label_7, "cell 2 5");
		
		label_8 = new JLabel("Pr\u00E9nom : ");
		add(label_8, "cell 2 4");
		
		label_9 = new JLabel("Nom : ");
		add(label_9, "cell 2 3");
		
		textPNom = new JTextArea();
		textPNom.setWrapStyleWord(true);
		textPNom.setRows(2);
		textPNom.setLineWrap(true);
		textPNom.setEditable(false);
		textPNom.setColumns(10);
		textPNom.setBackground(SystemColor.menu);
		add(textPNom, "cell 1 3");
		
		textPPrenom = new JTextArea();
		textPPrenom.setWrapStyleWord(true);
		textPPrenom.setRows(2);
		textPPrenom.setLineWrap(true);
		textPPrenom.setEditable(false);
		textPPrenom.setColumns(10);
		textPPrenom.setBackground(SystemColor.menu);
		add(textPPrenom, "cell 1 4");
		
		textPAdresse = new JTextArea();
		textPAdresse.setWrapStyleWord(true);
		textPAdresse.setRows(2);
		textPAdresse.setLineWrap(true);
		textPAdresse.setEditable(false);
		textPAdresse.setColumns(10);
		textPAdresse.setBackground(SystemColor.menu);
		add(textPAdresse, "cell 1 5");
		
		textPPays = new JTextArea();
		textPPays.setWrapStyleWord(true);
		textPPays.setRows(2);
		textPPays.setLineWrap(true);
		textPPays.setEditable(false);
		textPPays.setColumns(10);
		textPPays.setBackground(SystemColor.menu);
		add(textPPays, "cell 1 6");
		
		textDNom = new JTextArea();
		textDNom.setWrapStyleWord(true);
		textDNom.setRows(2);
		textDNom.setLineWrap(true);
		textDNom.setEditable(false);
		textDNom.setColumns(10);
		textDNom.setBackground(SystemColor.menu);
		add(textDNom, "cell 2 3");
		
		textDPrenom = new JTextArea();
		textDPrenom.setWrapStyleWord(true);
		textDPrenom.setRows(2);
		textDPrenom.setLineWrap(true);
		textDPrenom.setEditable(false);
		textDPrenom.setColumns(10);
		textDPrenom.setBackground(SystemColor.menu);
		add(textDPrenom, "cell 2 4");
		
		textDAdresse = new JTextArea();
		textDAdresse.setWrapStyleWord(true);
		textDAdresse.setRows(2);
		textDAdresse.setLineWrap(true);
		textDAdresse.setEditable(false);
		textDAdresse.setColumns(10);
		textDAdresse.setBackground(SystemColor.menu);
		add(textDAdresse, "cell 2 5");
		
		textDPays = new JTextArea();
		textDPays.setWrapStyleWord(true);
		textDPays.setRows(2);
		textDPays.setLineWrap(true);
		textDPays.setEditable(false);
		textDPays.setColumns(10);
		textDPays.setBackground(SystemColor.menu);
		add(textDPays, "cell 2 6");
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	
				rowCommandes = table.getSelectedRow();
				
				if(rowCommandes<0){
					System.out.println("Ligne négative !");
				}else{
					System.out.println("Ligne : " + rowCommandes);
					rowCommandes = table.convertRowIndexToModel(rowCommandes);
					c = ctm.getRowAt(rowCommandes);
					System.out.println(c.toString());
					List<Produit> listeProduits = prodMod.findAllByCommande(c.getId());
					ProduitsComTableModel ptm = new ProduitsComTableModel(listeProduits);
					tableProduits.setModel(ptm);
				}
				remplirChamps(c);
	        }
		});
		
	}

	public void remplirChamps(Commande c){
		textNom.setText(c.getClient().getNom());
		textPrenom.setText(c.getClient().getPrenom());
		textAdresse.setText(c.getClient().getAdresse());
		textPays.setText(c.getClient().getPays());
		textMail.setText(c.getClient().getMail());
		
		textPNom.setText(c.getPayeur().getNom());
		textPPrenom.setText(c.getPayeur().getPrenom());
		textPAdresse.setText(c.getPayeur().getAdresse());
		textPPays.setText(c.getPayeur().getPays());
		
		textDNom.setText(c.getDestinataire().getNom());
		textDPrenom.setText(c.getDestinataire().getPrenom());
		textDAdresse.setText(c.getDestinataire().getAdresse());
		textDPays.setText(c.getDestinataire().getPays());
	}
	
	public void actualiser(){
		listeCommandes=null;
		listeCommandes = comMod.findAllTraitee();
		ctm = new CommandesTableModel(listeCommandes);
		table.setModel(ctm);		
	}
}

