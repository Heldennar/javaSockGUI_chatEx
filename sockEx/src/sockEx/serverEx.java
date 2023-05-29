package sockEx;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serverEx implements sockIfEx{
	//private boolean onConnect = false;
	protected int port = 8000;
	private recvThread recv; sendThread send;
	
	public serverEx(){
		new serverEx(8000);
	}
	public serverEx(int port){
		this.port = port;
		try {
			ServerSocket sSock = new ServerSocket(this.port);
			System.out.println("포트설정 완료");
			
			Socket sock = sSock.accept();
			System.out.println("ip_(" + sock.getInetAddress() + ")과 연결 성공");
			
			recv = new recvThread(sock);
			recv.start();
			send = new sendThread(sock);
			send.start();
		
			}
			catch(IOException e) {
				System.out.println("통신문제");
			}
		
		
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
