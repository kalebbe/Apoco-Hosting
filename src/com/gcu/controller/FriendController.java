/**
 * This controller is used to handle all things relating to friends in the social platform.
 * 
 *
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-12-10
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
import com.gcu.business.FriendBusinessInterface;
import com.gcu.business.MessageBusinessInterface;
import com.gcu.business.UserBusinessInterface;
import com.gcu.model.Friend;
import com.gcu.model.Message;
import com.gcu.model.User;

@Controller
@RequestMapping("/friends")
public class FriendController {
	private FriendBusinessInterface fs;
	private UserBusinessInterface us;
	private MessageBusinessInterface ms;

	/**
	 * Dependency injection that instantiates allows me to get the FriendBusinessService without
	 * instantiating the object the normal way.
	 * @param fs This is the FriendBusinessInterface being set.
	 * @return Nothing.
	 */
	@Autowired
	public void setFriendBusinessService(FriendBusinessInterface fs) {
		this.fs = fs;
	}
	
	/**
	 * Dependency injection that retrieves the user business bean.
	 * @param us The interface being used.
	 * @return Nothing.
	 */
	@Autowired
	public void setUserBusinessService(UserBusinessInterface us) {
		this.us = us;
	}
	
	/**
	 * Dependency injection that retrieves the messagie business bean.
	 * @param ms The Message interface being used.
	 * @return Nothing.
	 */
	@Autowired
	public void setMessageBusinessService(MessageBusinessInterface ms) {
		this.ms = ms;
	}

	/**
	 * This method is used to search the database for users with a social profile based upon certain search terms.
	 * @param search This is the search term.
	 * @param session This is the session to set the page type.
	 * @return ModelAndView This is the view being forarded to and the models.
	 */
	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ModelAndView searchResults(@RequestParam String search, HttpSession session) {
		List<User> users = fs.searchPeople(search, (int)session.getAttribute("id"));
		session.setAttribute("page", "search"); //This view has multiple purposes, this allows me to identify which one.
		return new ModelAndView("friendList", "users", users);
	}
	
	/**
	 * This method loads a user's profile for the current user to view.
	 * Removed RequestMethod because this can now use POST and GET.
	 * @param id The id of the user being viewed.
	 * @param session
	 * @return ModelAndView ... the viewProfile view and user model.
	 */
	@RequestMapping(path = "/view")
	public ModelAndView viewProfile(@RequestParam int id, HttpSession session) {
		User user = us.findSocUser(id, (int)session.getAttribute("id"));
		user.setFriend(fs.checkFriend((int)session.getAttribute("id"), id)); //Sets whether this user is FRIEND or FOE
		session.setAttribute("profile", "friend"); //This view also serves multiple purposes. This one is for another user's profile.
		return new ModelAndView("viewProfile", "user", user);
	}
	
	/**
	 * This method is used to get a user's friend list (or lack therof).
	 * @param session We all know what this is used for.
	 * @return ModelAndView The friendList view and its list of friends.
	 */
	@RequestMapping(path = "/friendList", method = RequestMethod.GET)
	public ModelAndView getFriends(HttpSession session) {
		List<User> users = new ArrayList<User>(); //Instantiated list of users
		if(ms.checkRequest((int)session.getAttribute("id"), "request")){ //Checks if user has any friend requests.
			session.setAttribute("page", "requests"); //Again, multiple purposes. This one is for a requests page.
			List<Message> messages = ms.getMessages((int)session.getAttribute("id"), "request", "RECEIVER_ID"); //Gets all the user's friend requests.
			users = fs.getRequestProfiles(messages); //Gets the requestee's profiles.
		}
		else {
			session.setAttribute("page", "friends"); //Makes the page an actual friend list or again lack therof.
			users = fs.getFriends((int)session.getAttribute("id")); //Gets the user's friends.
		}
		return new ModelAndView("friendList", "users", users); //Same view no matter what. Multiple purposes!@!!!!@@!!!
	}
	
	/**
	 * This method is used to actually add a friend. Must be the confirmation of a request to get here.
	 * @param id
	 * @param friendId
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/addFriend", method = RequestMethod.POST)
	public ModelAndView addFriend(@RequestParam int id, @RequestParam int friendId, HttpSession session) {
		if(!ms.delete(id)) { //Deletes the friend request... or fails, I guess.
			session.setAttribute("message", "Something went wrong!");
		}
		Friend friend = new Friend((int)session.getAttribute("id"), friendId); //Grabs the friend object.
		if(fs.addFriend(friend)) { //Adds the friend
			session.setAttribute("message1", "Added to friend's list!");
		}
		
		//Friend's list logic below.. Already commented this above thoroughly.
		List<User> users = new ArrayList<User>();
		if(ms.checkRequest((int)session.getAttribute("id"), "request")){
			session.setAttribute("page", "requests");
			List<Message> messages = ms.getMessages((int)session.getAttribute("id"), "request", "RECEIVER_ID");
			users = fs.getRequestProfiles(messages);
		}
		else {
			session.setAttribute("page", "friends");
			users = fs.getFriends((int)session.getAttribute("id"));
		}
		return new ModelAndView("friendList", "users", users);
	}
	
	/**
	 * This method allows a user to reject that creep's friend request!
	 * @param id Id of the message (read: friend request).
	 * @param session Just no.
	 * @return ModelAndView Why.
	 */
	@RequestMapping(path = "/denyRequest", method = RequestMethod.POST)
	public ModelAndView denyRequest(@RequestParam int id, HttpSession session) {
		//This is literally the flip side of the addFriend method. I will not be commenting this throughout again.
		if(!ms.delete(id)) {
			session.setAttribute("message", "Something went wrong!");
		}
		else {
			session.setAttribute("message1", "Friend request deleted!");
		}
		List<User> users = new ArrayList<User>();
		if(ms.checkRequest((int)session.getAttribute("id"), "request")){
			session.setAttribute("page", "requests");
			List<Message> messages = ms.getMessages((int)session.getAttribute("id"), "request", "RECEIVER_ID");
			users = fs.getRequestProfiles(messages);
		}
		else {
			session.setAttribute("page", "friends");
			users = fs.getFriends((int)session.getAttribute("id"));
		}
		
		session.setAttribute("requests", ms.getNotifications((int)session.getAttribute("id"), "request"));
		return new ModelAndView("friendList", "users", users);
	}
	
	/**
	 * This method is used when a user wants to send a friend request to another dude.
	 * @param id Id of the dude being requested.
	 * @param session The session mannnn.
	 * @return ModelAndView So glad my job entails playing with models.
	 */
	@RequestMapping(path = "/sendRequest", method = RequestMethod.POST)
	public ModelAndView sendRequest(@RequestParam int id, HttpSession session) {
		Message msg = new Message((int)session.getAttribute("id"), id, -1, null, "request");
		if(fs.sendRequest(msg)) {
			session.setAttribute("message1", "Friend request sent!"); //Congrats you got through. They'll deny it.
		}
		else {
			session.setAttribute("message", "Duplicate request, you can only send one request to this person!"); //Gotta keep the DB cleannn
		}
		User user = us.findSocUser(id, (int)session.getAttribute("id"));
		user.setFriend(true); //If they're sending a request, they're obv not friend yet. Saving db calls here. Ignore that this is true. Shh.
		return new ModelAndView("viewProfile", "user", user);
	}
	
	/**
	 * This method is to remove that annoying friend from your friend's list.
	 * @param id The id of the sucker.
	 * @param session Man that Jeff Sessions guy amiright?
	 * @return ModelAndView The profile view and the user model.
	 */
	@RequestMapping(path = "/removeFriend", method = RequestMethod.POST)
	public ModelAndView removeFriend(@RequestParam int id, HttpSession session) {
		Friend friend = new Friend((int)session.getAttribute("id"), id); //Gotta create that friend to delete him. Heart breaking.
		if(fs.deleteFriend(friend)) {
			session.setAttribute("message1", "Friend has been removed!");
		}
		User user = us.findSocUser(id, (int)session.getAttribute("id"));
		user.setFriend(true);
		return new ModelAndView("viewProfile", "user", user);
	}
}
