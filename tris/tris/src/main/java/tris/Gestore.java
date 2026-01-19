package tris;//ogni gestore di partita deve gestire due client
//andrea carrafiello, fabien ebakassa
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class Gestore implements Runnable{
	private Socket s0;
	private BufferedReader in0;
	private PrintWriter out0;
	
	private Socket s1;
	private BufferedReader in1;
	private PrintWriter out1;
	
	private String tris[][];
	
	public Gestore(Socket s0, Socket s1) throws IOException {
		this.s0=s0;
		this.s1=s1;
		
		in0=new BufferedReader(new InputStreamReader(s0.getInputStream()));
		out0=new PrintWriter(s0.getOutputStream(), true);
		
		in1=new BufferedReader(new InputStreamReader(s1.getInputStream()));
		out1=new PrintWriter(s1.getOutputStream(), true);
		
		tris=new String[3][3];
		
		
		for(int i=0;i<3;i++) {//non è stata effettuata nessuna mossa
			for(int j=0;j<3;j++) {
				tris[i][j]="-";
			}
		}
	}
	
	public boolean vittoria(String m) {
	    boolean vit;

	    // righe
	    for (int i = 0; i < 3; i++) {
	        vit = true;
	        for (int j = 0; j < 3; j++) {
	            if (!tris[i][j].equals(m)) {
	                vit = false;
	                break;
	            }
	        }
	        if (vit) return true;
	    }

	    // colonne
	    for (int i = 0; i < 3; i++) {
	        vit = true;
	        for (int j = 0; j < 3; j++) {
	            if (!tris[j][i].equals(m)) {
	                vit = false;
	                break;
	            }
	        }
	        if (vit) return true;
	    }

	    // diagonale principale
	    vit = true;
	    for (int i = 0; i < 3; i++) {
	        if (!tris[i][i].equals(m)) {
	            vit = false;
	            break;
	        }
	    }
	    if (vit) return true;

	    // diagonale secondaria
	    vit = true;
	    for (int i = 0; i < 3; i++) {
	        if (!tris[i][2 - i].equals(m)) {
	            vit = false;
	            break;
	        }
	    }
	    if (vit) return true;

	    return false;
	}
	
	public void sta(PrintWriter out) {//invia al client la struttura del tris
		String ris;
		for(int i=0;i<3;i++) {
			ris=tris[i][0]+" "+tris[i][1]+" "+tris[i][2];
			out.println(ris);
		}
	}
	
	public void fine(PrintWriter out) {
		String n="2";
		out.println(n);
		sta(out);
	}
	
	
	
	public boolean mossa(PrintWriter out0, PrintWriter out1, BufferedReader in0, String m) throws IOException {
		String n="0";
		String a, b;
		

		out0.println(n);
		sta(out0);
		
		a=in0.readLine();
		b=in0.readLine();
		
		while(Integer.parseInt(a) < 0 || Integer.parseInt(a) > 2 ||
			       Integer.parseInt(b) < 0 || Integer.parseInt(b) > 2) {
			n="1";
			out0.println(n);
			a=in0.readLine();
			b=in0.readLine();
		}
		
		while(!tris[Integer.parseInt(a)][Integer.parseInt(b)].equals("-")) {
			n="1";
			out0.println(n);
			a=in0.readLine();
			b=in0.readLine();
			
			while(Integer.parseInt(a) < 0 || Integer.parseInt(a) > 2 ||
				       Integer.parseInt(b) < 0 || Integer.parseInt(b) > 2) {
				n="1";
				out0.println(n);
				a=in0.readLine();
				b=in0.readLine();
			}
		}
		
		tris[Integer.parseInt(a)][Integer.parseInt(b)]=m;
		
		if(vittoria(m)==true) {
			fine(out0);
			fine(out1);
			out0.println("Vittoria");
			out1.println("Sconfitta");
			return true;
		}
		
		return false;
	}
	
	public void gioco() {
		try {
			
			for(int i=0;i<9;i+=2) {
				
				if(mossa(out0, out1, in0, "x")) {
					return;
				}
				
				if(i==8) {
					break;
				}
				
				if(mossa(out1, out0, in1, "o")) {
					return;
				}
				
			}
			
			fine(out1);
			fine(out0);
			out1.println("Patta");
			out0.println("Patta");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void run() {	
		try {
			String name0 = in0.readLine();
			String name1=in1.readLine();
			
			String ris="Tu sei x e stai giocando contro: "+name1;
			
			out0.println(ris);
			
			ris="Tu sei o e stai giocando contro: "+name0;
			
			out1.println(ris);
			
			gioco();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
	}
}
