package co.edu.udistrital.poo.battleship.presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindowController implements ActionListener {

	private MainWindow mainWindow;

	public MainWindowController(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == "Create"){
			mainWindow.getModel().showDialogCreateGame();
		}
		else if(e.getActionCommand() == "Join"){
			mainWindow.getModel().showDialogJoinGame();
		}
		else if(e.getActionCommand() == "Place Ships"){
			mainWindow.getModel().startPlacingShips();
		}
		
		else if(e.getActionCommand() == "Begin Game"){
			mainWindow.getModel().readyToBegin();
		}

	}

}
