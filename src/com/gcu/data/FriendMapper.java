/**
 * This class is used to save database information to a Friend object.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-12-10
 */

package com.gcu.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gcu.model.Friend;

@SuppressWarnings("rawtypes")
public class FriendMapper implements RowMapper{

	/**
	 * This method maps the database information into a new Friend object.
	 * @param rs This is the ResultSet from the database.
	 * @param rowNum This is the row number of the ResultSet.
	 * @return Friend This is the created Friend.
	 */
	@Override
	public Friend mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Friend(rs.getInt("USER_ID"), rs.getInt("FRIEND_ID"));
	}

}
