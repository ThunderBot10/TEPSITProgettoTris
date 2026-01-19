package tris;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class Server {

	public static void main(String[] args) {
		
		final int port=12345;
		
		try {
			ServerSocket server=new ServerSocket(port);//creo la socket del server
			
			while(true) {
				Socket s0= server.accept();//ottengo la socket precisa con il nuovo client
				Socket s1= server.accept();
				
				Thread thread=new Thread(new Gestore(s0, s1));
				thread.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
