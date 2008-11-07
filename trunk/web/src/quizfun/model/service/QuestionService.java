package quizfun.model.service;

import java.util.List;

import quizfun.model.dto.QuestionSCDO;
import quizfun.model.entity.Question;
import quizfun.model.exception.DuplicateQuestionException;

public interface QuestionService {

	void saveQuestion(Question question) throws DuplicateQuestionException;

	List<Question> findQuestion(QuestionSCDO questionSCDO);

}
