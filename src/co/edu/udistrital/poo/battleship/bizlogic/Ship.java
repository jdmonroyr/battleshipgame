package co.edu.udistrital.poo.battleship.bizlogic;

import java.util.ArrayList;

public class Ship {
	
	public enum ShipType { 
		CARRIER     (5), 
		BATTLESHIP  (4), 
		CRUISER     (3), 
		SUBMARINE   (2), 
		DESTROYER   (2) 
		;
		
		private final int length;
		
		private ShipType(int length){
			this.length = length;
		}
		
		public int getLength(){
			return length;
		}
	}
	
	public enum ShipOrientation { VERTICAL, HORIZONTAL}
	public enum ShipStatus { FLOATING, SUNK }
	
	private ShipType type;
	private ShipStatus status;
	private ShipOrientation orientation;
	private int hits;
	private ArrayList<Cell> location;
	
	public Ship(ShipType type){
		this.type = type;
		status = ShipStatus.FLOATING;
	}

	public ShipType getType() {
		return type;
	}

	public void setType(ShipType type) {
		this.type = type;
	}

	public ArrayList<Cell> getLocation() {
		return location;
	}

	public void setLocation(ArrayList<Cell> location) {
		this.location = location;
	}

	public ShipOrientation getOrientation() {
		return orientation;
	}

	public void setOrientation(ShipOrientation orientation) {
		this.orientation = orientation;
	}
	
	public void increaseHits(){
		hits++;
	}
	
	public int getHits(){
		return hits;
	}
	
	public ShipStatus getStatus(){
		return status;
	}
	
	public void setStatus(ShipStatus status){
		this.status = status;
	}
	
	

}
