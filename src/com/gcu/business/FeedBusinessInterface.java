/**
 * This interface lays forth the method guidelines for the FeedBusinessService class.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.business;

import java.util.List;
import com.gcu.model.Feed;

public interface FeedBusinessInterface {
	
	/**
	 * This method calls the DAO to create a new Feed object.
	 * @param t This is the feed object being created.
	 * @return boolean This is the result of the database insertion.
	 */
	public boolean create(Feed t);
	
	/**
	 * This method gets a user's feed objects from the database.
	 * @param id This is the user's ID to grab feed objects from.
	 * @return List<Feed> This is the list of feed objects returned.
	 */
	public List<Feed> findUserFeed(int id);
	
	/**
	 * This method calls the DAO to delete the Feed object.
	 * @param id This is the feed's ID being deleted.
	 * @return boolean This is the result of the database object deletion.
	 */
	public boolean delete(int id);
	
	/**
	 * This method calls the DAO to update the Feed object.
	 * @param t This is the feed's ID being updated.
	 * @return boolean This is the result of the database object update.
	 */
	public boolean update(Feed t);
	
	/**
	 * This method finds a Feed object by its specific id using the DAO.
	 * @param id This is the ID of the specific feed object.
	 * @return Feed this is the Feed object returned.
	 */
	public Feed findById(int id);

	/**
	 * This method sets the feed post to voted by the user.
	 * @param fId This is the id of the feed.
	 * @param uId This is the id of the user being checked.
	 * @return String This returns whether the user liked or disliked the post.
	 */
	public String voted(int fId, int uId);

	/**
	 * This method sets whether or not the user has voted on the post and which way
	 * they voted.
	 * @param uid This is the id of the user being checked.
	 * @param feedList This is the list of feed being evaluated.
	 * @return List<Feed> This is the update list of feed being returned.
	 */
	public List<Feed> setVoted(int uid, List<Feed> feedList);

	/**
	 * This method calls the DAO to create the new vote in the database.
	 * @param t This is the feed post being voted on.
	 * @param uId This is the user id of the user voting.
	 * @param vote This is whether the vote is a like or dislike.
	 * @return boolean This is whether or not the vote was put into the database.
	 */
	public boolean createVote(Feed t, int uId, String vote);

	/**
	 * This method calls the DAO to delete the vote.
	 * @param t Feed post being updated.
	 * @param uId User id of the person updating their vote.
	 * @return boolean This is whether or not the vote was put into the database.
	 */
	public boolean deleteVote(Feed t, int uId);
}
