package tris;//ebakassa fabien, carrafiello andrea

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		String serverAddress = "127.0.0.1"; // IP del server (localhost)
        int port = 12345; // Porta del server
        Scanner sca=new Scanner(System.in);

        try {
            Socket socket = new Socket(serverAddress, port);
            OutputStream out = socket.getOutputStream();     
            PrintWriter writer = new PrintWriter(out, true);
            InputStream in = socket.getInputStream();    
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            
            String mes;
            
            //inizio comunicazione
        	System.out.println("Inserisci il tuo nome");
        	writer.println(sca.nextLine());
        	
        	System.out.println(reader.readLine());
        	
            
            while(true) {

                mes=reader.readLine();
                int n=Integer.parseInt(mes);
                switch(n) {
                	case 0:
                		for(int i=0;i<3;i++) {
                			mes=reader.readLine();
                    		System.out.println(mes);
                		}
                		System.out.println("Inserisci la prima coordinata");
                		mes=sca.nextLine();
                		writer.println(mes);
                		System.out.println("Inserisci la seconda coordinata");
                		mes=sca.nextLine();
                		writer.println(mes);
                		break;
                	case 1: 
                		System.out.println("Inserisci la prima coordinata corretta");
                		mes=sca.nextLine();
                		writer.println(mes);
                		System.out.println("Inserisci la seconda coordinata corretta");
                		mes=sca.nextLine();
                		writer.println(mes);
                		break;
                	case 2: 
                		for(int i=0;i<3;i++) {
                			mes=reader.readLine();
                    		System.out.println(mes);
                		}
                		mes=reader.readLine();
                		System.out.println("\n"+mes);
                		return;
                }
            }
            
        } catch (Exception e) {
            // Gestisce eventuali errori
            System.err.println("Errore: " + e.getMessage());
            e.printStackTrace();
        }
        
        sca.close();
	}

}

