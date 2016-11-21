package co.edu.udistrital.poo.battleship.presentation;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import co.edu.udistrital.poo.battleship.bizlogic.Board;
import co.edu.udistrital.poo.battleship.bizlogic.Cell.CellState;
import co.edu.udistrital.poo.battleship.bizlogic.Ship.ShipOrientation;
import co.edu.udistrital.poo.battleship.bizlogic.Ship.ShipStatus;
import co.edu.udistrital.poo.battleship.bizlogic.Ship.ShipType;
import co.edu.udistrital.poo.battleship.bizlogic.Game;
import co.edu.udistrital.poo.battleship.bizlogic.Player;
import co.edu.udistrital.poo.battleship.bizlogic.Ship;
import co.edu.udistrital.poo.battleship.bizlogic.Sistema;
import co.edu.udistrital.poo.battleship.conect.SocketPlayer;

public class Model implements Runnable{
	
	private Thread drawingThread;
	private MainWindow mainWindow;
	private Sistema sistema;
	private BufferedImage dblBufferOwnBoardCanvas;
	private BufferedImage dblBufferEnemyBoardCanvas;
	private Game game = null;
	private Board enemyBoard;
	private Board ownBoard;
	private SocketPlayer socketPlayer;
	private int xPos = 0;
	private int yPos = 0;
	
	public Model(){
		getSistema().setActivo(true);
		drawingThread = new Thread(this);		
	}
	
	public void init(){
		
		getMainWindown().setSize(1280, 700);
		getMainWindown().setVisible(true);
		drawingThread.start();
		
		game = new Game();
		game.init();
		
		Player activePlayer = game.getPlayerInTurn();
		enemyBoard = activePlayer.getEnemyBoard();
		ownBoard = activePlayer.getOwnBoard();
		
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
					pencilOwnBoardCanvasDblBuffer.setColor(Color.BLACK);
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
			
		Player activePlayer = game.getPlayerInTurn();
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
		
		painted = activePlayer.getOwnBoard().markBoat(xCell, yCell, ship);
		
		if(painted){
			//game.incrementCarrierCounter();
		}
			
		
	}
	
	public void fire(int xPos, int yPos){
		
		Player activePlayer = game.getPlayerInTurn();
		int xCell = xPos / 40;
		int yCell = yPos / 40;
		
		System.out.println("xCell: " + xCell + " yCell: " + yCell);
		
		enemyBoard = activePlayer.fire(xCell, yCell);
		
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
			socketPlayer = new SocketPlayer(this);
			socketPlayer.initClientConnection(playerName, hostName, Integer.parseInt(port));
			socketPlayer.readMessage();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void initServer(String playerName){
		
		try{
			socketPlayer = new SocketPlayer(this);
			socketPlayer.initServerConnection();
			socketPlayer.waitForConnection(playerName);
			socketPlayer.readMessage();
			
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
	
}
