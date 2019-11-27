/**
 * This controller handles the user's registration to my website and
 * also brings them to the registration page.
 * 					
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.01
 * @since   2018-11-25
 */

package com.gcu.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.gcu.business.UserBusinessInterface;
import com.gcu.model.User;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	UserBusinessInterface us;
	
	/**
	 * Dependency injection allows useage of User service without instantiating an object every time.
	 * @param us This is the class being set.
	 * @return Nothing.
	 */
	@Autowired
	public void setUserService(UserBusinessInterface us) {
		this.us = us;
	}
	
	/**
	 * Sends the user to the Registration page.
	 * @return ModelAndView This is a new User model being sent to the registration view.
	 */
	@RequestMapping(path="/reg", method=RequestMethod.GET)
	public ModelAndView Registration() {
		return new ModelAndView("registration", "user", new User());
	}
	
	/**
	 * This method checks the User model data validation and returns them to the registration page if anything is incorrect.
	 * If the user does not have JavaScript disabled in their browser, none of the validation errors should pop up because
	 * they are also checked in the browser.
	 * @param user This is the user model retrieved from the form.
	 * @param result This is the result returned to check for errors.
	 * @param session This is the session used to send error messages and save id to session.
	 * @return ModelAndView This is the updated user object grabbed from the view and either the reg or userhome view.
	 */
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public ModelAndView Register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		if(result.hasErrors()) { //User object has errors
			return new ModelAndView("registration", "user", user);
		}
		if(us.checkEmail(user)) { //Checks if the user's email is taken
			session.setAttribute("message", "Email is already taken!");
			return new ModelAndView("registration", "user", user);
		}
		if(us.checkUser(user)) { //Checks if the user's username is taken
			session.setAttribute("message2", "Username is already taken!");
			return new ModelAndView("registration", "user", user);
		}
		if(!user.getPassword().equals(user.getPassRe())) { //Ensure matching passwords. Regex checked in model
			session.setAttribute("message3", "Passwords must match!");
			return new ModelAndView("registration", "user", user);
		}
		int id = us.register(user); //Returns the id created by user registration
		if(id != 0) {
			session.setAttribute("id", id); //Sets session id for grabbing user's data later
			return new ModelAndView("userHome", "user", user);
		}
		else {
			session.setAttribute("message", "Something went wrong!"); //Database error
			return new ModelAndView("registration", "user", user);
		}
	}
}