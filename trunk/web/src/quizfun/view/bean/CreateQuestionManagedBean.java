package quizfun.view.bean;

import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quizfun.model.entity.Course;
import quizfun.model.entity.Question;
import quizfun.model.exception.DuplicateCourseException;
import quizfun.model.exception.DuplicateQuestionException;
import quizfun.view.util.JSFUtils;

public class CreateQuestionManagedBean extends QuestionManagedBean{

	final Logger logger = LoggerFactory.getLogger(CreateCourseManagedBean.class);

	@javax.annotation.PostConstruct
	public void init() {
		question = new Question();
	}

	public void saveActionListener(ActionEvent event) {
		try {
			serviceLocator.getQuestionService().saveQuestion(question);
			clearValues();
			JSFUtils.addFacesInfoMessage("question.save.successful");
		} catch (DuplicateQuestionException e) {
			JSFUtils.addFacesErrorMessage("question.save.duplicate", new Object[] { question.getId() });
			return;
		} catch (Throwable e) {
			logger.error("Exception when saving question: " + question, e);
			JSFUtils.addApplicationErrorMessage();
			return;
		}
	}
}
