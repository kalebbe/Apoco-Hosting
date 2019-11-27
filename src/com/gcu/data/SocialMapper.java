/**
 * This class is used to map the data from the database when creating a new
 * Social model using Spring JDBC.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.gcu.model.Social;


@SuppressWarnings("rawtypes")
public class SocialMapper implements RowMapper {

	/**
	 * This is a quick method for creating and returning a new social object from the database.
	 * @param rs This is the result set from the database.
	 * @param rowNum This is required by RowMapper, but unused.
	 * @return Social This is the social object returned after being set.
	 */
	@Override
	public Social mapRow(ResultSet rs, int rowNum) throws SQLException {
		Social social = new Social();
		
		//Grabs user's birth date and splits it for model insertion
		String date = rs.getString("BIRTH_DATE");
		String arr[] = date.split("/");
		
		social.setId(rs.getInt("ID"));
		social.setBio(rs.getString("BIO"));
		social.setBirthDay(Integer.parseInt(arr[1]));
		social.setBirthMonth(Integer.parseInt(arr[0]));
		social.setBirthYear(Integer.parseInt(arr[2]));
		social.setCareer(rs.getString("CAREER"));
		social.setCity(rs.getString("CITY"));
		social.setEducation(rs.getString("EDUCATION"));
		social.setJob(rs.getString("JOB"));
		social.setPrivacy(rs.getBoolean("PRIVACY"));
		social.setSchool(rs.getString("SCHOOL"));
		social.setState(rs.getString("STATE"));
		social.setStatus(rs.getString("RELATIONSHIP"));
		social.setUserId(rs.getInt("USER_ID"));
		social.setGender(rs.getString("GENDER"));
		return social;
	}
}
