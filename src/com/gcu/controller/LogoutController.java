/**
 * This controller handles the user logout and deletes all of the session
 * information.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.01
 * @since   2018-11-25
 */

package com.gcu.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	/**
	 * Clears the session and sends user to the login page.
	 * @param session This is the session being crabbed, so it can be cleared.
	 * @return String This is the view being returned through redirect.
	 */
	@RequestMapping(path="/log", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate(); //Clears session
		return "redirect:../login/log"; //Redirects user
	}
}
