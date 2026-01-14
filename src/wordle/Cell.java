package wordle;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

public class Cell extends JTextField{
	Cell(int num) {
		this.setEditable(false);
		this.setSize(40,40);
		this.setHorizontalAlignment(JTextField.CENTER);

	}
	public void newText(String text, Color color) {
		this.setBackground(color);
		this.setFont(new Font("arial", 1, 30));
		this.setText(text);
	}
}
