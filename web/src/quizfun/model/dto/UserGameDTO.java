package quizfun.model.dto;

import java.util.List;

public class UserGameDTO {

	private String userName;
	private Long marks;
	private Long gameId;
	private List<QuesAnswDTO> QuesAnswDTOs;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getMarks() {
		return marks;
	}
	public void setMarks(Long marks) {
		this.marks = marks;
	}
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public List<QuesAnswDTO> getQuesAnswDTOs() {
		return QuesAnswDTOs;
	}
	public void setQuesAnswDTOs(List<QuesAnswDTO> quesAnswDTOs) {
		QuesAnswDTOs = quesAnswDTOs;
	}
}
