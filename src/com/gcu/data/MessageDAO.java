/**
 * This class is used to create new messages, delete messages, and
 * retrieve all of a user's messages from the database with parameters.
 * This will be updated in the future to use more of these methods and 
 * possibly some new ones.
 * 
 * 
 * @author  Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-12-16
 */

package com.gcu.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import com.gcu.model.Message;

public class MessageDAO implements DataAccessInterface<Message> {

	
	@Autowired
	private JdbcTemplate jdbcTemp;
	
	/**
	 * This method gets a message by its id.
	 * @param id This is the id of the message.
	 * @return Message This is the message being got.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Message findById(int id) {
		String sql = "SELECT * FROM messages WHERE ID=?";
		Message message = (Message) jdbcTemp.queryForObject(sql, new Object[] { id }, new MessageMapper());
		return message;
	}
	
	/**
	 * This gets the last message in a thread of messages to check if it's read or unread
	 * for the logge din user.
	 * @param id
	 * @return Message
	 */
	@SuppressWarnings("unchecked")
	public Message getLastThread(int id) {
		try {
			String sql;
			sql = "SELECT * FROM messages where PARENT_ID=? ORDER BY DATE_SENT desc LIMIT 1";
			Message message = (Message) jdbcTemp.queryForObject(sql, new Object[] { id }, new MessageMapper());
			return message;
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * This method gets every message from the database. Will likely never be used. May
	 * even be removed and return null for privacy protection.
	 * @return List<Message> The list of messages.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findAll() {
		String sql = "SELECT * FROM messages";
		List<Message> messages = jdbcTemp.query(sql, new MessageMapper());
		return messages;
	}
	
	/**
	 * This method gets the inbox or outbox of a certain user. Refined by type.
	 * @param id The id of the user logged in.
	 * @param type receiver or sender depending on inbox/outbox
	 * @param type1 socUnread, busUnread
	 * @param type2 socRead, busRead
	 * @return List<Message> The returned messages.
	 */
	@SuppressWarnings("unchecked")
	public List<Message> getMessages(int id, String type, String type1, String type2){
		String sql = "SELECT * FROM messages WHERE " + type + " = ? AND (TYPE = ? OR TYPE = ?) AND PARENT_ID = '-1'"
				+ " ORDER BY DATE_SENT desc";
		//PreparedStatementSetter for multiple parameters.
		List<Message> message = jdbcTemp.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException{
				ps.setInt(1, id);
				ps.setString(2, type1);
				ps.setString(3, type2);
			}
		}, new MessageMapper()); //Maps messages to their values.
		return message;
	}
	
	/**
	 * This method gets all messages within a thread. Threads are created when a user sends
	 * a message to another from their profile.
	 * @param id
	 * @return List<message>
	 */
	@SuppressWarnings("unchecked")
	public List<Message> getThread(int id){
		String sql= "SELECT * FROM messages WHERE PARENT_ID = ? ORDER BY DATE_SENT asc";
		
		List<Message> message = jdbcTemp.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException{
				ps.setInt(1, id);
			}
		}, new MessageMapper());
		return message;
	}
	
	/**
	 * This method is used to create a new message in the database. Currently
	 * used only for requests.
	 * @param t The message object.
	 * @return boolean Whether or not the object creation was successful.
	 */
	@Override
	public boolean create(Message t) {
		String sql = "INSERT INTO messages (SENDER_ID, RECEIVER_ID, PARENT_ID, BODY, TYPE) VALUES(?,?,?,?,?)";
		if(jdbcTemp.update(sql, t.getSenderId(), t.getReceiverId(), t.getParentId(), t.getBody(), t.getType()) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * This method is used to update a message in the database.
	 * @param t The message being updated.
	 * @return boolean Whether or not the object update was successful.
	 */
	@Override
	public boolean update(Message t) {
		String sql = "UPDATE messages SET TYPE = ? WHERE ID = ?";
	
		boolean result = false;
		if(jdbcTemp.update(sql, t.getType(), t.getId()) == 1) {
			result = true;
		}
		return result;
	}

	/**
	 * This method is used to delete a message in the database. Currently only used
	 * to delete requests.
	 * @param id The id of the message.
	 * @return boolean Whether or not the object deletion was successful.
	 */
	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM messages WHERE ID = ?";
		if(jdbcTemp.update(sql, id) == 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method is used to check if a user has any friend requests. May be reworked
	 * in the future to return the count so user's can see how many bots.. er friends, they
	 * have waiting acceptance. We'll see.
	 * @param senderId The id of the request sender.
	 * @param receiverId The id of this dude.
	 * @return boolean Whether or not they have any friend requests.
	 */
	public boolean checkRequest(int senderId, int receiverId, String type) {
		String sql;
		int count;
		if(senderId == 0) { //This is used to check if a user has any requests at all
			sql = "SELECT count(*) FROM messages WHERE RECEIVER_ID=? AND TYPE=?";
			count = jdbcTemp.queryForObject(sql, new Object[] {receiverId, type}, Integer.class);
		}
		else { //This is used to check if a user has sent a request to a specific user.
			sql = "SELECT count(*) FROM messages WHERE SENDER_ID=? AND RECEIVER_ID=? AND TYPE=?";
			count = jdbcTemp.queryForObject(sql, new Object[] {senderId, receiverId, type}, Integer.class);
		}
		if(count > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * The purpose of this method is to retrieve the number of unread messages, friend requests, connection
	 * requests, or matches. 
	 * @param id
	 * @param type
	 * @return int
	 */
	public int getNotifications(int id, String type) {
		String sql;
		int count; //Holds the result
		sql = "SELECT count(*) FROM messages WHERE RECEIVER_ID=? AND TYPE=?";
		count = jdbcTemp.queryForObject(sql,  new Object[] {id, type}, Integer.class); 
		
		return count;
	}
}
