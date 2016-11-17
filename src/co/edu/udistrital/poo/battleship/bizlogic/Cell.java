package co.edu.udistrital.poo.battleship.bizlogic;

public class Cell {
	
	public enum CellState { BLANK, MISS, HIT, BOAT }
	
	private CellState state;
	private Ship ship;
	
	public Cell (){
	}
	
	public CellState getState(){
		return state;
	}
	
	public void setState(CellState state){
		this.state = state;
	}
	
	public void setShip (Ship ship){
		this.ship = ship;
	}
	
	public Ship getShip(){
		return ship;
	}

}
