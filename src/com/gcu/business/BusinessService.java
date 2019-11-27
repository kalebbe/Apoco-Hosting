/**
 * This class is used to handle the business logic of the Business profile
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;
import com.gcu.data.BusinessDAO;
import com.gcu.model.Business;

public class BusinessService implements BusinessInterface {
	
	@Autowired
	private BusinessDAO dao;
	
	/**
	 * This method calls the DAO to create a new business profile in the database.
	 * @param t This is the business profile being created.
	 * @return boolean This is the result of the profile creation.
	 */
	@Override
	public boolean createBusiness(Business t) {
		return dao.create(t);
	}
	
	/**
	 * This method checks to see if the user has a business profile in the database.
	 * @param id This is the id of the user being checked.
	 * @return boolean This is whether or not the profile exists.
	 */
	@Override
	public boolean checkBusiness(int id) {
		if(dao.findById(id) != null) {
			return true;
		}
		else {
			return false;
		}
	}
}
