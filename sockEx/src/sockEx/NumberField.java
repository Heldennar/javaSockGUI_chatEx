package sockEx;
import java.awt.event.*;
import javax.swing.*;

public class NumberField extends JTextField implements KeyListener {
	private static final long serialVersionUID = 1;
 
	public NumberField() {
		addKeyListener(this);
	}
	public NumberField(int columns) {
		super(columns);
		new NumberField();
	}
	
	public NumberField(String text, int columns) {
		super(text, columns);
		new NumberField();
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
		// Get the current character you typed...
		char c = e.getKeyChar();
 
		if (!Character.isDigit(c)) {
			e.consume();
			return;
		}
 	}
}