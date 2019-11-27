/**
 * This class is used to perform crud functionality for connections
 * in the database. This will not implement the Data Access Interface
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-10-19
 */

package com.gcu.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import com.gcu.model.Connection;

public class ConnectionDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemp;

	/**
	 * This method retrieves all of the connections that the logged in user has.
	 * @param id
	 * @return List<Connection>
	 */
	@SuppressWarnings("unchecked")
	public List<Connection> findAll(int id) {
		String sql = "SELECT * FROM connections WHERE USER_ID=?";
		List<Connection> conn = jdbcTemp.query(sql,  new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException{
				ps.setInt(1,  id);
			}
		}, new ConnectionMapper());
		return conn;
	}

	/**
	 * This method creates a new Connection in the database.
	 * @param t
	 * @return boolean
	 */
	public boolean create(Connection t) {
		String sql = "INSERT INTO connections (USER_ID, CONN_ID) VALUES(?,?)";
		boolean result = false;
		if(jdbcTemp.update(sql, t.getUserId(), t.getConnId()) == 1) {
			result = true;
		}
		return result;
	}

	/**
	 * This method deletes a Connection in the database.
	 * @param t
	 * @return boolean
	 */
	public boolean delete(Connection t) {
		String sql = "DELETE FROM connections WHERE USER_ID=? AND CONN_ID=?";
		boolean result = false;
		if(jdbcTemp.update(sql, t.getUserId(), t.getConnId()) == 1) {
			result = true;
		}
		return result;
	}
	
	/**
	 * This method checks if a certain user is a connection of the logged in user.
	 * @param userId
	 * @param connId
	 * @return boolean
	 */
	public boolean checkConn(int userId, int connId) {
		String sql = "SELECT count(*) FROM connections WHERE USER_ID=? AND CONN_ID=?";
		boolean result = true;
		
		int count = jdbcTemp.queryForObject(sql,  new Object[] {userId, connId}, Integer.class);
		if(count > 0) {
			result = false;
		}
		return result;
	}
}
