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

import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quizfun.model.entity.Module;
import quizfun.view.util.JSFUtils;

/**
 * @author Nevindaree Premarathne
 */
public class ModifyModuleManagedBean extends ModuleManagedBean {

	final Logger logger = LoggerFactory.getLogger(ModifyModuleManagedBean.class);
	
	private Module modifyingModule;

	@javax.annotation.PostConstruct
	public void init() {
		modifyingModule = (Module) JSFUtils.removeFromSessionMap("module");
		if (logger.isDebugEnabled()) {
			logger.debug("Object retrieved from request: {}", modifyingModule);
		}
		module = new Module();
		module.setCode(modifyingModule.getCode());
		module.setName(modifyingModule.getName());
	}

	public void saveActionListener(ActionEvent event) {
		try {
			modifyingModule.setCode(module.getCode());
			modifyingModule.setName(module.getName());
			modifyingModule = serviceLocator.getModuleService().updateModule(modifyingModule);
			if (logger.isDebugEnabled()) {
				logger.debug("Module updated: {}", modifyingModule);
			}
			JSFUtils.addFacesInfoMessage("module.save.successful");
			codeInputText.requestFocus();
		} catch (Throwable e) {
			logger.error("Exception when saving module: " + module, e);
			JSFUtils.addApplicationErrorMessage();
			return;
		}
	}
	
	public void clearActionListener(ActionEvent event) {
		module.setCode(modifyingModule.getCode());
		module.setName(modifyingModule.getName());

		codeInputText.resetValue();
		nameInputText.resetValue();
		codeInputText.requestFocus();
	}
}
