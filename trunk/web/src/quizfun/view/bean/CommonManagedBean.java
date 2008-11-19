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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import quizfun.model.entity.User;
import quizfun.view.servicelocator.ServiceLocator;

/**
 * @author M. Isuru Tharanga Chrishantha Perera
 */
public class CommonManagedBean {

	final Logger logger = LoggerFactory.getLogger(CommonManagedBean.class);

	private String username;
	
	// We might need the service locator
	@SuppressWarnings("unused")
	private ServiceLocator serviceLocator;

	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	@javax.annotation.PostConstruct
	public void init() {
		username = null;
		User user = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object obj = authentication.getPrincipal();
			if (obj instanceof User) {
				user = (User) obj;
				username = user.getUsername();
			} else if (obj instanceof UserDetails) {
				username = ((UserDetails) obj).getUsername();
			} else if (obj instanceof String) {
				username = (String) obj;
			}
		}
	}

	public String getUsername() {
		return username;
	}
}
