package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.Game;

public class GameControl implements ActionListener {
	
	private Game game;
	
	public GameControl() {
		this.game = new Game(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		int number = this.getNumberFromButton(b);

		System.out.println(this.game.getGameField().validMove(number));
		
		if (this.game.getGameField().validMove(number)) {
			this.game.updateHollows(number);
		}
	}
	
	private int getNumberFromButton(JButton button) {
		int number;
		char[] c = new char[2];
		button.getName().getChars(0, 2, c, 0);
		String s = new String(c);
		
		try {
			number = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			number = Character.getNumericValue(s.charAt(0));
		}
		
		return number;
	}
}
