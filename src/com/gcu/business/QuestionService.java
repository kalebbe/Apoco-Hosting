package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.QuestionDAO;
import com.gcu.model.Questionaire;
import com.gcu.model.User;

public class QuestionService implements QuestionInterface {
	
	@Autowired 
	private QuestionDAO dao;
	
	@Override
	public boolean createQuestion(Questionaire t) {
		return dao.create(t);
	}
	
	@Override
	public boolean checkQuestion(int id) {
		if(dao.findById(id) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public User getQuestionaire(User user) {
		Questionaire que = dao.findById(user.getId());
		user.getDating().setQuestion(que);
		return user;
	}
}
