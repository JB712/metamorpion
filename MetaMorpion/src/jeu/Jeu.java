package jeu;

import data.*;
import ui.*;

public class Jeu {
	
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
			int coup= partie.getJCourant().joue(partie.getGrille(), console, partie.getTour());
			tempsReflexion=System.currentTimeMillis()-tempsReflexion;
			//console.afficherCoup(partie.getJCourant(), coup, tempsReflexion);     afficherCoup sert surtout dans le choix de l'IA
			if(!partie.jouerCoup(coup, tempsReflexion))
			{
				System.out.println("COUP INVALIDE : Recommencez !");
			}
		}
		console.closeScanner();
		console.afficherFinPartie(partie);
	}
}
