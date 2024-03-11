package model;

import java.io.Serializable;

public class Naissance implements Serializable{

	//ajout de le serialisation pour la sauvegarde
	private static final long serialVersionUID = -421025954658964082L;
	
	
	//construction de la classe naissance avec des methodes get et set
	private int naissanceId;
    private Person pere;
    private Person mere;
    private String date;


    public Naissance(int naissanceId, Person pere, Person mere, String date){
        this.naissanceId = naissanceId;
        this.pere = pere;
        this.mere = mere;
        this.date = date;
    }


    public int getNaissanceId(){
        return naissanceId;
    }

    public Person getPere(){
        return pere;
    }

    public Person getMere(){
        return mere;
    }
    
    public String getNaissanceDate() {
    	return date;
    }
    
    
    //fonction override utilisée pendant les testes en consoles
    /*
    @Override
    public String toString(){
        return "ID-Naissance: " + this.naissanceId +"\nPere: " + pere.getName()+" "+pere.getFirstName() + "\nMere: " + mere.getName()+" "+mere.getFirstName()+", Date: "+ this.date;
    }
    */
}

