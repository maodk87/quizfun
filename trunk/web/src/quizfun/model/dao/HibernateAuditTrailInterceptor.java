package quizfun.model.dao;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import quizfun.model.entity.Auditable;
import quizfun.model.util.SpringSecurityUtil;

/**
 * @author M. Isuru Tharanga Chrishantha Perera
 */
public class HibernateAuditTrailInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 7715286660000990617L;

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		boolean modified = false;
		if (entity instanceof Auditable) {
			for (int i = 0; i < propertyNames.length; i++) {
				if ("modifiedDate".equals(propertyNames[i])) {
					currentState[i] = new Date();
					modified = true;
				} else if ("modifiedBy".equals(propertyNames[i])) {
					currentState[i] = SpringSecurityUtil.getUsername();
					modified = true;
				}
			}
		}
		return modified;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		boolean modified = false;
		if (entity instanceof Auditable) {
			for (int i = 0; i < propertyNames.length; i++) {
				if ("createdDate".equals(propertyNames[i])) {
					state[i] = new Date();
					modified = true;
				} else if ("createdBy".equals(propertyNames[i])) {
					state[i] = SpringSecurityUtil.getUsername();
					modified = true;
				}
			}
		}
		return modified;
	}
}