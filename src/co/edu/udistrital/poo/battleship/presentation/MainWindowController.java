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
			mainWindow.showPlayerDialog();
		}
		else if(e.getActionCommand() == "Join"){
			mainWindow.showServerDialog();
		}

	}

}