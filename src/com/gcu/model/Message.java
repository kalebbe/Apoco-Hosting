/**
 * This model is used for all things involved with messages in the social platform of the
 * website. This will be used a lot more in the future, but right now is only used for 
 * friend requests.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-12-16
 */

package com.gcu.model;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Message {
	private int id;
	private int senderId;
	private int receiverId;
	private int parentId;
	@NotNull(message = "You must fill the message body!")
	@Size(min=20, max = 10000, message="Your message must be atleast 20 characters!")
	private String body;
	private String type;
	private boolean read; //Sets parent to read/unread for logged in user (based on follow messages)
	private User user; //Added to attach a name to messages (Used for sender)
	private LocalDateTime date; //Added to show date/time sent in message center
	
	/**
	 * This is the getter for this message's id.
	 * @return int The id.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * This is the setter for this message's id.
	 * @param id The new id.
	 * @return Nothing.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * This is the id of the message sender.
	 * @return int The id.
	 */
	public int getSenderId() {
		return senderId;
	}
	
	/**
	 * This is the setter for the id of the message sender.
	 * @param senderId The new Sender id.
	 * @return Nothing.
	 */
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	
	/**
	 * This is the getter for the id of the message receiver.
	 * @return int The receiver id.
	 */
	public int getReceiverId() {
		return receiverId;
	}
	
	/**
	 * This is the setter for the id of the message receiver.
	 * @param receiverId The new receiver id.
	 * @return Nothing.
	 */
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	
	/**
	 * This is the getter for the message's body. Null for friend requests.
	 * @return String The actual message.
	 */
	public String getBody() {
		return body;
	}
	
	/**
	 * This is the setter for the message's body.
	 * @param body The new body.
	 * @return Nothing.
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * This is the getter for the message type. Read/Unread/Sent/Request
	 * @return String The type.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * This is the setter for the message type.
	 * @param type The new type.
	 * @return Nothing.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Getter for parent id
	 * @return
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * Setter for the parent Id.
	 * @param parentId
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * Getter for the user
	 * @return User
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Setter for the user
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Getter for the date of the message
	 * @return Date
	 */ 
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * Setter for the date
	 * @param date
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	 * Checks if message is read
	 * @return boolean
	 */
	public boolean isRead() {
		return read;
	}

	/**
	 * Sets read boolean
	 * @param read
	 */
	public void setRead(boolean read) {
		this.read = read;
	}

	/**
	 * This is the non-default constructor for a message.
	 * @param senderId The dude who sent the message.
	 * @param receiverId The dude gettin the message.
	 * @param body The message he gettin.
	 * @param type The type of message.
	 */
	public Message(int senderId, int receiverId, int parentId, String body, String type) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.parentId = parentId;
		this.body = body;
		this.type = type;
	}
	
	/**
	 * This is the default constructor. Will be used for new message creation in the future.
	 */
	public Message() {
		this.senderId = -1;
		this.receiverId = -1;
		this.parentId = -1;
		this.body = "";
		this.type = "";
	}
	
}
