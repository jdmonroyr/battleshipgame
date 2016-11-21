package co.edu.udistrital.poo.battleship.conect;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import co.edu.udistrital.poo.battleship.presentation.Model;
import co.edu.udistrital.poo.battleship.presentation.Model.AttackResult;

public class ThreadServer implements Runnable, ThreadBattleShip{

	final int PUERTO=5000;
	final String ENCABEZADO = "BNAVAL";
	final String HOST= "127.0.0.1";
	private String playerName="";
	private Model model;
	private SocketPlayer socketPlayer;	
	ServerSocket sc;
	private Socket player;
	DataOutputStream salida;
	DataInputStream entrada;

	
	public ThreadServer(Model model, String playerName) {
		this.model = model;
		this.playerName = playerName;
		player= new Socket();
		socketPlayer = new SocketPlayer(player,this.model);
	}

	public void waitForConnection(){
		try {
			sc = new ServerSocket(PUERTO);
			System.out.println("esperando conexión...");
			player = sc.accept();
			System.out.println("Player2 se ha conectado...");
			Thread hilo = new Thread(this);
			hilo.start();

		} catch (IOException e) {
			socketPlayer.closeConnection();
			e.printStackTrace();
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

	@Override
	public void initGame() {
		socketPlayer.sendMessage(SocketPlayer.COMANDO_LIS, "OK", null, salida);
	}

	@Override
	public void initClientConnection() {
		// TODO Auto-generated method stub
		
	}

	public void setEnemyFire(String posX, String posY) {
		socketPlayer.sendMessage(SocketPlayer.COMANDO_ATK, posX, posY, salida);

	}

	public void setOwnFire(int posX, int posY ) {
		AttackResult ar = model.getFired(posX, posY);
		String res="";
		if(ar == AttackResult.MISS){
			res="0";
		}else if(ar ==AttackResult.HIT){
			res="1";
		}else if(ar ==AttackResult.GAME_OVER){
			res="2";
		}
		socketPlayer.sendMessage(SocketPlayer.COMANDO_ATK, "OK", res, salida);
	}

}
