package sockEx;


public class sockEx {
	private static UI_Ex chatting;

	public static void main(String[] args) {
		chatting = new UI_Ex();
		chatting.UI();
	}
	public static void recvChat(String s) {
		chatting.recvChat(s);
	}

}
