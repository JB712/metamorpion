package data;


import java.util.Arrays;

import util.Constantes;
import util.Constantes.*;

public class SmallGrille{

	private int index;
	private Case etat = Case.V;
	private Case[] cases = new Case[9];		// les lignes sont [0-2], [3-5], [6-8]
	private int[] avantages = new int[16];	// 012colonne, 345ligne, 6diag1-9, 7diag3-7
	private BigGrille bigG;

	public SmallGrille(){
		for (int i=0; i<9; i++){
			cases[i]=Case.V;
		}
		Arrays.fill(avantages, 0);
	}
	
	public SmallGrille(BigGrille bg, int index){
		for (int i=0; i<9; i++){
			cases[i]=Case.V;
		}
		Arrays.fill(avantages, 0);
		this.bigG = bg;
		this.index=index;
	}

	public Case getEtat(){
		return etat;
	}

	public int[] getAvantages(){
		return avantages;
	}

	public Case getCase(int small) {
		return cases[small];
	}

	public void setCase (int small, Case symbol){
		cases[small] = symbol;
		if(symbol==Case.V) return;
		switch(small){
		case 0:
			majAv(0,symbol);
			majAv(3,symbol);
			majAv(6,symbol);
			break;
		case 1:
			majAv(1, symbol);
			majAv(3, symbol);
			break;
		case 2:
			majAv(2,symbol);
			majAv(3,symbol);
			majAv(7,symbol);
			break;
		case 3:	//Deuxieme ligne
			majAv(0,symbol);
			majAv(4,symbol);
			break;
		case 4:
			majAv(1,symbol);
			majAv(4,symbol);
			majAv(6,symbol);
			majAv(7,symbol);
			break;
		case 5:
			majAv(2, symbol);
			majAv(4, symbol);
			break;
		case 6:	//Troisième ligne
			majAv(0,symbol);
			majAv(5,symbol);
			majAv(7,symbol);
			break;
		case 7:
			majAv(1, symbol);
			majAv(5, symbol);
			break;
		case 8:
			majAv(2,symbol);
			majAv(5,symbol);
			majAv(6,symbol);
			break;
		default:
			break;
		}
		if(isFull() && this.etat==Case.F){
			bigG.changeAv(index, Case.F);
		}
	}

	private void majAv(int pos, Case symbol){
		if(avantages[pos]!=42){
			if(symbol==Constantes.SYMBOLE_J1){
				if(avantages[8+pos]>0){
					avantages[pos]=42;
					avantages[8+pos]=42;
				}
				else{
					if(++avantages[pos]==3) 
						{
						this.etat=Constantes.SYMBOLE_J1;
						bigG.changeAv(index, symbol);
						}
				}
			}
			else{
				if(avantages[pos]>0){
					avantages[8+pos]=42;
					avantages[pos]=42;
				}
				else{
					if(++avantages[8+pos]==3){
						this.etat=Constantes.SYMBOLE_J2;
						bigG.changeAv(index, symbol);
					}
				}
			}
		}
	}

	public void wintest(Case s) {
		if(this.etat==Case.V){
			for(int i=0; i<8; i++){	//Boucle pour les X
				if(avantages[i]==3){
					this.etat=Constantes.SYMBOLE_J1;
					bigG.changeAv(index, Constantes.SYMBOLE_J1);
				}
			}
			for(int i=8;i<16;i++){	//Boucle pour les O
				if(avantages[i]==3){
					this.etat=Constantes.SYMBOLE_J2;
					bigG.changeAv(index, Constantes.SYMBOLE_J2);
				}
			}
		}
		/*if((cases[4]==s &&
				((cases[0]==s && cases[8]==s) || (cases[1]==s && cases[7]==s) 
						|| (cases[2]==s && cases[6]==s) || (cases[3]==s && cases[5]==s)))
				|| (cases[0]==s && cases[3]==s && cases[6]==s)
				|| (cases[2]==s && cases[5]==s && cases[8]==s)
				|| (cases[0]==s && cases[1]==s && cases[2]==s)
				|| (cases[6]==s && cases[7]==s && cases[8]==s)){
			this.etat = s;
		}*/
	}

	public boolean isFull(){
		if(this.etat!=Case.V) return true;
		for (Case cel : cases) {
			if(cel==Case.V) return false;
		}
		this.etat=Case.F;
		return true;
	}

	public boolean isCaseLibre(int cas) {
		return (cases[cas]==Case.V);
	}

	/**
	 * Fonction qui évalue une smallgrille dans son entièreté: la victoire et les alignements
	 * @param s (Case du joueur qu'on évalue)
	 * @return valeur de la grille POUR LE JOUEUR DESIGNE
	 */
	public int evaluer(Case s){
		if(this.etat==s) return 200;
		if(this.etat==Case.F) return 0;
		int poidAllign = 0;
		int poidUnitaire = 1;

		if(s==Constantes.SYMBOLE_J1){
			for(int i=0;i<8;i++){
				if(avantages[i]==2) poidAllign += poidUnitaire;
			}
		}
		else{
			for(int i=8;i<16;i++){
				if(avantages[i]==2) poidAllign += poidUnitaire;
			}
		}

		/*
		// en ligne et colonnes
		for(int i=0; i<3; i++){
			if(cases[3*i]==s){		//Test des solutions en ligne
				if(cases[3*i+1]==s && cases[3*i+2]==Case.V){
					poidAllign += poidUnitaire;
				}
				else if(cases[3*i+1]==Case.V && cases[3*i+2]==s){
					poidAllign += poidUnitaire;
				}
			}
			else if (cases[3*i+1]==s && cases[3*i+2]==s && cases[3*i]==Case.V){
				poidAllign += poidUnitaire;
			}
			if(cases[i]==s){			//Test des solutions en colonne
				if(cases[3+i]==s && cases[6+i]==Case.V){
					poidAllign += poidUnitaire;
				}
				else if(cases[3+i]==Case.V && cases[6+i]==s){
					poidAllign += poidUnitaire;
				}
			}
			else if(cases[3+i]==s && cases[6+i]==s && cases[i]==Case.V){
				poidAllign += poidUnitaire;
			}
		}
		//Test du reste = les diagonales
		if(cases[0]==s && cases[4]==s && cases[8]==Case.V){
			poidAllign += poidUnitaire;
		}
		else if(cases[0]==s && cases[4]==Case.V && cases[8]==s){
			poidAllign += poidUnitaire;
		}
		else if(cases[0]==Case.V && cases[4]==s && cases[8]==s){
			poidAllign += poidUnitaire;
		}
		if(cases[2]==s && cases[4]==s && cases[6]==Case.V){
			poidAllign += poidUnitaire;
		}
		else if(cases[2]==s && cases[4]==Case.V && cases[6]==s){
			poidAllign += poidUnitaire;
		}
		else if(cases[2]==Case.V && cases[4]==s && cases[6]==s){
			poidAllign += poidUnitaire;
		}*/

		return poidAllign;
	}
}
