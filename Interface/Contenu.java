package Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Divorce;
import model.Mairie;
import model.Mariage;
import model.Naissance;
import model.Person;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Contenu extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mairie mairie;

	//La liste des choix pour le mode d'affichage
	public enum ModeListe{
		none,
		Personnes,
		Couples,
		Divorces,
		Naissances
	}
	private ModeListe mode;
	private JTable table;

	
    public Contenu(Mairie mairie) {
    	this.mairie = mairie;
        this.setBackground(Color.GRAY);
        
    }
    
    
    //fonction pour reload l'affichage 
    public void setMode(ModeListe m) {
		this.mode = m;
		this.removeAll();
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setBackground(Color.GRAY);
		this.updateUI();
    }
    
    
    //verifier si une date est valide de format dd/MM/yyyy par localdate
    public boolean estDateValide(String str) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(str, formatter);
            return true;
        } catch (DateTimeParseException e) {
        	JOptionPane.showMessageDialog(null, "La date saisie n'est pas valide" );
            return false;
        }
    }
    
    
    //verifier si le fomulaire à remplir est valide(pas de vide et indentifiants valides
    public boolean formulaireValide(String strId1, String strId2, String strDate) {
		if(strId1.matches("\\d+") && strId2.matches("\\d+") && estDateValide(strDate)) {
			return true;
		}
		else if(!strId1.matches("\\d+") || !strId2.matches("\\d+")) {
			JOptionPane.showMessageDialog(null, "Les identifiant ne sont pas valides" );
			return false;
		}
		else if(!estDateValide(strDate)) {
			JOptionPane.showMessageDialog(null, "La date saisie n'est pas valide" );
			return false;
		}
		
		else {
			return false;
		}
    }
    
    //fonction pour le mode d'affichage 
    public void affichageModeListe(String t1, String t2, String t3, String t4, String t5) {
    	GridBagLayout gbl_contenuListe = new GridBagLayout();
    	gbl_contenuListe .columnWidths = new int[]{591};
    	gbl_contenuListe .rowHeights = new int[]{32, 0, 0};
    	gbl_contenuListe .columnWeights = new double[]{1.0};
    	gbl_contenuListe .rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
    	this.setLayout(gbl_contenuListe );
    	
    	JLabel labelTitreListe = new JLabel("TITRE DE LA LISTE");
    	labelTitreListe.setHorizontalAlignment(SwingConstants.CENTER);
    	labelTitreListe.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 16));
    	GridBagConstraints gbc_labelTitreListe = new GridBagConstraints();
    	gbc_labelTitreListe.insets = new Insets(0, 0, 5, 0);
    	gbc_labelTitreListe.gridx = 0;
    	gbc_labelTitreListe.gridy = 0;
    	this.add(labelTitreListe, gbc_labelTitreListe);
    	
    	JSplitPane splitPaneListe = new JSplitPane();
    	GridBagConstraints gbc_splitPaneListe = new GridBagConstraints();
    	gbc_splitPaneListe.fill = GridBagConstraints.BOTH;
    	gbc_splitPaneListe.gridx = 0;
    	gbc_splitPaneListe.gridy = 1;
    	this.add(splitPaneListe, gbc_splitPaneListe);
    	
    	
    	//nouveau tableau (pour afficher les données listées
    	table = new JTable();
   	 	splitPaneListe.setLeftComponent(table);
    	
    	
    	JPanel informations = new JPanel();
    	splitPaneListe.setRightComponent(informations);
    	informations.setLayout(new GridLayout(0, 1, 0, 0));
    	
    	JLabel label1 = new JLabel(t1);
    	label1.setHorizontalAlignment(SwingConstants.LEFT);
    	informations.add(label1);
    	
    	JLabel label2 = new JLabel(t2);
    	informations.add(label2);
    	
    	JLabel label3 = new JLabel(t3);
    	informations.add(label3);
    	
    	JLabel label4 = new JLabel(t4);
    	informations.add(label4);
    	
    	JLabel label5 = new JLabel(t5);
    	informations.add(label5);
    	
    	 switch(this.mode){
    	 
    	 //cas affichage liste de personnnes
         case Personnes:
        	 labelTitreListe.setText("Listes des personnes");
        	 
        	 //initialiser un model de tableau
        	 table.removeAll();
        	 table.setModel(new DefaultTableModel(
        	    		new Object[][] {
        	    			{"Id", "Nom", "Prenom"}
        	    		},
        	    		new String[] {
        	    			"Identifiant", "Nom", "Prenom"
        	    		}
        	    	) {
		
        				private static final long serialVersionUID = 1L;
        				
        				Class[] columnTypes = new Class[] {
        	    			Integer.class, String.class, String.class
        	    		};
        	    		public Class getColumnClass(int columnIndex) {
        	    			return columnTypes[columnIndex];
        	    		}
        	    		boolean[] columnEditables = new boolean[] {
        	    			false, false, false
        	    		};
        	    		public boolean isCellEditable(int row, int column) {
        	    			return columnEditables[column];
        	    		};
    	    			
        	    	});
        	 
        	 DefaultTableModel model = (DefaultTableModel) table.getModel();
        	 for(Person p: mairie.affichageListePersonnes()) {
        		 Object[] element = {p.getId(), p.getName(), p.getFirstName()};
        		 model.addRow(element);
        	 }
        	 
        	 
        	 //recuperer le clique de la souris sur une ligne du tableau
        	 table.addMouseListener((MouseListener) new MouseAdapter() {
        		    @Override
        		    public void mouseClicked(MouseEvent e) {
        		        int ligneSelectionnee = table.getSelectedRow();
        		        
        		       
        		        if(ligneSelectionnee>0) {
        		        	int valeurColonne = (int) table.getValueAt(ligneSelectionnee, 0);
        		        	Person p = mairie.getperson(valeurColonne);
            		        label1.setText(t1 + p.getName() + " " + p.getFirstName());
            		        label2.setText(t2 + p.getBirthday() );
            		        label3.setText(t3 + p.getSituation() );
            		        if(p.getConjoint()!=null) {
            		        	label4.setText(t4 + p.getConjoint().getName() +" " +p.getConjoint().getFirstName());
            		        }  
            		        label5.setText(t5 + "" );
        		        }
        		        
        		        
        		       
        		    }
        		});
             break;
     
             
         //liste de couples
         case Couples:
        	 labelTitreListe.setText("Listes des Mariages");
        	 table.removeAll();
        	 table.setModel(new DefaultTableModel(
     	    		new Object[][] {
     	    			{"Id"}
     	    		},
     	    		new String[] {
     	    			"Identifiant"
     	    		}
     	    	) {
		
     				private static final long serialVersionUID = 1L;
     				
     				Class[] columnTypes = new Class[] {
     	    			Integer.class
     	    		};
     	    		public Class getColumnClass(int columnIndex) {
     	    			return columnTypes[columnIndex];
     	    		}
     	    		boolean[] columnEditables = new boolean[] {
     	    			false
     	    		};
     	    		public boolean isCellEditable(int row, int column) {
     	    			return columnEditables[column];
     	    		};
 	    			
     	    	});
     	 
	     	 DefaultTableModel model2 = (DefaultTableModel) table.getModel();
	     	 for(Mariage m: mairie.affichageCouplesMaries()) {
	     		 Object[] element = {m.getMariageId()};
	     		 model2.addRow(element);
	     	 }
	     	 
	     	table.addMouseListener((MouseListener) new MouseAdapter() {
    		    @Override
    		    public void mouseClicked(MouseEvent e) {
    		        int ligneSelectionnee = table.getSelectedRow();
    		        
    		     
    		        if(ligneSelectionnee>0) {
    		        	int valeurColonne = (int) table.getValueAt(ligneSelectionnee, 0);
    		        	Mariage m = mairie.getCoupleById(valeurColonne);
        		        label1.setText(t1 + m.getPerson1().getName()+" "+m.getPerson1().getFirstName());
        		        label2.setText(t2 + m.getPerson2().getName()+" "+m.getPerson2().getFirstName());
        		        label3.setText(t3 + m.getMariageDate());
    		        }
    		        
    		        
    		       
    		    }
    		});
             break;
     
         case Divorces:
        	 labelTitreListe.setText("Listes des divorces");
        	 table.removeAll();
        	 table.setModel(new DefaultTableModel(
     	    		new Object[][] {
     	    			{"Id"}
     	    		},
     	    		new String[] {
     	    			"Identifiant"
     	    		}
     	    	) {
		
     				private static final long serialVersionUID = 1L;
     				
     				Class[] columnTypes = new Class[] {
     	    			Integer.class, String.class, String.class
     	    		};
     	    		public Class getColumnClass(int columnIndex) {
     	    			return columnTypes[columnIndex];
     	    		}
     	    		boolean[] columnEditables = new boolean[] {
     	    			false, false, false
     	    		};
     	    		public boolean isCellEditable(int row, int column) {
     	    			return columnEditables[column];
     	    		};
 	    			
     	    	});
     	 
	     	 DefaultTableModel model3 = (DefaultTableModel) table.getModel();
	     	 for(Divorce d: mairie.affichageCouplesDivorces()) {
	     		 Object[] element = {d.getDivorceId()};
	     		 model3.addRow(element);
	     	 }
	     	 
	     	table.addMouseListener((MouseListener) new MouseAdapter() {
    		    @Override
    		    public void mouseClicked(MouseEvent e) {
    		        int ligneSelectionnee = table.getSelectedRow();
    		        if(ligneSelectionnee>0) {
    		        	int valeurColonne = (int) table.getValueAt(ligneSelectionnee, 0);
    		        	
    		        	Divorce d = mairie.getDivorceById(valeurColonne);
        		        label1.setText(t1 + d.getPerson1().getName()+" "+d.getPerson1().getFirstName());
        		        label2.setText(t2 + d.getPerson2().getName()+" "+d.getPerson2().getFirstName());
        		        label3.setText(t3 + d.getDivorceDate());
    		        }
    		        
    		        
    		       
    		    }
    		});
             break;
         case Naissances:
        	 labelTitreListe.setText("Listes des naissances");
        	 table.removeAll();
        	 table.setModel(new DefaultTableModel(
     	    		new Object[][] {
     	    			{"Id"}
     	    		},
     	    		new String[] {
     	    			"Identifiant"
     	    		}
     	    	) {
		
     				private static final long serialVersionUID = 1L;
     				
     				Class[] columnTypes = new Class[] {
     	    			Integer.class, String.class, String.class
     	    		};
     	    		public Class getColumnClass(int columnIndex) {
     	    			return columnTypes[columnIndex];
     	    		}
     	    		boolean[] columnEditables = new boolean[] {
     	    			false, false, false
     	    		};
     	    		public boolean isCellEditable(int row, int column) {
     	    			return columnEditables[column];
     	    		};
 	    			
     	    	});
     	 
	     	 DefaultTableModel model4 = (DefaultTableModel) table.getModel();
	     	 for(Naissance n: mairie.affichageNaissances()) {
	     		 Object[] element = {n.getNaissanceId()};
	     		 model4.addRow(element);
	     	 }
	     	 
	     	table.addMouseListener((MouseListener) new MouseAdapter() {
    		    @Override
    		    public void mouseClicked(MouseEvent e) {
    		        int ligneSelectionnee = table.getSelectedRow();
    		        

    		        if(ligneSelectionnee>0) {
    		        	int valeurColonne = (int) table.getValueAt(ligneSelectionnee, 0);
    		        	Naissance n = mairie.getNaissanceById(valeurColonne);
        		        label1.setText(t1 + n.getPere().getName()+" "+n.getPere().getFirstName());
        		        label2.setText(t2 + n.getMere().getName()+" "+n.getMere().getFirstName());
        		        label3.setText(t3 + n.getNaissanceDate());
    		        }
      
    		    }
    		});
        	 break;
         default:
             break;
     }
    }
    

}
