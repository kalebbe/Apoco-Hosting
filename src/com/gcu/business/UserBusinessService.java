/**
 * This class handles the business side of user login and registration. It also uses the
 * BCrypt class to hash passwords and check the hash upon login.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.2
 * @since   2018-11-25
 */

package com.gcu.business;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.BusinessDAO;
import com.gcu.data.DatingDAO;
import com.gcu.data.FeedDAO;
import com.gcu.data.SocialDAO;
import com.gcu.data.UserDAO;
import com.gcu.model.Business;
import com.gcu.model.Dating;
import com.gcu.model.Feed;
import com.gcu.model.Social;
import com.gcu.model.User;

public class UserBusinessService implements UserBusinessInterface {
	@Autowired
	private UserDAO dao;

	@Autowired
	private FeedDAO fDAO;

	@Autowired
	private SocialDAO sDAO;
	
	@Autowired
	private BusinessDAO bDAO;
	
	@Autowired
	private DatingDAO dDAO;

	private FeedBusinessInterface fs;

	/**
	 * Dependency Injection for the feed business service
	 * @param fs
	 */
	@Autowired
	public void setFeedService(FeedBusinessInterface fs) {
		this.fs = fs;
	}

	/**
	 * This method hashes the user's password then calls the DAO to create a new
	 * user in the database. Registration logic is now separated into multiple
	 * methods.
	 * 
	 * @param t This is the User object being processed and sent to the database.
	 * @return int This is the id of the newly created user being inserted. 0 if
	 *         failure.
	 */
	@Override
	public int register(User t) {
		String hashPass = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt()); // Takes the plain text pass and encrypts it
		t.setPassword(hashPass); // Updating the model object for database insertion
		if (dao.create(t)) { // creates a new
			return dao.getId(t.getEmail()); // Returns the ID of the newly created user for session capture
		} else
			return 0; // 0 tells me that the user creation has failed
	}

	/**
	 * This method gets the user's ID from the database if their username or email
	 * exists otherwise returns a 0. It then calls the DAO to check the validity of
	 * the password
	 * 
	 * @param login    This is the username or email of the user attempting login.
	 * @param password This is the password of the user attempting login.
	 * @return int This is the id of the logged in user or 0 if failure.
	 */
	@Override
	public User login(String login, String password) {
		int id = dao.getId(login); // Will return ID # or 0 if no match
		if (id == 0) { // Returns failed login message in controller
			return null;
		}
		
		return checkPass(password, id);
	}

	/**
	 * This method calls the UserDAO to update the user's password as long as they
	 * pass the required checks. User's password must have a letter and number as
	 * well as being 8 characters long. This method also uses BCrypt to match the
	 * user's input to the currently hashed password in the database.
	 * 
	 * @param t This is the User who's password is attempting update.
	 * @return boolean This is the used to give feedback on password failure.
	 */
	@Override
	public boolean changePass(User t) {
		if (t.getPassword().length() < 8
				|| !t.getPassword().matches("^([0-9]+[a-zA-Z]+|[a-zA-Z]+[0-9]+)[0-9a-zA-Z]*$")) {
			return false; // Returns for error message if the user's pass doesn't match regex and length
		}
		String hashPass = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt()); // This hashes the user's new password
		t.setPassword(hashPass); // Updates User model password for database insertion.
		if (dao.update(t)) {
			return true;
		} else
			return false; // Database failure. Currently no specific error message
	}

	/**
	 * This method is used to check the user's input password with the password in
	 * the database. This was separated in Milestone 5 for the purpose of specific
	 * error messaging in the controller
	 * 
	 * @param pass This is the password being checked to match with the old
	 *             password.
	 * @param id   This is the id of the user who is checking their password.
	 * @return boolean This tells the user if their password matched or did not.
	 */
	@Override
	public User checkPass(String pass, int id) {
		User user = dao.findById(id); // Grabs User object with ID

		if (BCrypt.checkpw(pass, user.getPassword())) { // Checks user's pass with database password for match
			return user;
		} else
			return null;
	}

	/**
	 * This method was separated with the others for milestone 5 error messaging
	 * purposes. Any time the user changes their first name, any feed posts created
	 * by them are then updated in the database to match their new name. In the
	 * future, turning off auto commit database changes for the purpose of not
	 * submitting database changes if one fails would be vital for this method and
	 * the updateLast method.
	 * 
	 * @param t This is the user attempting first name change.
	 * @return boolean This returns false if the users' first name doesn't match
	 *         requirements.
	 */
	@Override
	public boolean updateFirst(User t) {
		if (t.getFirstName().length() < 2 || t.getFirstName().length() > 30) { // Small check to see if betw 4-30 char
			return false;
		} else {
			if (dao.update(t)) { // User's name is now updated.
				String name = t.getFirstName() + " " + t.getLastName(); // Connects name for feed update
				List<Feed> feedList = fDAO.findUserFeed(t.getId()); // Grabs all of the feed posts made by this user
				for (Feed feed : feedList) { // Rotating through every Feed object in feedList
					feed.setName(name); // Sets feed name in object
					fDAO.update(feed); // Updates the feed with the new name
				}
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * This method does the same thing as the updateFirst method for the last name.
	 * I'm going to avoid commenting this up because it is literally the same as the
	 * firstName update for the last name.
	 * 
	 * @param t This is the user attempting last name change.
	 * @return boolean This returns false if the user's last name doesn't match
	 *         requirements.
	 */
	@Override
	public boolean updateLast(User t) {
		if (t.getLastName().length() < 2 || t.getLastName().length() > 30) {
			return false;
		} else {
			String name = t.getFirstName() + " " + t.getLastName();
			List<Feed> feedList = fDAO.findUserFeed(t.getId());
			for (Feed feed : feedList) {
				feed.setName(name);
				fDAO.update(feed);
			}
			if (dao.update(t)) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * This method only checks to make sure the username is between 4 and 30
	 * characters and updates the database. There is now another method for checking
	 * the existence of the previous username that is used for this and
	 * registration.
	 * 
	 * @param t This is the user attempting username change.
	 * @return boolean This returns false if the user's username doesn't match
	 *         requirements.
	 */
	@Override
	public boolean updateUser(User t) {
		if (t.getUsername().length() < 4 || t.getUsername().length() > 30) {
			return false;
		} else {
			if (dao.update(t)) { // Updates the user in the database with new username
				return true;
			} else {
				return false; // Currently no error message for db failure
			}
		}
	}

	/**
	 * This method checks to see if the username already exists in the database.
	 * This is used for registration and account editing.
	 * 
	 * @param t This is the user checking username existence.
	 * @return boolean This returns false if the username does not exist.
	 */
	@Override
	public boolean checkUser(User t) {
		return dao.checkUsername(t.getUsername());
	}

	/**
	 * This method checks to see if the email already exists in the database. This
	 * is used for registration and account editing.
	 * 
	 * @param t This is the user checking email existence.
	 * @return boolean This returns false if the email does not exist.
	 */
	@Override
	public boolean checkEmail(User t) {
		return dao.checkEmail(t.getEmail());
	}

	/**
	 * This method checks to see if the email is valid using basic xyz@xyz checks
	 * then updates the database.
	 * 
	 * @param t This is the user attempting email change.
	 * @return boolean This returns false if the email is not a valid email.
	 */
	@Override
	public boolean updateEmail(User t) {
		try {
			InternetAddress email = new InternetAddress(t.getEmail());
			email.validate(); // Validates email address.
			if (dao.update(t)) {
				return true;
			} else
				return false;
		} catch (AddressException e) { // Exception called if email is not valid
			return false;
		}

	}

	/**
	 * Returns a user model through the use of their ID. This is now used for the
	 * account editor rather than saving all information to the session.
	 * 
	 * @param id This is the id of the user being looked up in the DAO.
	 * @return User This is the User being returned from the database.
	 */
	@Override
	public User findById(int id) {
		User user = dao.findById(id);
		return user;
	}
	
	/**
	 * Returns a user with the social object as a property of the user. This was separated
	 * from find by ID because now there is a need to find social and business users.
	 * @param id
	 * @param userId
	 * @return User
	 */
	@Override
	public User findSocUser(int id, int userId) {
		User user = dao.findById(id);
		Social social = sDAO.findById(id);
		
		//Setting birth date to a date object
		LocalDate birthDate = LocalDate.of(social.getBirthYear(), social.getBirthMonth(), social.getBirthDay());
		
		//Checking and setting the age of the user
		social.setAge(Period.between(birthDate, LocalDate.now()).getYears());
		user.setSocial(social); //Attaches the social object to the user object
		if(userId != 0) { //Checks if the user is not self
			user.setFeed(fs.setVoted(userId, fDAO.findUserFeed(id)));
		}
		return user;
	}
	
	/**
	 * Returns a user with the business object as a property of the user. See above
	 * for separation reasons.
	 * @param id
	 * @return User
	 */
	@Override
	public User findBusUser(int id) {
		User user = dao.findById(id);	
		Business bus = bDAO.findById(id);
		//Setting birth date to a date object
		LocalDate birthDate = LocalDate.of(bus.getBirthYear(), bus.getBirthMonth(), bus.getBirthDay());
		
		//Setting the age of the user
		bus.setAge(Period.between(birthDate,  LocalDate.now()).getYears());
		user.setBusiness(bus);
		return user;
	}
	
	@Override
	public User findDatUser(int id) {
		User user = dao.findById(id);
		Dating dat = dDAO.findById(id);
		LocalDate birthDate = LocalDate.of(dat.getBirthYear(), dat.getBirthMonth(), dat.getBirthDay());
		
		dat.setAge(Period.between(birthDate, LocalDate.now()).getYears());
		user.setDating(dat);
		return user;
	}
}