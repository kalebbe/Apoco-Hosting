/**
 * This model holds all of the user fields and completes data validation for
 * each field. Email validation is done in javascript/html5.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.model;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
	
	//Checks that the email field is not empty
	@NotNull(message="Email cannot be empty!")
	@Size(min=4, max=50, message="Email must be between 4 and 50 characters!")
	private String email;
	//Checks the username field is not empty and is 4-30 chars.
	@NotNull(message="Username cannot be empty!")
	@Size(min=4, max=30, message="Username must be between 4 and 30 characters!")
	private String username;
	//Checks the first name is not empty and is 2-30 chars.
	@NotNull(message="First Name cannot be empty!")
	@Size(min=2, max=30, message="First Name must be between 2 and 30 characters!")
	private String firstName;
	//Checks the last name is not empty and is 2-30 chars.
	@NotNull(message="Last Name cannot be empty!")
	@Size(min=2, max=30, message="Last Name must be between 2 and 30 characters!")
	private String lastName;
	//Checks the password is not empty, is > 8 chars, and contains letters and numbers.
	@NotNull(message="Password cannot be empty!")
	@Size(min=8, message="Password must be atleast 8 characters!")
	@Pattern(regexp = "^([0-9]+[a-zA-Z]+|[a-zA-Z]+[0-9]+)[0-9a-zA-Z]*$", message="Password must contain letters and numbers!")
	private String password;
	//Same as above
	@NotNull(message="Password cannot be empty!")
	@Size(min=8, message="Password must be atleast 8 characters!")
	@Pattern(regexp = "^([0-9]+[a-zA-Z]+|[a-zA-Z]+[0-9]+)[0-9a-zA-Z]*$", message="Password must contain letters and numbers!")
	private String passRe;
	private int id;
	private Business business;
	private Social social; //Added for purpose of listing friends.
	private Dating dating;
	private List<Feed> feed;
	private boolean friend;
	private boolean connection;
	private String access;
	private int messageId;

	/**
	 * Getter for the id variable.
	 * @return int This is the id of the user.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for the id variable.
	 * @param id This is the new id of the user.
	 * @return Nothing.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter for the email variable.
	 * @return String This is the email of the user.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for the email variable.
	 * @param email This is the new email of the user.
	 * @return Nothing.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter for the username variable.
	 * @return String This is the username of the user.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter for the username variable.
	 * @param username This is the new username of the user.
	 * @return Nothing.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter for the firstName variable.
	 * @return String This is the first name of the user.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for the firstName variable.
	 * @param firstName This is the new first name of the user.
	 * @return Nothing.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for the lastName variable.
	 * @return String This is the last name of the user.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for the lastName variable.
	 * @param lastName This is the new last name of the user.
	 * @return Nothing.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for the password variable.
	 * @return String This is the password of the user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for the password variable.
	 * @param password This is the new password of the user.
	 * @return Nothing.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter for the passRe variable.
	 * @return String this is the repeated password of the user.
	 */
	public String getPassRe() {
		return passRe;
	}

	/**
	 * Setter for the passRe variable.
	 * @param passRe This is the new repeated password of the user.
	 * @return Nothing.
	 */
	public void setPassRe(String passRe) {
		this.passRe = passRe;
	}
	
	/**
	 * Getter for the social variable. This was added, so that I can list the user bio and profile
	 * information along with their name in the search and friends page.
	 * @return Social Returning the Social profile.
	 */
	public Social getSocial() {
		return social;
	}
	
	/**
	 * Getter for the business variable. This is here so the user's business bio can be listed and
	 * editted both on their profiel page and when others are viewing their profile.
	 * @return Business
	 */
	public Business getBusiness() {
		return business;
	}

	/**
	 * Getter to see if a user is a friend. Added with the friend functionality.
	 * @return boolean Well. This is a true or false, so that only makes sense.
	 */
	public boolean isFriend() {
		return friend;
	}

	/**
	 * Setter to say if someone is a friend or not.
	 * @param friend True or false.
	 * @return Nothing.
	 */
	public void setFriend(boolean friend) {
		this.friend = friend;
	}

	/**
	 * Setter for the social variable. Same reason as above.
	 * @param social This is the new Social profile being set.
	 * @return Nothing.
	 */
	public void setSocial(Social social) {
		this.social = social;
	}
	
	/**
	 * Setter for the business variable.
	 * @param business
	 * @return nothing
	 */
	public void setBusiness(Business business) {
		this.business = business;
	}
	
	/**
	 * Getter to grab all of the feed posts from the user. Added with profile viewing system.
	 * @return List<Feed> The list of feed posts.
	 */
	public List<Feed> getFeed() {
		return feed;
	}

	/**
	 * Setter to put all the feed posts in the model. Added with profile viewing system.
	 * @param feed The list of feed being applied.
	 * @return Nothing.
	 */
	public void setFeed(List<Feed> feed) {
		this.feed = feed;
	}
	
	/**
	 * Getter for grabbing a friend request message id. Added with the friend request system.
	 * @return int The id of the message.
	 */
	public int getMessageId() {
		return messageId;
	}

	/**
	 * Setter for the message id of a friend request sent by this user. Added with friend request system.
	 * @param messageId The id of the request being set.
	 * @return Nothing.
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	
	/**
	 * Simple User constructor that sets everything to empty. May include a non-default for this
	 * model in the future if I feel a need for it.
	 */
	public User() {
		this.email = "";
		this.username = "";
		this.firstName = "";
		this.lastName = "";
		this.password = "";
		this.passRe = "";
		this.access = "user";
	}

	/**
	 * This holds whether or not the user is connected to the logged in user.
	 * @return boolean
	 */
	public boolean isConnection() {
		return connection;
	}

	/**
	 * This sets the connection variable.
	 * @param connection
	 */
	public void setConnection(boolean connection) {
		this.connection = connection;
	}

	/**
	 * This defines the access level of the user
	 * @return String
	 */
	public String getAccess() {
		return access;
	}

	/**
	 * Sets the access level of the user;
	 * @param access
	 */
	public void setAccess(String access) {
		this.access = access;
	}

	public Dating getDating() {
		return dating;
	}

	public void setDating(Dating dating) {
		this.dating = dating;
	}
}
