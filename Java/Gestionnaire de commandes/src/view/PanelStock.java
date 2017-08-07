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
 * Cette classe correspond à l'onglet des stocks.
 *
 */
public class PanelStock extends JPanel {
	
	private JTable tableStocks;
	private JTable table;
	private ProduitModel prodMod = new ProduitModel();
	private Produit p;
	private List<Produit> listeProduits;
	private ProduitsTableModel ptm;
	private StockTableModel stm;
	private int rowProduits;
	private int row;
	private JButton btnImprimerLaCommande;
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JLabel lblNom;
	private JLabel lblPrnom;
	private JTextArea textTaille;
	private JTextArea textQuantite;
	private JLabel lblQuiAPass;
	private JLabel labelImage;
	private String pathSelect;
	private BufferedImage img;
	private JScrollPane scrollPane_1;
	
	/**
	 * Create the panel.
	 */
	public PanelStock() {
		setLayout(new MigLayout("", "[142.00,grow][grow][grow]", "[25.00%][][8.00%][][8.00%][8.00%][8.00%][8.00%][8.00%][20.00%,grow][10.00%]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0 3 1,grow");
		
		table = new JTable();
		table.setRowHeight (60) ; 
		scrollPane.setViewportView(table);

		listeProduits = prodMod.findAll();
		ProduitsTableModel ptm = new ProduitsTableModel(listeProduits);
		table.setModel(ptm);
		
		btnImprimerLaCommande = new JButton("Imprimer");
		btnImprimerLaCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		lblQuiAPass = new JLabel("Information du produit s\u00E9lectionn\u00E9 :");
		add(lblQuiAPass, "cell 0 1,aligny center");
		
		lblNom = new JLabel("Taille : ");
		add(lblNom, "flowx,cell 0 3");
		
		btnAjouter = new JButton("Ajouter taille");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				
			}
		});
		
		labelImage = new JLabel("");
		add(labelImage, "cell 2 3");
		
		scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 0 9 3 1,grow");
		add(btnAjouter, "cell 0 10 3 1,alignx center,aligny center");
		
		tableStocks = new JTable();
		scrollPane_1.setViewportView(tableStocks);
		
		btnSupprimer = new JButton("Supprimer taille");
		add(btnSupprimer, "cell 0 10 3 1,alignx center,aligny center");
		
		btnModifier = new JButton("Modifier stock");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		add(btnModifier, "cell 0 10 3 1,alignx center,aligny center");
		
		lblPrnom = new JLabel("Quantit\u00E9 : ");
		add(lblPrnom, "flowx,cell 0 4 3 1");
		
		textTaille = new JTextArea();
		textTaille.setWrapStyleWord(true);
		textTaille.setRows(1);
		textTaille.setLineWrap(true);
		textTaille.setColumns(10);
		textTaille.setBackground(Color.WHITE);
		add(textTaille, "flowx,cell 0 3");
		
		textQuantite = new JTextArea();
		textQuantite.setWrapStyleWord(true);
		textQuantite.setRows(1);
		textQuantite.setLineWrap(true);
		textQuantite.setColumns(10);
		textQuantite.setBackground(Color.WHITE);
		add(textQuantite, "cell 0 4 3 1");
		
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
					List<Produit> listeProduitsStock = prodMod.findStock(p.getId());					
					StockTableModel ptm = new StockTableModel(listeProduitsStock);
					tableStocks.setModel(ptm);
				}

	        }
		});
		
		tableStocks.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	
	        	row = tableStocks.getSelectedRow();
				
	        	if (!event.getValueIsAdjusting()) {
					System.out.println("Ligne : " + row);
					row = tableStocks.convertRowIndexToModel(row);
					//p.setTaille(stm.getRowAt(row).getTaille());
					//p.setQuantite(stm.getRowAt(row).getQuantite());
					//System.out.println(p.toString());
					
					System.out.println(stm.getRowAt(row).getTaille()+" "+stm.getRowAt(row).getQuantite());
					remplirChamps(p);
				}

	        }
		});
		
	}

	
	
	public void remplirChamps(Produit p){
		textTaille.setText(p.getTaille());
		textQuantite.setText(String.valueOf(p.getQuantite()));
		
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
		listeProduits = prodMod.findAll();
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
