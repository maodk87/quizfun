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

package quizfun.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import quizfun.model.dto.ModuleSCDO;
import quizfun.model.entity.Module;
import quizfun.model.exception.DuplicateModuleException;

public class HibernateModuleDao extends HibernateDaoSupport implements ModuleDao {

	final Logger logger = LoggerFactory.getLogger(HibernateCourseDao.class);
	
	@Override
	public void saveModule(Module module) throws DuplicateModuleException {
		if (logger.isDebugEnabled()) {
			logger.info("Saving Module: {}", module);
		}
		Session session = getSession(false);
		try {
			Module existingModule = (Module) session.get(Module.class, module.getCode());
			if (existingModule != null) {
				throw new DuplicateModuleException(module.toString());
			}
			session.save(module);
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> findModule(ModuleSCDO moduleSCDO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Finding Module(s): {}", moduleSCDO);
		}
		List<Module> list = null;
		Session session = getSession(false);
		try {
			String code = moduleSCDO.getCode();
			String name = moduleSCDO.getName();

			Criteria criteria = session.createCriteria(Module.class);
			if (code != null && !code.equals("")) {
				if (!code.contains("%")) {
					code = "%" + code + "%";
				}
				criteria.add(Restrictions.like("code", code));
			}
			if (name != null && !name.equals("")) {
				if (!name.contains("%")) {
					name = "%" + name + "%";
				}
				criteria.add(Restrictions.like("name", name));
			}

			criteria.addOrder(Order.asc("code"));
			list = criteria.list();
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
		return list;
	}

	@Override
	public Module updateModule(Module module) {
		if (logger.isDebugEnabled()) {
			logger.debug("Updating Module: {}", module);
		}
		Session session = getSession(false);
		try {
			return (Module) session.merge(module);
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
	}

	@Override
	public void deleteModule(Module module) {
		if (logger.isDebugEnabled()) {
			logger.debug("Deleting Module: {}", module);
		}
		Session session = getSession(false);
		try {
			session.delete(module);
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
	}

}
