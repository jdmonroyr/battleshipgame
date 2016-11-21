package co.edu.udistrital.poo.battleship.bizlogic;

import java.util.ArrayList;

import co.edu.udistrital.poo.battleship.bizlogic.Cell.CellState;
import co.edu.udistrital.poo.battleship.bizlogic.Ship.ShipOrientation;
import co.edu.udistrital.poo.battleship.bizlogic.Ship.ShipStatus;

public class Board {

	private int columns;
	private int rows;
	private Cell[][] cells;

	public Board(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}

	public void initBoard() {
		cells = new Cell[columns][rows];
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				cells[i][j] = new Cell();
				cells[i][j].setState(CellState.BLANK);
			}
		}
	}

	public Cell getCell(int xPos, int yPos) {
		return cells[xPos][yPos];
	}

	public boolean isShipSunk(int xPos, int yPos) {
		Ship ship;

		if (cells[xPos][yPos].getShip() == null)
			return false;

		ship = cells[xPos][yPos].getShip();

		if (ship.getHits() == ship.getType().getLength())
			ship.setStatus(ShipStatus.SUNK);

		return true;
	}

	public void markOwnFiring(int xPos, int yPos, CellState state) {
		cells[xPos][yPos].setState(state);
	}

	public boolean checkIfOwnHit(int xPos, int yPos) {
		if (cells[xPos][yPos].getState() == Cell.CellState.BOAT) {
			cells[xPos][yPos].setState(Cell.CellState.HIT);
			Ship ship = cells[xPos][yPos].getShip();
			ship.increaseHits();
			return true;
		} else {
			cells[xPos][yPos].setState(Cell.CellState.MISS);
			return false;
		}
	}

	public boolean markBoat(int xPos, int yPos, Ship ship) {

		if (ship.getOrientation() == Ship.ShipOrientation.HORIZONTAL) {

			int overFlowX = 0;
			overFlowX = xPos + ship.getType().getLength() - columns;
			if (overFlowX > 0)
				xPos = xPos - overFlowX;
			
			for (int i = xPos; i < xPos + ship.getType().getLength(); i++){
				if(cells[i][yPos].getState() == CellState.BOAT){
					return false;
				}
			}
			for (int i = xPos; i < xPos + ship.getType().getLength(); i++){
				cells[i][yPos].setState(CellState.BOAT);
				cells[i][yPos].setShip(ship);
			}
		}

		else if (ship.getOrientation() == Ship.ShipOrientation.VERTICAL) {

			int overFlowY = 0;
			overFlowY = yPos + ship.getType().getLength() - rows;
			if (overFlowY > 0)
				yPos = yPos - overFlowY;
			
			for (int j = yPos; j < yPos + ship.getType().getLength(); j++){
				if(cells[xPos][j].getState() == CellState.BOAT){
					return false;
				}
			}

			for (int j = yPos; j < yPos + ship.getType().getLength(); j++){
				cells[xPos][j].setState(CellState.BOAT);
				cells[xPos][j].setShip(ship);
			}
				
		}
		
		return true;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

}
