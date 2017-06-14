package ui.ig;

import java.util.Scanner;

import javax.swing.JFrame;

import data.BigGrille;
import data.Coup;
import data.Humain;
import data.IA;
import data.Joueur;
import data.Partie;
import jeu.Jeu;
import ui.Console;
import util.Constantes;
import util.Constantes.Case;

public class ConsoleIG extends Console {

	private GrilleIG ig;
	MetamorpionIG mm;
	private Scanner entry=new Scanner(System.in);

	public ConsoleIG()
	{
		mm=new MetamorpionIG(this, "C'est l'heure du duel ! ");
		mm.setVisible(true);
		mm.setResizable(false);
		ig=mm.getGrilleIG();
	}

	public void run()
	{
		Joueur j1,j2;
		//Scanner entry = new Scanner(System.in);
		mm.updateLogs("************* Ten *************");
	}

	public void closeScanner()
	{
		entry.close();
	}

	public void lancementPartie(Joueur joueur1, Joueur joueur2)
	{
		mm.updateLogs("************* Début de partie ************");
		mm.updateLogs("Joueur 1 : "+joueur1.getNom()+" ("+joueur1.getTypeNom()+")");
		mm.updateLogs("Joueur 2 : "+joueur2.getNom()+" ("+joueur2.getTypeNom()+")");		
	}

	public void lancementTour(int tour, Joueur jCourant, BigGrille grille)
	{
		mm.updateLogs("************* Tour "+tour+" ************");
		mm.updateLogs("C'est à  "+jCourant.getNom()+" ( "+jCourant.getSymbole()+" ) "+" de jouer !");
		ig.update(grille);
	}

	public void afficherFinPartie(Partie partie) {
		String msg;
		switch(partie.getEtatPartie())
		{
		case Constantes.VICTOIRE_JOUEUR_1 : 
			msg="VICTOIRE "+partie.getJ1().getNom();
			break;
		case Constantes.VICTOIRE_JOUEUR_2 : 
			msg="VICTOIRE "+partie.getJ2().getNom();
			break;
		default : 
			msg="MATCH NUL";
			break;
		}
		mm.updateLogs("************ "+msg+" en "+(partie.getTour()-1)+" tours ***************");
		mm.updateLogs("Joueur 1 :" + partie.getJ1().getNom());
		mm.updateLogs("Joueur 2 :" + partie.getJ2().getNom());
		mm.updateLogs("******************************************************************");
		ig.update(partie.getGrille());
	}

	public Coup getHumanCoup(BigGrille g, String nom, Case cas) {
		ig.activate(true);
		Coup c = ig.getCoup();
		ig.activate(false);
		return c;
	}

	public void reflexionIA(String nom)
	{
		mm.updateLogs(nom+" réfléchit ...");
	}

	public void afficherCoupSimple(Joueur joueurCourant, int coup) {
		mm.updateLogs(joueurCourant.getNom() +" a choisi de mettre son symbole dans la case "+(coup)+"\n");
	}

	public void afficherCoupDouble(Joueur joueurCourant, int bg, int sg) {
		mm.updateLogs(joueurCourant.getNom() +" a choisi de mettre son symbole dans la grille "+(bg)+ " et dans la case " +(sg) + "\n");
	}

	public void afficherCoup(Joueur joueurCourant, Coup coup) {
		mm.updateLogs(joueurCourant.getNom() +" a choisi de mettre son symbole dans la grille "+(coup.getGrille()+1)+ " et dans la case " +(coup.getC()+1) + "\n");
	}
	
	public void afficherCoupInvalide()
	{
		mm.updateLogs("COUP INVALIDE : Recommencez !");
	}
	
	private String timeToString(long t)
	{
		String s="";
		if(t>3600000)
		{
			long h=t/3600000;
			s+=h+"h ";
			t-=h*3600000;
		}
		if(t>60000)
		{
			long m=t/60000;
			s+=m+"m ";
			t-=m*60000;
		}
		if(t>1000)
		{
			long sec=t/1000;
			s+=sec+"s ";
			t-=sec*1000;
		}
		if(t>0)
		{
			s+=t+"ms";
		}
		return s;
	}
}
