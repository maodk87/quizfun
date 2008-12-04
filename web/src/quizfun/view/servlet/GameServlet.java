package quizfun.view.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import quizfun.model.entity.Answer;
import quizfun.model.entity.Module;
import quizfun.model.entity.Question;
import quizfun.model.exception.ModuleNotFoundException;
import quizfun.model.service.ModuleService;
import quizfun.model.service.QuestionService;

/**
 * MIDlet Game Servlet
 * 
 * @author M. Isuru Tharanga Chrishantha Perera
 */
public class GameServlet extends HttpServlet {

	private static final long serialVersionUID = 3866252845828221062L;

	private final Logger logger = LoggerFactory.getLogger(GameServlet.class);
	
	private static final int NUMBER_OF_QUESTIONS_PER_LEVEL = 5;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GameServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (logger.isTraceEnabled()) {
			logger.trace("Processing Game Request...");
		}

		// Retrieve the session object corresponding to this request.
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		boolean sessionExpired = session.isNew();
		if (logger.isTraceEnabled()) {
			if (sessionExpired) {
				// This should not happen
				logger.trace("New session created: {}", sessionId);
			} else {
				logger.trace("Used existing session: {}", sessionId);
			}
		}

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if (sessionExpired) {
			StringBuilder builder = new StringBuilder();
			builder.append("<game>");
			builder.append("<error-msg>");
			builder.append("Session Expired");
			builder.append("</error-msg>");
			builder.append("</game>");
			out.write(builder.toString());
			return;
		}

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		ModuleService moduleService = (ModuleService) ctx.getBean("moduleService");
		QuestionService questionService = (QuestionService) ctx.getBean("questionService");

		String gameId = request.getParameter("game");
		String moduleCode = request.getParameter("module");
		String level = request.getParameter("level");

		if (logger.isTraceEnabled()) {
			logger.trace("Game ID: {}", gameId);
			logger.trace("Module Code: {}", moduleCode);
			logger.trace("Level: {}", level);

			if (moduleCode != null) {
				logger.trace("Requesting questions by module...");
			}
			if (gameId != null) {
				logger.trace("Requesting questions by game...");
			}
		}
		
		if (moduleCode != null) {
			Module module = null;
			try {
				module = moduleService.findModuleByCode(moduleCode);
				if (logger.isTraceEnabled()) {
					logger.trace("Found: {}", module);
				}
			} catch (ModuleNotFoundException e) {
				StringBuilder builder = new StringBuilder();
				builder.append("<game>");
				builder.append("<error-msg>");
				builder.append("Module not found for code '").append(moduleCode).append("'");
				builder.append("</error-msg>");
				builder.append("</game>");
				out.write(builder.toString());
				return;
			}
		}
		
		boolean loadingSuccess = true;

		Map<Integer, List<Question>> questionMapByLevel = (Map<Integer, List<Question>>) session.getAttribute("QuestionMapByLevel");
		if (questionMapByLevel == null) {
			questionMapByLevel = new HashMap<Integer, List<Question>>();
			if (moduleCode != null) {
				if (logger.isTraceEnabled()) {
					logger.trace("Getting random questions for module.");
				}

				for (int i = 1; i <= 3; i++) {
					// Limit is 5
					List<Question> questionList = questionService.findRandomQuestionByLevel(moduleCode, i, NUMBER_OF_QUESTIONS_PER_LEVEL);
					if (questionList == null || questionList.isEmpty()) {
						loadingSuccess = false;
					} else if (questionList.size() < NUMBER_OF_QUESTIONS_PER_LEVEL) {
						// loadingSuccess = false;
						// ignore this
					}
					
					if (!loadingSuccess) {
						break;
					}
					
					questionMapByLevel.put(i, questionList);
					if (logger.isTraceEnabled()) {
						for (Question question : questionList) {
							logger.trace("Question Retrieved: {}", question);
						}
					}
				}
			}
		}
		
		if (!loadingSuccess) {
			StringBuilder builder = new StringBuilder();
			builder.append("<game>");
			builder.append("<error-msg>");
			builder.append("Quesion loading failed.");
			builder.append("</error-msg>");
			builder.append("</game>");
			out.write(builder.toString());
			return;
		}
		
		int levelNumber = Integer.parseInt(level);
		
		List<Question> questionList = questionMapByLevel.get(levelNumber);
		
		StringBuilder builder = new StringBuilder();
		builder.append("<?xml version='1.0' encoding='UTF-8'?>");
		builder.append("<game>");
		
		for (Question question : questionList) {
			builder.append("<question>");
			builder.append("<id>").append(question.getId()).append("</id>");
			builder.append("<value>").append(question.getQuestion()).append("</value>");
			Set<Answer> answers = question.getAnswers();
			for (Answer answer : answers) {
				builder.append("<answer>");
				builder.append("<id>").append(answer.getId()).append("</id>");
				builder.append("<value>").append(answer.getAnswer()).append("</value>");
				builder.append("<correct>").append(answer.isCorrect()).append("</correct>");
				builder.append("</answer>");
			}
			builder.append("</question>");
		}
		builder.append("</game>");
		
		if (logger.isTraceEnabled()) {
			logger.trace("XML: {}", builder.toString());
		}
		
		out.write(builder.toString());
		return;
	}

}
