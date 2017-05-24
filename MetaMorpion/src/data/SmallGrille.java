package data;


import util.Constantes;
import util.Constantes.*;

public class SmallGrille{

	private Case etat = Case.V;
	private Case[] cases = new Case[9];		// les lignes sont [0-2], [3-5], [6-8]

	public SmallGrille(){
		for (int i=0; i<9; i++){
			cases[i]=Case.V;
		}
	}

	public Case getEtat(){
		return etat;
	}

	public Case getCase(int small) {
		return cases[small];
	}

	public void setCase (int small, Case symbol){
		cases[small] = symbol;

	}

	public void wintest(Case s) {
		for(int i=0; i<3; i++){
			if(cases[3*i].equals(s)){		//Test des solutions en ligne
				if(cases[3*i+1].equals(s) && cases[3*i+2].equals(s)){
					this.etat = s;
				}
			}
			if(cases[i].equals(s)){			//Test des solutions en colonne
				if(cases[3+i].equals(s) && cases[6+i].equals(s)){
					this.etat = s;
				}
			}
		}
		//Test du reste = les diagonales
		if(cases[0].equals(s) && cases[4].equals(s) && cases[8].equals(s)){
			this.etat = s;
		}
		if(cases[2].equals(s) && cases[4].equals(s) && cases[6].equals(s)){
			this.etat = s;
		}
	}

	public boolean isFull(){
		for (Case cel : cases) {
			if(cel==Case.V) return false;
		}
		return true;
	}

	public boolean isCaseLibre(int cas) {
		return (cases[cas].equals(Case.V));
	}

	public int rechercheValeur(Case symboleJoueurCourant){
		int valeurDesChoix = 0;
		Case symboleAdverse = (symboleJoueurCourant==Constantes.SYMBOLE_J1)?Constantes.SYMBOLE_J2:Constantes.SYMBOLE_J1;
		//ajout de la valeur du coup si la petite grille est gagnée ( de différentes façons)
		for(int i=0; i<3; i++){
			if(cases[3*i].equals(symboleJoueurCourant)){		//Test des solutions en ligne
				if(cases[3*i+1].equals(symboleJoueurCourant) && cases[3*i+2].equals(symboleJoueurCourant)){
					valeurDesChoix += 100;
				}
			}
			if(cases[i].equals(symboleJoueurCourant)){			//Test des solutions en colonne
				if(cases[3+i].equals(symboleJoueurCourant) && cases[6+i].equals(symboleJoueurCourant)){
					valeurDesChoix += 100;
				}
			}
		}
		//Test du reste = les diagonales
		if(cases[0].equals(symboleJoueurCourant) && cases[4].equals(symboleJoueurCourant) && cases[8].equals(symboleJoueurCourant)){
			valeurDesChoix += 100;
		}
		if(cases[2].equals(symboleJoueurCourant) && cases[4].equals(symboleJoueurCourant) && cases[6].equals(symboleJoueurCourant)){
			valeurDesChoix += 100;
		}


		//réduction de la valeur du coup si la petite grille est perdue ( de différentes façons)
		for(int i=0; i<3; i++){
			if(cases[3*i].equals(symboleAdverse)){		//Test des solutions en ligne
				if(cases[3*i+1].equals(symboleAdverse) && cases[3*i+2].equals(symboleAdverse)){
					valeurDesChoix -= 100;
				}
			}
			if(cases[i].equals(symboleAdverse)){			//Test des solutions en colonne
				if(cases[3+i].equals(symboleAdverse) && cases[6+i].equals(symboleAdverse)){
					valeurDesChoix -= 100;
				}
			}
		}
		//Test du reste = les diagonales
		if(cases[0].equals(symboleAdverse) && cases[4].equals(symboleAdverse) && cases[8].equals(symboleAdverse)){
			valeurDesChoix -= 100;
		}
		if(cases[2].equals(symboleAdverse) && cases[4].equals(symboleAdverse) && cases[6].equals(symboleAdverse)){
			valeurDesChoix -= 100;
		}

		//ajout de la valeur du coup si 2 cases de même nature sont alignées ( de différentes façons)
		for(int i=0; i<3; i++){
			if(cases[3*i].equals(symboleJoueurCourant)){		//Test des solutions en ligne
				if(cases[3*i+1].equals(symboleJoueurCourant) || cases[3*i+2].equals(symboleJoueurCourant)){ //ATTENTION ne passes qu'une seule fois, dangereux en cas de plusieurs doublons (2 cases sur le même alignement) 
					valeurDesChoix += 10;
				}				
			}
			if(cases[3*i+1].equals(symboleJoueurCourant) && cases[3*i+2].equals(symboleJoueurCourant))
				valeurDesChoix += 10;

			if(cases[i].equals(symboleJoueurCourant)){			//Test des solutions en colonne
				if(cases[3+i].equals(symboleJoueurCourant) || cases[6+i].equals(symboleJoueurCourant)){
					valeurDesChoix += 10;
				}
			}
			if(cases[3+i].equals(symboleJoueurCourant) && cases[6+i].equals(symboleJoueurCourant))
				valeurDesChoix += 10;
		}
		//Test du reste = les diagonales
		if(cases[0].equals(symboleJoueurCourant) && (cases[4].equals(symboleJoueurCourant) || cases[8].equals(symboleJoueurCourant))){
			valeurDesChoix += 10;
		}
		if(cases[4].equals(symboleJoueurCourant) && cases[8].equals(symboleJoueurCourant)){
			valeurDesChoix += 10;
		}
		if(cases[2].equals(symboleJoueurCourant) && (cases[4].equals(symboleJoueurCourant) || cases[6].equals(symboleJoueurCourant))){
			valeurDesChoix += 10;
		}
		if(cases[4].equals(symboleJoueurCourant) && cases[6].equals(symboleJoueurCourant)){
			valeurDesChoix += 10;
		}

		//réduction de la valeur du coup si 2 cases de même nature sont alignées ( de différentes façons)
		for(int i=0; i<3; i++){
			if(cases[3*i].equals(symboleAdverse)){		//Test des solutions en ligne
				if(cases[3*i+1].equals(symboleAdverse) || cases[3*i+2].equals(symboleAdverse)){ //ATTENTION ne passes qu'une seule fois, dangereux en cas de plusieurs doublons (2 cases sur le même alignement) 
					valeurDesChoix -= 10;
				}				
			}
			if(cases[3*i+1].equals(symboleAdverse) && cases[3*i+2].equals(symboleAdverse))
				valeurDesChoix -= 10;

			if(cases[i].equals(symboleAdverse)){			//Test des solutions en colonne
				if(cases[3+i].equals(symboleAdverse) || cases[6+i].equals(symboleAdverse)){
					valeurDesChoix -= 10;
				}
			}
			if(cases[3+i].equals(symboleAdverse) && cases[6+i].equals(symboleAdverse))
				valeurDesChoix -= 10;
		}
		//Test du reste = les diagonales
		if(cases[0].equals(symboleAdverse) && (cases[4].equals(symboleAdverse) || cases[8].equals(symboleAdverse))){
			valeurDesChoix -= 10;
		}
		if(cases[4].equals(symboleAdverse) && cases[8].equals(symboleAdverse)){
			valeurDesChoix -= 10;
		}
		if(cases[2].equals(symboleAdverse) && (cases[4].equals(symboleAdverse) || cases[6].equals(symboleAdverse))){
			valeurDesChoix -= 10;
		}
		if(cases[4].equals(symboleAdverse) && cases[6].equals(symboleAdverse)){
			valeurDesChoix -= 10;
		}
		return valeurDesChoix;
	}
}
