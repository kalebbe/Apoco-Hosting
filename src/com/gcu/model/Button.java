/**
 * This is the model for the Minesweeper button. This is actually a very simple
 * class and everything is pretty self-explantory. Also wasn't changed much from c#'s
 * implementation of the button model.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.model;

public class Button {
	private boolean live; //Button is or is not a live bomb
	private int liveNeighbors; //How many bombs the button has surrounding it
	private boolean visited; //Has the button been visited?
	private int x; //X coordinate of the button
	private int y; //Y coordinate of the button
	private boolean flagged; //Flagged by right click. Not yet implemented
	
	/**
	 * Getter for live variable.
	 * @return boolean This is whether or not the variable is live.
	 */
	public boolean isLive() {
		return live;
	}

	/**
	 * Setter for live variable.
	 * @param isLive This is the new status for the live variable.
	 * @return Nothing.
	 */
	public void setLive(boolean isLive) {
		this.live = isLive;
	}

	/**
	 * Getter for liveNeighbors variable.
	 * @return int This is the amount of live neighbors.
	 */
	public int getLiveNeighbors() {
		return liveNeighbors;
	}

	/**
	 * Setter for liveNeighbors variable.
	 * @param liveNeighbors This is the new amount of live neighbors.
	 * @return Nothing.
	 */
	public void setLiveNeighbors(int liveNeighbors) {
		this.liveNeighbors = liveNeighbors;
	}

	/**
	 * Getter for visited variable.
	 * @return boolean This is whether or not the button is visited.
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * Setter for the visited variable.
	 * @param isVisited This is the new status of visited.
	 * @return Nothing.
	 */
	public void setVisited(boolean isVisited) {
		this.visited = isVisited;
	}

	/**
	 * Getter for the x variable.
	 * @return int This is the value of X.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setter for the x variable.
	 * @param x This is the new x variable.
	 * @return Nothing.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter for the y variable.
	 * @return int this is the value of y.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter for the y variable.
	 * @param y This is the new y variable.
	 * @return Nothing.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Getter for the flagged boolean.
	 * @return boolean Whether or not button is flagged.
	 */
	public boolean isFlagged() {
		return flagged;
	}

	/**
	 * Setter for the flagged boolean.
	 * @param flagged The new status of flagged variable.
	 * @return Nothing.
	 */
	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	/**
	 * Non default constructor that creates a global button.
	 * There will not be a default constructor for this model.
	 * @param x This is the x coordinate of the button being set.
	 * @param y This is the y coordinate of the button being set.
	 */
	public Button(Integer x, Integer y) {
		this.live = false;
		this.visited = false;
		this.liveNeighbors = 0;
		this.x = x;
		this.y = y;
		this.flagged = false;
	}
}
