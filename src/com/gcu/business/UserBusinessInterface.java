/**
 * This interface gives the methods required in the UserBusinessService class and any
 * other classes that may eventually implement this interface.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-11-25
 */
package com.gcu.business;

import com.gcu.model.User;

public interface UserBusinessInterface {

	/**
	 * This method sends a user to the database for creation.
	 * @param t This is the user being created.
	 * @return int This is the id being returned from creation.
	 */
	public int register(User t);
	
	/**
	 * This method logs the user in to the website.
	 * @param login This is the username or email used for login.
	 * @param password This is the password used for login.
	 * @return int This is the user id returned from login.
	 */
	public User login(String login, String password);
	
	/**
	 * This method is for changing the user password.
	 * @param t This is the user being sent for update.
	 * @return boolean This is whether or not the update passed.
	 */
	public boolean changePass(User t);
	
	/**
	 * This method checks to ensure the password matches DB password.
	 * @param pass This is the password to check.
	 * @param id This is the id of the User.
	 * @return boolean This is whether or not the password matches.
	 */
	public User checkPass(String pass, int id);
	
	/**
	 * This method is for changing the user first name.
	 * @param t This is the user being sent for update.
	 * @return boolean This is whether or not the update passed.
	 */
	public boolean updateFirst(User t);
	
	/**
	 * This method is for changing the user last name.
	 * @param t This is the user being sent for update.
	 * @return boolean This is whether or not the update passed.
	 */
	public boolean updateLast(User t);
	
	/**
	 * This method is for changing the username of the user.
	 * @param t This is the user being sent for update.
	 * @return boolean This is whether or not the update passed.
	 */
	public boolean updateUser(User t);
	
	/**
	 * This method checks to see if the username is taken already.
	 * @param t This is the user who's username is being checked.
	 * @return boolean This is whether or not the username is taken.
	 */
	public boolean checkUser(User t);
	
	/**
	 * This method checks to see if the email is already taken.
	 * @param t This is the user who's email is being checked.
	 * @return boolean This is whether or not the email is taken.
	 */
	public boolean checkEmail(User t);
	
	/**
	 * This method is for changing the email of the user.
	 * @param t This is the user being sent for update.
	 * @return boolean This is whether ot not the update passed.
	 */
	public boolean updateEmail(User t);
	
	/**
	 * This method gets a User object using its ID.
	 * @param id This is the ID used to get the user.
	 * @return User This is the user returned.
	 */
	public User findById(int id);
	
	/**
	 * This method gets a user + social profile from the database.
	 * @param id
	 * @param userId
	 * @return User
	 */
	public User findSocUser(int id, int userId);
	
	/**
	 * This method gets a user + business profile from the database.
	 * @param id
	 * @return User
	 */
	public User findBusUser(int id);
}
