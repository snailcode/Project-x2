package model;

public class Gamefield {

	/**
	 * Array with all 14 Hollows for the game
	 */
	private Hollow[] hollows;
	
	/**
	 * Indicator if it's Player 1 turn
	 * True =>  Player 1 turn
	 * False => Player 2 turn
	 */
	private boolean player1Turn;
	
	public Gamefield() {
		this.hollows = new Hollow[14];
		this.initGame();
	}
	
	public void checkMove(int hollowNumber) {
		int seeds = this.hollows[hollowNumber].getNumberOfSeeds();
		int index = hollowNumber - seeds;
				
		System.out.println("Index : " + index);
		/*
		 * 
		 */
		if (index >= 0 && index < 6) { // Within row of player 1
			System.out.println("Seeds: " + this.hollows[index].getNumberOfSeeds());
			if (this.hollows[index].getNumberOfSeeds() == 0) {				
				// Add the seeds from the opposite site to your kallah
				this.hollows[12].addSeed(this.hollows[index+6].getNumberOfSeeds());
				
				// Removes the seeds from the opposite site
				this.hollows[index+6].removeSeeds();
			} 
		} 
	}
	
	/**
	 * Checks, based on the given number, if the move is valid, which means,
	 * if the player chose the right hollow.
	 * 
	 * @param hollowNumber Number of the hollow the player has chosen
	 * @return	true if valid ; false if not
	 */
	public boolean validMove(int hollowNumber) {
		return (this.player1Turn && hollowNumber <= 5) ? true : 
			(!this.player1Turn && hollowNumber > 5 && hollowNumber <= 11) ? true : false;
	}
	
	/**
	 * Disperses the seeds to the other hollows
	 * 
	 * @param hollowNumber starting point
	 */
	public void disperseSeeds(int hollowNumber) {
		this.checkMove(hollowNumber);
		
		// Number of seeds in the chosen Hollow
		int seeds = this.hollows[hollowNumber].getNumberOfSeeds();
		
		// Hollow where the seed should be placed
		int toHollow = hollowNumber <= 5 ? hollowNumber - 1 : hollowNumber + 1;
		
		/*
		 * Runs as long as there are seeds left
		 * and puts the seeds to the right hollow
		 */
		while (seeds > 0) {
			if (toHollow == -1) {			// End of Player 1 row (to the left side)
				toHollow = 12;				// Puts it in the kallah of Player 1
			} else if (toHollow == 12) {	// End of Player 2 row (to the right side)
				toHollow = 13;				// Puts it in the kallah of Player 2
			}
			
			// Adds a seed to the hollow
			this.hollows[toHollow].addSeed(1);
			
			
			if (toHollow == 12) {	// If it's the kallah from P1, the "index" points to field 1 of P2 (6)
				toHollow = 6;
			} else if (toHollow > -1 && toHollow < 6) {	// If it's within the row of P1, decrease the "index"
				toHollow--;
			} else if (toHollow == 13) {	// If it's the kallah from P2, the "index" points to field 6 of P1 (5)
				toHollow = 5;
			} else if (toHollow > 5 && toHollow < 12) { // If it's within the row of P2 increase the "index"
				toHollow++;
			}
			
			// Decrease the seed by one
			seeds--;
		}
		
		// Removes all seeds from the chosen hollow
		this.hollows[hollowNumber].removeSeeds();
	}
	
	/**
	 * Switches the turns of the player
	 * player1Turn = true  (Player 1 turn)
	 * player1Turn = false (Player 2 turn)
	 */
	public void switchTurn() {
		this.player1Turn = player1Turn ? false : true;
	}
	
	/**
	 * Intialize the array and other variables
	 */
	private void initGame() {
		this.player1Turn = true;
		
		for(int i = 0; i < this.hollows.length; i++) {
			if (i >= this.hollows.length - 2) {			// Is kallah (the last two)
				this.hollows[i] = new Hollow(0, true);
			} else {									// Is normal hollow
				this.hollows[i] = new Hollow(3, false);
			}
		}
	}
	
	/**
	 * Returns the array with the initalized hollows
	 */
	public Hollow[] getHollows() {
		return this.hollows;
	}
	
	/**
	 * Return true if it's Player 1 turn and false if not
	 */
	public boolean isPlayer1Turn() {
		return this.player1Turn;
	}
	
}
