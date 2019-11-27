/**
 * This model is used for the purpose of creating and deleting friends from the database.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-12-10
 */

package com.gcu.model;

public class Friend {
	private int userId;
	private int friendId;
	
	/**
	 * This is the getter for the friend's id.
	 * @return int This is the id of the friend.
	 */
	public int getFriendId() {
		return friendId;
	}
	
	/**
	 * This is the setter for the friend's id.
	 * @param friendId This is the new friend's id.
	 * @return Nothing.
	 */
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	
	/**
	 * This is the getter for the user's id.
	 * @return int This is the id of the user.
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * This is the setter for the user's id.
	 * @param userId This is the new user's id.
	 * @return Nothing.
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * This is the constructor for the Friend model. There will not be a default constructor.
	 * @param userId Id of the user.
	 * @param friendId Id of the friend.
	 */
	public Friend(int userId, int friendId) {
		this.userId = userId;
		this.friendId = friendId;
	}
}
