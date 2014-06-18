package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Gamefield;
import model.Hollow;

public class Game extends JFrame {

	private final Color PLAYER_1_COLOR = Color.RED;
	private final Color PLAYER_2_COLOR = Color.MAGENTA;
	private final Color KALLAH_COLOR = Color.ORANGE;
	
	private Gamefield field;
	
	private JPanel mainPanel;
	private JButton[] buttons;
	
	private JLabel lblPlayer1;
	private JLabel lblPlayer2;
	
	/*
	 * Für Testzwecke
	 */
	private JButton testButton;
	
	public Game(ActionListener al) {
		this.field = new Gamefield();
		this.buttons = new JButton[this.field.getHollows().length];
		
		this.setTitle("Bantumi");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.mainPanel = new JPanel(null);
		this.mainPanel.setBounds(0, 0, 500, 300);
		
		this.lblPlayer1 = new JLabel("Player 1: Your turn");
		this.lblPlayer1.setBounds(175, 40, 200, 25);
		this.lblPlayer1.setForeground(this.PLAYER_1_COLOR);
		
		this.lblPlayer2 = new JLabel("Player 2: Waiting");
		this.lblPlayer2.setBounds(175, 175, 200, 25);
		this.lblPlayer2.setForeground(this.PLAYER_2_COLOR);
		
		this.testButton = new JButton("Change Player Turn");
		this.testButton.setBounds(0, 0, 150, 25);
		this.testButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchPlayerTurn();				
			}
		});
		
		this.drawHollows(al);
		
		this.mainPanel.add(this.lblPlayer1);
		this.mainPanel.add(this.lblPlayer2);
		this.mainPanel.add(this.testButton);
		
		this.add(this.mainPanel);
		this.setVisible(true);
	}
	
	public void updateHollows(int hollowNumber) {
		this.field.disperseSeeds(hollowNumber);
		
		for (int i = 0; i < this.buttons.length; i++) {
			this.buttons[i].setText(Integer.toString(this.field.getHollows()[i].getNumberOfSeeds()));
		}
	}
	
	private void drawHollows(ActionListener al) {
		final int buttonHeight = 30;		// Height of the button
		final int buttonWidth  = 50;		// Width of the button
		
		int gapWidth  = 0;					// Gap between each button (Width)
		int gapHeight = 0;					// Gap between each button (Height)
		
		String buttonName = "";				// The name that the button will get
		
		for (int i = 0; i < this.field.getHollows().length; i++) {
			
			/*
			 * We need a new row after the first six Buttons were set.
			 * Therefor the gap needs to get other values
			 */
			if (i == 6) {
				gapWidth  = 0;
				gapHeight = buttonHeight + buttonHeight / 2;
			}
			
			/*
			 * The top row belongs to Player 1, the row below to Player 2.
			 * If the gapHeight changed, it means, that the buttons for Player 2
			 * will be set. Therefore the buttons need another name
			 * 
			 */
			buttonName = i + (gapHeight == 0 ? "p1" : "p2");
			this.buttons[i] = new JButton();
			
			/* Checks if it's kallah for either player 1
			 * or player 2, to put the buttons at the right
			 * position.
			 * 
			 * It'll also set the names and colors.
			 */
			if (i == 12) {			// Kallah Player 1	
				this.buttons[i].setText(Integer.toString(this.field.getHollows()[i].getNumberOfSeeds()));
				this.buttons[i].setBounds(50, 105, buttonWidth, buttonHeight);
				this.buttons[i].setBackground(this.KALLAH_COLOR);
			} else if (i == 13) { 	// Kallah Player 2
				this.buttons[i].setText(Integer.toString(this.field.getHollows()[i].getNumberOfSeeds()));
				this.buttons[i].setBounds(400, 105, buttonWidth, buttonHeight);
				this.buttons[i].setBackground(this.KALLAH_COLOR);
			} else {				// Normal Hollows for P1 and P2
				this.buttons[i].setText(Integer.toString(this.field.getHollows()[i].getNumberOfSeeds()));
				this.buttons[i].setBounds(100 + (buttonWidth * gapWidth), 80 + gapHeight, buttonWidth, buttonHeight);
				this.buttons[i].setBackground(this.PLAYER_1_COLOR);
				this.buttons[i].setName(buttonName);
				this.buttons[i].addActionListener(al);
			}
			
			gapWidth++;
			this.mainPanel.add(this.buttons[i]);
		}
	}
	
	private void switchPlayerTurn() {
		this.field.switchTurn();
		
		if (this.field.isPlayer1Turn()) {
			this.lblPlayer1.setText("Player 1: Your turn");
			this.lblPlayer2.setText("Player 2: Waiting");
		} else {
			this.lblPlayer1.setText("Player 1: Waiting");
			this.lblPlayer2.setText("Player 2: Your Turn");
		}
	}
	
	public Gamefield getGameField() {
		return this.field;
	}
}
