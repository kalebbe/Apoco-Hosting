/**
 * This interface defines the methods required in the FriendsBusinessService class.
 *
 *
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-12-10
 */
package com.gcu.business;

import java.util.List;

import com.gcu.model.Friend;
import com.gcu.model.Message;
import com.gcu.model.User;

public interface FriendBusinessInterface {

	/**
	 * This method returns a list of people based upon the search results.
	 * @param search This is the search term.
	 * @return List<User> This is the list of Users.
	 */
	public List<User> searchPeople(String search, int id);

	/**
	 * This method calls the dao to add a friend to the database.
	 * @param t This is the friend being added.
	 * @return boolean Success or failure of creation.
	 */
	public boolean addFriend(Friend t);

	/**
	 * This method calls the dao to delete a friend from the database.
	 * @param t This is the friend being deleted.
	 * @return Success or failure of deletion.
	 */
	public boolean deleteFriend(Friend t);

	/**
	 * This method checks each user to see if they have a social profile.
	 * @param users This is the list of users being checked.
	 * @param type This is whether the search is for email or names.
	 * @return List<User> This is the list of users returned that have a profile.
	 */
	public List<User> checkProfiles(List<User> users, String type);

	/**
	 * This method gets all of the friends of a user.
	 * @param id Id of the user checking all his 5 friends...
	 * @return List<User> The actual list.. impressive.
	 */
	public List<User> getFriends(int id);

	/**
	 * This method checks if that rando is really a friend.
	 * @param userId Id of logged in user.
	 * @param friendId Id of suspectttt.
	 * @return boolean Well this one is obvious.
	 */
	public boolean checkFriend(int userId, int friendId);

	/**
	 * This method sends that's coveted friend request.
	 * @param t Friend requests are actually messages.. who'd've thunk.
	 * @return boolean Whether or not it worked.
	 */
	public boolean sendRequest(Message t);

	/**
	 * This method gets the profiles of them dudes sending requests.
	 * @param msg List of friend request messages.
	 * @return List<User> Their profiles duddeeeee.
	 */
	public List<User> getRequestProfiles(List<Message> msg);
}
