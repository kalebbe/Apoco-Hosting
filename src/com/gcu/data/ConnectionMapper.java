/**
 * This class is used to save database information to a Connection object
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since 2019-10-19
 */

package com.gcu.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.gcu.model.Connection;

@SuppressWarnings("rawtypes")
public class ConnectionMapper implements RowMapper {

	/**
	 * This method maps the database information into a new Connection object.
	 * @param rs
	 * @param rowNum
	 * @return Object
	 */
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Connection(rs.getInt("USER_ID"), rs.getInt("CONN_ID"));
	}

}
