package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Sauvegarde {

	
	private Mairie mairie;
	
	public Sauvegarde(Mairie mairie) {
		this.mairie = mairie;
	}
	
	public void Sauvegarder() {
		//try pour eviter les plantages et les exceptions
		try {
			//créer un fichier de sortie pour la sauvegarde
            FileOutputStream fichierSortie = new FileOutputStream("sauvegarde.txt");
            ObjectOutputStream objetSortie = new ObjectOutputStream(fichierSortie);

            // Écriture de la liste d'objets dans le fichier
            objetSortie.writeObject(mairie.affichageListePersonnes());
            objetSortie.writeObject(mairie.affichageCouplesMaries());
            objetSortie.writeObject(mairie.affichageCouplesDivorces());
            objetSortie.writeObject(mairie.affichageNaissances());

            objetSortie.close();
            fichierSortie.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	public void charger() {
		//try pour eviter les plantages et les exceptions
        try {
        	//ouverture du fichier de sauvegardes
            FileInputStream fichierEntree = new FileInputStream("sauvegarde.txt");
            ObjectInputStream objetEntree = new ObjectInputStream(fichierEntree);

            // Lecture de la liste d'objets depuis le fichier 
            mairie.setListePersonnes((ArrayList<Person>) objetEntree.readObject());
            mairie.setListeCouples((ArrayList<Mariage>) objetEntree.readObject());
            mairie.setListeDivorces((ArrayList<Divorce>) objetEntree.readObject());
            mairie.setListeNaissances((ArrayList<Naissance>) objetEntree.readObject());

            objetEntree.close();
            fichierEntree.close();
        } catch (IOException | ClassNotFoundException e) {
        	JOptionPane.showMessageDialog(null, "Le chargement de certaines données n'a pas été éffectué(fichier ou donnée innexistante)");
            e.printStackTrace();
        }
	}
}

