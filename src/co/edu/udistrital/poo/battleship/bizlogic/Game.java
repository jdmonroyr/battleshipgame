package co.edu.udistrital.poo.battleship.bizlogic;

public class Game {
	
	public enum GameState { FINISHED, INGAME, PLACINGSHIPS, WAITINGFOROPP }
	public enum GameType { SERVER, CLIENT }
	public enum GameShipsMax {  
		BATTLESHIP  (1), 
		CRUISER     (2), 
		SUBMARINE   (3), 
		DESTROYER   (4) 
		;
		
		private final int quantity;
		
		private GameShipsMax(int quantity){
			this.quantity = quantity;
		}
		
		public int getQuantity(){
			return quantity;
		}
	}
	
	private GameState state;
	private GameType gameType;
	private Player playerOne;
	private Player playerTwo;
	
	final static int BOARD_LENGTH = 10;
	final static int BOARD_WIDTH = 10;
	
	public Game(){
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}
	
	public void init(Player playerOne, Player playerTwo){
		setState(GameState.WAITINGFOROPP);
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		if(playerOne != null){
			playerOne.initBoards();
			setGameType(GameType.SERVER);
		}
		else if(playerTwo != null){
			playerTwo.initBoards();
			setGameType(GameType.CLIENT);
		}
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

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}
	
	public Player getPlayerForGame(){
		if(this.gameType == GameType.SERVER)
			return playerOne;
		else if(this.gameType == GameType.CLIENT)
			return playerTwo;
		
		return playerOne;
			
	}
	
}
