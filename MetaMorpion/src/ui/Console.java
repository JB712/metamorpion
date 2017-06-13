package ui;

import java.util.Scanner;

import data.BigGrille;
import data.Coup;
import data.Humain;
import data.IA;
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
		System.out.println("************* Ten *************\nhttps://sciencetonnante.wordpress.com/2013/05/27/1234567-112/");
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
		else
		{
			nomJoueur=Constantes.IA_NAMES[(int)Math.floor(Math.random()*Constantes.IA_NAMES.length)];

			do{
				System.out.println("Quel IA pour joueur "+order+" ("+nomJoueur+") ?");
				for(int j=0; j<Constantes.IA_ALGOS.length; j++)
				{
					System.out.println((j+1)+") "+Constantes.IA_ALGOS[j]);

				}
				System.out.print("Votre choix : ");
				typeIA=entry.nextInt()-1;
			}while(typeIA<0||typeIA>=Constantes.IA_ALGOS.length);
			do{
				System.out.println("Niveau de difficulté de l'IA ("+nomJoueur+") [1-10] ?");
				System.out.print("Votre choix : ");
				levelIA=entry.nextInt();
			}while(levelIA<0||levelIA>Constantes.NB_TOUR_MAX);
			joueur= new IA(nomJoueur, order, typeIA, levelIA);
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
		System.out.println("C'est à  "+jCourant.getNom()+" ( "+jCourant.getSymbole()+" ) "+" de jouer !");
		afficheGrille(grille);
	}

	public static void afficheGrille(BigGrille tab) {
		//String symbol = "V";
		String s="";
		for(int i=0; i<3; i++){ // crée la méta grille

			for(int j=0;j<3;j++) // duplique 1 ligne de la bigGrille
			{
				for(int k=0;k<3;k++) // assemble les 3 premières lignes des smallGrille
				{
					for(int l=0;l<3;l++){ // crée la première ligne d'une smallGrille
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
		System.out.println("Joueur 1 :" + partie.getJ1().getNom());
		System.out.println("Joueur 2 :" + partie.getJ2().getNom());
		System.out.println("******************************************************************");

	}

	public Coup getHumanCoup(BigGrille g, String nom, Case cas) {
		int grille;
		if (g.isGrilleLibre(g.getCoupPrecedent()))
		{
			grille=g.getCoupPrecedent();
		}
		else
		{
			System.out.print("Coup de "+nom+" ( "+cas+" ) "+". Choisissez votre grille : ");
			grille=entry.nextInt()-1;
		}
		System.out.print("Coup de "+nom+" ( "+cas+" ) "+". Choisissez votre case : ");
		int c=entry.nextInt()-1;
		return new Coup(grille, c);
	}

	public void reflexionIA(String nom)
	{
		System.out.println(nom+" réfléchit ...");
	}

	public void afficherCoupSimple(Joueur joueurCourant, int coup) {
		System.out.println(joueurCourant.getNom() +" a choisi de mettre son symbole dans la case "+(coup)+"\n");
	}

	public void afficherCoupDouble(Joueur joueurCourant, int bg, int sg) {
		System.out.println(joueurCourant.getNom() +" a choisi de mettre son symbole dans la grille "+(bg)+ " et dans la case " +(sg) + "\n");
	}

	public void afficherCoup(Joueur joueurCourant, Coup coup) {
		System.out.println(joueurCourant.getNom() +" a choisi de mettre son symbole dans la grille "+(coup.getGrille()+1)+ " et dans la case " +(coup.getC()+1) + "\n");
	}

	public void afficherCoupInvalide()
	{
		System.out.println("COUP INVALIDE : Recommencez !");
	}
}
