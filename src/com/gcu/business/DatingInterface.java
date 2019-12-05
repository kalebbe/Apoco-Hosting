/**
 * This interface defines the methods that will be used in the dating service.
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-11-18
 */

package com.gcu.business;

import com.gcu.model.Dating;

public interface DatingInterface {
	
	/**
	 * This method checks if the user's age is over the age of 18.
	 * @param t
	 * @return boolean
	 */
	public boolean checkAge(Dating t);

	/**
	 * Checks the database to see if user has a dating profile.
	 * @param id
	 * @return boolean
	 */
	public boolean checkDating(int id);

	/**
	 * Creates a new dating profile for the user in the database.
	 * @param t
	 * @return boolean
	 */
	public boolean createDating(Dating t);
}
