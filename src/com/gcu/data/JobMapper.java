/**
 * This class is used to map the data from the database when creating a new
 * job model using Spring JDBC.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-10-21
 */

package com.gcu.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.gcu.model.Job;

@SuppressWarnings("rawtypes")
public class JobMapper implements RowMapper {

	/**
	 * This is a method used to create and return a new Job object using database
	 * information.
	 * @param rs
	 * @param rowNum
	 * @return Job
	 */
	@Override
	public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
		Job job = new Job();
		job.setDescription(rs.getString("DESCRIPTION"));
		job.setId(rs.getInt("ID"));
		job.setJobTitle(rs.getString("TITLE"));
		job.setDate(rs.getDate("DATE_POSTED"));
		job.setLink(rs.getString("LINK"));
		job.setRequirements(rs.getString("REQUIREMENTS"));
		job.setSalary(rs.getString("SALARY"));
		job.setUserId(rs.getInt("USER_ID"));
		job.setType(rs.getString("TYPE"));
		job.setCity(rs.getString("CITY"));
		job.setState(rs.getString("STATE"));
		job.setCompany(rs.getString("COMPANY"));
		return job;
	}

}
