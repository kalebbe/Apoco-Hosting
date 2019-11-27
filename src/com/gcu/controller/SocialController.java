/**
 * This controller is used to handle most things that are involved with the
 * social service in this project. Will be expanded in the future for more
 * social functions.
 * 
 * 
 * @authors Kaleb Eberhart, Kaleb Eberhart
 * @version 1.01
 * @since   2018-11-25
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

import com.gcu.business.MessageBusinessInterface;
import com.gcu.business.SocialBusinessInterface;
import com.gcu.business.UserBusinessInterface;
import com.gcu.model.Social;
import com.gcu.model.User;
import com.gcu.utilities.ControllerLists;

@Controller
@RequestMapping("/social")
public class SocialController {

	private SocialBusinessInterface ss;
	private UserBusinessInterface us;
	private MessageBusinessInterface ms;
	private ControllerLists cl;
	
	/**
	 * Dependency injection for the ControllerLists
	 * @param cl
	 */
	@Autowired
	public void setControllerList(ControllerLists cl) {
		this.cl = cl;
	}

	/**
	 * Dependency injection for the SocialBusinessService
	 * @param ss This is the class being set.
	 * @return Nothing.
	 */
	@Autowired
	public void setProfileService(SocialBusinessInterface ss) {
		this.ss = ss;
	}

	/**
	 * Dependency injection for the UserBusinessService
	 * @param us This is the class being set.
	 * @return Nothing.
	 */
	@Autowired
	public void setUserService(UserBusinessInterface us) {
		this.us = us;
	}
	
	/**
	 * Dependency injection for the message business service.
	 * @param ms
	 */
	@Autowired
	public void messageBusinessService(MessageBusinessInterface ms) {
		this.ms = ms;
	}
	
	/**
	 * Sets the user's theme to social which changes their navbar and footer to green. It then checks if the
	 * user has a profile and then either sends them to the creator or the dashboard.
	 * @param social This is the social model retrieved from the view.
	 * @param session This is the session used to check if the user has a social profile.
	 * @return ModelAndView This is the new social object sent to the socialprofile view.
	 */
	@RequestMapping(path = "/social", method = RequestMethod.GET)
	public ModelAndView socialProfile(@ModelAttribute("social") Social social, HttpSession session) {
		if(session.getAttribute("id") == null) {
			return new ModelAndView("redirect:../login/log", "user", new User());
		}
		session.setAttribute("theme", "social"); //Makes header and footer of page green and removes social link
		
		//Being set here instead of controller advice because controller advice happens before this
		session.setAttribute("requests", ms.getNotifications((int)session.getAttribute("id"), "request"));
		session.setAttribute("messages", ms.getNotifications((int)session.getAttribute("id"), "socUnread"));
		if(session.getAttribute("hasSocial") != null) {
			//Returns the dashboard if the user has a social profile
			return new ModelAndView("socialDash", "social", new Social()); 
		}
		else {
			//Pushes user to social creator if they don't have a social profile
			return new ModelAndView("socialProfile", "social", new Social()); 
		}
	}
	
	/**
	 * This method is being used to send the user to their own profile.
	 * @param session The session for changing profile in session.
	 * @return ModelAndView The viewProfile view with its user model.
	 */
	@RequestMapping(path = "/profile", method = RequestMethod.GET)
	public ModelAndView viewProfile(HttpSession session) {
		int id = (int)session.getAttribute("id");
		User user = us.findSocUser(id, id);
		session.setAttribute("profile", "user");
		return new ModelAndView("viewProfile", "user", user);
	}
	
	/**
	 * Used to load the games tab of the social service. Games may receive their own controller
	 * in the future, but right now there is only one game, so it doesn't seem worth
	 * @return String This is the playGames view being returned.
	 */
	@RequestMapping(path = "/games", method = RequestMethod.GET)
	public String getGames(HttpSession session) {
		if(session.getAttribute("id") == null) {
			return "redirect:../login/log";
		}
		return "playGames"; //No model required
	}

	/**
	 * Submits the user's social profile for entrance into the database after going through the profile service.
	 * The next milestone will likely have pictures in here as well.
	 * @param social This is the social model grabbed from the view.
	 * @param result This is the result used to check for errors.
	 * @param session This is the session used to set the hasSocial variable.
	 * @return ModelAndView This is the updated social object with errors being sent back to the socialProfile view.
	 */
	@RequestMapping(path = "/submitSocial", method = RequestMethod.POST)
	public ModelAndView submitSocial(@Valid @ModelAttribute("social") Social social, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			return new ModelAndView("socialProfile", "social", social); //Returns socialProfile with errors
		}
		social.setUserId((int)session.getAttribute("id"));
		if(ss.createSocial(social)) {
			session.setAttribute("hasSocial", true); //This allows the user to land at the dashboard when they go to social
			return new ModelAndView("socialDash", "social", social);
		}
		else { //Social profile failed insertion into database.
			return new ModelAndView("socialProfile", "social", new Social());
		}
		
		/**
		 * Being saved for future milestone usage
		 * File dir = new File("F:Workspaces/CST341/Apoco/WebContent/assets/img/social/" + (String) session.getAttribute("userId")); dir.mkdirs(); try { mpf.transferTo(dir); }
		 * catch (IllegalStateException | IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); System.out.println(e); }
		 */		
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
	 * Returns a list of education levels for the user to choose from. None might not really be
	 * necessary for this website, but oh well.
	 * @return List<String> This is the list of education levels being returned.
	 */
	@ModelAttribute("edList")
	public List<String> getEdList(){
		return cl.getEdList();
	}
	
	/**
	 * Returns a list of relationship statuses for the user to choose from. Couldn't think of any others
	 * that wouldn't be troll.
	 * @return List<String> This is the list of different relationship statuses being returned.
	 */
	@ModelAttribute("statusList")
	public List<String> getStatList(){
		return cl.getStatList();
	}
	
	/**
	 * Returns a list of state initials for the user to choose from.
	 * @return List<String> This is the list of different state abbreviations being returned.
	 */
	@ModelAttribute("stateList")
	public List<String> getStates(){
		return cl.getStates();
	}
}
