/**
 * This interface gives the methods required in the MessageBusinessService class and any
 * other classes that may eventually implement this interface.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-12-16
 */

package com.gcu.business;

import java.util.List;

import com.gcu.model.Message;

public interface MessageBusinessInterface {

	/**
	 * Creates a new message.
	 * @param t The actual message object.
	 * @return boolean Whether or not it was created.
	 */
	public boolean create(Message t);

	/**
	 * Deletes any message by id.
	 * @param id The message's id.
	 * @return boolean Whether or not it was deleted.
	 */
	public boolean delete(int id);

	/**
	 * Updates any message in the database.
	 * @param t The message object.
	 * @return boolean Whether or not it was updated.
	 */
	public boolean update(Message t);

	/**
	 * Finds a message using its id.
	 * @param id Id being checked.
	 * @return Message Message object being returned.
	 */
	public Message findById(int id);

	/**
	 * Gets the incoming messages of a user.
	 * @param id Id of the user.
	 * @param type Type of messages to look for. (used for requests as well).
	 * @return List<Message> This is the list of inbox messages returned.
	 */
	public List<Message> getMessages(int id, String theme, String type);

	/**
	 * Checks to see if the user has any friend requests.
	 * @param receiverId Id of the self-conscious user.
	 * @param type
	 * @return boolean Whether or not they have requests.
	 */
	public boolean checkRequest(int receiverId, String type);
	
	/**
	 * Gets the count of the specified notification type.
	 * @param id
	 * @param type
	 * @return int
	 */
	public int getNotifications(int id, String type);

	
	/**
	 * Sends a message to the selected user
	 * @param senderId
	 * @param receiverId
	 * @param parentId
	 * @param body
	 * @param theme
	 * @return boolean
	 */
	public boolean sendMessage(int senderId, int receiverId, int parentId, String body, String theme);

	/**
	 * Retrieves a thread of messages
	 * @param id
	 * @return List<Message>
	 */
	public List<Message> getThread(int id, String theme);

	/**
	 * Marks thread as read
	 * @param t
	 * @param theme
	 * @return boolean
	 */
	public boolean markRead(Message t, String theme);

	/**
	 * Gets the other person's ID
	 * @param id
	 * @param msg
	 * @return int
	 */
	public int getCorrespondentId(int id, Message msg);

}
