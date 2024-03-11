package Interface;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Interface.Contenu.ModeListe;
import model.Mairie;
import model.Person;
import model.Person.civicState;
import model.Person.sexe;

public class CreateMenuBarre extends JMenuBar{
	
	//numero serial important pour la sauvegarde
	private static final long serialVersionUID = 1L;
	
	private JPanel contenu;
	private Mairie mairie;
	
	
	public CreateMenuBarre(JPanel contenu, Mairie mairie) {
		this.contenu = contenu;
		this.mairie = mairie;
		
		//construction du menu et des différents sous-menu qui le composent
		//+ ajout des listener necessaires
		
		JMenu RecherchePersonne = new JMenu("Recherche");
		JMenuItem Chercher = new JMenuItem("Chercher une personne par ID");
		JMenuItem SaisirPersonne = new JMenuItem("Saisir une nouvelle personne");
		JMenuItem ListePersonnes = new JMenuItem("Afficher la liste des personnes");
		RecherchePersonne.add(SaisirPersonne);
		RecherchePersonne.add(Chercher);
		RecherchePersonne.add(ListePersonnes);
		Chercher.addActionListener(this::RecherchePersonneListener);
		SaisirPersonne.addActionListener(this::SaisirPersonneListener);
		ListePersonnes.addActionListener(this::ListePersonneListener);
		this.add(RecherchePersonne);
		
		
		
		JMenu MenuMariage = new JMenu("Mariage");
		JMenuItem Marier = new JMenuItem("Enregistrer un mariage");
		Marier.addActionListener(this::MarierListener);
		JMenuItem ListeCouples = new JMenuItem("Afficher la liste des couples enregistrés");
		ListeCouples.addActionListener(this::ListeCouplesListener);
		MenuMariage.add(Marier);
		MenuMariage.add(ListeCouples);
		this.add(MenuMariage);
		
		JMenu MenuDivorce = new JMenu("Divorce");
		JMenuItem Divorcer = new JMenuItem("Enregistrer un divorce");
		Divorcer.addActionListener(this::DivorcerListener);
		JMenuItem ListeDivorces = new JMenuItem("Afficher la liste des divorces enrigistrés");
		ListeDivorces.addActionListener(this::ListeDivorcesListener);
		MenuDivorce.add(Divorcer);
		MenuDivorce.add(ListeDivorces);
		this.add(MenuDivorce);
		
		JMenu MenuNaissance = new JMenu("Naissance");
		JMenuItem Naissance = new JMenuItem("Enregistrer une naissance");
		Naissance.addActionListener(this::NaissanceListener);
		JMenuItem ListeNaissances = new JMenuItem("Afficher la liste des naissances enregistrées");
		ListeNaissances.addActionListener(this::ListeNaissancesListener);
		MenuNaissance.add(Naissance);
		MenuNaissance.add(ListeNaissances);
		this.add(MenuNaissance);
		
	}
	
	
	//fonction pour la recherche
	public void RecherchePersonneListener(ActionEvent e) {
		((Contenu) contenu).setMode(ModeListe.none);
		
		//chargement des différents objets panel dans le contenu de la fenetre d'interactions
		JTextField txtIdentifiant;
		GridBagLayout gbl_contenu = new GridBagLayout();
		gbl_contenu.columnWidths = new int[]{326, 554, 0};
		gbl_contenu.rowHeights = new int[] {49, 183};
		gbl_contenu.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contenu.rowWeights = new double[]{0.0, 1.0};
		contenu.setLayout(gbl_contenu);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contenu.add(panel, gbc_panel);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.LEFT, 10, 10);
		panel.setLayout(fl_panel);
		
		
		txtIdentifiant = new JTextField();
		panel.add(txtIdentifiant);
		txtIdentifiant.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 13));
		txtIdentifiant.setText("Identifiant");
		txtIdentifiant.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentifiant.setColumns(10);
		
		JButton actionCherche = new JButton("Chercher");
		panel.add(actionCherche);
		actionCherche.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 12));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contenu.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new GridLayout(5, 2, 0, 0));
		
		JLabel NameLabel = new JLabel("NOM : ");
		panel_1.add(NameLabel);
		NameLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 14));
		
		JLabel PrenomLabel = new JLabel("PRENOM : ");
		PrenomLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 14));
		panel_1.add(PrenomLabel);
		
		JLabel DateLabel = new JLabel("DATE DE NAISSANCE : ");
		DateLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 13));
		panel_1.add(DateLabel);
		
		JLabel SexeLabel = new JLabel("SEXE : ");
		SexeLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 14));
		panel_1.add(SexeLabel);
		
		JLabel SituationLabel = new JLabel("SITUATION : ");
		SituationLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 14));
		panel_1.add(SituationLabel);
		
		JLabel ConjointLabel = new JLabel("CONJOINT : ");
		ConjointLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 14));
		panel_1.add(ConjointLabel);
		
		
		//fonction listener pour le bouton chercher
		actionCherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = (txtIdentifiant.getText()).trim();
				if(!id.matches("\\d+")) {
					JOptionPane.showMessageDialog(null, "Veuillez remplir avec un identifiant valide");
				}
				else {
					Person person = mairie.getperson(Integer.valueOf(id));
    				if(person!=null) {
						NameLabel.setText("NOM : " +person.getName());
						PrenomLabel.setText("PRENOM : " +person.getFirstName());
						DateLabel.setText("DATE DE NAISSANCE : " +person.getBirthday());
						SexeLabel.setText("SEXE : " +person.getSexe());
						SituationLabel.setText("SITUATION : " +person.getSituation());
						if(person.getConjoint()!=null) {
							ConjointLabel.setText("CONJOINT : " +person.getConjoint().getName() + " " + person.getConjoint().getFirstName());
						}
						else {
							ConjointLabel.setText("CONJOINT : / " );
						}
    				}
    				else {
    					JOptionPane.showMessageDialog(null, "L'identifiant saisie ne correspend à aucune personne");
    				}
				}
				
			}
		});
	}
	
	
	public void MarierListener(ActionEvent e) {
		((Contenu) contenu).setMode(ModeListe.none);
		GridBagLayout gbl_contenu2 = new GridBagLayout();
    	gbl_contenu2.columnWidths = new int[] {30, 200, 30};
    	gbl_contenu2.rowHeights = new int[] {55, 55, 55, 55, 55, 40};
    	gbl_contenu2.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
    	gbl_contenu2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    	contenu.setLayout(gbl_contenu2);
    	
    	JLabel ContenuNameLabel = new JLabel("Mariage   ");
    	ContenuNameLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 20));
    	GridBagConstraints gbc_ContenuNameLabel = new GridBagConstraints();
    	gbc_ContenuNameLabel.fill = GridBagConstraints.VERTICAL;
    	gbc_ContenuNameLabel.insets = new Insets(0, 0, 5, 0);
    	gbc_ContenuNameLabel.gridx = 1;
    	gbc_ContenuNameLabel.gridy = 1;
    	contenu.add(ContenuNameLabel, gbc_ContenuNameLabel);
    	
    	JTextField txtIdentifiant2 = new JTextField();
    	txtIdentifiant2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 13));
    	txtIdentifiant2.setText("Identifiant");
    	txtIdentifiant2.setHorizontalAlignment(SwingConstants.CENTER);
    	GridBagConstraints gbc_txtIdentifiant2 = new GridBagConstraints();
    	gbc_txtIdentifiant2.fill = GridBagConstraints.BOTH;
    	gbc_txtIdentifiant2.insets = new Insets(0, 0, 5, 0);
    	gbc_txtIdentifiant2.gridx = 1;
    	gbc_txtIdentifiant2.gridy = 2;
    	contenu.add(txtIdentifiant2, gbc_txtIdentifiant2);
    	txtIdentifiant2.setColumns(10);
    	contenu.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 13));
    	contenu.setBorder(new EmptyBorder(0, 60, 50, 60));
    	
    	
    	
    	JTextField txtIdentifiant1 = new JTextField();
    	txtIdentifiant1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 13));
    	txtIdentifiant1.setText("Identifiant");
    	txtIdentifiant1.setHorizontalAlignment(SwingConstants.CENTER);
    	GridBagConstraints gbc_txtIdentifiant1 = new GridBagConstraints();
    	gbc_txtIdentifiant1.fill = GridBagConstraints.BOTH;
    	gbc_txtIdentifiant1.insets = new Insets(0, 0, 5, 0);
    	gbc_txtIdentifiant1.gridx = 1;
    	gbc_txtIdentifiant1.gridy = 3;
    	contenu.add(txtIdentifiant1, gbc_txtIdentifiant1);
    	txtIdentifiant1.setColumns(10);
    	
    	JTextField txtDate = new JTextField();
    	txtDate.setText("jj/mm/aaaa");
    	txtDate.setHorizontalAlignment(SwingConstants.CENTER);
    	GridBagConstraints gbc_txtDate = new GridBagConstraints();
    	gbc_txtDate.insets = new Insets(0, 0, 5, 0);
    	gbc_txtDate.fill = GridBagConstraints.BOTH;
    	gbc_txtDate.gridx = 1;
    	gbc_txtDate.gridy = 4;
    	contenu.add(txtDate, gbc_txtDate);
    	txtDate.setColumns(10);
    	
    	JButton Enregistrer = new JButton("Enregistrer");
    	Enregistrer.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			String strId1 = (txtIdentifiant1.getText()).trim();
        		String strId2 = (txtIdentifiant2.getText()).trim();
        		String strDate = (txtDate.getText()).trim();
        		if(((Contenu) contenu).formulaireValide(strId1, strId2, strDate)) {
        			mairie.marier(Integer.valueOf(strId1), Integer.valueOf(strId2),  strDate);
        		}
    		}
    	});
  
    	
    	
    	GridBagConstraints gbc_identifiant2 = new GridBagConstraints();
    	gbc_identifiant2.insets = new Insets(0, 0, 5, 0);
    	gbc_identifiant2.anchor = GridBagConstraints.WEST;
    	gbc_identifiant2.fill = GridBagConstraints.VERTICAL;
    	gbc_identifiant2.gridx = 1;
    	gbc_identifiant2.gridy = 5;
    	contenu.add(Enregistrer, gbc_identifiant2);
	}
	
	public void DivorcerListener(ActionEvent e) {
		((Contenu) contenu).setMode(ModeListe.none);	
		GridBagLayout gbl_contenuDivorce = new GridBagLayout();
    	gbl_contenuDivorce.columnWidths = new int[] {30, 200, 30};
    	gbl_contenuDivorce.rowHeights = new int[] {55, 55, 55, 55, 55, 40};
    	gbl_contenuDivorce.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
    	gbl_contenuDivorce.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    	contenu.setLayout(gbl_contenuDivorce);
    	
    	
    	JLabel ContenuNameLabelDivorce = new JLabel("Divorce   ");
    	ContenuNameLabelDivorce.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 20));
    	GridBagConstraints gbc_ContenuNameLabelDivorce = new GridBagConstraints();
    	gbc_ContenuNameLabelDivorce.fill = GridBagConstraints.VERTICAL;
    	gbc_ContenuNameLabelDivorce.insets = new Insets(0, 0, 5, 0);
    	gbc_ContenuNameLabelDivorce.gridx = 1;
    	gbc_ContenuNameLabelDivorce.gridy = 1;
    	contenu.add(ContenuNameLabelDivorce, gbc_ContenuNameLabelDivorce);
    	
    	
    	JTextField Identifiant1 = new JTextField();
    	Identifiant1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 13));
    	Identifiant1.setText("Identifiant");
    	Identifiant1.setHorizontalAlignment(SwingConstants.CENTER);
    	GridBagConstraints gbc_Identifiant3 = new GridBagConstraints();
    	gbc_Identifiant3.fill = GridBagConstraints.BOTH;
    	gbc_Identifiant3.insets = new Insets(0, 0, 5, 0);
    	gbc_Identifiant3.gridx = 1;
    	gbc_Identifiant3.gridy = 2;
    	contenu.add(Identifiant1, gbc_Identifiant3);
    	Identifiant1.setColumns(10);
    	
    	JTextField Identifiant2 = new JTextField();
    	Identifiant2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 13));
    	Identifiant2.setText("Identifiant");
    	Identifiant2.setHorizontalAlignment(SwingConstants.CENTER);
    	GridBagConstraints gbc_Identifiant4 = new GridBagConstraints();
    	gbc_Identifiant4.fill = GridBagConstraints.BOTH;
    	gbc_Identifiant4.insets = new Insets(0, 0, 5, 0);
    	gbc_Identifiant4.gridx = 1;
    	gbc_Identifiant4.gridy = 3;
    	contenu.add(Identifiant2, gbc_Identifiant4);
    	Identifiant2.setColumns(10);
    	
    	JTextField txtDate1 = new JTextField();
    	contenu.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 13));
    	txtDate1.setText("jj/mm/aaaa");
    	txtDate1.setHorizontalAlignment(SwingConstants.CENTER);
    	GridBagConstraints gbc_txtDate1 = new GridBagConstraints();
    	gbc_txtDate1.insets = new Insets(0, 0, 5, 0);
    	gbc_txtDate1.fill = GridBagConstraints.BOTH;
    	gbc_txtDate1.gridx = 1;
    	gbc_txtDate1.gridy = 4;
    	contenu.add(txtDate1, gbc_txtDate1);
    	txtDate1.setColumns(10);
    	
    	JButton Enregistre = new JButton("Enregistrer");
    	Enregistre.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			String strId1 = (Identifiant1.getText()).trim();
        		String strId2 = (Identifiant2.getText()).trim();
        		String strDate = (txtDate1.getText()).trim();
        		
        		if(((Contenu) contenu).formulaireValide(strId1, strId2, strDate)) {
        			mairie.divorcer(Integer.valueOf(strId1), Integer.valueOf(strId2),  strDate);
        		}		
    		}
    	});
    	
    	GridBagConstraints gbc_identifiant4 = new GridBagConstraints();
    	gbc_identifiant4.anchor = GridBagConstraints.WEST;
    	gbc_identifiant4.insets = new Insets(0, 0, 5, 0);
    	gbc_identifiant4.fill = GridBagConstraints.VERTICAL;
    	gbc_identifiant4.gridx = 1;
    	gbc_identifiant4.gridy = 5;
    	contenu.add(Enregistre, gbc_identifiant4);
    	contenu.setBorder(new EmptyBorder(0, 60, 50, 60));
	}
	public void NaissanceListener(ActionEvent e) {
		((Contenu) contenu).setMode(ModeListe.none);	
		GridBagLayout gbl_contenuNaissance = new GridBagLayout();
    	gbl_contenuNaissance.columnWidths = new int[] {30, 200, 30};
    	gbl_contenuNaissance.rowHeights = new int[] {55, 55, 55, 55, 55, 40};
    	gbl_contenuNaissance.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
    	gbl_contenuNaissance.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    	contenu.setLayout(gbl_contenuNaissance);
    	
    	
    	JLabel ContenuNameLabelNaissance = new JLabel("Naissance   ");
    	ContenuNameLabelNaissance.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 20));
    	GridBagConstraints gbc_contenuNaissance = new GridBagConstraints();
    	gbc_contenuNaissance.fill = GridBagConstraints.VERTICAL;
    	gbc_contenuNaissance.insets = new Insets(0, 0, 5, 0);
    	gbc_contenuNaissance.gridx = 1;
    	gbc_contenuNaissance.gridy = 1;
    	contenu.add(ContenuNameLabelNaissance, gbc_contenuNaissance);
    	
    	
    	JTextField pere = new JTextField();
    	pere.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 13));
    	pere.setText("Identifiant");
    	pere.setHorizontalAlignment(SwingConstants.CENTER);
    	GridBagConstraints gbc_pere = new GridBagConstraints();
    	gbc_pere.fill = GridBagConstraints.BOTH;
    	gbc_pere.insets = new Insets(0, 0, 5, 0);
    	gbc_pere.gridx = 1;
    	gbc_pere.gridy = 2;
    	contenu.add(pere, gbc_pere);
    	pere.setColumns(10);
    	
    	JTextField mere = new JTextField();
    	mere.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 13));
    	mere.setText("Identifiant");
    	mere.setHorizontalAlignment(SwingConstants.CENTER);
    	GridBagConstraints gbc_mere = new GridBagConstraints();
    	gbc_mere.fill = GridBagConstraints.BOTH;
    	gbc_mere.insets = new Insets(0, 0, 5, 0);
    	gbc_mere.gridx = 1;
    	gbc_mere.gridy = 3;
    	contenu.add(mere, gbc_mere);
    	mere.setColumns(10);
    	
    	JTextField birthday = new JTextField();
    	contenu.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 13));
    	birthday.setText("jj/mm/aaaa");
    	birthday.setHorizontalAlignment(SwingConstants.CENTER);
    	GridBagConstraints gbc_birthday = new GridBagConstraints();
    	gbc_birthday.insets = new Insets(0, 0, 5, 0);
    	gbc_birthday.fill = GridBagConstraints.BOTH;
    	gbc_birthday.gridx = 1;
    	gbc_birthday.gridy = 4;
    	contenu.add(birthday, gbc_birthday);
    	birthday.setColumns(10);
    	
    	JButton EnregistreNaissance = new JButton("Enregistrer");
    	EnregistreNaissance.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			String strId1 = (pere.getText()).trim();
        		String strId2 = (mere.getText()).trim();
        		String strDate = (birthday.getText()).trim();
        		if(((Contenu) contenu).formulaireValide(strId1, strId2, strDate)) {
        			mairie.naitre(Integer.valueOf(strId1), Integer.valueOf(strId2),  strDate);
        		}
    		}
    		
    	});
    	
    	GridBagConstraints gbc_identifiant5 = new GridBagConstraints();
    	gbc_identifiant5.anchor = GridBagConstraints.WEST;
    	gbc_identifiant5.insets = new Insets(0, 0, 5, 0);
    	gbc_identifiant5.fill = GridBagConstraints.VERTICAL;
    	gbc_identifiant5.gridx = 1;
    	gbc_identifiant5.gridy = 5;
    	contenu.add(EnregistreNaissance, gbc_identifiant5);
    	contenu.setBorder(new EmptyBorder(0, 60, 50, 60));
	}
	public void SaisirPersonneListener(ActionEvent e) {
		((Contenu) contenu).setMode(ModeListe.none);	
		
		GridBagLayout gbl_contenuSaisie = new GridBagLayout();
		gbl_contenuSaisie.columnWidths = new int[]{326, 554, 0};
		gbl_contenuSaisie.rowHeights = new int[] {49, 183};
		gbl_contenuSaisie.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contenuSaisie.rowWeights = new double[]{0.0, 1.0};
		contenu.setLayout(gbl_contenuSaisie);
		
		JPanel panelSaisie = new JPanel();
		panelSaisie.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelSaisie.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_panelSaisie = new GridBagConstraints();
		gbc_panelSaisie.anchor = GridBagConstraints.WEST;
		gbc_panelSaisie.gridwidth = 2;
		gbc_panelSaisie.fill = GridBagConstraints.BOTH;
		gbc_panelSaisie.insets = new Insets(0, 0, 5, 0);
		gbc_panelSaisie.gridx = 0;
		gbc_panelSaisie.gridy = 0;
		contenu.add(panelSaisie, gbc_panelSaisie);
		FlowLayout fl_panelSaisie = new FlowLayout(FlowLayout.LEFT, 10, 10);
		panelSaisie.setLayout(fl_panelSaisie);
		
		
		JLabel Formulaire = new JLabel();
		panelSaisie.add(Formulaire);
		Formulaire.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
		Formulaire.setText("Ajouter une Nouvelle Personne");
		Formulaire.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton ActionAjouter = new JButton("Ajouter");
		
		
		
		panelSaisie.add(ActionAjouter);
		ActionAjouter.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 12));
		
		JPanel panel_1saisie = new JPanel();
		panel_1saisie.setBackground(Color.LIGHT_GRAY);
		panel_1saisie.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagConstraints gbc_panel_1Saisie = new GridBagConstraints();
		gbc_panel_1Saisie.gridwidth = 2;
		gbc_panel_1Saisie.fill = GridBagConstraints.BOTH;
		gbc_panel_1Saisie.gridx = 0;
		gbc_panel_1Saisie.gridy = 1;
		contenu.add(panel_1saisie, gbc_panel_1Saisie);
		panel_1saisie.setLayout(new GridLayout(0, 4, 20, 70));
		
		JLabel NewNameLabel = new JLabel("NOM : ");
		panel_1saisie.add(NewNameLabel);
		NewNameLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		
		JTextField newName = new JTextField();
		panel_1saisie.add(newName);
		newName.setColumns(10);
		
		JLabel NewPrenomLabel = new JLabel("PRENOM : ");
		NewPrenomLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		panel_1saisie.add(NewPrenomLabel);
		
		JTextField newPrenom = new JTextField();
		panel_1saisie.add(newPrenom);
		newPrenom.setColumns(10);
		
		JLabel Birth = new JLabel("DATE DE NAISSANCE : ");
		Birth.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		panel_1saisie.add(Birth);
		
		JTextField getDate = new JTextField();
		panel_1saisie.add(getDate);
		getDate.setColumns(10);
		
		JLabel NewSexeLabel = new JLabel("SEXE : ");
		NewSexeLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		panel_1saisie.add(NewSexeLabel);
		
		JComboBox comboBox_saisie = new JComboBox(civicState.values());
		panel_1saisie.add(comboBox_saisie);
		
		JLabel newSituationLabel = new JLabel("SITUATION : ");
		newSituationLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		panel_1saisie.add(newSituationLabel);
		
		JComboBox comboBoxSexe = new JComboBox(sexe.values());
		panel_1saisie.add(comboBoxSexe);
		
		JLabel newConjointLabel = new JLabel("Id-CONJOINT : ");
		newConjointLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		panel_1saisie.add(newConjointLabel);
		
		JTextField newConjoint = new JTextField();
		panel_1saisie.add(newConjoint);
		newConjoint.setColumns(10);
		
		
		ActionAjouter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String nom = (newName.getText()).trim();
				String prenom = (newPrenom.getText()).trim();
				String naissanceDate = (getDate.getText()).trim();
				String choixSexe = String.valueOf(comboBoxSexe.getSelectedItem());
				String choixSituation = String.valueOf(comboBox_saisie.getSelectedItem());
				String strIdConjoint = (newConjoint.getText()).trim();
				if(!nom.matches("^[^\\d]+$") || !prenom.matches("^[^\\d]+$")) {
					JOptionPane.showMessageDialog(null, "Veuillez saisir un Nom et un Prenom valide" );
				}
				else {
					if(((Contenu) contenu).estDateValide(naissanceDate)) {
						if(choixSituation == "marie" || choixSituation == "veuve") {
    						int idConjoint = Integer.valueOf(strIdConjoint);
        					mairie.addPersonne(nom, prenom, choixSexe, naissanceDate, choixSituation, idConjoint);
    					}
    					else {
    						mairie.addPersonne(nom, prenom, choixSexe, naissanceDate, choixSituation, -1);
    					}
    					JOptionPane.showMessageDialog(null, "Individu enregistré avec succés" );
					}	
				}
				
    		}
		});
	}
	
	//initier le mode d'affichage liste pour les différentes données listées
	public void ListePersonneListener(ActionEvent e) {
		((Contenu) contenu).setMode(ModeListe.Personnes);
		((Contenu) contenu).affichageModeListe("Mr/Mme : ", "Né(e) le ", "A comme situation : ", "Conjoint : ", "Note particulière");
	}
	
	
	public void ListeCouplesListener(ActionEvent e) {
		((Contenu) contenu).setMode(ModeListe.Couples);
		((Contenu) contenu).affichageModeListe("Conjoint 1 : ", "Conjoint 2 ", "Date de mariage : ", "", "");
	}
	
	
	public void ListeDivorcesListener(ActionEvent e) {
		((Contenu) contenu).setMode(ModeListe.Divorces);
		((Contenu) contenu).affichageModeListe("Conjoint 1 : ", "Conjoint 2 ", "Date de divorce : ", "", "");
	}
	
	
	public void ListeNaissancesListener(ActionEvent e) {
		((Contenu) contenu).setMode(ModeListe.Naissances);
		((Contenu) contenu).affichageModeListe("Pere : ", "Mere : ", "Date de naissance : ", "", "");	
	}
	
	
	
	
	
	
}
