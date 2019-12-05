/**
 * This class is used to give instructions to each controller when they receive
 * an exception. As of right now, only database exceptions and 500 errors + null pointers
 * are being caught here, but it may be updated in the future if required.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.utilities;

import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice //Guidelines for each controller to follow
public class GlobalExceptionHandler {

	/**
	 * This method handles all SQLExceptions and DataAccessExceptions that are caught through the application.
	 * Users will be redirected to a screen that tells them the database is currently down.
	 * @param session This is used to set the theme of the webpage.
	 * @param e This is used to print the stacktrace of the error.
	 * @return String This is returning to the databaseError view.
	 */
	@ExceptionHandler({ SQLException.class, DataAccessException.class })
	public String databaseError(HttpSession session, SQLException e) {
		session.setAttribute("theme", null);
		e.printStackTrace();
		return "databaseError";
	}

	/**
	 * This should only be reachable when the user's session has been timed out if
	 * everything is coded correctly. Otherwise, it still avoids them seeing that
	 * I'm using Spring. The way I understand it, they will only be redirected if
	 * their session times out and they try to access a page that requires a session variable.
	 * I might be wrong about this though.
	 * @param session This is used to get the id of the user and send feedback.
	 * @return String This is returning the user to home or the login page.
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500 error
	@ExceptionHandler(NullPointerException.class)
	public String timeout(HttpSession session) {
		if (session.getAttribute("id") == null) {
			//This should be the only place reachable with this method
			session.setAttribute("message", "Your session has timed out!");
			return "redirect:../login/log"; //Redirects to login page.
		}
		else {
			return "userHome";
		}
	}
}
