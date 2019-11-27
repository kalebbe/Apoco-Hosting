/**
 * This class is used to map the data from the database when creating a new
 * feed model using Spring JDBC.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.gcu.model.Feed;


@SuppressWarnings("rawtypes")
public class FeedMapper implements RowMapper {

	/**
	 * This is a quick method for creating and returning a new Feed object from the database.
	 * @param rs This is the result set from the database.
	 * @param rowNum This is required by RowMapper, but unused.
	 * @return Feed This is the Feed object returned after being set up.
	 */
	@Override
	public Feed mapRow(ResultSet rs, int rowNum) throws SQLException {
		Feed feed = new Feed();
		feed.setFeed(rs.getString("POST"));
		feed.setId(rs.getInt("ID"));
		feed.setUserId(rs.getInt("USER_ID"));
		feed.setLink(rs.getString("LINK"));
		feed.setName(rs.getString("NAME"));
		feed.setPrivacy(rs.getString("PRIVACY"));
		feed.setVotes(rs.getInt("VOTES"));
		return feed;
	}

}
