package quizfun.model.exception;

public class QuestionNotFoundException extends Exception {

	public QuestionNotFoundException() {
	}

	public QuestionNotFoundException(String message) {
		super(message);
	}

	public QuestionNotFoundException(Throwable cause) {
		super(cause);
	}

	public QuestionNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
