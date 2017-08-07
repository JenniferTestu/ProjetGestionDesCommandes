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

import model.Evenement;
import model.EvenementModel;
import model.Fournisseur;
import model.FournisseurModel;
import net.miginfocom.swing.MigLayout;

/***
 * 
 * Cette classe correspond à l'onglet des evenements.
 *
 */
public class PanelEvenement extends JPanel {
	
	private JTable table;
	private EvenementModel eMod = new EvenementModel();
	private Evenement eSelect;
	private List<Evenement> listeEvenement;
	private EvenementTableModel etm;
	private int rowEvenement;
	private JButton btnImprimer;
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JLabel lblNom;
	private JLabel lblPrnom;
	private JTextArea textTitre;
	private JTextArea textTexte;
	private JLabel lblQuiAPass;

	/**
	 * Create the panel.
	 */
	public PanelEvenement() {
		setLayout(new MigLayout("", "[142.00,grow][grow][grow]", "[25.00%][][8.00%][][8.00%][8.00%][8.00%][8.00%][8.00%][20.00%][10.00%]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0 3 1,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);

		listeEvenement = eMod.findAll();
		EvenementTableModel ftm = new EvenementTableModel(listeEvenement);
		table.setModel(ftm);
		
		btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		lblQuiAPass = new JLabel("Information de l'\u00E9v\u00E9nement s\u00E9lectionn\u00E9 :");
		add(lblQuiAPass, "cell 0 1,aligny center");
		
		lblNom = new JLabel("Titre : ");
		add(lblNom, "flowx,cell 0 3");
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				Evenement ev = new Evenement();
				ev.setTitre(textTitre.getText());
				ev.setTexte(textTexte.getText());
				
				if(eMod.create(ev)){
					JOptionPane.showMessageDialog(null, "L'événement a été ajouté");
					actualiser();
				}
				else
					JOptionPane.showMessageDialog(null, "Echec de l'ajout de l'événement");
				
				
			}
		});
		add(btnAjouter, "cell 0 10 3 1,alignx center,aligny center");
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(eMod.delete(eSelect)){
					JOptionPane.showMessageDialog(null, "L'événement a été supprimé");
					actualiser();
				}
				else
					JOptionPane.showMessageDialog(null, "Echec de la supression de l'événement");

			}
		});
		add(btnSupprimer, "cell 0 10 3 1,alignx center,aligny center");
		
		btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Evenement ev = new Evenement();
				ev.setId(eSelect.getId());
				ev.setTitre(textTitre.getText());
				ev.setTexte(textTexte.getText());
				
				if(eMod.update(ev)){
					JOptionPane.showMessageDialog(null, "L'événement a été modifié");
					actualiser();
					remplirChamps(ev);
				}
				else
					JOptionPane.showMessageDialog(null, "Echec de la modification de l'événement");
				
			}
		});
		add(btnModifier, "cell 0 10 3 1,alignx center,aligny center");
		
		lblPrnom = new JLabel("Texte : ");
		add(lblPrnom, "flowx,cell 0 4 3 1");
		
		textTitre = new JTextArea();
		textTitre.setWrapStyleWord(true);
		textTitre.setRows(2);
		textTitre.setLineWrap(true);
		textTitre.setColumns(10);
		textTitre.setBackground(Color.WHITE);
		add(textTitre, "flowx,cell 0 3 3 1");
		
		textTexte = new JTextArea();
		textTexte.setWrapStyleWord(true);
		textTexte.setRows(5);
		textTexte.setLineWrap(true);
		textTexte.setColumns(20);
		textTexte.setBackground(Color.WHITE);
		add(textTexte, "cell 0 4 3 1");
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	
				rowEvenement = table.getSelectedRow();
				
				if(rowEvenement<0){
					System.out.println("Ligne négative !");
				}else{
					System.out.println("Ligne : " + rowEvenement);
					rowEvenement = table.convertRowIndexToModel(rowEvenement);
					eSelect = ftm.getRowAt(rowEvenement);
					System.out.println(eSelect.toString());
				}
				remplirChamps(eSelect);
	        }
		});
		
	}

	public void remplirChamps(Evenement e){
		textTitre.setText(e.getTitre());
		textTexte.setText(e.getTexte());
		
	}
	
	public void actualiser(){
		listeEvenement=null;
		listeEvenement = eMod.findAll();
		etm = new EvenementTableModel(listeEvenement);
		table.setModel(etm);		
	}

}
