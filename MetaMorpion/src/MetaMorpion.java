import data.BigGrille;
import util.Constantes;
import ui.Console;

public class MetaMorpion {

	public static void main(String[] args) {

		int mode = Constantes.MODE_CONSOLE;
		//Indique la bonne interface et la lance dans un thread différent
		switch(mode)
		{
		default :
			Console console = new Console();
			console.start();
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
