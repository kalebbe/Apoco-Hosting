package com.gcu.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gcu.model.Questionaire;

@SuppressWarnings("rawtypes")
public class QuestionMapper  implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Questionaire que = new Questionaire();
		que.setUserId(rs.getInt("USER_ID"));
		que.setQue1(rs.getString("A1").charAt(0));
		que.setQue2(rs.getString("A2").charAt(0));
		que.setQue3(rs.getString("A3").charAt(0));
		que.setQue4(rs.getString("A4").charAt(0));
		que.setQue5(rs.getString("A5").charAt(0));
		return que;
	}

}
