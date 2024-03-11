package model;

import java.io.Serializable;

public class Person implements Serializable{

	//ajout de le serialisation pour la sauvegarde
	private static final long serialVersionUID = 8622660442071006483L;

	
	
	
	//type enumerate pour l'etat civile
	public enum civicState{ 
		celibataire,
        divorce,
        marie,
        veuve,
    }
	
	//type enumerate pour le sexe
    public enum sexe{ 
        homme,
        femme,
    }
	private int id;
    private String name;
    private String firstName;
    private sexe choixSexe;
    private civicState situation;
    private String birthday;
	private Person conjoint;

	//construction de la classe person avec des methodes get et set
	public Person(int id, String name, String firstName, sexe choixSexe , String birthday, civicState situation, Person conjoint) {
		// TODO Auto-generated constructor stub
		this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.choixSexe = choixSexe;
        this.birthday = birthday;
        this.situation = situation;
        this.conjoint = conjoint;
	}

	// Récuperer l'Identifiant de la personne
    public int getId() {
        return id;
    }

    // Récuperer le Nom de la personne
    public String getName() {
        return name;
    }


    // Récuperer le Prenom de la personne
    public String getFirstName() {
        return firstName;
    }

    
    // Récuperer le sexe de la personne
    public sexe getSexe() {
    	return choixSexe;
    }

    // Récuperer la date de naissance de la personne
    public String getBirthday() {
        return birthday;
    }

    
    public civicState getSituation() {
    	return situation;
    }

    public void setSituation(civicState state){
        this.situation = state;
    }

    public Person getConjoint(){
        return conjoint;
    }

    public void setConjoint(Person personne){
        this.conjoint = personne;
    }

	public int getIdConjoint() {
		return conjoint.getId();
	}
	
	
	//fonction override utilisée pendant les testes en console
	/*
    @Override
	public String toString() {
        String info = "Nom: " + this.getName() + "\nPrenom: " + this.getFirstName() + "\nDate de naissance:" + this.getBirthday() + 
            "\nSexe: " + this.getSexe() + "\nSituation: " + this.getSituation()+"\n";
        if(conjoint!=null){
            info = info + "Nom du conjoint: "+ conjoint.getName() + "\nPrénom du conjoint: " + conjoint.getFirstName() +"\n";
        }
        return info;
	}*/
}
