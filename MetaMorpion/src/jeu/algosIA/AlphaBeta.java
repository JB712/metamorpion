package jeu.algosIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import data.BigGrille;
import data.Joueur;
import util.Constantes;


public class AlphaBeta extends Algorithm {

	public AlphaBeta(int levelIA, BigGrille grilleDepart, Joueur joueurActuel, int tour) {
		super(levelIA, grilleDepart, joueurActuel, tour);

	}


	@Override
	public Coup choisirCoup()
	{

		List<Coup> meilleursCoups=new ArrayList<>();
		Thread[] threads = new Thread[9];

		for (int i=0;i<9;i++)
		{
			final int g=i;
			threads[i]=new Thread(new Runnable() {

				@Override
				public void run() {
					for (int c=0;c<9;c++)
					{
						double valeur = Constantes.SCORE_MIN_NON_DEFINI;

						if (grilleDepart.isCoupPossible(new Coup(g,c))){
							BigGrille bg2 = grilleDepart.clone();
							bg2.ajouterCoup(new Coup(g, c), symboleMax);
							double valeurCourante = min(bg2, Constantes.SCORE_MIN_NON_DEFINI, Constantes.SCORE_MAX_NON_DEFINI, tourDepart);
							if (valeurCourante > valeur) {
								meilleursCoups.clear();
								meilleursCoups.add(new Coup(g,c));
								valeur = valeurCourante;
							}else if (valeurCourante == valeur){
								meilleursCoups.add(new Coup(g,c));
							}
						}
					}
				}
			});
			threads[i].start();
		}
		for (Thread t : threads)
		{
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Les meilleurs coups sont : ");
		for (Coup coup : meilleursCoups) {
			System.out.println(coup);
		}
		return meilleursCoups.get(new Random().nextInt(meilleursCoups.size()));

	}

	private double max(BigGrille g, double alpha, double beta, int tour){

		if(tour == tourMax || g.getEtatPartie(symboleMin)!=Constantes.PARTIE_EN_COURS)
		{
			return g.evaluer(symboleMax)/*+profondeur*/;
		}

		double valeur = Constantes.SCORE_MIN_NON_DEFINI;

		for (int i=0; i<9; i++)
		{
			int bgrille=i;
			for (int j=0; j<9; j++)
			{
				if(g.isCoupPossible(new Coup(bgrille, j)))
				{
					BigGrille bg2 = g.clone();
					bg2.ajouterCoup(new Coup(bgrille, j), symboleMax);
					valeur = Math.max(valeur, this.min(bg2, alpha ,beta, tour+1));
					if (valeur >= beta){
						return valeur;
					}
					alpha = Math.max(alpha, valeur);
				}
			}
		}
		return valeur;
	}

	private double min(BigGrille g, double alpha, double beta, int tour) {

		if(tour == tourMax || g.getEtatPartie(symboleMax)!=Constantes.PARTIE_EN_COURS)
		{
			return g.evaluer(symboleMax)/*-profondeur*/;
		}

		double valeur = Constantes.SCORE_MAX_NON_DEFINI;
		for (int i=0; i<9; i++)
		{
			int bgrille=i;
			for (int j=0;j<9;j++)
			{
				if(g.isCoupPossible(new Coup(bgrille, j)))
				{

					BigGrille bg2 = g.clone();
					bg2.ajouterCoup(new Coup(bgrille, j), symboleMin);
					valeur = Math.min(valeur, this.max(bg2, alpha ,beta, tour+1));
					if (valeur <= alpha){
						return valeur;
					}
					beta = Math.min(beta, valeur);
				}
			}
		}
		return valeur;
	}
}

