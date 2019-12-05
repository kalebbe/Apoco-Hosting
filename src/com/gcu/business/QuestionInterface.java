package com.gcu.business;

import com.gcu.model.Questionaire;
import com.gcu.model.User;

public interface QuestionInterface {

	public boolean createQuestion(Questionaire t);

	public boolean checkQuestion(int id);

	public User getQuestionaire(User user);

}
