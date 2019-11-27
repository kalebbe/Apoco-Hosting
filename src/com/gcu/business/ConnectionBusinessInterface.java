/**
 * This interface defines the methods required in the ConnectionBusinessService class.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-10-19
 */

package com.gcu.business;

import java.util.List;
import com.gcu.model.Connection;
import com.gcu.model.Message;
import com.gcu.model.User;

public interface ConnectionBusinessInterface {
	
	/**
	 * Returns a list of people based upon the search results.
	 * @param search
	 * @param id
	 * @return List<User>
	 */
	public List<User> searchPeople(String search, int id);
	
	/**
	 * Calls the dao to add a connection to the database.
	 * @param t
	 * @return boolean
	 */
	public boolean addConnection(Connection t);
	
	/**
	 * Calls the dao to delete a connection from the database.
	 * @param t
	 * @return boolean
	 */
	public boolean deleteConnection(Connection t);
	
	/**
	 * Checks each user to see if they have a business profile.
	 * @param users
	 * @param type
	 * @return List<User>
	 */
	public List<User> checkProfiles(List<User> users);
	
	/**
	 * Returns all of the connections of a user.
	 * @param id
	 * @return List<User>
	 */
	public List<User> getConnections(int id);
	
	/**
	 * Checks if the user is a connection already
	 * @param userId
	 * @param connId
	 * @return boolean
	 */
	public boolean checkConn(int userId, int connId);
	
	/**
	 * Sends a connection request
	 * @param t
	 * @return boolean
	 */
	public boolean sendRequest(Message t);
	
	/**
	 * Gets the profile of the person sending a connection request.
	 * @param msg
	 * @return
	 */
	public List<User> getRequestProfiles(List<Message> msg);
}
