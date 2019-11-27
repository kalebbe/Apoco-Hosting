/**
 * This class is used to search jobs, add jobs, and delete jobs
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-10-20
 */
package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.JobDAO;
import com.gcu.model.Job;

public class JobBusinessService implements JobBusinessInterface {
	@Autowired
	private JobDAO dao;

	/**
	 * Calls the dao to create a new job. Will be incorporated with
	 * job creation soon.
	 * @param t
	 * @return boolean
	 */
	@Override
	public boolean createJob(Job t) {
		return dao.create(t);
	}

	/**
	 * Calls the dao to delete a job listing. This will be incorporated along
	 * with job creation soon.
	 * @param id
	 * @return boolean
	 */
	@Override
	public boolean deleteJob(int id) {
		return dao.delete(id);
	}
	
	/**
	 * Searches for job listings using a specific search term. Currently limited, but
	 * will be expanded in the next couple weeks.
	 * @param search
	 * @return List<Job>
	 */
	@Override
	public List<Job> searchJobs(String search){
		return dao.searchJobs(search, "ASC");
	}
	
	/**
	 * Takes all of the job listings from the database
	 * @return List<Job>
	 */
	@Override
	public List<Job> findAll(){
		return dao.findAll();
	}
	
	/**
	 * Find the details of a job using its unique identifier
	 * @param id
	 * @return Job
	 */
	@Override
	public Job findById(int id) {
		return dao.findById(id);
	}
}
