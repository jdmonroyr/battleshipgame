package co.edu.udistrital.poo.battleship.conect;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketPlayer1 implements SocketPlayer{

	final int PUERTO=5000;
	ServerSocket sc;
	Socket player1;
	DataOutputStream salida;
	BufferedReader entrada;
	
	public void initConnection() {
		try {
			sc = new ServerSocket(PUERTO);
			player1 = new Socket();
			
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	public void waitForConnection(){
		try {
			System.out.println("esperando conexión...");
			player1 = sc.accept();
			entrada = new BufferedReader(new InputStreamReader(player1.getInputStream()));
			salida = new DataOutputStream(player1.getOutputStream());
			System.out.println(entrada.readLine());
			System.out.println("Player2 se ha conectado...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

}
