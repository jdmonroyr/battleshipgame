package co.edu.udistrital.poo.battleship.conect;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import co.edu.udistrital.poo.battleship.presentation.Model;

public class SocketPlayer {

	final String ENCABEZADO = "BNAVAL";
	final static String COMANDO_CON ="CON", COMANDO_LIS ="LIS";
	private Model model;
	
	ServerSocket sc;
	Socket player;
	DataOutputStream salida;
	public SocketPlayer(Socket player, Model model){
		this.player = player;
		this.model = model;
	}

	public void readMessage(DataInputStream entrada) {
		try {
			//while(true){
			String encabezado = "", comando="", param1="", param2="";
			String[] mensajeEntrada, cuerpo;
			String readLine=entrada.readUTF();
			System.out.println("la entrada es: "+readLine);
			mensajeEntrada =readLine.split(":");
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
				switch (comando) {
				case "CON":
					model.setOpponentName(param1);
					break;
				case "LIS":
					if (param1.equals("OK")) {
						model.beginGame();	
					}
					break;
				default:
					break;
				}
			}
			
			else {
				System.out.println("ERROR DE CONEXIÓN");
			}
			//}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR LEYENDO");
			closeConnection();
		}
	}

	public void closeConnection() {
		try {
			//sendMessage("END", "Conexiòn finalizada", null);
			salida.close();
			player.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void sendMessage(String comando, String param1,String param2,DataOutputStream salida) {
		String msj = ENCABEZADO+":"+comando+","+param1;
		if(param2 != null && param2 != "")
			msj+=","+param2;
		try {
			salida.writeUTF(msj);
			salida.flush();
			System.out.println(msj);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
