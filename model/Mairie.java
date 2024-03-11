package model;
import model.Person.civicState;
import model.Person.sexe;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Mairie {
	//les arrayliste qui vont stockés les données 
    private ArrayList<Person> listePersonnes;
    private ArrayList<Mariage> coupleMaries;
    private ArrayList<Divorce> coupleDivorces;
    private ArrayList<Naissance> naissances;
    


    public Mairie(){
        this.listePersonnes = new ArrayList<Person>();
        this.coupleMaries = new ArrayList<Mariage>();
        this.coupleDivorces = new ArrayList<Divorce>();
        this.naissances = new ArrayList<Naissance>();
    }

    //fonction ajouter une personne
    public void addPersonne(String name, String firstName, String choixSexe , String birthday, String situation, int idconjoint){
    	Person conjoint = getperson(idconjoint);
    	Person person = new Person(listePersonnes.size(),name, firstName, sexe.valueOf(choixSexe), birthday, civicState.valueOf(situation), conjoint);
        this.listePersonnes.add(person);
        
    }
    
    //afficher la liste des personnes
    public ArrayList<Person> affichageListePersonnes(){
        return this.listePersonnes;
    }
    
    //remplir la liste leur du chargement au lancement du programme
    public void setListePersonnes(ArrayList<Person> liste) {
    	this.listePersonnes = liste;
    }
    
    public ArrayList<Mariage> affichageCouplesMaries() {
    	return this.coupleMaries;
    }
    
    //remplir la liste leur du chargement au lancement du programme
    public void setListeCouples(ArrayList<Mariage> liste) {
    	this.coupleMaries= liste;
    }
    

    public ArrayList<Divorce> affichageCouplesDivorces() {
    	return this.coupleDivorces;
    }
    
    
    //remplir la liste leur du chargement au lancement du programme
    public void setListeDivorces(ArrayList<Divorce> liste) {
    	this.coupleDivorces = liste;
    }

    public ArrayList<Naissance> affichageNaissances(){
        return this.naissances;
    }
    
    //remplir la liste leur du chargement au lancement du programme
    public void setListeNaissances(ArrayList<Naissance> liste) {
    	this.naissances = liste;
    }

    
    //fonction pour marier deux personnes(elle gere toute les exceptions d'entrées et peut alerte avec des popup
    public void marier(int person1id, int person2id, String date){
        if(person1id!=person2id){
            Person person1 = getperson(person1id);
            Person person2 = getperson(person2id);
            if(person1!=null && person2!=null){
                if(person1.getConjoint()==null && person2.getConjoint()==null){
                    person1.setSituation(civicState.marie);
                    person1.setConjoint(person2);
                    person2.setSituation(civicState.marie);
                    person2.setConjoint(person1);
                    Mariage mariage = new Mariage(coupleMaries.size(), person1, person2, date);
                    coupleMaries.add(mariage);
                    JOptionPane.showMessageDialog(null, "Mariage ajouté avec succés" );
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "la personne saisie a déjà un conjoint" );
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "la personne saisie n'existe pas");
                
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Vous avez saisie la meme personnne" );
        }
    }

    
    //fonction pour divorcer avec gestion des exceptions et alertes
    public void divorcer(int person1id, int person2id, String date){
        if(person1id!=person2id){
            Person person1 = getperson(person1id);
            Person person2 = getperson(person2id);
            if(person1!=null && person2!=null){
                Mariage couple = getcouple(person1id, person2id);
                if(couple!=null){
                    person1.setSituation(civicState.celibataire);
                    person1.setConjoint(null);
                    person2.setSituation(civicState.celibataire);
                    person2.setConjoint(null);
                    Divorce divorce = new Divorce(coupleDivorces.size(), person1, person2, date);
                    this.coupleDivorces.add(divorce);
                    this.coupleMaries.remove(couple);  
                    JOptionPane.showMessageDialog(null, "Le Divorce est enregistré" );
                }
                else{
                    JOptionPane.showMessageDialog(null, "le couple saisie n'existe pas" );
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "l'une des personnnes saisies n'existe pas");
            }
        }
        else{
        	JOptionPane.showMessageDialog(null,"vous avez saisie la meme personnne");
        }
    }

    
    //fonction de naissances avec exceptions et alertes
    public void naitre(int pereId, int mereId, String date){
        if (pereId!=mereId){
            Person pere = getperson(pereId);
            Person mere = getperson(mereId);
            if(pere!=null && mere!=null){
                Naissance naissance = new Naissance(this.naissances.size(), pere, mere,date);
                this.naissances.add(naissance);
                JOptionPane.showMessageDialog(null,"La naissance est enregistrée");
            }
            else{
            	JOptionPane.showMessageDialog(null,"L'un des deux parents n'existe pas");
            }
        }
        else{
        	JOptionPane.showMessageDialog(null,"Vous avez saisie la meme personne");
        }
    }


    //recuperation d'une personnnes avec id
    public Person getperson(int idperson){
        Person person = null;
        for(Person p: this.listePersonnes){
                if(p.getId()==idperson){
                    person = p;
                }
        }
        return person;
    }
    
    
    //fonction non utilisé mais qui peut etre ajouter pendant pour des amélioration de recherche
    public Mariage getcouple(int idperson1, int idperson2){
        Mariage couple = null;
        for(Mariage m : this.coupleMaries){
            if((m.getPerson1().getId()==idperson1 && m.getPerson2().getId()==idperson2) || (m.getPerson1().getId()==idperson2 && m.getPerson2().getId()==idperson1) ){
                couple = m;
            }
        }
        return couple;
    }
    
    
    //recuperer un couple de mariés par id
    public Mariage getCoupleById(int id) {
    	Mariage couple = null;
    	for(Mariage m: this.coupleMaries) {
    		if( m.getMariageId() == id) {
    			couple = m;
    			break;
    		}
    	}
    	return couple;
    }
    
    //récuperer un divorce
    public Divorce getDivorceById(int id) {
    	Divorce couple = null;
    	for(Divorce d: this.coupleDivorces) {
    		if( d.getDivorceId() == id) {
    			couple = d;
    			break;
    		}
    	}
    	return couple;
    }
    
    //récuperer une naissances
    public Naissance getNaissanceById(int id) {
    	Naissance tmp = null;
        for(Naissance n: this.affichageNaissances()) {
        	if(n.getNaissanceId() == id) {
        		tmp = n;
        		break;
        	}
        }
        return tmp;
    }
}
