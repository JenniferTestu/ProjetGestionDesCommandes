package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

/***
 * 
 * Cette classe correspond à la fenetre principale de l'application.
 *
 */
public class FenetrePrincipale extends JFrame {

	private JPanel contentPane;
	private PanelATraiter panelATraiter = new PanelATraiter();
	private PanelTraitee panelTraitee = new PanelTraitee();
	private PanelStock panelStock = new PanelStock();
	private PanelProduit panelProduit = new PanelProduit();
	private PanelFournisseur panelFournisseur = new PanelFournisseur();
	private PanelClient panelClient = new PanelClient();
	private PanelEvenement panelEvenement = new PanelEvenement();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePrincipale frame = new FenetrePrincipale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenetrePrincipale() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);
		
		JMenuItem mntmManuel = new JMenuItem("Manuel");
		mnAide.add(mntmManuel);
		
		JMenuItem mntmContacterLesDveloppeurs = new JMenuItem("Contacter les d\u00E9veloppeurs");
		mnAide.add(mntmContacterLesDveloppeurs);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		/***
		 * Ajout des onglets
		 *
		 */
		tabbedPane.addTab("Commandes \u00E0 traiter", null, panelATraiter, null);
		tabbedPane.setBackgroundAt(0, new Color(255, 153, 204));
		tabbedPane.addTab("Commandes trait\u00E9es", null, panelTraitee, null);
		tabbedPane.setBackgroundAt(1, new Color(0, 204, 153));
		tabbedPane.addTab("Stock", null, panelStock, null);
		tabbedPane.setBackgroundAt(2, new Color(0, 204, 204));
		tabbedPane.addTab("Gestion des produits", null, panelProduit, null);
		tabbedPane.setBackgroundAt(3, new Color(204, 204, 255));
		tabbedPane.addTab("Gestion des fournisseurs", null, panelFournisseur, null);
		tabbedPane.setBackgroundAt(4, new Color(240, 230, 140));
		tabbedPane.setBackgroundAt(3, new Color(204, 204, 255));
		tabbedPane.addTab("Annuaire des clients", null, panelClient, null);
		tabbedPane.setBackgroundAt(5, new Color(255, 192, 203));
		tabbedPane.setBackgroundAt(3, new Color(204, 204, 255));
		tabbedPane.addTab("Gestion des événements", null, panelEvenement, null);
		tabbedPane.setBackgroundAt(6, new Color(221, 160, 221));
		tabbedPane.setBackgroundAt(3, new Color(204, 204, 255));
	}

}
