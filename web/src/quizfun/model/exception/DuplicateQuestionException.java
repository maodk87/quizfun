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

package quizfun.model.exception;

/**
 * @author Hiranya Mudunkotuwa
 */
public class DuplicateQuestionException extends Exception {

	private static final long serialVersionUID = 5607596049296544746L;

	public DuplicateQuestionException() {
	}

	public DuplicateQuestionException(String message) {
		super(message);
	}

	public DuplicateQuestionException(Throwable cause) {
		super(cause);
	}

	public DuplicateQuestionException(String message, Throwable cause) {
		super(message, cause);
	}
}
