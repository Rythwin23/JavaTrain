package model;

import java.io.Serializable;


//ajout de le serialisation pour la sauvegarde
public class Divorce implements Serializable{
	
	//ajout de le serialisation pour la sauvegarde
	private static final long serialVersionUID = 3065989024662334574L;
	
	
	//construction de la classe divorce avec des methodes get et set
	private int divorceId;
	private Person person1;
    private Person person2;
    private String divorceDate;
    
    public Divorce(int divorceId, Person person1, Person person2, String divorceDate) {
    	this.divorceId = divorceId;
    	this.person1 = person1;
        this.person2 = person2;
        this.divorceDate = divorceDate;
    }
    
    
    public int getDivorceId() {
        return divorceId;
    }

    
    public Person getPerson1() {
        return person1;
    }


    public Person getPerson2() {
        return person2;
    }
    
    public String getDivorceDate() {
        return divorceDate;
    }
    
    
    //fonction override utilisée pendant les testes en consoles
    /*
    @Override
	public String toString() { 
        return "id divorce: "+ this.getDivorceId() + "\nPersonne1: " + this.getPerson1() + "\nPersonne2:"
        		+ this.getPerson2() + "\nDate: " + getDivorceDate() + "\n";		
	}
	*/
}
