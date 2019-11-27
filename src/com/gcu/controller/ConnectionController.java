/**
 * This controller handles all things related to connections in the social platform.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-10-19
 */
package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.gcu.business.ConnectionBusinessInterface;
import com.gcu.business.MessageBusinessInterface;
import com.gcu.business.UserBusinessInterface;
import com.gcu.model.Connection;
import com.gcu.model.Message;
import com.gcu.model.User;

@Controller
@RequestMapping("/connections")
public class ConnectionController {
	private ConnectionBusinessInterface cs;
	private UserBusinessInterface us;
	private MessageBusinessInterface ms;
	
	/**
	 * Dependency injection for the connection business service.
	 * @param cs
	 */
	@Autowired
	public void setConnectionBusinessService(ConnectionBusinessInterface cs) {
		this.cs = cs;
	}
	
	/**
	 * Dependency injection for the user business service.
	 * @param us
	 */
	@Autowired
	public void setUserBusinessService(UserBusinessInterface us) {
		this.us = us;
	}
	
	/**
	 * Dependency injection for the message business service.
	 * @param ms
	 */
	@Autowired
	public void setMessageBusinessService(MessageBusinessInterface ms) {
		this.ms = ms;
	}
	
	/**
	 * Sends the user to a view of their own profile.
	 * Removed RequestMethod because this can now be a GET or POST
	 * @param id
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/view")
	public ModelAndView viewProfile(@RequestParam int id, HttpSession session) {
		User user = us.findBusUser(id);
		user.setConnection(cs.checkConn((int)session.getAttribute("id"), id));
		session.setAttribute("profile", "connection");
		return new ModelAndView("busViewProfile", "user", user);
	}
	
	/**
	 * Gets a list of the user's connections.
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/connList", method = RequestMethod.GET)
	public ModelAndView getConnections(HttpSession session) {
		List<User> users = new ArrayList<User>();
		if(ms.checkRequest((int)session.getAttribute("id"), "connection")){ //Checks if user has any connection requests.
			session.setAttribute("page", "requests"); //The connection list page will serve as a connection list + connection request hub.
			List<Message> messages = ms.getMessages((int)session.getAttribute("id"), "connection", "RECEIVER_ID"); //Gets all the user's connection requests.
			users = cs.getRequestProfiles(messages); //Gets the requestee's profiles.
		}
		else {
			session.setAttribute("page", "connections"); //Makes the page an actual connection list or lack therof.
			users = cs.getConnections((int)session.getAttribute("id")); //Gets the user's friends.
		}
		return new ModelAndView("connectionList", "users", users);
	}
	
	/**
	 * Adds the selected connection to the user's connection list.
	 * @param id
	 * @param connId
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/addConnection", method = RequestMethod.POST)
	public ModelAndView addConnection(@RequestParam int id, @RequestParam int connId, HttpSession session) {
		if(!ms.delete(id)) { //Deletes the connection request
			session.setAttribute("message", "Something went wrong!");
		}
		Connection conn = new Connection((int)session.getAttribute("id"), connId); //Grabs the Conection object.
		if(cs.addConnection(conn)) { //Adds the connection
			session.setAttribute("message1", "Added to connection list!");
		}
		
		//Connection list logic below.
		List<User> users = new ArrayList<User>();
		if(ms.checkRequest((int)session.getAttribute("id"), "connection")){
			session.setAttribute("page", "requests");
			List<Message> messages = ms.getMessages((int)session.getAttribute("id"), "connection", "RECEIVER_ID");
			users = cs.getRequestProfiles(messages);
		}
		else {
			session.setAttribute("page", "connections");
			users = cs.getConnections((int)session.getAttribute("id"));
		}
		return new ModelAndView("connectionList", "users", users);
	}
	
	/**
	 * Denies a connection request to the logged in user.
	 * @param id
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/denyRequest", method = RequestMethod.POST)
	public ModelAndView denyRequest(@RequestParam int id, HttpSession session) {
		if(!ms.delete(id)) {
			session.setAttribute("message", "Something went wrong!");
		}
		else {
			session.setAttribute("message1", "Connection request deleted!");
		}
		List<User> users = new ArrayList<User>();
		if(ms.checkRequest((int)session.getAttribute("id"), "connection")){
			session.setAttribute("page", "requests");
			List<Message> messages = ms.getMessages((int)session.getAttribute("id"), "connection", "RECEIVER_ID");
			users = cs.getRequestProfiles(messages);
		}
		else {
			session.setAttribute("page", "connections");
			users = cs.getConnections((int)session.getAttribute("id"));
		}
		
		session.setAttribute("requests", ms.getNotifications((int)session.getAttribute("id"), "connection"));
		return new ModelAndView("connectionList", "users", users);
	}
	
	/**
	 * Sends a connection request to the selected user
	 * @param id
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/sendRequest", method = RequestMethod.POST)
	public ModelAndView sendRequest(@RequestParam int id, HttpSession session) {
		Message msg = new Message((int)session.getAttribute("id"), id, -1, null, "connection");
		if(cs.sendRequest(msg)) {
			session.setAttribute("message1", "Connection request sent!");
		}
		else {
			session.setAttribute("message", "Duplicate request, you can only send one request to this person!"); //Gotta keep the DB cleannn
		}
		User user = us.findBusUser(id);
		user.setConnection(true);
		return new ModelAndView("busViewProfile", "user", user);
	}
	
	/**
	 * Removes a connection from the user's connection list.
	 * @param id
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/removeConn", method = RequestMethod.POST)
	public ModelAndView removeConnection(@RequestParam int id, HttpSession session) {
		Connection conn = new Connection((int)session.getAttribute("id"), id);
		if(cs.deleteConnection(conn)) {
			session.setAttribute("message1", "Connection has been removed!");
		}
		User user = us.findBusUser(id);
		user.setConnection(true);
		return new ModelAndView("busViewProfile", "user", user);
	}
}
