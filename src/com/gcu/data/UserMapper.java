/**
 * This class is used to map the data from the database when creating a new
 * user model using Spring JDBC.
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
import com.gcu.model.User;

/**
 * This is a quick method for creating and returning a new User object.
 * @param rs This is the result set from the database.
 * @param rowNum This is required by RowMapper, but unused.
 * @return User This is the user object returned after being set here.
 */
@SuppressWarnings("rawtypes")
public class UserMapper implements RowMapper {
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("ID")); //ID added for updating purposes
		user.setEmail(rs.getString("EMAIL"));
		user.setUsername(rs.getString("USERNAME"));
		user.setFirstName(rs.getString("FIRST_NAME"));
		user.setLastName(rs.getString("LAST_NAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setPassRe(rs.getString("PASSWORD"));
		user.setAccess(rs.getString("ROLE"));
		return user;
	}
}
