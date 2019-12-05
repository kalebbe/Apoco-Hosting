package com.gcu.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gcu.model.Questionaire;

public class QuestionDAO implements DataAccessInterface<Questionaire>{

	@Autowired
	private JdbcTemplate jdbcTemp;

	@SuppressWarnings("unchecked")
	@Override
	public Questionaire findById(int id) {
		try {
			String sql = "SELECT * FROM questionaire WHERE USER_ID=?";
			Questionaire que = (Questionaire) jdbcTemp.queryForObject(sql, new Object[] { id },
					new QuestionMapper());
			return que;		
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Questionaire> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Questionaire t) {
		String sql = "INSERT INTO questionaire (USER_ID, A1, A2, A3, A4, A5) VALUES(?,?,?,?,?,?)";
		boolean result = false;
		
		if(jdbcTemp.update(sql, t.getUserId(), String.valueOf(t.getQue1()), String.valueOf(t.getQue2()),
				String.valueOf(t.getQue3()), String.valueOf(t.getQue4()),
				String.valueOf(t.getQue5())) == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean update(Questionaire t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
