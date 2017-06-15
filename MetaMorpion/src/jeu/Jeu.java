package jeu;

import data.*;
import ui.*;
import util.Constantes;

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
		Coup coup=null;
		long tempsReflexion=0;
		while(!partie.isGameOver())
		{
			console.lancementTour(partie.getTour(), partie.getJCourant(), partie.getGrille());

			tempsReflexion=System.currentTimeMillis();
			coup=partie.getJCourant().joue(partie.getGrille(), console, partie.getTour(), partie.getJCourant().getSymbole());
			tempsReflexion=System.currentTimeMillis()-tempsReflexion;
			console.afficherCoup(partie.getJCourant(), coup, tempsReflexion);     //afficherCoup sert surtout dans le choix de l'IA
			if(!partie.jouerCoup(coup, tempsReflexion))
			{
				System.out.println("COUP INVALIDE : Recommencez !");
			}
			if (partie.getJ1().getType() == Constantes.JOUEUR_IA && partie.getJ2().getType()==Constantes.JOUEUR_IA)
				console.update(partie.getGrille(), true);
			else
				console.update(partie.getGrille(),partie.getJCourant().getType()!=Constantes.JOUEUR_IA);
			//System.out.println(partie.getGrille().printAv());
		}
		console.closeScanner();
		console.afficherFinPartie(partie);
	}
	
	public Partie getPartie()
	{
		return partie;
	}
}
