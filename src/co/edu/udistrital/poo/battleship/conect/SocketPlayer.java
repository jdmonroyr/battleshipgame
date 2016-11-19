package co.edu.udistrital.poo.battleship.conect;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketPlayer {

	final int PUERTO=5000;
	final String ENCABEZADO = "BNAVAL";
	final String HOST= "127.0.0.1";
	
	ServerSocket sc;
	Socket player;
	DataOutputStream salida;
	BufferedReader entrada;
	
	public void initClientConnection() {
		//String msg = "Yo me llamo José";
		
		try {
		player = new Socket(HOST, PUERTO);		
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	public void initServerConnection() {
		try {
			sc = new ServerSocket(PUERTO);
			player = new Socket();
			
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	public void waitForConnection(){
		try {
			System.out.println("esperando conexión...");
			player = sc.accept();
			System.out.println("Player2 se ha conectado...");
			sendMessage("CON", "JOSE", null);
			closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	public void readMessage() {
		try {
			entrada = new BufferedReader(new InputStreamReader(player.getInputStream()));
			String encabezado = "", comando="", param1="", param2="";
			String[] mensajeEntrada, cuerpo;
			System.out.println("la entrada es: "+entrada.readLine());
			mensajeEntrada = entrada.readLine().split(":");
			if(mensajeEntrada!=null && mensajeEntrada.length==2){
				encabezado = mensajeEntrada[0];
				cuerpo = mensajeEntrada[1].split(",");
				if(cuerpo != null && cuerpo.length >=2){
					comando = cuerpo[0];
					param1 = cuerpo[1];
					if(cuerpo.length== 3){
						param2=cuerpo[2];
					}
				}
				System.out.println("encabezado: "+encabezado+" - comando: "+comando+"- param1: "+param1+" - param2: "+param2);
			}
			else {
				System.out.println("ERROR DE CONEXIÓN");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public void closeConnection() {
		try {
			sendMessage("END", "Conexiòn finalizada", null);
			salida.close();
			player.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void sendMessage(String comando, String param1,String param2) {
		String msj = ENCABEZADO+":"+comando+","+param1;
		if(param2 != null && param2 != "")
			msj+=","+param2;
		try {
			salida = new DataOutputStream(player.getOutputStream());
			salida.write(msj.getBytes());
			readMessage();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
