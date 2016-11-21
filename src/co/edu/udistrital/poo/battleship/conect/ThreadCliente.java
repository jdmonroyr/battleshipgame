package co.edu.udistrital.poo.battleship.conect;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import co.edu.udistrital.poo.battleship.presentation.Model;

public class ThreadCliente implements Runnable, ThreadBattleShip{

	private Model model;
	private String playerName, hostName;
	private int port;
	private Socket player;
	private SocketPlayer socketPlayer;
	private DataOutputStream salida;
	private DataInputStream entrada;
	
	public ThreadCliente(Model model, String playerName, String hostName, int port) {
		this.model = model;
		this.playerName = playerName;
		this.port = port;
		this.hostName = hostName;


	}
	
public void initClientConnection() {
		
		try {
		player = new Socket(hostName, port);
		socketPlayer = new SocketPlayer(player,model);
		Thread hilo = new Thread(this);
		hilo.start();
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	public void run() {
		try {
			salida = new DataOutputStream(player.getOutputStream());
			entrada = new DataInputStream(player.getInputStream());
			socketPlayer.sendMessage(SocketPlayer.COMANDO_CON, playerName, null,salida);
			while (true){
				socketPlayer.readMessage(entrada);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initGame() {
		socketPlayer.sendMessage(SocketPlayer.COMANDO_LIS, "OK", null, salida);		
	}

	public void waitForConnection() {
	
	}

	
	public void setEnemyFire(String posX, String posY) {
		socketPlayer.sendMessage(SocketPlayer.COMANDO_ATK, posX, posY, salida);		
	}

	public void setOwnFire() {
		
	}

}
