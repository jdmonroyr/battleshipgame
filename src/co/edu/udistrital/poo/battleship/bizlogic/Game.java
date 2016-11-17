package co.edu.udistrital.poo.battleship.bizlogic;

import co.edu.udistrital.poo.battleship.bizlogic.Player.Color;

public class Game {
	
	public enum GameState { FINISHED, INGAME, PLACINGSHIPS, NOTBEGUN, FABIO }
	public enum GameShipsMax { 
		CARRIER     (1), 
		BATTLESHIP  (1), 
		CRUISER     (1), 
		SUBMARINE   (2), 
		DESTROYER   (2) 
		;
		
		private final int quantity;
		
		private GameShipsMax(int quantity){
			this.quantity = quantity;
		}
		
		public int getQuantity(){
			return quantity;
		}
	}
	
	private static int plays = 0;
	private GameState state;
	private Player playerOne;
	private Player playerTwo;
	private Player playerInTurn;
	
	private int carrierCounter = 0;
	private int battleshipCounter = 0;
	private int cruiserCounter = 0;
	private int submarineCounter = 0;
	private int destroyerCounter = 0;
	
	
	public Game(){
		init();	
	}

	public static void updatePlays() {
		plays++;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}
	
	public void init(){
		setState(GameState.INGAME);
		playerOne = new Player("player1", Color.BLUE);
		//playerTwo = new Player("player2", Color.RED);
		playerOne.initBoards();
		//playerTwo.initBoards();
		updatePlays();
		setPlayerInTurn(playerOne);
		
	}
	
	public void shiftTurn(){
		
		if(playerOne.isInTurn()){
			playerOne.setTurn(false);
			playerTwo.setTurn(true);
		}
		else {
			playerOne.setTurn(true);
			playerTwo.setTurn(false);
		}
	}

	public Player getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}

	public Player getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}

	public Player getPlayerInTurn() {
		return playerInTurn;
	}

	public void setPlayerInTurn(Player playerInTurn) {
		this.playerInTurn = playerInTurn;
	}

	public boolean areCarriersAvailable() {
		if(carrierCounter >= GameShipsMax.CARRIER.getQuantity())
			return false;
		
		return true;
	}

	public void incrementCarrierCounter() {
		carrierCounter++;
	}	

}
