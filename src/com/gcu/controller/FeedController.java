/**
 * This controller handles all of the controlling for feed posts.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.controller;

import java.util.List;
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
import com.gcu.business.FeedBusinessInterface;
import com.gcu.business.FriendBusinessInterface;
import com.gcu.business.UserBusinessInterface;
import com.gcu.model.Feed;
import com.gcu.model.User;

@Controller
@RequestMapping("/feed")
public class FeedController {
	
	/**
	 * Dependency injection for FeedBusInt and UserBusInt + setters.
	 * Changed to private per rubric
	 */
	private FeedBusinessInterface fs;
	private FriendBusinessInterface frs;
	private UserBusinessInterface us;
	
	/**
	 * Dependency injection for the FeedBusinessService
	 * @param fs This is the class being set.
	 * @return Nothing.
	 */
	@Autowired
	public void setFeedService(FeedBusinessInterface fs) {
		this.fs = fs;
	}
	
	@Autowired
	public void setFriendService(FriendBusinessInterface frs) {
		this.frs = frs;
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
	 * This method is used to display the feed page of the user. Right now, it will
	 * only show the posts of the user logged in, but in the future it will show the
	 * posts of the user's friends.
	 * @param feed This is the new Feed object being sent to the veiw
	 * @param session This session is used to grab the user's feed objects.
	 * @return ModelAndView This is the list of feed being sent to the socialFeed view.
	 */
	@RequestMapping(path="/feed", method = RequestMethod.GET)
	public ModelAndView displayFeed(@ModelAttribute Feed feed, @ModelAttribute String vote, HttpSession session) {
		if(session.getAttribute("id") == null) {
			return new ModelAndView("redirect:../login/log", "user", new User());
		}
		session.setAttribute("profile", "feed"); //This is for returning to the right page when updating feed.
		//Grabs all user's feed. This will be changed soon to include friends and public depending on settings.
		List<Feed> feedList = fs.findUserFeed((int)session.getAttribute("id"));
		feedList = fs.setVoted((int)session.getAttribute("id"), feedList);
		return new ModelAndView("socialFeed", "feedList", feedList);
	}
	
	/**
	 * This method validates the model and then appends the feed object to the database if everything is correct.
	 * This will include the possibility of adding a picture in a coming update, but may be pushed for business portion
	 * of website.
	 * @param feed This is the new feed object being set to the modelattribute for object creation.
	 * @param result This is the result used to check for errors.
	 * @param session This is the session used to get user model and get feed list.
	 * @return ModelAndView This is the feedList being sent back to the socialFeed view.
	 */
	@RequestMapping(path="/createFeed", method=RequestMethod.POST)
	public ModelAndView postFeed(@Valid @ModelAttribute Feed feed, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			List<Feed> feedList = fs.findUserFeed((int)session.getAttribute("id"));
			return new ModelAndView("socialFeed", "feedList", feedList);
		}
		if(feed.getLink() == "") { //sets link to null if the user doesn't put anything in
			feed.setLink(null);
		}
		User user = us.findSocUser((int)session.getAttribute("id"), 0); //Gets current user's model
		String name = user.getFirstName() + " " + user.getLastName(); //Sets users name to feed
		feed.setName(name);
		feed.setUserId((int)session.getAttribute("id")); //Sets user's id to their session id
		if(fs.create(feed)) { //Creates a new feed post
			session.setAttribute("message", "Your post has been created successfully!");
		}
		else {
			//This will be changed in the future to be a red message rather than standard black text.
			session.setAttribute("message", "Something went wrong!");
		}
		
		List<Feed> feedList = fs.findUserFeed((int)session.getAttribute("id"));
		feedList = fs.setVoted((int)session.getAttribute("id"), feedList);
		return new ModelAndView("socialFeed", "feedList", feedList);
	}
	
	/**
	 * This method just deletes the selected post from the user's feed. They have to be the creator of the post, but that's
	 * all they can see right now, so it does not matter. In the future I intend to show friends' posts on their feed
	 * @param feed This is the feed model being set to the modelattribute for the view.
	 * @param id This is the id of the feed object being sent for deletion.
	 * @param session This is the session used to send feedback and get the feed list.
	 * @return ModelAndView This is the feedList being sent back to the socialFeed view.
	 */
	@RequestMapping(path="/deleteFeed", method=RequestMethod.POST)
	public ModelAndView deleteFeed(@ModelAttribute Feed feed, @RequestParam("id") int id, HttpSession session) {
		if(fs.delete(id)) { //User deletes their feed. Currently no warning message
			session.setAttribute("message3", "You have deleted your post successfully!");
		}
		else {
			session.setAttribute("message2", "Something went wrong!"); //Database error. Usually a SQLException handled by globalexchandler
		}
		int uid = (int)session.getAttribute("id");
		if(session.getAttribute("profile").equals("user")) { //This is being checked to see if the user is coming from profile page.
			User user = us.findSocUser(uid, uid);
			return new ModelAndView("viewProfile", "user", user); //Returns user to profile page.
		}
		List<Feed> feedList = fs.findUserFeed(uid);
		feedList = fs.setVoted(uid, feedList);
		return new ModelAndView("socialFeed", "feedList", feedList); //Returns user to socialFeed view.
	}
	
	/**
	 * This method was added with Milestone 5 to satisfy CRUD requirements and some jquery stuff I wanted to do personally.
	 * It sets feed to "" after because otherwise the top feed will contain the updated feed post for some reason. Post and id
	 * are passed via old school forms because <form:textarea> cannot contain a value in our version of spring and updating to 
	 * spring 5 caused many failures due to some other dependency and I couldn't figure that out.
	 * @param feed This is the feed model being set to the modelattribute for the view.
	 * @param id This is the id of the feed object being sent for update.
	 * @param post This is the feed post body being sent for update.
	 * @param session This is the session used to send feedback and get the feed list.
	 * @return ModelAndView This is the feedList being sent back to the socialFeed view.
	 */
	@RequestMapping(path="/updateFeed", method=RequestMethod.POST)
	public ModelAndView updateFeed(@ModelAttribute Feed feed, @RequestParam int id, @RequestParam("feed") String post, HttpSession session) {
		Feed newFeed = fs.findById(id); //Set to newFeed to avoid modelAttribute collision
		newFeed.setFeed(post); //Changes the post portion of the Feed object.
		if(post.length() >= 20) { //Ensures the post is atleast 20 charactesr
			if(fs.update(newFeed)) {
				session.setAttribute("message3", "Your post has been updated!");
			}
			else {
				session.setAttribute("message2", "Something went wrong!"); //Database error
			}
		}
		else {
			session.setAttribute("message2", "Your post must be atleast 20 characters!");
		}
		int uid = (int)session.getAttribute("id");
		if(session.getAttribute("profile").equals("user")) { //Added FINAL Sends user to profile if they came from there.
			User user = us.findSocUser(uid, uid);
			return new ModelAndView("viewProfile", "user", user);
		}
		
		List<Feed> feedList = fs.findUserFeed(uid); //Grabs updated feedlist
		feed.setFeed(""); //See method comment
		feedList = fs.setVoted(uid, feedList);
		return new ModelAndView("socialFeed", "feedList", feedList);
	}
	
	/**
	 * This method is called when the user presses like on a feed post. This method can create
	 * a new like or delete the existing like in the database. Users cannot switch from like to dislike
	 * currently without first deleting their like at the moment. This will likely not be changed this
	 * semester.
	 * @param feed
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(path="/likeFeed", method=RequestMethod.POST)
	public ModelAndView likeFeed(@ModelAttribute Feed feed, @RequestParam int id, HttpSession session) {
		feed = fs.findById(id); //Grabs the feed post from the DB.
		feed.setVote(fs.voted(feed.getId(), (int)session.getAttribute("id"))); //This sets the feed post as voted or unvoted
		if(feed.getVote() == null) { //New vote
			feed.setVotes(feed.getVotes() + 1);
			if(!fs.createVote(feed, (int)session.getAttribute("id"), "Like")) {
				session.setAttribute("message2", "Something went wrong!");
			}
		}
		else if(feed.getVote().equals("Like")) {
			feed.setVotes(feed.getVotes() - 1); //Removing the vote
			if(!fs.deleteVote(feed, (int)session.getAttribute("id"))){
				session.setAttribute("message2", "Something went wrong!");
			}
		}
		
		int uid = (int)session.getAttribute("id");
		if(session.getAttribute("profile").equals("user")) { //Sends user back to THEIR profile view page.
			User user = us.findSocUser(uid, uid);
			return new ModelAndView("viewProfile", "user", user);
		}
		else if(session.getAttribute("profile").equals("friend")) { //Sends user back to other person's profile page.
			User user = us.findSocUser(feed.getUserId(), uid);
			user.setFriend(frs.checkFriend(uid, feed.getUserId()));
			return new ModelAndView("viewProfile", "user", user);
		}
		List<Feed> feedList = fs.findUserFeed(uid); //Grabs updated feedlist
		feedList = fs.setVoted(uid, feedList);
		return new ModelAndView("socialFeed", "feedList", feedList);
	}
	
	/**
	 * This method is called when the user presses dislike on a feed post. This method can create a new
	 * dislike or delete the existing dislike in the database. Same system as the likes.
	 * @param feed
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(path="/dislikeFeed", method=RequestMethod.POST)
	public ModelAndView dislikeFeed(@ModelAttribute Feed feed, @RequestParam int id, HttpSession session) {
		feed = fs.findById(id);
		feed.setVote(fs.voted(feed.getId(), (int)session.getAttribute("id")));
		if(feed.getVote() == null) {
			feed.setVotes(feed.getVotes() - 1);
			if(!fs.createVote(feed, (int)session.getAttribute("id"), "Dislike")) {
				session.setAttribute("message2", "Something went wrong!");
			}
		}
		else if(feed.getVote().equals("Dislike")) {
			feed.setVotes(feed.getVotes() + 1);
			if(!fs.deleteVote(feed, (int)session.getAttribute("id"))){
				session.setAttribute("message2", "Something went wrong!");
			}
		}
		int uid = (int)session.getAttribute("id");
		if(session.getAttribute("profile").equals("user")) { //Sends the user back to their profile page.
			User user = us.findSocUser(uid, uid);
			return new ModelAndView("viewProfile", "user", user);
		}
		else if(session.getAttribute("profile").equals("friend")) { //Sends the user back to another user's profile page.
			User user = us.findSocUser(feed.getUserId(), uid);
			user.setFriend(frs.checkFriend(uid, feed.getUserId()));
			return new ModelAndView("viewProfile", "user", user);
		}
		List<Feed> feedList = fs.findUserFeed(uid); //Grabs updated feedlist
		feedList = fs.setVoted(uid, feedList);
		return new ModelAndView("socialFeed", "feedList", feedList);
	}
	
	
	
}
