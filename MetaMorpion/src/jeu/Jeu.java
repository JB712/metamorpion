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
			
			// premier coup  ==> géré par l'initialisation de precedent dans Partie
			// if isGrilleLibre = wrong ==> il faut demander 2 int (grille + case)
			
			long tempsReflexion=System.currentTimeMillis();
			if(!partie.isGrilleLibre(partie.getPrecedent())){
				int bg= partie.getJCourant().joue2(partie.getGrille(), console, partie.getTour(), partie.getJCourant().getSymbole());
				int sg= partie.getJCourant().joue(partie.getGrille(), console, partie.getTour(), partie.getJCourant().getSymbole());
				if(!partie.jouerCoupDouble(bg, sg, tempsReflexion)){
					System.out.println("COUP INVALIDE : Recommencez !");
				}
			}
			else{
				int coup= partie.getJCourant().joue(partie.getGrille(), console, partie.getTour(), partie.getJCourant().getSymbole());
				tempsReflexion=System.currentTimeMillis()-tempsReflexion;
				//console.afficherCoup(partie.getJCourant(), coup, tempsReflexion);     afficherCoup sert surtout dans le choix de l'IA
				if(!partie.jouerCoupSimple(coup, tempsReflexion))
				{
					System.out.println("COUP INVALIDE : Recommencez !");
				}
			}
		}
		console.closeScanner();
		console.afficherFinPartie(partie);
	}
}
