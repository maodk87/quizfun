package quizfun.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import quizfun.model.dto.QuestionSCDO;
import quizfun.model.entity.Course;
import quizfun.model.entity.Question;
import quizfun.model.exception.DuplicateCourseException;
import quizfun.model.exception.DuplicateQuestionException;

public class HibernateQuestionDao extends HibernateDaoSupport implements QuestionDao {

	@Override
	public void saveQuestion(Question question) throws DuplicateQuestionException {
		if (logger.isDebugEnabled()) {
			logger.info("Saving Question: " + question);
		}
		Session session = getSession(false);
		try {
			Question existingCourse = (Question) session.get(Question.class, question.getId());
			if (existingCourse != null) {
				throw new DuplicateQuestionException(question.toString());
			}
			session.save(question);
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
	}

	@Override
	public List<Question> findQuestion(QuestionSCDO questionSCDO) {/*

		if (logger.isDebugEnabled()) {
			logger.debug("Finding Question(s): {}", questionSCDO);
		}
		List<Question> list = null;
		Session session = getSession(false);
		try {
			String id = questionSCDO.getId();
			String question = questionSCDO.getQuestion();
			String moduleCode = questionSCDO.getModuleCode();
			String level = questionSCDO.getLevel();

			Criteria criteria = session.createCriteria(Question.class);
			if (id != null && !id.isEmpty()) {
				if (!id.contains("%")) {
					id = "%" + id + "%";
				}
				criteria.add(Restrictions.like("id", id));
			}
			if (question != null && !question.isEmpty()) {
				if (!question.contains("%")) {
					question = "%" + question + "%";
				}
				criteria.add(Restrictions.like("question", question));
			}
			if (moduleCode != null && !moduleCode.isEmpty()) {
				if (!moduleCode.contains("%")) {
					moduleCode = "%" + moduleCode + "%";
				}
				criteria.add(Restrictions.like("module", moduleCode));
			}
			if (level != null && !level.isEmpty()) {
				if (!level.contains("%")) {
					level = "%" + level + "%";
				}
				criteria.add(Restrictions.like("level", level));
			}			

			criteria.addOrder(Order.asc("id"));
			list = criteria.list();
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
		return list;
	*/
		return null;
	}
}
