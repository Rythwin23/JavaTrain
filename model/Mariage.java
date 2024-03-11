package model;

import java.io.Serializable;

public class Mariage implements Serializable{

	
	//ajout de le serialisation pour la sauvegarde
	private static final long serialVersionUID = 1389106812503846766L;
	
	
	//construction de la classe mariage avec des methodes get et set
	private int mariageId;
	private Person person1;
    private Person person2;
    private String mariageDate;
    
    public Mariage(int mariageId, Person person1, Person person2, String mariageDate) {
    	this.mariageId = mariageId;
    	this.person1 = person1;
        this.person2 = person2;
        this.mariageDate = mariageDate;
    }
    
    public int getMariageId() {
        return mariageId;
    }

    
    public Person getPerson1() {
        return person2;
    }


    public Person getPerson2() {
        return person1;
    }
    
    public String getMariageDate() {
        return mariageDate;
    }
    
  //fonction override utilisée pendant les testes en consoles
    /*
    @Override
	public String toString() { 
        return "id mariage: "+ this.getMariageId() + "\nPersonne1: " + this.getPerson1() + "\nPersonne2:"
        		+ this.getPerson2() + "\nDate: " + getMariageDate() + "\n";		
	}*/
}



