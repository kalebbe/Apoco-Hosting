/**
 * This class is used to search for friends and will be used to add and delete friends 
 * in the final update.
 *
 *
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-12-10
 */

package com.gcu.business;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import com.gcu.data.FriendDAO;
import com.gcu.data.UserDAO;
import com.gcu.data.SocialDAO;
import com.gcu.model.Friend;
import com.gcu.model.Message;
import com.gcu.model.Social;
import com.gcu.model.User;
import com.gcu.data.MessageDAO;

public class FriendBusinessService implements FriendBusinessInterface {
	@Autowired
	private FriendDAO dao;
	@Autowired
	private UserDAO uDao;
	@Autowired
	private SocialDAO sDao;
	@Autowired
	private MessageDAO mDao;

	/**
	 * This method is used to search for profiles based upon name or email.
	 * @param search This is the search term being checked.
	 * @return List<User> This is the list of search results.
	 */
	@Override
	public List<User> searchPeople(String search, int id) {
		try {
			InternetAddress email = new InternetAddress(search);
			email.validate(); //Checks to see if the search term is an email address
			List<User> users = new ArrayList<User>();
			if (uDao.searchEmail(search, id) == null) { //Failed search
				return null;
			}
			users.add(uDao.searchEmail(search, id)); //Adds the user to the search list
			users = checkProfiles(users, "email"); //Checks to make sure the user has a social profile
			return users;
		} catch (AddressException e) {
			List<User> users = uDao.searchUsers(search, id);
			return checkProfiles(users, "other");
		}
	}
	
	/**
	 * This method is used to retrieve all the friends of the logged in user.
	 * @param id This is the id of the logged in user.
	 * @return List<User> This is obviously the list of friends.
	 */
	@Override
	public List<User> getFriends(int id){
		List<Friend> friends = dao.findAll(id);
		List<User> users = new ArrayList<User>();
		for(Friend f : friends) {
			User user = uDao.findById(f.getFriendId()); //Getting the user profile for each friend
			Social soc = sDao.findById(f.getFriendId()); //Getting the social profile for each friend
			//Generate a localdate of their birthday
			LocalDate birthDate = LocalDate.of(soc.getBirthYear(), soc.getBirthMonth(), soc.getBirthDay());
			//Calculates the difference between their bday and current day to get age.
			soc.setAge(Period.between(birthDate, LocalDate.now()).getYears()); 
			user.setSocial(soc);
			users.add(user);
		}
		return users;
	}
	
	/**
	 * This method is used to get all of the profiles of the user's who have sent the current user a friend
	 * request.
	 * @param msg This is the list of messages (read: friend requests).
	 * @return List<User> This is the list of profiles returned.
	 */
	@Override
	public List<User> getRequestProfiles(List<Message> msg){
		List<User> users = new ArrayList<User>();
		for(Message m : msg) {
			User user = uDao.findById(m.getSenderId()); //Gets the user profile of each message sender
			Social soc = sDao.findById(m.getSenderId()); //Gets the social profile of each message sender
			LocalDate birthDate = LocalDate.of(soc.getBirthYear(), soc.getBirthMonth(), soc.getBirthDay());
			soc.setAge(Period.between(birthDate, LocalDate.now()).getYears());
			user.setSocial(soc); //Attaches social to the user object
			user.setMessageId(m.getId()); //This is used to later delete the friend request.
			users.add(user);
		}
		return users;
		
	}

	/**
	 * This method is for checking to see if the searched users have a social profile.
	 * @param users This is the list of users being checked.
	 * @param type This is whether the search is an email or otherwise.
	 * @return List<User> This is the list of users returned that have a profile.
	 */
	@Override
	public List<User> checkProfiles(List<User> users, String type) {
		List<User> newUsers = new ArrayList<User>();
		for (User u : users) { //foreach for the userlist
			if (!type.equals("email")) { //Email overrules the privacy.
				if (sDao.getPrivacy(u.getId()) == 0) {
					if(sDao.findById(u.getId()) != null) {
						Social soc = sDao.findById(u.getId());
						//Calculate age of user and set to social profile.
						LocalDate birthDate = LocalDate.of(soc.getBirthYear(), soc.getBirthMonth(), soc.getBirthDay());
						soc.setAge(Period.between(birthDate, LocalDate.now()).getYears());
						u.setSocial(soc);
						newUsers.add(u);
					}
				}
			}
			else { //Goes here if it's an email search. Has to be exact match.
				if(sDao.findById(u.getId()) != null) {
					Social soc = sDao.findById(u.getId());
					LocalDate birthDate = LocalDate.of(soc.getBirthYear(), soc.getBirthMonth(), soc.getBirthDay());
					soc.setAge(Period.between(birthDate, LocalDate.now()).getYears());
					u.setSocial(soc);
					newUsers.add(u);
				}
			}
		}
		return newUsers;
	}

	/**
	 * This method calls the DAO to add a new friend to the database. Currently not in use.
	 * @param t This is the friend being added to the database.
	 * @return boolean This is whether or not the transaction was successful.
	 */
	@Override
	public boolean addFriend(Friend t) {
		Friend friend = new Friend(t.getFriendId(), t.getUserId()); //Creating a friend object for the other party
		if(!dao.create(friend)) { //Inserts a friend object for the other dude too.
			return false;
		}
		return dao.create(t); //Creates a friend object for this dude.
	}

	/**
	 * This method calls the DAO to delete a friend from the database. Currently not in use.
	 * @param t This is the friend being deleted.
	 * @return boolean This is whether or not the transaction was successful.
	 */
	@Override
	public boolean deleteFriend(Friend t) {
		//This is being done to delete this friend from the other's friend's list as well.
		Friend friend = new Friend(t.getFriendId(), t.getUserId());
		if(!dao.delete(friend)) { //Deletes friend object for both parties.
			return false;
		}
		return dao.delete(t);
	}
	
	/**
	 * This method calls the dao to see if a certain user is a friend of current user or not.
	 * @param userId This is the id of the current user.
	 * @param friendId This is the id of the suspected friend.
	 */
	@Override
	public boolean checkFriend(int userId, int friendId) {
		return dao.checkFriend(userId, friendId);
	}
	
	/**
	 * This method calls the dao to see if the user has already sent a request then creates a new one
	 * if they have not.
	 * @param t This is the message object being created for the request.
	 * @return boolean This is whether or not the request has been created.
	 */
	@Override
	public boolean sendRequest(Message t) {
		if(mDao.checkRequest(t.getSenderId(), t.getReceiverId(), "request")) { //Rejected, stop sending so many requests dudeeee.
			return false;
		}
		return mDao.create(t);
	}
}
