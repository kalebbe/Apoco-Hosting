/**
 * This class is used for any database interactions with Job objects.
 * Editting job listings is not within the scope of this project; however,
 * we would like to make it so that companies or admin(?) can delete their
 * job listings.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-10-21
 */
package com.gcu.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.gcu.model.Job;

public class JobDAO implements DataAccessInterface<Job>{

	@Autowired
	private JdbcTemplate jdbcTemp;
	
	/**
	 * This method is used to return a specific job listing by its ID.
	 * @param id
	 * @return Job
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Job findById(int id) {
		String sql = "SELECT * FROM jobs WHERE ID=?";
		Job job = (Job) jdbcTemp.queryForObject(sql, new Object[] { id }, new JobMapper());
		return job;
	}

	/**
	 * This method gets all of the job listings from the database. This will be
	 * used when the user loads the "jobs" page without searching or providing
	 * any filters.
	 * @return List<Job>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Job> findAll() {
		String sql = "SELECT * FROM jobs";
		try {
			List<Job> job = jdbcTemp.query(sql, new JobMapper());
			return job;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method creates a new job in the database. Only company
	 * accounts should be able to create jobs (for now, admins
	 * will be able to in the future if they are implemented before
	 * final project completion).
	 * @param t
	 * @return boolean
	 */
	@Override
	public boolean create(Job t) {
		String sql = "INSERT INTO jobs (USER_ID, TITLE, DESCRIPTION, SALARY, "
				+ "LINK, REQUIREMENTS, TYPE, CITY, STATE, COMPANY) VALUES (?,?,?,?,?,?,?,?,?,?)";
		boolean result = false;
		if(jdbcTemp.update(sql, t.getUserId(), t.getJobTitle(), t.getDescription(), 
				t.getSalary(), t.getLink(), t.getRequirements(), t.getType(),
				t.getCity(), t.getState(), t.getCompany()) == 1) {
			result = true;
		}
		return result;
	}

	/**
	 * Update will not be used for job listings.
	 * @param t
	 * @return boolean
	 */
	@Override
	public boolean update(Job t) {
		return false;
	}

	/**
	 * Deletes a job posting from the database.
	 * @param id
	 * @return boolean
	 */
	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM jobs WHERE ID=?";
		boolean result = false;
		if(jdbcTemp.update(sql, id) == 1) {
			result = true;
		}
		return result;
	}
	
	/**
	 * This method returns the details of a job when searched for using the search bar.
	 * @param search
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Job> searchJobs(String search, String order){
		String sql = "SELECT * FROM jobs WHERE (TITLE LIKE ? OR TYPE LIKE ? OR CITY = ? "
				+ "OR STATE = ?) ORDER BY DATE_POSTED " + order;
		List<Job> jobs = jdbcTemp.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1,  "%" + search + "%");
				ps.setString(2, "%" + search + "%");
				ps.setString(3, search);
				ps.setString(4, search);
			}
		}, new JobMapper());
		return jobs;
	}

	/**
	 * This method is used to filter a job search by the type of job
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Job> filterByType(String type, String order){
		String sql = "SELECT * FROM jobs WHERE TYPE=? "
				+ "ORDER BY DATE_POSTED " + order;
		List<Job> jobs = jdbcTemp.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1,  type);
			}
		}, new JobMapper());
		return jobs;
	}
}
