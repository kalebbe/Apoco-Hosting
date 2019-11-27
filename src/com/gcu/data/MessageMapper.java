/**
 * This class is used to save database information to a Message object.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-12-16
 */

package com.gcu.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.gcu.model.Message;

@SuppressWarnings("rawtypes")
public class MessageMapper implements RowMapper {

	/**
	 * This method maps the database information into a new message object.
	 * @param rs This is the ResultSet from the database.
	 * @param rowNum This is the row number of the ResultSet.
	 * @return Message This is the created message.
	 */
	@Override
	public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
		Message message = new Message();
		message.setId(rs.getInt("ID"));
		message.setReceiverId(rs.getInt("RECEIVER_ID"));
		message.setSenderId(rs.getInt("SENDER_ID"));
		message.setBody(rs.getString("BODY"));
		message.setType(rs.getString("TYPE"));
		message.setDate((rs.getTimestamp("DATE_SENT")).toLocalDateTime());
		return message;
	}
	
}
