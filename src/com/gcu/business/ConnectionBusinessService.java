/**
 * This class is used to search connections, add connections, and delete connections
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-10-19
 */

package com.gcu.business;

import com.gcu.data.BusinessDAO;
import com.gcu.data.ConnectionDAO;
import com.gcu.data.MessageDAO;
import com.gcu.data.UserDAO;
import com.gcu.model.Business;
import com.gcu.model.Connection;
import com.gcu.model.Message;
import com.gcu.model.User;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ConnectionBusinessService implements ConnectionBusinessInterface {
	
	@Autowired
	private ConnectionDAO dao;
	@Autowired
	private UserDAO uDao;
	@Autowired
	private BusinessDAO bDao;
	@Autowired
	private MessageDAO mDao;

	/**
	 * This method is used to search for profiles based upon name or email.
	 * @param search
	 * @param id
	 * @return List<User>
	 */
	@Override
	public List<User> searchPeople(String search, int id) {
		List<User> users = uDao.searchUsers(search,  id);
		return checkProfiles(users);
	}

	/**
	 * This method calls the DAO to add a new connection to the database.
	 * @param t
	 * @return boolean
	 */
	@Override
	public boolean addConnection(Connection t) {
		Connection conn = new Connection(t.getConnId(), t.getUserId()); //Create a connection for other user too
		if(!dao.create(conn)) {
			return false;
		}
		return dao.create(t);
	}

	/**
	 * This method deletes the connection from the database
	 * @param t
	 * @return boolean
	 */
	@Override
	public boolean deleteConnection(Connection t) {
		return dao.delete(t);
	}

	/**
	 * This method checks to see if the searched users have a business profile.
	 * @param users
	 * @return List<User>
	 */
	@Override
	public List<User> checkProfiles(List<User> users) {
		List<User> newUsers = new ArrayList<User>();
		for (User u: users) {
			Business bus = bDao.findById(u.getId());
			if(bus != null) {
				LocalDate birthDate = LocalDate.of(bus.getBirthYear(), bus.getBirthMonth(), bus.getBirthDay());
				bus.setAge(Period.between(birthDate, LocalDate.now()).getYears());
				u.setBusiness(bus);
				newUsers.add(u);
			}
		}
		return newUsers;
	}

	/**
	 * this method is used to retrieve the connections of the logged in user.
	 * @param id
	 * @return List<User>
	 */
	@Override
	public List<User> getConnections(int id) {
		List<Connection> conns = dao.findAll(id);
		List<User> users = new ArrayList<User>();
		for(Connection c: conns) {
			User user = uDao.findById(c.getConnId());
			Business bus = bDao.findById(c.getConnId());
			LocalDate birthDate = LocalDate.of(bus.getBirthYear(), bus.getBirthMonth(), bus.getBirthDay());
			bus.setAge(Period.between(birthDate, LocalDate.now()).getYears());
			user.setBusiness(bus);
			users.add(user);
		}
		return users;
	}

	/**
	 * Checks to see if another user is a friend of the logged in user.
	 * @param userId
	 * @param connId
	 * @return boolean
	 */
	@Override
	public boolean checkConn(int userId, int connId) {
		return dao.checkConn(userId, connId);
	}

	/**
	 * Checks if the logged in user has already sent a connection request before
	 * sending another.
	 * @param t
	 * @return boolean
	 */
	@Override
	public boolean sendRequest(Message t) {
		if(mDao.checkRequest(t.getSenderId(), t.getReceiverId(), "connection")) {
			return false;
		}
		return mDao.create(t);
	}

	/**
	 * This method gets the profiles of the users that have sent a connection request
	 * to the logged in user.
	 * @param msg
	 * @return List<User>
	 */
	@Override
	public List<User> getRequestProfiles(List<Message> msg) {
		List<User> users = new ArrayList<User>();
		for(Message m: msg) {
			User user = uDao.findById(m.getSenderId());
			Business bus = bDao.findById(m.getSenderId());
			LocalDate birthDate = LocalDate.of(bus.getBirthYear(), bus.getBirthMonth(), bus.getBirthDay());
			bus.setAge(Period.between(birthDate, LocalDate.now()).getYears());
			user.setBusiness(bus);
			user.setMessageId(m.getId());
			users.add(user);
		}
		return users;
	}
}
