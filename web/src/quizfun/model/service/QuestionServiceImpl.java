package quizfun.model.service;

import java.util.List;

import quizfun.model.dao.QuestionDao;
import quizfun.model.dto.QuestionSCDO;
import quizfun.model.entity.Question;
import quizfun.model.exception.DuplicateQuestionException;

public class QuestionServiceImpl implements QuestionService {

	private QuestionDao questionDao;

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}
	
	@Override
	public void saveQuestion(Question question) throws DuplicateQuestionException {
		questionDao.saveQuestion(question);
		return;
	}

	@Override
	public List<Question> findQuestion(QuestionSCDO questionSCDO) {
		questionDao.findQuestion(questionSCDO);
		return questionDao.findQuestion(questionSCDO);
	}

}
