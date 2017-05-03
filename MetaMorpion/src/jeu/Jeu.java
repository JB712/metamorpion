package jeu;

import data.*;
import ui.*;

public class Jeu extends Thread{
	
	private Partie partie;
	private Console console;
	
	public Jeu(Joueur j1, Joueur j2, Console console)
	{
		this.partie=new Partie(j1, j2);
		this.console=console;
	}
	
	public void run()
	{
		console.lancementPartie(partie.getJ1(), partie.getJ2());
		while(!partie.isGameOver())
		{
			console.lancementTour(partie.getTour(), partie.getJCourant(), partie.getGrille());
			
			long tempsReflexion=System.currentTimeMillis();
			if(!partie.isGrilleLibre(partie.getPrecedent())){
				/*if(!partie.jouerCoupDouble(coup, tempsReflexion)){
					Put the code of Double entry
				}*/
			}
			int coup= partie.getJCourant().joue(partie.getGrille(), console, partie.getTour());
			tempsReflexion=System.currentTimeMillis()-tempsReflexion;
			//console.afficherCoup(partie.getJCourant(), coup, tempsReflexion);     afficherCoup sert surtout dans le choix de l'IA
			if(!partie.jouerCoupSimple(coup, tempsReflexion))
			{
				System.out.println("COUP INVALIDE : Recommencez !");
			}
		}
		console.closeScanner();
		console.afficherFinPartie(partie);
	}
}
