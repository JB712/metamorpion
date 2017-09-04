import util.Constantes;
import ui.Console;
import ui.ig.ConsoleIG;

public class MetaMorpion {

	public static void main(String[] args) {

		int mode = Constantes.MODE_INTERFACE_GRAPHIQUE;
		//Indique la bonne interface et la lance dans un thread différent
		switch(mode)
		{
		case Constantes.MODE_INTERFACE_GRAPHIQUE:
			new ConsoleIG().start();
			break;
		default :
			new Console().start();
			break;
		}

		/*
		Console console = new Console();
		console.start();
		//BigGrille tata = new BigGrille();
		//Console.afficheGrille(tata);
		 */

	}

}

// Made by Lecoin Jean-Baptiste & Didelot Igor