/**
 * This is just a down and dirty model for the questionaire being added to the dating portion.
 * This is being ripped out extremely quick, so comments will be thin and implementation will not
 * be thicc. More questions would be added if this project were to be continued.
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-12-04
 */

package com.gcu.model;

public class Questionaire {
	//The answers for each question are being stored here.
	private char que1;
	private char que2;
	private char que3;
	private char que4;
	private char que5;
	
	private int userId; //Id of the user who answered these questions.
	
	/**
	 * Non-default constructor for a questionaire object
	 * @param que1
	 * @param que2
	 * @param que3
	 * @param que4
	 * @param que5
	 * @param userId
	 */
	public Questionaire(char que1, char que2, char que3, char que4, char que5, int userId) {
		this.que1 = que1;
		this.que2 = que2;
		this.que3 = que3;
		this.que4 = que4;
		this.que5 = que5;
		this.userId = userId;
	}
	
	/**
	 * Standard constructor for a questionaire object
	 */
	public Questionaire() {
		this.que1 = 'a';
		this.que2 = 'a';
		this.que3 = 'a';
		this.que4 = 'a';
		this.que5 = 'a';
		this.userId = -1;
	}

	/**
	 * Getter for answer 1
	 * @return char
	 */
	public char getQue1() {
		return que1;
	}

	/**
	 * Setter for answer 1
	 * @param que1
	 */
	public void setQue1(char que1) {
		this.que1 = que1;
	}

	/**
	 * Getter for answer 2
	 * @return char
	 */
	public char getQue2() {
		return que2;
	}

	/**
	 * Setter for answer 2
	 * @param que2
	 */
	public void setQue2(char que2) {
		this.que2 = que2;
	}

	/**
	 * Getter for answer 3
	 * @return char
	 */
	public char getQue3() {
		return que3;
	}

	/**
	 * Setter for answer 3
	 * @param que3
	 */
	public void setQue3(char que3) {
		this.que3 = que3;
	}

	/**
	 * Getter for answer 4
	 * @return char
	 */
	public char getQue4() {
		return que4;
	}

	/**
	 * Setter for answer 4
	 * @param que4
	 */
	public void setQue4(char que4) {
		this.que4 = que4;
	}

	/**
	 * Getter for answer 5
	 * @return char
	 */
	public char getQue5() {
		return que5;
	}

	/**
	 * Setter for answer 5
	 * @param que5
	 */
	public void setQue5(char que5) {
		this.que5 = que5;
	}

	/**
	 * Getter for user id
	 * @return char
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Setter for user id
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
