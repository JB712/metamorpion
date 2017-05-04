package ui;

import java.util.Scanner;

import data.BigGrille;
import data.Grille;
import data.Humain;
import data.Joueur;
import data.Partie;
import jeu.Jeu;
import util.Constantes;
import util.Constantes.Case;


public class Console extends Thread {
	
	private Scanner entry;
	
	public Console()
	{
		super("console");
		entry = new Scanner(System.in);
	}

	public void run()
	{
		Joueur j1,j2;
		//Scanner entry = new Scanner(System.in);
		System.out.println("************* Ten *************");
		j1 = choixJoueur(Constantes.JOUEUR_1, entry);
		j2 = choixJoueur(Constantes.JOUEUR_2, entry);		
		Jeu jeu = new Jeu(j1, j2, this);
		jeu.start();
	}
	
	public void closeScanner()
	{
		entry.close();
	}

	private Joueur choixJoueur(int order, Scanner entry)
	{
		Joueur joueur=null;
		int typeJoueur;
		String nomJoueur;
		int typeIA=-1;
		int levelIA=-1;
		do{
			System.out.println("Le joueur "+order+" est :");
			System.out.println("1) Humain");
			System.out.println("2) Intelligence Artificielle");
			System.out.print("Votre choix : ");
			typeJoueur = entry.nextInt();
		}while(typeJoueur!=1&&typeJoueur!=2);
		if(typeJoueur==Constantes.JOUEUR_HUMAN)
		{
			System.out.print("Entrez le nom du joueur "+order+" : ");
			entry.nextLine();
			nomJoueur= entry.nextLine();
			joueur=new Humain(nomJoueur,order);
		}
		
		return joueur;
	}
	
	public void lancementPartie(Joueur joueur1, Joueur joueur2)
	{
		System.out.println("************* Début de partie ************");
		System.out.println("Joueur 1 : "+joueur1.getNom()+" ("+joueur1.getTypeNom()+")");
		System.out.println("Joueur 2 : "+joueur2.getNom()+" ("+joueur2.getTypeNom()+")");		
	}
	
	public void lancementTour(int tour, Joueur jCourant, BigGrille grille)
	{
		System.out.println("************* Tour "+tour+" ************");
		System.out.println("C'est à "+jCourant.getNom()+" ( "+jCourant.getSymbole()+" ) "+" de jouer !");
		afficheGrille(grille);
	}
	
	public static void afficheGrille(BigGrille tab) {
		//String symbol = "V";
		String s="";
		for(int i=0; i<3; i++){ // cr�e la m�ta grille
			
			for(int j=0;j<3;j++) // duplique 1 ligne de la bigGrille
			{
				for(int k=0;k<3;k++) // assemble les 3 premi�res lignes des smallGrille
				{
					for(int l=0;l<3;l++){ // cr�e la premi�re ligne d'une smallGrille
						//s+=" " + symbol + " ";
						s += " ";
						if(tab.getCase(3*i+k).getCase(3*j+l).equals(Case.V)){
							s+= " ";
						}
						else{
							s+=tab.getCase(3*i+k).getCase(3*j+l);
						}
						s+= " ";
						if (l != 2)
							s+="|";
					}
					if (k != 2)
						s+=" || ";
				}
				s+="\n";
				if (j != 2){
					s+="----------  ||  ---------  ||  ----------";
				    s+="\n";
				}
			}
			if (i != 2){
				s+="=========================================";
			    s+="\n";
			}
		}
		System.out.println(s);
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
		System.out.println("************ "+msg+" en "+(partie.getTour()-1)+" tours ***************");
		afficheGrille(partie.getGrille());
		System.out.println(partie.getJ1().getNom());
		System.out.println(partie.getJ2().getNom());
		System.out.println("******************************************************************");
		
	}
	
	public int getHumanCoup(String nom, Case cas) {
		System.out.print("Coup de "+nom+" ( "+cas+" ) "+". Choisissez votre case : ");
		return entry.nextInt();
	}

	public int getHuman2Coup(String nom, Case cas) {
		System.out.print("Coup de "+nom+" ( "+cas+" ) "+". Choisissez votre grille : ");
		return entry.nextInt();
	}

	public void reflexionIA(String nom)
	{
		System.out.println(nom+" réfléchit ...");
	}
	
}
