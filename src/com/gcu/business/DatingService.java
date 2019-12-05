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
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DatingDAO;
import com.gcu.model.Dating;
import com.gcu.model.User;

public class DatingService implements DatingInterface {

	@Autowired
	private DatingDAO dao;
	private UserBusinessInterface us;
	private QuestionInterface qs;
	
	@Autowired
	public void setUserService(UserBusinessInterface us) {
		this.us = us;
	}
	
	@Autowired
	public void setQuestionService(QuestionInterface qs) {
		this.qs = qs;
	}
	
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
	
	@Override
	public List<User> getMatches(int id) {
		List<Dating> dat = dao.findAll();
		List<User> users = new ArrayList<User>();
		User me = qs.getQuestionaire(us.findDatUser(id));
		for(Dating d: dat) {
			if(d.getUserId() != id) {
				User user = us.findDatUser(d.getUserId());
				user = qs.getQuestionaire(user);
				
				user.getDating().setPercentage((int)Math.round(this.calculateMatch(me, user)));
				users.add(user);
			}
		}
		return users;
	}
	
	@Override
	public double calculateMatch(User me, User other) {
		double percent = 100;
		percent = percent - this.calculateQue(me.getDating().getQuestion().getQue1(), 
				other.getDating().getQuestion().getQue1());
		percent = percent - this.calculateQue(me.getDating().getQuestion().getQue2(), 
				other.getDating().getQuestion().getQue2());
		percent = percent - this.calculateQue(me.getDating().getQuestion().getQue3(), 
				other.getDating().getQuestion().getQue3());
		percent = percent - this.calculateQue(me.getDating().getQuestion().getQue4(), 
				other.getDating().getQuestion().getQue4());
		percent = percent - this.calculateQue(me.getDating().getQuestion().getQue5(), 
				other.getDating().getQuestion().getQue5());
		return percent;
	}
	
	public double calculateQue(char que, char otherQue) {
		double diff = 0;
		if(que == 'a') {
			switch(otherQue) {
				case 'a':
					break;
				case 'b':
					diff += 6.66;
					break;
				case 'c':
					diff += 13.33;
					break;
				case 'd':
					diff += 20;
					break;
			}
		}
		else if(que == 'b') {
			switch(otherQue) {
				case 'a':
					diff += 6.66;
					break;
				case 'b':
					break;
				case 'c':
					diff += 6.66;
					break;
				case 'd':
					diff += 13.33;
					break;
			}
		}
		else if(que == 'c') {
			switch(otherQue) {
				case 'a':
					diff += 13.33;
					break;
				case 'b':
					diff += 6.66;
					break;
				case 'c':
					break;
				case 'd':
					diff += 6.66;
					break;
			}
		}
		else if(que == 'd') {
			switch(otherQue) {
				case 'a':
					diff += 20;
					break;
				case 'b':
					diff += 13.33;
					break;
				case 'c':
					diff += 6.66;
					break;
				case 'd':
					break;
			}
		}
		return diff;
	}
}
