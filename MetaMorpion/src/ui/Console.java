package ui;

import java.util.Scanner;

import data.Grille;
import data.Joueur;
import data.Partie;
import util.Constantes;
import util.Constantes.Case;


public class Console extends Thread {
	
	private Scanner entry;

	public void run()
	{
		Joueur j1,j2;
		//Scanner entry = new Scanner(System.in);
		System.out.println("************* PUISSANCE 4 *************");
		j1 = choixJoueur(Constantes.JOUEUR_1, entry);
		j2 = choixJoueur(Constantes.JOUEUR_2, entry);		
		//Jeu jeu = new Jeu(j1, j2, this);
		//jeu.start();
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
			//joueur=new Humain(nomJoueur,order);
		}
		
		return joueur;
	}
	
	public void lancementPartie(Joueur joueur1, Joueur joueur2)
	{
		System.out.println("************* DÃ©but de partie ************");
		System.out.println("Joueur 1 : "+joueur1.getNom()+" ("+joueur1.getTypeNom()+")");
		System.out.println("Joueur 2 : "+joueur2.getNom()+" ("+joueur2.getTypeNom()+")");		
	}
	
	private static void afficheGrille() {
		String symbol = "V";
		String s="";
		for(int i=0; i<3; i++){ // crée la méta grille
			
			for(int j=0;j<3;j++) // duplique 1 ligne de la bigGrille
			{
				for(int k=0;k<3;k++) //assemble les 3 premières lignes des smallGrille
				{
					for(int l=0;l<3;l++){ //crée la première ligne d'une smallGrille
						s+=" " + symbol + " ";
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
	
}
