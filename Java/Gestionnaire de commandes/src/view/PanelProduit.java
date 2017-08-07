package view;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Commande;
import model.CommandeModel;
import model.Fournisseur;
import model.Produit;
import model.ProduitModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Color;

/***
 * 
 * Cette classe correspond à l'onglet des produits.
 *
 */
public class PanelProduit extends JPanel {
	private JTable table;
	private JTable tableFournisseurs;
	private ProduitModel comProd = new ProduitModel();
	private Produit p;
	private Fournisseur f;
	private List<Produit> listeProduits;
	private ProduitsTableModel ptm;
	private int rowProduits;
	private int row;
	private JButton btnImprimerLaCommande;
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JLabel lblNom;
	private JLabel lblPrnom;
	private JLabel lblAdresse;
	private JLabel lblMail;
	private JLabel lblPays;
	private JTextArea textSousCat;
	private JTextArea textNom;
	private JTextArea textCategorie;
	private JTextArea textPrix;
	private JTextArea textDetails;
	private JLabel lblQuiAPass;
	private JLabel label;
	private JLabel labelImage;
	private JButton btnParcourir;
	private String pathSelect;
	private BufferedImage img;
	private JTextField textField;
	private JButton btnAjouterFournisseur;
	private JButton btnSupprimerFournisseur;
	private FournisseursTableModel ftm;
	
	/**
	 * Create the panel.
	 */
	public PanelProduit() {
		setLayout(new MigLayout("", "[142.00,grow][grow][grow]", "[25.00%][][8.00%][][8.00%][8.00%][8.00%][8.00%][8.00%][20.00%][][10.00%]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0 3 1,grow");
		
		table = new JTable();
		table.setRowHeight (60) ; 
		scrollPane.setViewportView(table);

		listeProduits = comProd.findAll();
		ProduitsTableModel ptm = new ProduitsTableModel(listeProduits);
		table.setModel(ptm);
		
		btnImprimerLaCommande = new JButton("Imprimer");
		btnImprimerLaCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		lblQuiAPass = new JLabel("Information du produit s\u00E9lectionn\u00E9 :");
		add(lblQuiAPass, "cell 0 1,aligny center");
		
		lblNom = new JLabel("Nom : ");
		add(lblNom, "flowx,cell 0 3");
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 0 9 3 1,grow");
		tableFournisseurs = new JTable();
		scrollPane_1.setViewportView(tableFournisseurs);
		
		labelImage = new JLabel("");
		add(labelImage, "cell 2 3");
		
		textField = new JTextField();
		add(textField, "cell 0 10,alignx center,aligny center");
		textField.setColumns(10);
		
		btnAjouterFournisseur = new JButton("Ajouter fournisseur");
		btnAjouterFournisseur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				/*if(comProd.createFournisseur(p,f)){
					JOptionPane.showMessageDialog(null, "Le fournisseur a été ajouté");
					tableFournisseurs.removeAll();
					actualiser();
				}*/
			}
		});
		add(btnAjouterFournisseur, "cell 1 10,alignx center,aligny center");
		
		btnSupprimerFournisseur = new JButton("Supprimer fournisseur");
		add(btnSupprimerFournisseur, "cell 2 10,alignx center,aligny center");
		add(btnAjouter, "cell 0 11 3 1,alignx center,aligny center");
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(comProd.deleteFournisseur(p,f)){
					JOptionPane.showMessageDialog(null, "Le fournisseur a été supprimé");
					tableFournisseurs.removeAll();
					actualiser();
				}
			}
		});
		add(btnSupprimer, "cell 0 11 3 1,alignx center,aligny center");
		
		btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		add(btnModifier, "cell 0 11 3 1,alignx center,aligny center");
		
		lblMail = new JLabel("D\u00E9tails : ");
		add(lblMail, "flowx,cell 0 7 3 1");
		
		lblPays = new JLabel("Prix Unitaire : ");
		add(lblPays, "flowx,cell 0 6 3 1");
		
		lblAdresse = new JLabel("Sous cat\u00E9gorie : ");
		add(lblAdresse, "flowx,cell 0 5 3 1");
		
		textSousCat = new JTextArea();
		textSousCat.setWrapStyleWord(true);
		textSousCat.setLineWrap(true);
		textSousCat.setRows(1);
		textSousCat.setBackground(Color.WHITE);
		textSousCat.setColumns(10);
		add(textSousCat, "cell 0 5 3 1");
		
		lblPrnom = new JLabel("Cat\u00E9gorie : ");
		add(lblPrnom, "flowx,cell 0 4 3 1");
		
		textNom = new JTextArea();
		textNom.setWrapStyleWord(true);
		textNom.setRows(2);
		textNom.setLineWrap(true);
		textNom.setColumns(10);
		textNom.setBackground(Color.WHITE);
		add(textNom, "flowx,cell 0 3");
		
		textCategorie = new JTextArea();
		textCategorie.setWrapStyleWord(true);
		textCategorie.setRows(1);
		textCategorie.setLineWrap(true);
		textCategorie.setColumns(10);
		textCategorie.setBackground(Color.WHITE);
		add(textCategorie, "cell 0 4 3 1");
		
		textPrix = new JTextArea();
		textPrix.setWrapStyleWord(true);
		textPrix.setRows(1);
		textPrix.setLineWrap(true);
		textPrix.setColumns(10);
		textPrix.setBackground(Color.WHITE);
		add(textPrix, "cell 0 6 3 1");
		
		label = new JLabel("\u20AC");
		add(label, "cell 0 6 3 1");
		
		textDetails = new JTextArea();
		textDetails.setWrapStyleWord(true);
		textDetails.setRows(2);
		textDetails.setLineWrap(true);
		textDetails.setColumns(10);
		textDetails.setBackground(Color.WHITE);
		add(textDetails, "cell 0 7 3 1");
		
		btnParcourir = new JButton("Parcourir ...");
		btnParcourir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(); 
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); 
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png"); 
				fileChooser.addChoosableFileFilter(filter); 
				int result = fileChooser.showSaveDialog(null); 
				if(result == JFileChooser.APPROVE_OPTION){ 
					File selectedFile = fileChooser.getSelectedFile(); 
					String path = selectedFile.getAbsolutePath(); 
					labelImage.setIcon(ResizeImagePath(path)); 
					pathSelect = path; 
					} else if(result == JFileChooser.CANCEL_OPTION){ 
						System.out.println("No Data"); 
					}
				}
		});
		add(btnParcourir, "cell 2 7");
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	
				rowProduits = table.getSelectedRow();
				
				if(rowProduits<0){
					System.out.println("Ligne négative !");
				}else{
					System.out.println("Ligne : " + rowProduits);
					rowProduits = table.convertRowIndexToModel(rowProduits);
					p = ptm.getRowAt(rowProduits);
					System.out.println(p.toString());
					ftm = new FournisseursTableModel(p.getListeFournisseurs());
					tableFournisseurs.setModel(ftm);
				}
				remplirChamps(p);
	        }
		});
		
		tableFournisseurs.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	
				row = tableFournisseurs.getSelectedRow();
				
				if(row<0){
					System.out.println("Ligne négative !");
				}else{
					System.out.println("Ligne : " + row);
					row = tableFournisseurs.convertRowIndexToModel(row);
					f = ftm.getRowAt(row);
					System.out.println(p.toString());
				}
				remplirChamps(p);
	        }
		});
	}

	public void remplirChamps(Produit p){
		textNom.setText(p.getNom());
		textCategorie.setText(p.getCategorie());
		textSousCat.setText(p.getSousCategorie());
		textPrix.setText(String.valueOf(p.getPrixUni()));
		textDetails.setText(p.getDetails());
		
		
		labelImage.setIcon(ResizeImage(p.getImage()));
		
		/*try {
			img = ImageIO.read(new URL("http://autourdelamoto.esy.es/images_inventaire/"+p.getId()+".jpg"));
			ImageIcon imgicon = new ImageIcon(img);
			Image img = imgicon.getImage();
			Image resizeImg = img.getScaledInstance(-1, 100, Image.SCALE_SMOOTH);
			imgicon = new ImageIcon(resizeImg);
			labelImage.setIcon(imgicon);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}
	
	public void actualiser(){
		listeProduits=null;
		listeProduits = comProd.findAll();
		ptm = new ProduitsTableModel(listeProduits);
		table.setModel(ptm);		
	}
	
	private ImageIcon ResizeImagePath(String imgPath){ 
		ImageIcon MyImage = new ImageIcon(imgPath); 
		Image img = MyImage.getImage(); 
		Image newImage = img.getScaledInstance(-1, 100,Image.SCALE_SMOOTH); 
		ImageIcon image = new ImageIcon(newImage); 
		return image; 
	}
	
	private ImageIcon ResizeImage(ImageIcon MyImage){ 
		Image img = MyImage.getImage(); 
		Image newImage = img.getScaledInstance(-1, 200,Image.SCALE_SMOOTH); 
		ImageIcon image = new ImageIcon(newImage); 
		return image; 
	}
}
