package co.edu.udistrital.poo.battleship.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowLeftPaneController implements ActionListener{

	private MainWindow mainWindow;
	
	public MainWindowLeftPaneController(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//mainWindow.getModel().placeShips();
		
	}

}
