package co.edu.udistrital.poo.battleship.bizlogic;

import java.util.ArrayList;
import java.util.List;

import co.edu.udistrital.poo.battleship.bizlogic.Cell.CellState;

public class Player {
	
	public enum Color { BLUE, RED, YELLOW, GREEN };
	
	private static int victories;
	
	private String name;
	private Color color;
	
	private boolean isInTurn;
	private Board ownBoard;
	private Board enemyBoard;
	private List<Ship> ships;
	
	private Game game;
	
	public Player(String name, Color color){
		this.name = name;
		this.color = color;
		ships = new ArrayList<Ship>();
	}
	
	// Getters and Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Color getColor() {
		return color;
	}

	public void addVictory() {
		victories++;
	}
	
	public int getVictories() {
		return victories;
	}
	
	public void setGame(Game game){
		this.game = game;
	}
	
	public Game getGame(){
		return game;
	}
	
	////////////////
	
	public Board fire(int xPos, int yPos){
		
		Cell cell = enemyBoard.getCell(xPos, yPos);
		
		int max = 2;
		int min = 1;
		
		int random = (int )(Math.random() * max + min);
		
		if(random == min){
			cell.setState(CellState.MISS);
			System.out.println("MISS!!!");
			return enemyBoard;
		}
		else {
			cell.setState(CellState.HIT);
			System.out.println("HIT!!!");
			return enemyBoard;
		}
	}
	
	public boolean checkIfHit(int xPos, int yPos){
		 return ownBoard.checkIfOwnHit(xPos, yPos);
	}
	
	public boolean checkIfSunk(int xPos, int yPos){
		return ownBoard.isShipSunk(xPos, yPos);
	}
	
	public void initBoards(){
		ownBoard = new Board (10, 10);
		enemyBoard = new Board (10, 10);
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
	
	
			

}
