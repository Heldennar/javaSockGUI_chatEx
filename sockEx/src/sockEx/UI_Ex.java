package sockEx;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.InetAddress;
import java.util.concurrent.Flow;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class UI_Ex extends JFrame  {
	private JLabel lChat = new JLabel("채팅창");
	private JTextArea chatTA = new JTextArea(10,20);
	private JTextField chatTF = new JTextField(20); //채팅입력필드
	private JTextField ipTF = new JTextField("127.0.0.1",15);
	private NumberField cport = new NumberField("8000",5);
	private NumberField sport = new NumberField("8000",5);
	private sendChatEx sendchat = new sendChatEx();
	private JCheckBox checkServer = new JCheckBox("서버 여부", false);
	private sockIfEx sock;
	JButton chatB, ipB, portB, serverB;
	
	
	
	public void UI() {
		setTitle("Chatting Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel pnlChat = new JPanel(new FlowLayout());
		JPanel pnlSet = new JPanel(new GridLayout(2,1));
		JPanel sPnl = new JPanel(new FlowLayout());
		JPanel cPnl = new JPanel(new FlowLayout());
		Container c = getContentPane();
		c.setLayout(new GridLayout(1,0));
		c.add(pnlChat, BorderLayout.WEST);
		c.add(pnlSet, BorderLayout.EAST);
		pnlSet.add(sPnl);
		pnlSet.add(cPnl);
		
		sPnl.add(checkServer);
		sPnl.add(new JLabel("Server Port : "));
		sPnl.add(sport);
		serverB = new JButton("서버 구축");
		sPnl.add(serverB);
		serverB.addActionListener(new serverOn());
		
		cPnl.add(new JLabel("IP : ")); cPnl.add(ipTF);
		cPnl.add(new JLabel("Port : ")); cPnl.add(cport);
		ipB = new JButton("연결");
		cPnl.add(ipB);
		ipB.addActionListener(new clientOn());
		
		serverB.setEnabled(false); sport.setEnabled(false);
		ipB.setEnabled(true); ipTF.setEnabled(true); cport.setEnabled(true);
		
		sock = new clientEx();
		
		checkServer.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					ipB.setEnabled(false); ipTF.setEnabled(false); cport.setEnabled(false);
					serverB.setEnabled(true); sport.setEnabled(true);
				}
				else if(e.getStateChange() == ItemEvent.DESELECTED) {
					serverB.setEnabled(false); sport.setEnabled(false);
					ipB.setEnabled(true); ipTF.setEnabled(true); cport.setEnabled(true);
				}
			}
		});
		
		pnlChat.add(lChat, BorderLayout.NORTH);
		pnlChat.add(new JScrollPane(chatTA), BorderLayout.WEST);
		pnlChat.add(new JLabel("채팅을 입력 후 <Enter>키"), BorderLayout.EAST);
		pnlChat.add(chatTF, BorderLayout.CENTER); 
		
		chatTF.addActionListener(sendchat); //채팅
		
		chatB = new JButton("전송");
		pnlChat.add(chatB);
		chatB.addActionListener(sendchat); //채팅
		
		
		setSize(480,350);
		setVisible(true);
		
	}
	
	public void recvChat(String s) {
		chatTA.append("("+ "상대" + ") : " + s + "\n");
	}
	
	class sendChatEx implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String tmp = chatTF.getText();
			chatTA.append("나 : " + tmp + "\n");
			chatTF.setText("");
			sock.sendChat(tmp);
		}
	}
	class serverOn implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int tmp = Integer.parseInt(sport.getText());
			sock = new serverEx(tmp);
		}
	}
	class clientOn implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int tmp = Integer.parseInt(sport.getText());
			sock = new clientEx(ipTF.getText(),tmp);
		}
	}



}
