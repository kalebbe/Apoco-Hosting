/**
 * This interface sets for the guidelines for the BusinessService class.
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.business;

import com.gcu.model.Business;

public interface BusinessInterface {

	/**
	 * This method requires any implementing class to have a business profile creation method.
	 * @param t This is the business profile being sent for creation.
	 * @return boolean This is the result of creation.
	 */
	public boolean createBusiness(Business t);
	
	/**
	 * This method requires any implementing class to have a method that checks for the existence
	 * of a business profile.
	 * @param id
	 * @return
	 */
	public boolean checkBusiness(int id);
}
