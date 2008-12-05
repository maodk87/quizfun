/*
 * QuizFun - A quiz game
 * Copyright (C) 2008
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package quizfun.view.bean;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icesoft.faces.component.ext.HtmlOutputText;


import quizfun.model.entity.Answer;
import quizfun.model.entity.Game;
import quizfun.model.entity.Question;
import quizfun.model.exception.QuestionNotFoundException;
import quizfun.view.servicelocator.ServiceLocator;
import quizfun.view.util.JSFUtils;

/**
 * 
 * @author Hiranya Mudunkotuwa
 *
 */
public class PlayGameManagedBean {
	
	final Logger logger = LoggerFactory.getLogger(PlayGameManagedBean.class);
	
	private ServiceLocator serviceLocator;
	private Game selectedGame; 
	private Game game;
	private List<Question> quesList;
	private List<Answer> ansList;	

	HtmlOutputText idOutputText;

	@SuppressWarnings("unchecked")
	private ArrayList chartData;

	@SuppressWarnings("unchecked")
	private ArrayList colors;
	@SuppressWarnings("unchecked")
	@javax.annotation.PostConstruct
	public void init() {
		game = new Game();
		quesList = new ArrayList<Question>();
		ansList = new ArrayList<Answer>();
		selectedGame = (Game) JSFUtils.removeFromSessionMap("game");
		if (logger.isDebugEnabled()) {
			logger.debug("Object retrieved from session: {}", selectedGame);
		}
		resetValues();
        chartData = new ArrayList(Arrays.asList(
                new Double[]{new Double(50.0),
                             new Double(60.0),
                             new Double(80.0),
                             new Double(40.0)}));


        // build colors
        colors = new ArrayList(Arrays.asList(
                new Color[]{new Color(26, 86, 138),
                new Color(76, 126, 167),
                new Color(0, 34, 102),
                new Color(148, 179, 203)}));
	}
	private void resetValues() {

		game.setId(selectedGame.getId());
		Game sgame = new Game();

			sgame = serviceLocator.getGameService().findGameById(selectedGame.getId());

	//	ans = ques.getAnswers();
		game.setQuestions(sgame.getQuestions());
		List<Question> list = new ArrayList<Question>();
		list = new ArrayList<Question>(game.getQuestions());
		for(Question q :list) {
			Question ques = new Question();
			try {
				ques = serviceLocator.getQuestionService().findQuestionById(q.getId());
			} catch (QuestionNotFoundException e) {
				return;
			}
			
			q.setAnswers(ques.getAnswers());
			quesList.add(q);
		}

	}
	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public Game getSelectedGame() {
		return selectedGame;
	}

	public void setSelectedGame(Game selectedGame) {
		this.selectedGame = selectedGame;
	}
	
	
	public HtmlOutputText getIdOutputText() {
		return idOutputText;
	}

	public void setIdOutputText(HtmlOutputText idOutputText) {
		this.idOutputText = idOutputText;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	public List<Question> getQuesList() {
		return quesList;
	}
	public void setQuesList(List<Question> quesList) {
		this.quesList = quesList;
	}
	public ArrayList getChartData() {
		return chartData;
	}
	public void setChartData(ArrayList chartData) {
		this.chartData = chartData;
	}
	public ArrayList getColors() {
		return colors;
	}
	public void setColors(ArrayList colors) {
		this.colors = colors;
	}
	public List<Answer> getAnsList() {
		return ansList;
	}
	public void setAnsList(List<Answer> ansList) {
		this.ansList = ansList;
	}
}
