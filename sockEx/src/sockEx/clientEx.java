package sockEx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class clientEx implements sockIfEx {
	//private boolean onConnect = false;
	protected int port = 8000;
	protected String ip = "127.0.0.1";
	private recvThread recv; sendThread send;
	//private UI_Ex chatting;
	
	public clientEx(String ip, int port) {
		this.ip = ip; this.port = port;
		try {
			Socket sock = new Socket(this.ip, this.port);
			System.out.println("서버와의 연결 성공");
			
			Scanner sc = new Scanner(System.in);
			
			recv = new recvThread(sock);
			recv.start();
			send =new sendThread(sock);
			send.start();
			
		}
		catch(IOException e) {
			System.out.println("통신문제");
		}
	}
	public clientEx() {
		new clientEx(ip, port);
	}
	@Override
	public void sendChat(String s) {
		try {
			send.sendChat(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
