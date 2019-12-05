/**
 * This class handles the business logic of the dating profile
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-11-18
 */

package com.gcu.business;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DatingDAO;
import com.gcu.model.Dating;

public class DatingService implements DatingInterface {

	@Autowired
	private DatingDAO dao;
	
	/**
	 * This method is used to check if the user's age is over 18
	 * @param t
	 * @return boolean
	 */
	@Override
	public boolean checkAge(Dating t) {
		//Creates a date object with the data to check difference
		LocalDate birthDate = LocalDate.of(t.getBirthYear(), t.getBirthMonth(), t.getBirthDay());
		
		//Returns the year between birthDate and current
		int age = Period.between(birthDate, LocalDate.now()).getYears();
		
		if(age < 18) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * This method calls the dao to create a new dating profile.
	 * @param t
	 * @return boolean
	 */
	@Override
	public boolean createDating(Dating t) {
		return dao.create(t);
	}
	
	/**
	 * This method checks if the user has a dating profile int he database.
	 * @param id
	 * @return boolean
	 */
	@Override
	public boolean checkDating(int id) {
		if(dao.findById(id) != null) {
			return true;
		} else {
			return false;
		}
	}
}
