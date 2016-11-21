package co.edu.udistrital.poo.battleship.conect;

public interface ThreadBattleShip {

	
	public void initGame();

	public void initClientConnection();

	public void waitForConnection();
	
	public void setEnemyFire(String posX, String posY);
	
	public void setOwnFire(String posX, String posY);
	
}
