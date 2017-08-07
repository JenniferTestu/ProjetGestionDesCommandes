package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Commande;
import model.CommandeModel;
import model.Fournisseur;
import model.FournisseurModel;
import model.Produit;
import model.ProduitModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Color;

/***
 * 
 * Cette classe correspond à l'onglet des fournisseurs.
 *
 */
public class PanelFournisseur extends JPanel {
	
	private JTable table;
	private FournisseurModel fMod = new FournisseurModel();
	private Fournisseur fSelect;
	private List<Fournisseur> listeFournisseurs;
	private FournisseursTableModel ftm;
	private int rowFournisseurs;
	private JButton btnImprimer;
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JLabel lblNom;
	private JLabel lblPrnom;
	private JLabel lblAdresse;
	private JLabel lblPays;
	private JTextArea textMail;
	private JTextArea textNom;
	private JTextArea textAdresse;
	private JTextArea textTel;
	private JLabel lblQuiAPass;

	/**
	 * Create the panel.
	 */
	public PanelFournisseur() {
		setLayout(new MigLayout("", "[142.00,grow][grow][grow]", "[25.00%][][8.00%][][8.00%][8.00%][8.00%][8.00%][8.00%][20.00%][10.00%]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0 3 1,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);

		listeFournisseurs = fMod.findAll();
		FournisseursTableModel ftm = new FournisseursTableModel(listeFournisseurs);
		table.setModel(ftm);
		
		btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		lblQuiAPass = new JLabel("Information du fournisseur s\u00E9lectionn\u00E9 :");
		add(lblQuiAPass, "cell 0 1,aligny center");
		
		lblNom = new JLabel("Nom de l'entreprise : ");
		add(lblNom, "flowx,cell 0 3");
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				Fournisseur fourni = new Fournisseur();
				fourni.setNom(textNom.getText());
				fourni.setAdresse(textAdresse.getText());
				fourni.setMail(textMail.getText());
				fourni.setTel(textTel.getText());
				
				if(fMod.create(fourni)){
					JOptionPane.showMessageDialog(null, "Le fournisseur a été ajouté");
					actualiser();
				}
				else
					JOptionPane.showMessageDialog(null, "Echec de l'ajout du fournisseur");
				
				
			}
		});
		add(btnAjouter, "cell 0 10 3 1,alignx center,aligny center");
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(fMod.delete(fSelect)){
					JOptionPane.showMessageDialog(null, "Le fournisseur a été supprimé");
					actualiser();
				}
				else
					JOptionPane.showMessageDialog(null, "Echec de la supression du fournisseur");

			}
		});
		add(btnSupprimer, "cell 0 10 3 1,alignx center,aligny center");
		
		btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Fournisseur fourni = new Fournisseur();
				fourni.setId(fSelect.getId());
				fourni.setNom(textNom.getText());
				fourni.setAdresse(textAdresse.getText());
				fourni.setMail(textMail.getText());
				fourni.setTel(textTel.getText());
				
				if(fMod.update(fourni)){
					JOptionPane.showMessageDialog(null, "Le fournisseur a été modifié");
					actualiser();
					remplirChamps(fourni);
				}
				else
					JOptionPane.showMessageDialog(null, "Echec de la modification du fournisseur");
				
			}
		});
		add(btnModifier, "cell 0 10 3 1,alignx center,aligny center");
		
		lblPays = new JLabel("T\u00E9l\u00E9phone : ");
		add(lblPays, "flowx,cell 0 6 3 1");
		
		lblAdresse = new JLabel("Mail :");
		add(lblAdresse, "flowx,cell 0 5 3 1");
		
		textMail = new JTextArea();
		textMail.setWrapStyleWord(true);
		textMail.setLineWrap(true);
		textMail.setRows(1);
		textMail.setBackground(Color.WHITE);
		textMail.setColumns(10);
		add(textMail, "cell 0 5 3 1");
		
		lblPrnom = new JLabel("Adresse : ");
		add(lblPrnom, "flowx,cell 0 4 3 1");
		
		textNom = new JTextArea();
		textNom.setWrapStyleWord(true);
		textNom.setRows(2);
		textNom.setLineWrap(true);
		textNom.setColumns(10);
		textNom.setBackground(Color.WHITE);
		add(textNom, "flowx,cell 0 3 3 1");
		
		textAdresse = new JTextArea();
		textAdresse.setWrapStyleWord(true);
		textAdresse.setRows(1);
		textAdresse.setLineWrap(true);
		textAdresse.setColumns(10);
		textAdresse.setBackground(Color.WHITE);
		add(textAdresse, "cell 0 4 3 1");
		
		textTel = new JTextArea();
		textTel.setWrapStyleWord(true);
		textTel.setRows(1);
		textTel.setLineWrap(true);
		textTel.setColumns(10);
		textTel.setBackground(Color.WHITE);
		add(textTel, "cell 0 6 3 1");
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	
				rowFournisseurs = table.getSelectedRow();
				
				if(rowFournisseurs<0){
					System.out.println("Ligne négative !");
				}else{
					System.out.println("Ligne : " + rowFournisseurs);
					rowFournisseurs = table.convertRowIndexToModel(rowFournisseurs);
					fSelect = ftm.getRowAt(rowFournisseurs);
					System.out.println(fSelect.toString());
				}
				remplirChamps(fSelect);
	        }
		});
		
	}

	public void remplirChamps(Fournisseur f){
		textNom.setText(f.getNom());
		textAdresse.setText(f.getAdresse());
		textMail.setText(f.getMail());
		textTel.setText(f.getTel());
		
	}
	
	public void actualiser(){
		listeFournisseurs=null;
		listeFournisseurs = fMod.findAll();
		ftm = new FournisseursTableModel(listeFournisseurs);
		table.setModel(ftm);		
	}
}
