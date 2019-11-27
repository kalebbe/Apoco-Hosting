/**
 * This class contains the logic for the minesweeper game in Apoco social.
 * this class is likely to remain the way it is currently.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.utilities;

import com.gcu.model.Button;

public class MinesweeperLogic {
	
	//Variables changed to private per rubric feedback
	private static Button[][] btnHolder;
	private static boolean lose = false;
	private static boolean win = false;
	
	/**
	 * Getter for the btnHolder multidimensional array.
	 * @return Button[][] This is the full array of buttons for the board.
	 */
	public static Button[][] getBtnHolder() {
		return btnHolder;
	}

	/**
	 * Setter for the btnHolder multidimensional array.
	 * @param btnHolder This is the new btnHolder array.
	 * @return Nothing.
	 */
	public static void setBtnHolder(Button[][] btnHolder) {
		MinesweeperLogic.btnHolder = btnHolder;
	}

	/**
	 * Getter for the lose boolean.
	 * @return boolean This is whether or not the user has lost.
	 */
	public static boolean isLose() {
		return lose;
	}

	/**
	 * Setter for the lose boolean.
	 * @param lose This is the new status for the lose boolean.
	 * @return Nothing.
	 */
	public static void setLose(boolean lose) {
		MinesweeperLogic.lose = lose;
	}

	/**
	 * Getter for the win boolean.
	 * @return boolean This is whether or not the user has won.
	 */
	public static boolean isWin() {
		return win;
	}

	/**
	 * Setter for the win boolean.
	 * @param win This is the new status for the win boolean.
	 * @return Nothing.
	 */
	public static void setWin(boolean win) {
		MinesweeperLogic.win = win;
	}

	/**
	 * Loops twice to create a multidimensional array of buttons that cover the entire board
	 * based off the given size and then sets roughly 15 percent of the buttons to have bombs. 
	 * There is then there's another loop to determine how many live neighbors each button has.
	 * @param size This is the size of the board being generated.
	 * @return Nothing.
	 */
	public void generateBoard(int size) {
		btnHolder = new Button[size][size];
		
		//first loops to check if each button will be a bomb and then set it
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				btnHolder[i][j] = new Button(i, j);
				if(isActive()) {
					btnHolder[i][j].setLive(true);
				}
			}
		}
		
		//Second loops to set live neighbors for each button
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				countNeighbors(i, j, size);
			}
		}
	}
	
	/**
	 * Short method used to determine if a button is going to be a bomb or not.
	 * This can be changed to allow for easier games, but I will not be changing it.
	 * @return boolean This is a return for whether or not the processed button will be a bomb.
	 */
	public boolean isActive() {
		int random = 1 + (int)(Math.random() * 20);
		return (random <= 3) ? true : false; //15% chance at being a bomb
	}
	
	/**
	 * This method counts how many live neighbors each button has, so it can be displayed
	 * to the user when they click on a button
	 * @param x This is the x coordinate of the button.
	 * @param y This is the y coordinate of the button.
	 * @param size This is the size of the board.
	 * @return Nothing.
	 */
	public void countNeighbors(int x, int y, int size) {
		int count = 0;
		if(!btnHolder[x][y].isLive()) {
			for(int i = -1; i <= 1; i++) { //This looks confusing, but it's just looping around the cell
				for(int j = -1; j <= 1; j++) { //with a nested loop to check each neighbor
					if(x + i >= 0 && x + i < size && y + j >= 0 && y + j < size) {
						if(btnHolder[x + i][y + j].isLive()) count++; 
					}
				}
			}
			btnHolder[x][y].setLiveNeighbors(count);;
		}
		else btnHolder[x][y].setLiveNeighbors(9); //9 == boom
	}
	
	/**
	 * This is a recursive method that is used to populate all those
	 * naked cells you love on the board when you make that first click.
	 * Although my version isn't very forgiving and you can die on the first click..
	 * gotta fix that soon.
	 * @param x This is the x coordinate of the button.
	 * @param y This is the y coordinate of the button.
	 * @param size This is the size of the board.
	 * @return Nothing.
	 */
	public static void processCell(int x, int y, int size) {
		if(inBounds(x, y, size)) { //This makes sure we don't get an outofbounds exception
			if(btnHolder[x][y].isLive()) { //Kaboom
				setLose(size);
			}
			else if(btnHolder[x][y].getLiveNeighbors() != 0) {
				btnHolder[x][y].setVisited(true); //Button has now been visited
			}
			else {
				for(int i = -1; i < 1; i++) { //There's that loop around the neighbors again
					for(int j = -1; j < 1; j++) {
						if(inBounds(x + i, y + j, size) && !btnHolder[x][y].isVisited()) {
							btnHolder[x][y].setVisited(true);
							processCell(x-1, y, size); //This is recursively calling
							processCell(x+1, y, size); //this same method 8 times to check
							processCell(x, y-1, size); //every cell around the clicked cell.
							processCell(x, y+1, size); //recursion is a bit confusing though.
							processCell(x+1, y+1, size);
							processCell(x-1, y-1, size);
							processCell(x+1, y-1, size);
							processCell(x-1, y+1, size);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Short method that checks to see if the button at those coordinates
	 * is in bounds of the array.
	 * @param x This is the x coordinate of the button.
	 * @param y This is the y coordinate of the button.
	 * @param size This is the size of the board.
	 * @return boolean This returns whether the button is in bounds of the array or not.
	 */
	public static boolean inBounds(int x, int y, int size) {
		return x >= 0 && x < size && y >= 0 && y < size;
	}
	
	/**
	 * All this does is set every cell to visited if the user clicks on a bomb.
	 * @param size This is the size of the board.
	 * @return Nothing.
	 */
	public static void setLose(int size) {
		lose = true;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				btnHolder[i][j].setVisited(true);
			}
		}
	}
	
	/**
	 * Compares the current visited cells with the amount of 
	 * cells remaining to determine if the user has won or not.
	 * @param size This is the size of the board.
	 * @return Nothing.
	 */
	public static void checkWin(int size) {
		int count = 0;
		int bombCount = 0;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(btnHolder[i][j].isVisited()) {
					count++;
				}
				if(btnHolder[i][j].isLive()) {
					bombCount++;
				}
			}
		}
		if(count == ((size * size) - bombCount)) {
			win = true;
		}
	}
}
