package quizfun.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import quizfun.model.dao.GameDao;
import quizfun.model.dto.GameSCDO;
import quizfun.model.entity.Game;
import quizfun.model.exception.DuplicateQuestionException;

public class GameServiceImpl implements GameService {
	final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

	private GameDao gameDao;
	private ModuleService moduleService;
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { DuplicateQuestionException.class })
	public void saveGame(Game game){
		Long id = gameDao.saveGame(game);
		return;

	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Game> findGame(GameSCDO gameSCDO) {
		//gameDao.findGame(gameSCDO);
		return gameDao.findGame(gameSCDO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Game findGameById(Long id){
		Game game = gameDao.findGameById(id);
/*		if(question == null || question.equals("")) {
			throw new QuestionNotFoundException();
		*/
		return game;
		
	}
	
	public ModuleService getModuleService() {
		return moduleService;
	}
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	public void setGameDao(GameDao gameDao) {
		this.gameDao = gameDao;
	}


}
