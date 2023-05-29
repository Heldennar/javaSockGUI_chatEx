package sockEx;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class recvThread extends Thread {

	private final Socket sock;
	
	public recvThread(Socket sock) {
		this.sock = sock;
	}
	
	@Override
	public void run() {
		try {
			DataInputStream buf = new DataInputStream(sock.getInputStream());
			while(true) {
				String recvString = buf.readUTF();
				recvChat(recvString);
			}
		}
		catch(SocketException se) {
			System.out.println("상대방과의 연결이 종료되었습니다.");
		}
		catch(IOException ie) {
			ie.printStackTrace();
		}
	}
	
	public void recvChat(String recvString) throws IOException {
		InetAddress ip = sock.getInetAddress();
		System.out.println(ip + " : " + recvString);
		sockEx.recvChat(recvString);
	}
}
