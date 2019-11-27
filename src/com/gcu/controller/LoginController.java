/**
 * This controller handles user login and gives them an error message if their
 * login information is incorrect.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.01
 * @since   2018-11-25
 */

package com.gcu.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.BusinessInterface;
import com.gcu.business.DatingInterface;
import com.gcu.business.SocialBusinessInterface;
import com.gcu.business.UserBusinessInterface;
import com.gcu.model.User;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private UserBusinessInterface us; //Changed to private per rubric feedback
	private SocialBusinessInterface ss;
	private BusinessInterface bs;
	private DatingInterface ds;
	
	/**
	 * Dependency injection for the UserBusinessService.
	 * @param us This is the class being set.
	 * @return Nothing.
	 */
	@Autowired
	public void setUserService(UserBusinessInterface us) {
		this.us = us;
	} 
	
	/**
	 * Dependency injection for the SocialBusinessService.
	 * @param ss This is the class being set.
	 * @return Nothing.
	 */
	@Autowired
	public void setSocialService(SocialBusinessInterface ss) {
		this.ss = ss;
	}
	
	/**
	 * Dependency injection for the BusinessService.
	 * @param bs This is the class being set.
	 * @param bs
	 */
	@Autowired
	public void setBusinessService(BusinessInterface bs) {
		this.bs = bs;
	}
	
	@Autowired
	public void setDatingService(DatingInterface ds) {
		this.ds = ds;
	}
	
	/**
	 * Points the user to the login page with no messages.
	 * @return String This returns the login view.
	 */
	@RequestMapping(path="/log", method=RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	/**
	 * This method calls the UserService and performs the required login
	 * actions there. If the user's login information is correct, the user will
	 * be sent to their home page. Otherwise, the user will be given an error
	 * message and sent back to the login page.
	 * @param email This is the user's email or username for login.
	 * @param password This is the user's password for login.
	 * @param session This is the session being used to set ID and bus/social.
	 * @return ModelAndView This is used to return the login view or userhome with the user's model.
	 */
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam String email, @RequestParam String password, HttpSession session) {
		User user = us.login(email, password); //Checks user login and returns their id if login is correct
		if(user == null) {
			session.setAttribute("message", "Username/password combination is incorrect!");
			return new ModelAndView("login", "user", new User());
		}
		
		if(ss.checkSocial(user.getId())) { //Checks if the user has a social profile
			session.setAttribute("hasSocial", true);
		}
		if(bs.checkBusiness(user.getId())) { //Checks if the user has a business profile
			session.setAttribute("hasBusiness", true);
		}
		if(ds.checkDating(user.getId())) { //Checks if the user has a dating profile
			session.setAttribute("hasDating", true);
		}
		session.setAttribute("id", user.getId()); //Sets user id for grabbing data
		session.setAttribute("access", user.getAccess()); //Sets the access level of the account logged in
		
		if(user.getAccess().equals("user")) {
			return new ModelAndView("userHome", "user", us.findById(user.getId()));
		}
		else {
			//PLACEHOLDER. THIS WILL SEND COMPANY ROLES TO A NEW PAGE WHEN LOGGING IN
			session.setAttribute("message", "Access level is: " + user.getAccess());
			return new ModelAndView("login", "user", new User());
		}
	}
	
	/**
	 * This redirection sends the user to the login page with a message telling them why they
	 * were sent there.
	 * @param session This is used to send feedback to the user.
	 * @return String This is used to send the user to the login page.
	 */
	@RequestMapping(path="/redirect", method=RequestMethod.GET)
	public String redirect(HttpSession session) {
		session.setAttribute("message", "You must be logged in to do that!");
		return "login";
	}
}