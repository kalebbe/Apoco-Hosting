/**
 * This class is used to map the data from the database when creating a new
 * Dating model using Spring JDBC.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-11-18
 */

package com.gcu.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gcu.model.Dating;

@SuppressWarnings("rawtypes")
public class DatingMapper implements RowMapper {

	/**
	 * This method is used to map an object being taken from the database.
	 * @param rs
	 * @param rowNum
	 * @return Object
	 */
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Dating dat = new Dating();
		
		//Splitting up the date to put it back int eh object
		String date = rs.getString("DOB");
		String arr[] = date.split("/");
		
		//Setting each value of the new Dating object.
		//Could be done with constructor, but would be just as tedious.
		dat.setId(rs.getInt("ID"));
		dat.setUserId(rs.getInt("USER_ID"));
		dat.setProfession(rs.getString("PROFESSION"));
		dat.setSalary(rs.getString("SALARY"));
		dat.setEducation(rs.getString("EDUCATION"));
		dat.setEthnicity(rs.getString("ETHNICITY"));
		dat.setBirthDay(Integer.parseInt(arr[1]));
		dat.setBirthMonth(Integer.parseInt(arr[0]));
		dat.setBirthYear(Integer.parseInt(arr[2]));
		dat.setCity(rs.getString("CITY"));
		dat.setState(rs.getString("STATE"));
		dat.setStatus(rs.getString("STATUS"));
		dat.setGender(rs.getString("GENDER"));
		dat.setNickname(rs.getString("NICKNAME"));
		dat.setInterests(rs.getString("INTERESTS"));
		dat.setAbout(rs.getString("ABOUT"));
		return dat;
	}

}
