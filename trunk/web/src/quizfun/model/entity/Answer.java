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

package quizfun.model.entity;

import java.io.Serializable;

public class Answer implements Serializable {

	private static final long serialVersionUID = 3598134666777318602L;

	private Long id;
	private String answer;
	private boolean correct;

	private Question question;

	private Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getAnswer() == null) ? 0 : getAnswer().hashCode());
		result = prime * result + (isCorrect() ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Answer))
			return false;
		Answer other = (Answer) obj;
		if (getAnswer() == null) {
			if (other.getAnswer() != null)
				return false;
		} else if (!getAnswer().equals(other.getAnswer()))
			return false;
		if (isCorrect() != other.isCorrect())
			return false;
		return true;
	}

	@Override
	public String toString() {
		final String separator = ", ";

		StringBuilder builder = new StringBuilder();
		builder.append("Answer {");
		builder.append("id = ").append(getId());
		builder.append(separator).append("answer = ").append('\"').append(getAnswer()).append('\"');
		builder.append(separator).append("correct = ").append(isCorrect());
		builder.append(separator).append("version = ").append(getVersion());
		builder.append(separator).append("question = ").append(getQuestion());
		builder.append("}");
		return builder.toString();
	}

}
