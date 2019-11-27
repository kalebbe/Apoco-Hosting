/**
 * This interface defines the methods required in the JobBusinessService class.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-10-20
 */
package com.gcu.business;

import java.util.List;

import com.gcu.model.Job;

public interface JobBusinessInterface {
	
	/**
	 * Calls the dao to create a new job listing
	 * @param t
	 * @return
	 */
	public boolean createJob(Job t);
	
	/**
	 * calls the dao to delete a job listing
	 * @param t
	 * @return
	 */
	public boolean deleteJob(int t);

	/**
	 * Searches the database for jobs like the search term.
	 * @param search
	 * @return List<Job>
	 */
	public List<Job> searchJobs(String search);

	/**
	 * Returns a list of all job listings in the database.
	 * @return List<Job>
	 */
	public List<Job> findAll();

	/**
	 * Find the details of a job using its unique identifier
	 * @param id
	 * @return Job
	 */
	public Job findById(int id);
}
