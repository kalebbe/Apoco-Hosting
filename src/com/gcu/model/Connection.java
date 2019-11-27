/**
 * This model is used for the purpose of creating and deleting connections from the database.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-10-19
 */

package com.gcu.model;

public class Connection {
	private int userId;
	private int connId;
	
	/**
	 * Getter for user id.
	 * @return int
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Setter for user id.
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Getter for the connection id.
	 * @return int
	 */
	public int getConnId() {
		return connId;
	}
	
	/**
	 * Setter for the conneciton id.
	 * @param connId
	 */
	public void setConnId(int connId) {
		this.connId = connId;
	}
	
	/**
	 * This is the constructor for the Connection model. No default constructor needed.
	 * @param userId
	 * @param connId
	 */
	public Connection(int userId, int connId) {
		this.userId = userId;
		this.connId = connId;
	}
}
