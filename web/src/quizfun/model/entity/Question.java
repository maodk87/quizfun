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
import java.util.HashSet;
import java.util.Set;

public class Question implements Serializable {

	private static final long serialVersionUID = 7400661908891515938L;

	private Long id;
	private String question;
	private int type;
	private int level;
	private String hint;
	private String reference;
	
	private Module module;

	private Long version;
	
	private Set<Answer> answers = new HashSet<Answer>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getLevel();
		result = prime * result + ((getQuestion() == null) ? 0 : getQuestion().hashCode());
		result = prime * result + getType();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Question))
			return false;
		Question other = (Question) obj;
		if (getLevel() != other.getLevel())
			return false;
		if (getQuestion() == null) {
			if (other.getQuestion() != null)
				return false;
		} else if (!getQuestion().equals(other.getQuestion()))
			return false;
		if (getType() != other.getType())
			return false;
		return true;
	}

	@Override
	public String toString() {
		final String separator = ", ";
		
		StringBuilder builder = new StringBuilder();
		builder.append("Question {");
		builder.append("id = ").append(getId());
		builder.append(separator).append("question = ").append('\"').append(getQuestion()).append('\"');
		builder.append(separator).append("type = ").append(getType());
		builder.append(separator).append("level = ").append(getLevel());
		builder.append(separator).append("hint = ").append('\"').append(getHint()).append('\"');
		builder.append(separator).append("reference = ").append('\"').append(getReference()).append('\"');
		builder.append(separator).append("version = ").append(getVersion());
		builder.append(separator).append("module = ").append(getModule());
		builder.append("}");
		return builder.toString();
	}
}
