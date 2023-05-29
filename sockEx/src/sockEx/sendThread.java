package sockEx;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class sendThread extends Thread {
	private final Socket sock;
	private Scanner sc = new Scanner(System.in);
	
	public sendThread(Socket sock) {
		this.sock = sock;
	}
	
	@Override
	public void run() {
		try {
			//DataOutputStream buf = new DataOutputStream(sock.getOutputStream());
			sendChat();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendChat(String sendString) throws IOException {
		DataOutputStream buf = new DataOutputStream(sock.getOutputStream());
		System.out.println(sock.getInetAddress() + " : " + sendString);
		buf.writeUTF(sendString);
		buf.flush();
		
	}
	
	public void sendChat() throws IOException {
		String sendString;
		sendString = sc.nextLine();
		sendChat(sendString);
	}
	
}
