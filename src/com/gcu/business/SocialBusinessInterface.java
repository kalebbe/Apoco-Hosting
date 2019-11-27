/**
 * This interface lays forth the method guidelines for the SocialBusinessService class
 * 
 * @author  Kaleb Eberhart
 * @version 1.01
 * @since   2018-11-25
 */
package com.gcu.business;

import com.gcu.model.Social;

public interface SocialBusinessInterface {
	
	/**
	 * This method requires implementing classes to have a create social method.
	 * @param t This is the social object being sent for creation.
	 * @return boolean This is the return from the database.
	 */
	public boolean createSocial(Social t);
	
	/**
	 * This method requires implementing classes to have a method for checking social profiles.
	 * @param id This is the id of the user being checked for a profile.
	 * @return boolean This is whether or not the user has a profile.
	 */
	public boolean checkSocial(int id);
}
