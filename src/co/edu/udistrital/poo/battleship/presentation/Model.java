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
import co.edu.udistrital.poo.battleship.conect.SocketPlayer1;

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
		
		getMainWindown().getLblCarrierQty().setText(String.valueOf(Game.GameShipsMax.CARRIER.getQuantity()));
		getMainWindown().getLblBattleshipQty().setText(String.valueOf(Game.GameShipsMax.BATTLESHIP.getQuantity()));
		getMainWindown().getLblCruiserQty().setText(String.valueOf(Game.GameShipsMax.CRUISER.getQuantity()));
		getMainWindown().getLblSubmarineQty().setText(String.valueOf(Game.GameShipsMax.SUBMARINE.getQuantity()));
		getMainWindown().getLblDestroyerQty().setText(String.valueOf(Game.GameShipsMax.DESTROYER.getQuantity()));
		
		getMainWindown().getLblLengthCarrier().setText(String.valueOf(ShipType.CARRIER.getLength()));
		getMainWindown().getLblLengthBattleship().setText(String.valueOf(ShipType.BATTLESHIP.getLength()));
		getMainWindown().getLblLengthCruiser().setText(String.valueOf(ShipType.CRUISER.getLength()));
		getMainWindown().getLblLengthSubmarine().setText(String.valueOf(ShipType.SUBMARINE.getLength()));
		getMainWindown().getLblLengthDestroyer().setText(String.valueOf(ShipType.DESTROYER.getLength()));
	}
	
	
	protected void draw(){
		
		Canvas ownBoardCanvas = getMainWindown().getOwnBoardCanvas();
		Canvas enemyBoardCanvas = getMainWindown().getEnemyBoardCanvas();
		
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
		
		if(getMainWindown().getRdBtnCarrier().isSelected() == true){
			if(game.areCarriersAvailable()){
				ship = new Ship(ShipType.CARRIER);
			}
		}
		else if(getMainWindown().getRdBtnBattleship().isSelected() == true)
			ship = new Ship(ShipType.BATTLESHIP);
		else if (getMainWindown().getRdBtnCruiser().isSelected() == true)
			ship = new Ship(ShipType.CRUISER);
		else if(getMainWindown().getRdBtnSubmarine().isSelected() == true)
			ship = new Ship(ShipType.SUBMARINE);
		else if(getMainWindown().getRdBtnDestroyer().isSelected() == true)
			ship = new Ship(ShipType.DESTROYER);
			
		Player activePlayer = game.getPlayerInTurn();
		int xCell = xPos / 40;
		int yCell = yPos / 40;
		
		if(getMainWindown().getRdbtnHorizontal().isSelected() == true)
			ship.setOrientation(ShipOrientation.HORIZONTAL);
		else if(getMainWindown().getRdbtnVertical().isSelected() == true)
			ship.setOrientation(ShipOrientation.VERTICAL);
		else
			ship.setOrientation(ShipOrientation.HORIZONTAL);
		
		ship.setStatus(ShipStatus.FLOATING);
		
		boolean painted;
		
		painted = activePlayer.getOwnBoard().markBoat(xCell, yCell, ship);
		
		if(painted)
			game.incrementCarrierCounter();
		
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
	
}
