package quizfun.model.exception;

public class QuestionNotFoundException extends Exception {

	private static final long serialVersionUID = 90166071655897020L;

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
