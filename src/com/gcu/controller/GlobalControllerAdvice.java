/**
 * This class is being used to retrieve the notification counts for each platform in Apoco.
 * These methods will be called prior to any other controller methods when the application is being run.
 * This may be reworked to get all types of messages/notifications if the global dashboard is implemented.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-11-23
 */

package com.gcu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.gcu.business.MessageBusinessInterface;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	private MessageBusinessInterface ms;
	private int id;
	private int requests;
	private int messages;
	
	/**
	 * Dependency injection for the message service
	 * @param ms
	 */
	@Autowired
	public void setMessageService(MessageBusinessInterface ms) {
		this.ms = ms;
	}
	
	/**
	 * This method calls the business service to retrieve notifications and then appends
	 * them to the session. Will try to find a way to perform this action after other controllers
	 * are called to prevent erroneous database calls.
	 * @param session
	 */
	@ModelAttribute
	public void getNotifications(HttpSession session) {
		if(session.getAttribute("id") != null) {
			id = (int)session.getAttribute("id");
			if(session.getAttribute("theme") == "social") {
				requests = ms.getNotifications(id, "request");
				messages = ms.getNotifications(id, "socUnread");
			}
			else if(session.getAttribute("theme") == "business") {
				requests = ms.getNotifications(id, "connection");
				messages = ms.getNotifications(id, "busUnread");
			}
			session.setAttribute("requests", requests);
			session.setAttribute("messages", messages);
		}
		
	}
}
