/**
 * This model is used for user feed posts. This is pretty simple and
 * there isn't much data validation here either.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-11-25
 */
package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Feed {
	@NotNull(message = "You must choose a privacy option!")
	private String privacy;
	@NotNull(message = "Your post cannot be null!")
	@Size(min = 20, max = 5000, message="Your post must have more than 20 characters!")
	private String feed;
	private String name;
	private String link;
	private int userId;
	private int id;
	private int votes;
	private String vote;
	
	/**
	 * Getter for the userId variable.
	 * @return int This is the user id of the feed profile's owner.
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Setter for the userId variable.
	 * @param userId This is the new user id for the feed object.
	 * @return Nothing.
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Getter for the id variable.
	 * @return int This is the id of the feed object.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Getter for the link variable.
	 * @return String This is the link of the feed object.
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Setter for the link variable.
	 * @param link This is the new link for the feed object.
	 * @return Nothing.
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Setter for the id variable.
	 * @param id This is the new id for the feed object.
	 * @return Nothing.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter for the name variable.
	 * @return String This is the name variable of the feed object.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for the name variable.
	 * @param name This is the new name for the feed object.
	 * @return Nothing.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for the privacy boolean.
	 * @return boolean This is the privacy variable of the feed object.
	 */
	public String getPrivacy() {
		return privacy;
	}
	
	/**
	 * Setter for the privacy boolean.
	 * @param privacy This is the new privacy value of the feed object.
	 * @return Nothing.
	 */
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	
	/**
	 * Getter for the feed variable.
	 * @return String This is the feed variable of the feed object.
	 */
	public String getFeed() {
		return feed;
	}
	
	/**
	 * Setter for the feed variable.
	 * @param feed This is the new feed value of the feed object.
	 * @return Nothing.
	 */
	public void setFeed(String feed) {
		this.feed = feed;
	}
	
	/**
	 * This gets the number of votes for the post.
	 * @return int This is the number of votes returned.
	 */
	public int getVotes() {
		return votes;
	}

	/**
	 * This method sets the number of votes for the post.
	 * @param votes This is the new number of votes.
	 * @return Nothing.
	 */
	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	/**
	 * This gets the vote given by the user on a feed post. Different for every user.
	 * @return String This is either like or dislike.
	 */
	public String getVote() {
		return vote;
	}

	/**
	 * This sets the vote for a certain user on a feed post. Different for every user.
	 * @param vote This is the new vote.
	 * @return Nothing.
	 */
	public void setVote(String vote) {
		this.vote = vote;
	}
	
	/**
	 * Default constructor to give default values to the model
	 */
	public Feed() {
		this.link = "";
		this.privacy = "";
		this.feed = "";
		this.name = "";
		this.id = 0;
		this.userId = 0;
		this.votes = 0;
	}
	
	/**
	 * Non-standard constructor used to set model manually from database.
	 * Done to allow for reading data from database and displaying in view.
	 * @param id This is the id of the feed object.
	 * @param userId This is the user id of the creator of the feed object.
	 * @param feed This is the actual feed post of the feed object.
	 * @param privacy This is the privacy of the feed object.
	 * @param name This is the name of the creator of the feed object.
	 * @param link This is the link attached to the feed post.
	 * @param votes This is the number of votes the post has.
	 */
	public Feed(int id, int userId, String feed, String privacy, String name, String link, int votes) {
		this.id = id;
		this.votes = votes;
		this.feed = feed;
		this.privacy = privacy;
		this.name = name;
		this.link = link;
		this.userId = userId;
	}	
}
