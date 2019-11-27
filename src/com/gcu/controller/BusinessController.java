/**
 * This controller is used to handle all things that are involved with the business profile
 * in this project. This will likely be expanded in the future to include updating and
 * deleting profiles.
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.gcu.business.BusinessInterface;
import com.gcu.business.ConnectionBusinessInterface;
import com.gcu.business.JobBusinessInterface;
import com.gcu.business.MessageBusinessInterface;
import com.gcu.business.UserBusinessInterface;
import com.gcu.model.Business;
import com.gcu.model.Job;
import com.gcu.model.User;
import com.gcu.utilities.ControllerLists;

@Controller
@RequestMapping("/business")
public class BusinessController {
	private BusinessInterface bs;
	private UserBusinessInterface us;
	private ConnectionBusinessInterface cs;
	private JobBusinessInterface js;
	private MessageBusinessInterface ms;
	private ControllerLists cl;
	
	/**
	 * Dependency injection for the ControllerLists
	 * @param cl
	 */
	@Autowired
	public void setControllerLists(ControllerLists cl) {
		this.cl = cl;
	}
	
	/**
	 * Dependency injection for the BusinessService
	 * @param bs This is the class being set.
	 * @return Nothing.
	 */
	@Autowired
	public void setBusinessService(BusinessInterface bs) {
		this.bs = bs;
	}
	
	/**
	 * Dependency injection for the user business service
	 * @param us
	 */
	@Autowired
	public void setUserService(UserBusinessInterface us) {
		this.us = us;
	}
	
	/**
	 * Dependency injection for the connection business service
	 * @param cs
	 */
	@Autowired
	public void setConnectionService(ConnectionBusinessInterface cs) {
		this.cs = cs;
	}
	
	/**
	 * Dependency injection for the job business service
	 * @param js
	 */
	@Autowired
	public void setJobService(JobBusinessInterface js) {
		this.js = js;
	}
	
	/**
	 * Dependency injection for the message business service
	 * @param ms
	 */
	@Autowired
	public void setMessageService(MessageBusinessInterface ms) {
		this.ms = ms;
	}
	
	/**
	 * Sets the user's theme to business which will change the appearance of their navbar and footer.
	 * It will then check to see if the user has a business profile and either sends them to the profile
	 * creator or the business dashboard.
	 * @param bus This is the business model retrieved from the view.
	 * @param session This is the session used to check if the user has a business profile.
	 * @return ModelAndView This is the new business object sent to the busProfile view.
	 */
	@RequestMapping(path = "/bus", method = RequestMethod.GET)
	public ModelAndView businessProfile(@ModelAttribute("business") Business bus, HttpSession session) {
		if(session.getAttribute("id") == null) {
			return new ModelAndView("redirect:../login/log", "user", new User());
		}
		session.setAttribute("theme", "business");
		session.setAttribute("requests", ms.getNotifications((int)session.getAttribute("id"), "connection"));
		session.setAttribute("messages", ms.getNotifications((int)session.getAttribute("id"), "busUnread"));
		if(session.getAttribute("hasBusiness") != null) {
			//Dashboard is returned if the user has a business profile
			return new ModelAndView("busDash", "business", new Business());
		}
		else {
			return new ModelAndView("busProfile", "business", new Business()); //To the creator
		}
	}
	
	/**
	 * Submits the user's business profile for insertion into the database after pushing through the business
	 * service.
	 * @param bus This is the business model grabbed from the view.
	 * @param result This is the result used to check for errors.
	 * @param session This is the session used to set the hasBusiness variable.
	 * @return ModelAndView This is the updated business object with errors being sent back to the busProfile view.
	 */
	@RequestMapping(path = "/submitBus", method = RequestMethod.POST)
	public ModelAndView submitBus(@Valid @ModelAttribute("business") Business bus, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return new ModelAndView("busProfile", "business", bus); //REturns busProfile with errors
		}
		
		bus.setUserId((int)session.getAttribute("id"));
		
		if(bs.createBusiness(bus)) {
			session.setAttribute("hasBusiness", true); //This allows the user to land at the dashboard when they got to business
			return new ModelAndView("busDash", "business", bus);
		}
		else { //Business profile failed insertion into database.
			return new ModelAndView("busProfile", "business", new Business());
		}
	}
	
	/**
	 * Sends the user to their business profile.
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/profile", method = RequestMethod.GET)
	public ModelAndView viewProfile(HttpSession session) {
		int id = (int)session.getAttribute("id");
		User user = us.findBusUser(id);
		session.setAttribute("profile",  "user");
		return new ModelAndView("busViewProfile", "user", user);
	}
	
	/**
	 * Searches the people and jobs in the database.
	 * @param search
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ModelAndView searchResults(@RequestParam String search, HttpSession session) {
		List<User> users = cs.searchPeople(search, (int)session.getAttribute("id"));
		List<Job> jobs = js.searchJobs(search);
		Map<String, Object> model = new HashMap<String, Object>(); //Two models will be passed to the search
		model.put("users", users);
		model.put("jobs", jobs);
		session.setAttribute("search", "both");
		return new ModelAndView("busSearch", "model", model);
	}
	
	/**
	 * Gives a list from 1 to 31 for a drop down in the view.
	 * @return List<Integer> This is the list of days in a month being returned.
	 */
	@ModelAttribute("dayList")
	public List<Integer> getBirthDay() {
		return cl.getBirthDay();
	}
	
	/**
	 * Returns a list of years for a dropdown in the view. I don't
	 * think anyone is 118 years old that will be registering, but 
	 * that's all right.
	 * @return List<Integer> This is a list of many years being returned.
	 */
	@ModelAttribute("yearList")
	public List<Integer> getBirthYear(){
		return cl.getBirthYear();
	}
	
	/**
	 * Returns a list of months for a dropdown in the view. Each month
	 * is given a numeric value.
	 * @return Map<Integer, String> This is a map of months->value pairs being returned.
	 */
	@ModelAttribute("monthList")
	public Map<Integer, String> getMonthList(){
		return cl.getMonths();
	}
	
	/**
	 * Returns a generic list of job types for the user to choose from as a career field.
	 * @return List<String> This is the list of different job types being returned.
	 */
	@ModelAttribute("jobList")
	public List<String> getJobList(){
		return cl.getJobList();
	}
	
	/**
	 * This method gives a list of different ethnicities to the model for the user to choose from.
	 * @return List<String> This is the list of ethnicities being returned.
	 */
	@ModelAttribute("ethList")
	public List<String> getEthList(){
		return cl.getEthList();
	}
	
	/**
	 * Returns a list of education levels for the user to choose from. None might not really be
	 * necessary for this website, but oh well.
	 * @return List<String> This is the list of education levels being returned.
	 */
	@ModelAttribute("edList")
	public List<String> getEdList(){
		return cl.getEdList();
	}
	
	/**
	 * Returns a list of state initials for the user to choose from.
	 * @return List<String> This is the list of state abbreviations being returned.
	 */
	@ModelAttribute("stateList")
	public List<String> getStates(){
		return cl.getStates();
	}
}
