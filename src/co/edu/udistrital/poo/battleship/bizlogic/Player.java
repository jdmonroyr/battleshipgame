package co.edu.udistrital.poo.battleship.bizlogic;

import java.util.ArrayList;
import java.util.List;

import co.edu.udistrital.poo.battleship.bizlogic.Cell.CellState;
import co.edu.udistrital.poo.battleship.bizlogic.Game.GameShipsMax;
import co.edu.udistrital.poo.battleship.bizlogic.Ship.ShipType;

public class Player {
	
	public enum PlacingShipsStatus { OK, COLLISION, MAXNUM }
	
	private String name;
	
	private boolean isInTurn;
	private Board ownBoard;
	private Board enemyBoard;
	private int shots = 0;
	private int hits = 0;
	private int misses = 0;
	private List<Ship> ships;
	
	private Game game;
	
	public Player(String name, Game game){
		this.name = name;
		this.game = game;
		ships = new ArrayList<Ship>();
	}
	
	// Getters and Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setGame(Game game){
		this.game = game;
	}
	
	public Game getGame(){
		return game;
	}
	
	////////////////
	
	public CellState fire(int xPos, int yPos){
		
		Cell cell = enemyBoard.getCell(xPos, yPos);
		
		
		// Call socket to fire;
		
		int max = 2;
		int min = 1;
		
		int random = (int )(Math.random() * max + min);
		
		if(random == min){
			cell.setState(CellState.MISS);
			System.out.println("MISS!!!");
		}
		else {
			cell.setState(CellState.HIT);
			System.out.println("HIT!!!");
		}
		
		shots++;
		if(cell.getState() == CellState.HIT)
			hits++;
		else if(cell.getState() == CellState.MISS)
			misses++;
		
		return cell.getState();
		
	}
	
	public boolean checkIfHit(int xPos, int yPos){
		 return ownBoard.checkIfOwnHit(xPos, yPos);
	}
	
	public boolean checkIfSunk(int xPos, int yPos){
		return ownBoard.isShipSunk(xPos, yPos);
	}
	
	public void initBoards(){
		ownBoard = new Board (Game.BOARD_LENGTH, Game.BOARD_WIDTH);
		enemyBoard = new Board (Game.BOARD_LENGTH, Game.BOARD_WIDTH);
		ownBoard.initBoard();
		enemyBoard.initBoard();
	}
	
	public boolean isInTurn(){
		return isInTurn;
	}
	
	public void setTurn(boolean isInTurn){
		this.isInTurn = isInTurn;
	}
	
	
	public void updateBoard(Board board){
		
	}
	
	public void addShip(Ship ship){
		ships.add(ship);
	}

	public Board getEnemyBoard() {
		return enemyBoard;
	}
	
	public Board getOwnBoard() {
		return ownBoard;
	}

	public void setEnemyBoard(Board enemyBoard) {
		this.enemyBoard = enemyBoard;
	}
	
	public PlacingShipsStatus markBoat(int xPos, int yPos, Ship ship) {
		
		int counterShips = 0;
		
		for(Ship ownShip : ships){
			if(ownShip.getType() == ship.getType())
				counterShips++;
		}
		
		GameShipsMax gameShipTypeMax = null;
		
		if(ship.getType() == ShipType.BATTLESHIP)
			gameShipTypeMax = GameShipsMax.BATTLESHIP;
		
		else if(ship.getType() == ShipType.CRUISER)
			gameShipTypeMax = GameShipsMax.CRUISER;
		
		else if(ship.getType() == ShipType.SUBMARINE)
			gameShipTypeMax = GameShipsMax.SUBMARINE;
		
		else if(ship.getType() == ShipType.DESTROYER)
			gameShipTypeMax = GameShipsMax.DESTROYER;
		
		
		if(counterShips >= gameShipTypeMax.getQuantity())
			return PlacingShipsStatus.MAXNUM;
		
		
		if(ownBoard.markBoat(xPos, yPos, ship)){
			ships.add(ship);
			return PlacingShipsStatus.OK;
		}
		else{
			return PlacingShipsStatus.COLLISION;
		}
		
	}
	
	public int getShipNumberForType(ShipType shipType){
		int counterShips = 0;
		
		for(Ship ownShip : ships){
			if(ownShip.getType() == shipType)
				counterShips++;
		}
		
		return counterShips;
	}

	public int getShots() {
		return shots;
	}

	public int getHits() {
		return hits;
	}

	public int getMisses() {
		return misses;
	}
	
	public List<Ship> getShips(){
		return ships;
	}
}
