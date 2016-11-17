
import co.edu.udistrital.poo.battleship.conect.SocketPlayer1;
import co.edu.udistrital.poo.battleship.presentation.*;

public class LauncherPlayer1 {
	
	private Model myApp;
	private SocketPlayer1 player1;	
	
	public LauncherPlayer1() {
		myApp = new Model();
		myApp.init();
		
	}

	public static void main(String[] args) {
		
		new LauncherPlayer1();

	}

}
