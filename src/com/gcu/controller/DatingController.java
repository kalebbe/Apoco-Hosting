/**
 * This controller is used to handle all things that are involved with the dating profile
 * in this project.
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-10-21
 */

package com.gcu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.DatingInterface;
import com.gcu.model.Dating;
import com.gcu.model.User;
import com.gcu.utilities.ControllerLists;

@Controller
@RequestMapping("/dating")
public class DatingController {
	
	private ControllerLists cl;
	private DatingInterface ds;
	
	/**
	 * Dependency injection for Controller List
	 * @param cl
	 */
	@Autowired
	public void setControllerList(ControllerLists cl) {
		this.cl = cl;
	}
	
	/**
	 * Dependency injection for the Dating Service
	 * @param ds
	 */
	@Autowired
	public void setDatingService(DatingInterface ds) {
		this.ds = ds;
	}

	/**
	 * This method routes the user to either the profile creator or dating dashboard if
	 * their profile is already created.
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/dat", method = RequestMethod.GET)
	public ModelAndView datingProfile(HttpSession session) {
		if(session.getAttribute("id") == null) {
			return new ModelAndView("redirect:../login/log", "user", new User());
		}
		session.setAttribute("theme", "dating"); //Setting theme for color scheme
		
		if(session.getAttribute("hasDating") != null) { //Has profile, goes to dash.
			return new ModelAndView("datDash", "dating", new Dating());
		}
		else { //Doesn't have a profile, goes to creator.
			return new ModelAndView("datProfile", "dating", new Dating());
		}
	}
	
	/**
	 * This method submits the user's dating profile for business logic checks and database insertion.
	 * @param dat
	 * @param result
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/submitDat", method = RequestMethod.POST)
	public ModelAndView submitDat(@Valid @ModelAttribute("dating") Dating dat, BindingResult result, HttpSession session) {
		if(result.hasErrors()) { //Spring result used in model
			return new ModelAndView("datProfile", "dating", dat);
		}
		
		dat.setUserId((int)session.getAttribute("id"));
		
		if(!ds.checkAge(dat)) { //User must be over 18, so this checks here.
			session.setAttribute("message", "You must be over the age of 18!");
			return new ModelAndView("datProfile", "dating", dat);
		}
		else if (ds.createDating(dat)){
			session.setAttribute("hasDating", true);
			return new ModelAndView("datDash", "dating", dat);
		}
		else {
			return new ModelAndView("datProfile", "dating", new Dating());
		}
	}
	
	/**
	 * Gets a list from the controller lists
	 * @return List<Integer>
	 */
	@ModelAttribute("dayList")
	public List<Integer> getDayList(){
		return cl.getBirthDay();
	}
	
	/**
	 * Gets a year list to be used in the view
	 * @return List<Integer>
	 */
	@ModelAttribute("yearList")
	public List<Integer> getYearList(){
		return cl.getBirthYear();
	}
	
	/**
	 * Gets a hashmap of months for the view
	 * @return Map<Integer, String>
	 */
	@ModelAttribute("monthList")
	public Map<Integer, String> getMonthList(){
		return cl.getMonths();
	}
	
	/**
	 * Returns a list of different job types for the view
	 * @return List<String>
	 */
	@ModelAttribute("jobList")
	public List<String> getJobList(){
		return cl.getJobList();
	}
	
	/**
	 * Returns a list of different education levels for the view
	 * @return List<String>
	 */
	@ModelAttribute("edList")
	public List<String> getEdList(){
		return cl.getEdList();
	}
	
	/**
	 * Returns a list of different ethnicities to the view
	 * @return List<String>
	 */
	@ModelAttribute("ethList")
	public List<String> getEthList(){
		return cl.getEthList();
	}
	
	/**
	 * Returns a list of salary ranges to the view
	 * @return List<String>
	 */
	@ModelAttribute("salaryList")
	public List<String> getSalaryList(){
		return cl.getSalaryList();
	}
	
	/**
	 * Returns a list of state abbreviations to the view
	 * @return List<String>
	 */
	@ModelAttribute("stateList")
	public List<String> getStateList(){
		return cl.getStates();
	}
	
	/**
	 * Returns a list of possible relationship status' to the view
	 * @return List<String>
	 */
	@ModelAttribute("statusList")
	public List<String> getStatusList(){
		return cl.getStatList();
	}
}
