/**
 * This class is used for any database interactions with Business objects. Currently, the
 * only methods being used are findById and create, but this will change.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.data;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import com.gcu.model.Business;

public class BusinessDAO implements DataAccessInterface<Business> {

	@Autowired
	private JdbcTemplate jdbcTemp;

	/**
	 * This method is used to return a Business profile using its id. Realistically, this is probably
	 * supposed to be grabbing a busprofile by its ID not the USER_ID, but this is how I currently get
	 * user profiles.
	 * @param id This is the id of the profile being grabbed.
	 * @return Business This is the business profile returned from the database.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Business findById(int id) {
		try {
			String sql = "SELECT * FROM busprofiles WHERE USER_ID=?";
			Business bus = (Business) jdbcTemp.queryForObject(sql, new Object[] { id }, new BusinessMapper());
			return bus;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * This method returns every Business profile in the database. Currently not in use.
	 * @return List<Business> This is the list of Business profiles returned.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Business> findAll() {
		String sql = "SELECT * FROM busprofiles";
		List<Business> bus = jdbcTemp.query(sql, new BusinessMapper());
		return bus;
	}

	/**
	 * This method creates a new business profile in the database. This is one of the few methods here that are currently
	 * in use.
	 * @param t This is the business profile being created.
	 * @return boolean This is the success or failure of database insertion.
	 */
	@Override
	public boolean create(Business t) {
		String sql = "INSERT INTO busprofiles (USER_ID, DOB, GENDER, ETHNICITY, CITY, STATE, EDUCATION, PROFESSION)"
				+ " VALUES (?,?,?,?,?,?,?,?)";
		boolean result = false;
		//This is inserted as a String rather than util.date because the String serves my purposes.
		String date = t.getBirthMonth() + "/" + t.getBirthDay() + "/" + t.getBirthYear();
		if (jdbcTemp.update(sql, t.getUserId(), date, t.getGender(), t.getEthnicity(), t.getCity(), t.getState(), t.getEducation(),
				t.getProfession()) == 1) {
			result = true;
		}
		return result;
	}

	/**
	 * This method updates a business profile in the database. This is currently not in use, but it will be in the
	 * near future.
	 * @param t This is the business profile being updated.
	 * @return boolean This is the success or failure of the database update.
	 */
	@Override
	public boolean update(Business t) {
		String sql = "UPDATE busprofiles SET USER_ID = ?, DOB = ?, GENDER = ?, ETHNICITY = ?, CITY = ?, STATE = ?, EDUCATION = ?,"
				+ " PROFESSION = ? WHERE ID = ?";
		boolean result = false;
		String date = t.getBirthMonth() + "/" + t.getBirthDay() + "/" + t.getBirthYear();
		if (jdbcTemp.update(sql, t.getUserId(), date, t.getGender(), t.getEthnicity(), t.getCity(), t.getState(), t.getEducation(),
				t.getProfession(), t.getId()) == 1) {
			result = true;
		}
		return result;
	}

	/**
	 * This method deletes a business profile from the database by it's id. This is currently not in use,
	 * but will be added for future purposes.
	 * @param id This is the id of the profile scheduled for deletion.
	 * @return boolean This is the success or failure of the database deletion.
	 */
	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM busprofiles WHERE USER_ID = ?";
		boolean result = false;
		if (jdbcTemp.update(sql, id) == 1) {
			result = true;
		}
		return result;
	}

}
