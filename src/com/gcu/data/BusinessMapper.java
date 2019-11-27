/**
 * This class is used to map the data from the database when creating a new
 * Business object using Spring JDBC.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @Version 1.0
 * @since   2018-11-25
 */

package com.gcu.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.gcu.model.Business;

@SuppressWarnings("rawtypes")
public class BusinessMapper implements RowMapper {
	
	/**
	 * This is a quick method for creating and returning a new business object from the database.
	 * @param rs This is the result set from the database.
	 * @param rowNum This is required by RowMapper, but unused.
	 * @return Business This is the business object returned after being characterized by this class.
	 */
	@Override
	public Business mapRow(ResultSet rs, int rowNum) throws SQLException {
		Business bus = new Business();
		
		String date = rs.getString("DOB");
		String arr[] = date.split("/");
		
		bus.setId(rs.getInt("ID"));
		bus.setBirthDay(Integer.parseInt(arr[1]));
		bus.setBirthMonth(Integer.parseInt(arr[0]));
		bus.setBirthYear(Integer.parseInt(arr[2]));
		bus.setGender(rs.getString("GENDER"));
		bus.setEthnicity(rs.getString("ETHNICITY"));
		bus.setCity(rs.getString("CITY"));
		bus.setState(rs.getString("STATE"));
		bus.setEducation(rs.getString("EDUCATION"));
		bus.setProfession(rs.getString("PROFESSION"));
		return bus;
	}
}
