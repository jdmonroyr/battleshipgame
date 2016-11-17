package co.edu.udistrital.poo.battleship.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OwnBoardCanvasController implements MouseListener {
	
	private MainWindow mainWindow;
	
	public OwnBoardCanvasController(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mainWindow.getModel().placeShips(e.getX(), e.getY());
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
