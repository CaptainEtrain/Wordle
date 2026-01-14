package wordle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.text.*;

public class TextBox extends JTextField implements ActionListener {
	Color orgColor;
	public boolean stop = false;
	TextBox() {
		
		orgColor = this.getBackground();
		this.setPreferredSize(new Dimension(200, 40));

		this.setFont(new Font("arial", 1, 30));
		this.setHorizontalAlignment(JTextField.CENTER);
		this.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!stop) {
			if (checkParamiters(this.getText())) {
				this.setBackground(orgColor);
				Game.sendWord(this.getText());
				this.setText("");
			} else {
				this.setBackground(Color.RED);
			}
			if (Game.nextRow >= 6) {
				String word = "";
				for (int i = 0; i < Game.trueWord.length; i++) {
					word += Game.trueWord[i];
				}
				this.setText(word);
				this.setEditable(false);
				stop = true;
			}
		}
	}

	private boolean checkParamiters(String text) {
		if (text.equals("")) {//fix
			this.setBackground(orgColor);
		}
		if (text.length()!=5) {
			return false;
		}
		if (!text.chars().allMatch(Character::isLetter)) {
			return false;
		}
		for (int i = 0; i < Game.guessableWords.length; i++) {
			if (Game.guessableWords[i].equalsIgnoreCase(text)) {
				break;
			}
			if (i >= Game.guessableWords.length-1) {
				return false;
			}
			
		}
		return true;
	}
	
	@Override
	protected Document createDefaultModel() {
		return new LimitDocument();
	}

	private class LimitDocument extends PlainDocument {

		@Override
		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null)
				return;

			if ((getLength() + str.length()) <= 5) {
				super.insertString(offset, str, attr);
			}
		}

	}
}
