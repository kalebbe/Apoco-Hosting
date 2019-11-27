/**
 * This class is used for all things involving our social service and the database. Currently this class
 * can create the user's social profile, create a feed post, and grab the data as well.
 * 
 *
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.1
 * @since   2018-11-25
 */

package com.gcu.data;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import com.gcu.model.Social;

public class SocialDAO implements DataAccessInterface<Social> {

	@Autowired
	private JdbcTemplate jdbcTemp; // Used for spring jdbc database calls

	/**
	 * This method takes the user's id and returns the user model for them. If there
	 * is no result for that id, the method will catch the exception and return
	 * null.
	 * 
	 * @param id This is the id of the Social profile being fetched.
	 * @return Social This is the object being returned.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Social findById(int id) {
		try {
			String sql = "SELECT * FROM socialprofiles WHERE USER_ID=?";
			Social social = (Social) jdbcTemp.queryForObject(sql, new Object[] { id }, new SocialMapper());
			return social;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * This method returns every single social profile in the database. As of right
	 * now, this method does not have a purpose, but may be used in the future for
	 * data purposes.
	 * 
	 * @return List<Social> This is the list of Social objects returned.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Social> findAll() {
		String sql = "SELECT * FROM socialprofiles";
		List<Social> social = jdbcTemp.query(sql, new SocialMapper());
		return social;
	}

	/**
	 * This method takes the Social model and inserts it into the database.
	 * 
	 * @param t This is the social object being created.
	 * @return boolean This returns the success of database insertion.
	 */
	@Override
	public boolean create(Social t) {
		String sql = "INSERT INTO socialprofiles (USER_ID, CAREER, CITY, STATE, RELATIONSHIP, BIO,"
				+ " EDUCATION, SCHOOL, JOB, BIRTH_DATE, PRIVACY, GENDER) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		boolean result = false;

		// Makes a simple String date with / between numbers
		String date = t.getBirthMonth() + "/" + t.getBirthDay() + "/" + t.getBirthYear();

		// This checks to see if the insertion into the database returns 1 as a result
		// which would mean the insertion was successful.
		if (jdbcTemp.update(sql, t.getUserId(), t.getCareer(), t.getCity(), t.getState(), t.getStatus(), t.getBio(),
				t.getEducation(), t.getSchool(), t.getJob(), date, t.isPrivacy(), t.getGender()) == 1) {
			result = true;
		}
		return result;
	}

	/**
	 * This method updates a single column of the social profile. This was changed
	 * from the base ICA update method which would update the entire model. I feel
	 * like this is more efficient as only the required columns are updated. This is
	 * currently not in use.
	 * 
	 * @param t This is the social object being updated.
	 * @return boolean This returns the success of the database update.
	 */
	@Override
	public boolean update(Social t) {
		String sql = "UPDATE socialprofiles SET CAREER = ?, CITY = ?, STATE = ?, RELATIONSHIP = ?, BIO = ?, EDUCATION = ?, "
				+ "SCHOOL = ?, JOB = ?, BIRTH_DATE = ?, PRIVACY = ?, GENDER = ? WHERE ID = ?";
		boolean result = false;
		String date = t.getBirthMonth() + "/" + t.getBirthDay() + t.getBirthYear();
		if (jdbcTemp.update(sql, t.getCareer(), t.getCity(), t.getState(), t.getStatus(), t.getBio(), t.getEducation(),
				t.getSchool(), t.getJob(), date, t.isPrivacy(), t.getGender(), t.getId()) == 1) {
			result = true;
		}
		return result;
	}

	/**
	 * This method deletes the social profile of the logged in user. Currently not
	 * in use.
	 * 
	 * @param id This is the id of the social object being deleted in the database.
	 * @return boolean This returns the success of the database deletion.
	 */
	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM socialprofiles WHERE USER_ID = ?";
		boolean result = false;
		if (jdbcTemp.update(sql, id) == 1) {
			result = true;
		}
		return result;
	}

	/**
	 * This method gets the user's privacy level from the database .
	 * @param id User id being checked.
	 * @return int This is the actual privacy level being returned.
	 */
	public int getPrivacy(int id) {
		try {
			String sql = "SELECT PRIVACY FROM socialprofiles WHERE USER_ID = ?";
			int priv = jdbcTemp.queryForObject(sql, new Object[] { id }, Integer.class);
			return priv;
		} catch (EmptyResultDataAccessException e) {
			return -1;
		}

	}
}
