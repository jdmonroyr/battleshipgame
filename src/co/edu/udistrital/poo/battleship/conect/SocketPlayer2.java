package co.edu.udistrital.poo.battleship.conect;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;

public class SocketPlayer2 implements SocketPlayer{

	final int PUERTO=5000;
	final String HOST= "127.0.0.1";
	Socket player2;
	DataOutputStream mensaje;
	BufferedReader entrada;
	
	public void initConnection() {
		String msg = "Yo me llamo José";
		
		try {
		player2 = new Socket(HOST, PUERTO);
		mensaje = new DataOutputStream(player2.getOutputStream());
		mensaje.write(msg.getBytes());
		
		} catch (Exception e) {
		}
	}

	public void waitForConnection() {
		
	}

}


