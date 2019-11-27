/**
 * This controller is used to send the user back to their home page
 * when they are logged in. In the future, this will be used to send
 * mods and admin to their respective home pages.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.05
 * @since   2018-11-25
 */

package com.gcu.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.gcu.business.UserBusinessInterface;
import com.gcu.model.User;


@Controller
@RequestMapping("/home")
public class HomeController {
	
private UserBusinessInterface us;
	
	/**
	 * Dependency injection allows useage of User service without instantiating an object every time
	 * @param us This is the class being set to a variable.
	 * @return Nothing.
	 */
	@Autowired
	public void setUserService(UserBusinessInterface us) {
		this.us = us;
	}
	
	
	/**
	 * This method sends the user to their home page. The user's model
	 * is also sent to the home page to display their username.
	 * @param session This is the session being grabbed to updated session variables.
	 * @return ModelAndView This is the returning of the user's model and userHome view.
	 */
	@RequestMapping(path="/user", method=RequestMethod.GET)
	public ModelAndView goHome(HttpSession session) {
		if(session.getAttribute("id") == null) {
			return new ModelAndView("redirect:../login/log", "user", new User());
		}
		session.setAttribute("theme", null); //Regular red header and footer with all tabs
		return new ModelAndView("userHome", "user", us.findById((int)session.getAttribute("id")));
	}
}
