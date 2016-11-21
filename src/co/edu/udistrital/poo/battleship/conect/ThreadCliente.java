package co.edu.udistrital.poo.battleship.conect;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import co.edu.udistrital.poo.battleship.presentation.Model;

public class ThreadCliente implements Runnable{

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

}
