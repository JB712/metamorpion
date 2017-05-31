package data;


import util.Constantes.*;

public class SmallGrille{

	private Case etat = Case.V;
	private Case[] cases = new Case[9];		// les lignes sont [0-2], [3-5], [6-8]
	//private byte[] avantages = new byte[8];

	public SmallGrille(){
		for (int i=0; i<9; i++){
			cases[i]=Case.V;
		}
		//Arrays.fill(avantages, (byte) 0);
	}

	public Case getEtat(){
		return etat;
	}

	public Case getCase(int small) {
		return cases[small];
	}

	public void setCase (int small, Case symbol){
		cases[small] = symbol;
		/*byte unit = (symbol==Constantes.SYMBOLE_J1)?Constantes.SYMBOLE_J1:Constantes.SYMBOLE_J2;
		switch (small){
		case 0: avantages =
		}*/
	}

	public void wintest(Case s) {
		if((cases[4]==s &&
				((cases[0]==s && cases[8]==s) || (cases[1]==s && cases[7]==s) 
						|| (cases[2]==s && cases[6]==s) || (cases[3]==s && cases[5]==s)))
				|| (cases[0]==s && cases[3]==s && cases[6]==s)
				|| (cases[2]==s && cases[5]==s && cases[8]==s)
				|| (cases[0]==s && cases[1]==s && cases[2]==s)
				|| (cases[6]==s && cases[7]==s && cases[8]==s)){
			this.etat = s;
		}

		/*for(int i=0; i<3; i++){
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
		}*/
	}

	public boolean isFull(){
		if(this.etat!=Case.V) return true;
		for (Case cel : cases) {
			if(cel==Case.V) return false;
		}
		return true;
	}

	public boolean isCaseLibre(int cas) {
		return (cases[cas]==Case.V);
	}

	public int evaluer(Case s){
		int poidAllign = 0;
		int poidUnitaire = 1;

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
		}

		return poidAllign;
	}
}
