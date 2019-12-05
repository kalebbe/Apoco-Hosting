/**
 * This class is used for any database interactions with Dating objects. Not all of the
 * methods required by the interface will be used.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-11-18
 */

package com.gcu.data;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import com.gcu.model.Dating;

public class DatingDAO implements DataAccessInterface<Dating>{
	
	@Autowired
	private JdbcTemplate jdbcTemp;

	/**
	 * This method uses a user's id to get their business profile or null if it does not exist.
	 * @param id
	 * @return Dating
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Dating findById(int id) {
		try {
			String sql = "SELECT * FROM datprofiles WHERE USER_ID=?";
			Dating dat = (Dating) jdbcTemp.queryForObject(sql, new Object[] { id }, new DatingMapper());
			return dat;
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * Method stub. Not in use
	 * @return List<Dating>
	 */
	@Override
	public List<Dating> findAll() {
		return null;
	}

	/**
	 * This method creates a new Dating object in the database for the logged in user
	 * @param t
	 * @return boolean
	 */
	@Override
	public boolean create(Dating t) {
		String sql = "INSERT INTO datprofiles (USER_ID, PROFESSION, SALARY, EDUCATION, ETHNICITY, DOB, "
				+ "CITY, STATE, STATUS, GENDER, NICKNAME, INTERESTS, ABOUT) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		boolean result = false;
		
		//Formats the date into a string before insertion
		String date = t.getBirthMonth() + "/" + t.getBirthDay() + "/" + t.getBirthYear();
		
		//Returns 1 if the insertion is successful
		if(jdbcTemp.update(sql, t.getUserId(), t.getProfession(), t.getSalary(), t.getEducation(),
				t.getEthnicity(), date, t.getCity(), t.getState(), t.getStatus(), t.getGender(),
				t.getNickname(), t.getInterests(), t.getAbout()) == 1) {
			result = true;
		}
		
		return result;
	}

	/**
	 * Method stub. Not in use
	 * @param t
	 * @return boolean
	 */
	@Override
	public boolean update(Dating t) {
		return false;
	}

	/**
	 * Method stub. Not in use
	 * @param id
	 * @return boolean
	 */
	@Override
	public boolean delete(int id) {
		return false;
	}

}
