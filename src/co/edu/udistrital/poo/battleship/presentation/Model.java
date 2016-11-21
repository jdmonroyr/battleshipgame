package co.edu.udistrital.poo.battleship.presentation;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.udistrital.poo.battleship.bizlogic.Board;
import co.edu.udistrital.poo.battleship.bizlogic.Cell.CellState;
import co.edu.udistrital.poo.battleship.bizlogic.Ship.ShipOrientation;
import co.edu.udistrital.poo.battleship.bizlogic.Ship.ShipStatus;
import co.edu.udistrital.poo.battleship.bizlogic.Ship.ShipType;
import co.edu.udistrital.poo.battleship.bizlogic.Game;
import co.edu.udistrital.poo.battleship.bizlogic.Game.GameShipsMax;
import co.edu.udistrital.poo.battleship.bizlogic.Player;
import co.edu.udistrital.poo.battleship.bizlogic.Player.PlacingShipsStatus;
import co.edu.udistrital.poo.battleship.bizlogic.Ship;
import co.edu.udistrital.poo.battleship.bizlogic.Sistema;
import co.edu.udistrital.poo.battleship.conect.ThreadBattleShip;
import co.edu.udistrital.poo.battleship.conect.ThreadCliente;
import co.edu.udistrital.poo.battleship.conect.ThreadServer;

public class Model implements Runnable{
	
	private Thread drawingThread;
	private MainWindow mainWindow;
	private Sistema sistema;
	private BufferedImage dblBufferOwnBoardCanvas;
	private BufferedImage dblBufferEnemyBoardCanvas;
	private Game game = null;
	private Board enemyBoard;
	private Board ownBoard;
//<<<<<<< HEAD
	private ThreadBattleShip hiloJuego;
	
	//FABIO

	// jdm
	
	private int xPos = 0;
	private int yPos = 0;
//=======
	//private SocketPlayer socketPlayer;
//>>>>>>> branch 'master' of https://github.com/jdmonroyr/battleshipgame.git
	
	public Model(){
		getSistema().setActivo(true);
		drawingThread = new Thread(this);		
	}
	
	public void init(){
		
		getMainWindown().setSize(1280, 700);
		getMainWindown().setVisible(true);
		
		getMainWindown().lblPlayerNameText.setText("no name yet");
		getMainWindown().lblOpponentsNameText.setText("no name yet");
		getMainWindown().lblStatusText.setText("waiting game...");
		
		getMainWindown().lblBattleshipQty.setText(String.valueOf(Game.GameShipsMax.BATTLESHIP.getQuantity()));
		getMainWindown().lblCruiserQty.setText(String.valueOf(Game.GameShipsMax.CRUISER.getQuantity()));
		getMainWindown().lblSubmarineQty.setText(String.valueOf(Game.GameShipsMax.SUBMARINE.getQuantity()));
		getMainWindown().lblDestroyerQty.setText(String.valueOf(Game.GameShipsMax.DESTROYER.getQuantity()));
		
		getMainWindown().lblLengthBattleship.setText(String.valueOf(ShipType.BATTLESHIP.getLength()));
		getMainWindown().lblLengthCruiser.setText(String.valueOf(ShipType.CRUISER.getLength()));
		getMainWindown().lblLengthSubmarine.setText(String.valueOf(ShipType.SUBMARINE.getLength()));
		getMainWindown().lblLengthDestroyer.setText(String.valueOf(ShipType.DESTROYER.getLength()));
		
		getMainWindown().rdBtnBattleship.setEnabled(false);
		getMainWindown().rdBtnCruiser.setEnabled(false);
		getMainWindown().rdBtnSubmarine.setEnabled(false);
		getMainWindown().rdBtnDestroyer.setEnabled(false);
		
		getMainWindown().rdbtnHorizontal.setEnabled(false);
		getMainWindown().rdbtnVertical.setEnabled(false);
		
		getMainWindown().lblShotsQty.setText("--");
		getMainWindown().lblHitsQty.setText("--");
		getMainWindown().lblMissesQty.setText("--");
		
		getMainWindown().lblShipsQty.setText("--");
		getMainWindown().lblShipsAliveQty.setText("--");
		getMainWindown().lblShipsSunkQty.setText("--");
		
	}
	
	public void startPlacingShips(){
		
		getMainWindown().rdBtnBattleship.setEnabled(true);
		getMainWindown().rdBtnCruiser.setEnabled(true);
		getMainWindown().rdBtnSubmarine.setEnabled(true);
		getMainWindown().rdBtnDestroyer.setEnabled(true);
		
		getMainWindown().rdbtnHorizontal.setEnabled(true);
		getMainWindown().rdbtnVertical.setEnabled(true);
		
	}
	
	
	protected void draw(){
		
		Canvas ownBoardCanvas = getMainWindown().ownBoardCanvas;
		Canvas enemyBoardCanvas = getMainWindown().enemyBoardCanvas;
		
		Graphics pencilOwnBoardCanvas = ownBoardCanvas.getGraphics();
		dblBufferOwnBoardCanvas = new BufferedImage(ownBoardCanvas.getWidth(), ownBoardCanvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		Graphics pencilEnemyBoardCanvas = enemyBoardCanvas.getGraphics();
		dblBufferEnemyBoardCanvas = new BufferedImage(enemyBoardCanvas.getWidth(), enemyBoardCanvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		Graphics pencilOwnBoardCanvasDblBuffer = dblBufferOwnBoardCanvas.getGraphics();
		pencilOwnBoardCanvasDblBuffer.setColor(Color.BLUE);
		pencilOwnBoardCanvasDblBuffer.fillRect(0, 0, ownBoardCanvas.getWidth(), ownBoardCanvas.getHeight());
		
		for (int i = 0; i < ownBoard.getRows(); i++){
			for(int j= 0; j < ownBoard.getColumns(); j++){
				
				if(ownBoard.getCells()[i][j].getState() == CellState.HIT){
					pencilOwnBoardCanvasDblBuffer.setColor(Color.RED);
					pencilOwnBoardCanvasDblBuffer.fillRect(i * 40, j * 40, 40, 40);
				}
				
				else if(ownBoard.getCells()[i][j].getState() == CellState.MISS){
					pencilOwnBoardCanvasDblBuffer.setColor(Color.WHITE);
					pencilOwnBoardCanvasDblBuffer.fillRect(i * 40, j * 40, 40, 40);
				}
				
				else if(ownBoard.getCells()[i][j].getState() == CellState.BOAT){
					
					if(ownBoard.getCells()[i][j].getShip().getType() == ShipType.BATTLESHIP){
						pencilOwnBoardCanvasDblBuffer.setColor(Color.YELLOW);
					}
					else if(ownBoard.getCells()[i][j].getShip().getType() == ShipType.CRUISER){
						pencilOwnBoardCanvasDblBuffer.setColor(Color.GREEN);
					}
					else if(ownBoard.getCells()[i][j].getShip().getType() == ShipType.SUBMARINE){
						pencilOwnBoardCanvasDblBuffer.setColor(Color.CYAN);
					}
					else if(ownBoard.getCells()[i][j].getShip().getType() == ShipType.DESTROYER){
						pencilOwnBoardCanvasDblBuffer.setColor(Color.ORANGE);
					}
					
					pencilOwnBoardCanvasDblBuffer.fillRect(i * 40, j * 40, 40, 40);
				}
			}
		}
		
		pencilOwnBoardCanvasDblBuffer.setColor(Color.RED);
		
		for (int i = 0; i < 10; i++){
			pencilOwnBoardCanvasDblBuffer.drawLine(0, i*40, ownBoardCanvas.getWidth(), i*40);
		}
		
		for (int j = 0; j < 10; j++){
			pencilOwnBoardCanvasDblBuffer.drawLine(j*40, 0, j*40, ownBoardCanvas.getHeight());
		}
		
		pencilOwnBoardCanvas.drawImage(dblBufferOwnBoardCanvas, 0, 0, ownBoardCanvas);
		
		Graphics pencilEnemyBoardCanvasDblBuffer = dblBufferEnemyBoardCanvas.getGraphics();
		pencilEnemyBoardCanvasDblBuffer.setColor(Color.RED);
		pencilEnemyBoardCanvasDblBuffer.fillRect(0, 0, ownBoardCanvas.getWidth(), ownBoardCanvas.getHeight());
		
		for (int i = 0; i < enemyBoard.getRows(); i++){
			for(int j= 0; j < enemyBoard.getColumns(); j++){
				if(enemyBoard.getCells()[i][j].getState() == CellState.HIT){
					pencilEnemyBoardCanvasDblBuffer.setColor(Color.BLUE);
					pencilEnemyBoardCanvasDblBuffer.fillRect(i * 40, j * 40, 40, 40);
				}
				else if(enemyBoard.getCells()[i][j].getState() == CellState.MISS){
					pencilEnemyBoardCanvasDblBuffer.setColor(Color.WHITE);
					pencilEnemyBoardCanvasDblBuffer.fillRect(i * 40, j * 40, 40, 40);
				}
			}
		}
		
		pencilEnemyBoardCanvasDblBuffer.setColor(Color.BLUE);
		
		for (int i = 0; i < 10; i++){
			pencilEnemyBoardCanvasDblBuffer.drawLine(0, i*40, enemyBoardCanvas.getWidth(), i*40);
		}
		
		for (int j = 0; j < 10; j++){
			pencilEnemyBoardCanvasDblBuffer.drawLine(j*40, 0, j*40, enemyBoardCanvas.getHeight());
		}
		
	
		pencilEnemyBoardCanvas.drawImage(dblBufferEnemyBoardCanvas, 0, 0, enemyBoardCanvas);
		
	}
	
	public void placeShips(int xPos, int yPos){
		
		Ship ship = null;
		
		if(getMainWindown().rdBtnBattleship.isSelected() == true)
			ship = new Ship(ShipType.BATTLESHIP);
		else if (getMainWindown().rdBtnCruiser.isSelected() == true)
			ship = new Ship(ShipType.CRUISER);
		else if(getMainWindown().rdBtnSubmarine.isSelected() == true)
			ship = new Ship(ShipType.SUBMARINE);
		else if(getMainWindown().rdBtnDestroyer.isSelected() == true)
			ship = new Ship(ShipType.DESTROYER);
			
		Player player = game.getPlayerForGame();
		
		int xCell = xPos / 40;
		int yCell = yPos / 40;
		
		if(getMainWindown().rdbtnHorizontal.isSelected() == true)
			ship.setOrientation(ShipOrientation.HORIZONTAL);
		else if(getMainWindown().rdbtnVertical.isSelected() == true)
			ship.setOrientation(ShipOrientation.VERTICAL);
		else
			ship.setOrientation(ShipOrientation.HORIZONTAL);
		
		ship.setStatus(ShipStatus.FLOATING);
		
		boolean painted;
		
		PlacingShipsStatus placingShipStatus = player.markBoat(xCell, yCell, ship);
		
		if(placingShipStatus == PlacingShipsStatus.COLLISION)
			showErrorPopUp("No es posible posicionar el barco en esa posición.");
		else if(placingShipStatus == PlacingShipsStatus.MAXNUM)
			showErrorPopUp("Se ha alcanzado el máxino número de barcos de este tipo.");
		else if (placingShipStatus == PlacingShipsStatus.OK){
			int left = 0;
			if(ship.getType() == ShipType.BATTLESHIP){
				left = GameShipsMax.BATTLESHIP.getQuantity() - player.getShipNumberForType(ShipType.BATTLESHIP); 
				getMainWindown().lblBattleshipQty.setText(String.valueOf(left));
			}
			if(ship.getType() == ShipType.CRUISER){
				left = GameShipsMax.CRUISER.getQuantity() - player.getShipNumberForType(ShipType.CRUISER);
				getMainWindown().lblCruiserQty.setText(String.valueOf(left));
			}
			if(ship.getType() == ShipType.SUBMARINE){
				left = GameShipsMax.SUBMARINE.getQuantity() - player.getShipNumberForType(ShipType.SUBMARINE);
				getMainWindown().lblSubmarineQty.setText(String.valueOf(left));
			}
			if(ship.getType() == ShipType.DESTROYER){
				left = GameShipsMax.DESTROYER.getQuantity() - player.getShipNumberForType(ShipType.DESTROYER);
				getMainWindown().lblDestroyerQty.setText(String.valueOf(left));
			}
		}
		
	}
	
	public void fire(int xPos, int yPos){
		
		Player player = game.getPlayerForGame();
		
		int xCell = xPos / 40;
		int yCell = yPos / 40;
		
		System.out.println("xCell: " + xCell + " yCell: " + yCell);
		
		player.fire(xCell, yCell);
		
		getMainWindown().lblShotsQty.setText(String.valueOf(player.getShots()));
		getMainWindown().lblHitsQty.setText(String.valueOf(player.getHits()));
		getMainWindown().lblMissesQty.setText(String.valueOf(player.getMisses()));
		
	}
	
	public void run(){
		while(getSistema().isActivo()){
			draw();
		}
	}
	
	public MainWindow getMainWindown(){
		if(mainWindow == null){
			mainWindow = new MainWindow(this);
		}
		return mainWindow;
	}
	
	public Sistema getSistema(){
		if(sistema == null){
			sistema = new Sistema();
		}
		return sistema;
	}
	
	public void initClient(String hostName,String port, String playerName){
		try {
			hiloJuego = new ThreadCliente(this, playerName, hostName, Integer.parseInt(port));
			hiloJuego.initClientConnection();
			//socketPlayer.initClientConnection(playerName, hostName, Integer.parseInt(port));
			//socketPlayer.readMessage();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void initServer(String playerName){
		
		try{
				hiloJuego = new ThreadServer(this, playerName);
				hiloJuego.waitForConnection();
				//socketPlayer.readMessage();
		} catch (Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		
		getMainWindown().lblStatusText.setText("Esperando oponente...");
	}
	
	public void setPlayerName(String name){
		getMainWindown().lblPlayerNameText.setText(name);
	}
	
	public void setOpponentName(String name){
		getMainWindown().lblOpponentsNameText.setText(name);
	}
	
	public void configServerPlayer(String playerName){
				
		game = new Game();
		Player player = new Player(playerName, game);
		
		game.init(player, null);
		enemyBoard = player.getEnemyBoard();
		ownBoard = player.getOwnBoard();
		
		drawingThread.start();
		
		initServer(playerName);
		
		getMainWindown().lblPlayerNameText.setText(playerName);
		getMainWindown().lblStatusText.setText("waiting for opp..");
		
	}
	
	public void configClientPlayer(String playerName, String serverAddress, String serverPort){
		
		game = new Game();
		Player player = new Player(playerName, game);
		
		game.init(null, player);
		enemyBoard = player.getEnemyBoard();
		ownBoard = player.getOwnBoard();
		
		drawingThread.start();
		
		initClient(serverAddress, serverPort, playerName);
		
		getMainWindown().lblPlayerNameText.setText(playerName);
		getMainWindown().lblStatusText.setText("joined game...");

	}
	
	public void showErrorPopUp(String message){
		
		JOptionPane.showMessageDialog(getMainWindown(), message, "Error Message", JOptionPane.ERROR_MESSAGE);
		
	}
	
	// Pop up for server player

	public void showDialogCreateGame() {

			JTextField playerName = new JTextField("player 1");
			JPanel panel = new JPanel(new GridLayout(0, 1));
			panel.add(new JLabel("Player Name:"));
			panel.add(playerName);
			
			int result = JOptionPane.showConfirmDialog(getMainWindown(), panel, "Create Game", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);
			
			if (result == JOptionPane.OK_OPTION) {
				if(playerName.getText().isEmpty()){
					playerName.setText("player 1");
				}
				//getModel().initServer(playerName.getText());
				configServerPlayer(playerName.getText());
			} else {
				System.out.println("Cancelled");
			}
		}

		// Pop up window for client player
		
		public void showDialogJoinGame() {

			JTextField playerName = new JTextField("player 2");
			JTextField serverAddress = new JTextField("127.0.0.1");
			JTextField serverPort = new JTextField("50000");

			JPanel panelPlayerTwo = new JPanel(new GridLayout(0, 1));
			panelPlayerTwo.add(new JLabel("Player Name:"));
			panelPlayerTwo.add(playerName);
			panelPlayerTwo.add(new JLabel("Server Address:"));
			panelPlayerTwo.add(serverAddress);
			panelPlayerTwo.add(new JLabel("Server Port:"));
			panelPlayerTwo.add(serverPort);

			int result = JOptionPane.showConfirmDialog(getMainWindown(), panelPlayerTwo, "Join Game", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);
			
			if (result == JOptionPane.OK_OPTION) {
				configClientPlayer(playerName.getText(), serverAddress.getText(), serverPort.getText());
				//getModel().initClient(serverAddress.getText(), serverPort.getText(), playerName.getText());
			} else {
				System.out.println("Cancelled");
			}
		}
		
		public void beginGame(){
			
			int shipsNumber = GameShipsMax.BATTLESHIP.getQuantity() + GameShipsMax.CRUISER.getQuantity() +
					GameShipsMax.SUBMARINE.getQuantity() + GameShipsMax.DESTROYER.getQuantity();
			
			Player player = game.getPlayerForGame();
			if(player.getShips().size() != shipsNumber){
				int left = shipsNumber - player.getShips().size();
				showErrorPopUp("Faltan " + left + " barcos por posicionar.");
			}
			else {			
				getMainWindown().enemyBoardCanvas.addMouseListener(getMainWindown().getEnemyBoardCanvasController());
			// call action command LIS
			}
		}
}
