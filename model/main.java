package model;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import Interface.Fenetre;

public class main{

	public main() {
		
	}
	
    public static void main(String[] args) throws Exception{
    	
    	
    	//initialiser la mairie
    	Mairie mairie =  new Mairie();

        
    	//initiliser la fenetre du programme
        JFrame programme = new Fenetre(mairie);
        
        //charger les données sauvegarder
        Sauvegarde save = new Sauvegarde(mairie);
        save.charger();
        
        //detecter la fermeture du programme pour lancer la sauvegarde
        programme.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                
            	save.Sauvegarder();
            }
        });        
        
    }
}