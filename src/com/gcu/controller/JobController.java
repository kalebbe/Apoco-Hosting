/**
 * This controller is used to list jobs, and view details of a job.
 * THIS WILL SOON BE UPDATED TO ALLOW FOR CREATION AND DELETION OF
 * JOBS WITH THE ADDITION OF COMPANY ACOCUNTS.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1
 * @since   2019-10-21
 */

package com.gcu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.gcu.utilities.ControllerLists;
import com.gcu.business.JobBusinessInterface;
import com.gcu.model.Job;;

@Controller
@RequestMapping("/jobs")
public class JobController {

	private ControllerLists cl;
	
	private JobBusinessInterface js;
	
	/**
	 * Dependency injection for the job business service
	 * @param js
	 */
	@Autowired
	public void setJobService(JobBusinessInterface js) {
		this.js = js;
	}
	
	/**
	 * Returns the user to the search page with all of the job listings in ascending
	 * order.
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/jobList", method = RequestMethod.GET)
	public ModelAndView jobList(HttpSession session) {
		List<Job> jobs = js.findAll();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("jobs", jobs);
		session.setAttribute("search",  "jobs");
		return new ModelAndView("busSearch", "model", model);
	}
	
	/**
	 * Returns a view of the job listing with the job model passed in.
	 * @param id
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/view", method = RequestMethod.POST)
	public ModelAndView viewJob(@RequestParam int id, HttpSession session) {
		Job job = js.findById(id);
		return new ModelAndView("viewJob", "job", job);
	}
	
	/**
	 * Returns a list of state initials for the user to choose from.
	 * NOT INCLUDED IN THIS MILESTONE, WILL BE USED WITH JOB CREATION COMING SOON
	 * @return List<String> This is the list of different state abbreviations being returned.
	 */
	@ModelAttribute("stateList")
	public List<String> getStates(){
		cl = new ControllerLists();
		return cl.getStates();
	}
}
