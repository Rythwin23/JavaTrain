package Interface;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Interface.Contenu.ModeListe;
import model.Mairie;
import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;

public class Fenetre extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6440078781309405401L;
	
	//constructeur pour la fenetre principale
	public Fenetre(Mairie mairie) throws UnsupportedLookAndFeelException {
		super("Fenêtre principale");
		getContentPane().setBackground(new Color(227, 227, 227));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 350);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
		//theme pour swing
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
    	
    	JPanel contenu = new Contenu(mairie);
    	contenu.setBorder(null);
    	contenu.setBackground(UIManager.getColor("Button.background"));
    	
    	//création du menu barre
    	JMenuBar menu = new CreateMenuBarre(contenu, mairie);
    	this.setJMenuBar(menu);
    	getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
    	getContentPane().add(contenu);
    	GridBagLayout gbl_contenu = new GridBagLayout();
    	gbl_contenu.columnWidths = new int[]{590, 0};
    	gbl_contenu.rowHeights = new int[]{291, 0};
    	gbl_contenu.columnWeights = new double[]{0.0, Double.MIN_VALUE};
    	gbl_contenu.rowWeights = new double[]{0.0, Double.MIN_VALUE};
    	contenu.setLayout(gbl_contenu);
    	
    	
    	//affichage principale lors du lancement
    	JLabel lblNewLabel = new JLabel("GESTIONNAIRE DE CITOYENS  ");
    	lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 21));
    	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
    	gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
    	gbc_lblNewLabel.gridx = 0;
    	gbc_lblNewLabel.gridy = 0;
    	contenu.add(lblNewLabel, gbc_lblNewLabel);
    	contenu.updateUI();
    	
    	
    	
    	
	}

}
