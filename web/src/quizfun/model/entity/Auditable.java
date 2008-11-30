package quizfun.model.entity;

import java.util.Date;

/**
 * <p>
 * Use this interface to mark an entity as Auditable
 * </p>
 * <p>
 * The DAO implementation will set the values during the life cycle of the
 * entity
 * </p>
 * 
 * @author Hiranya Mudunkotuwa
 */
public interface Auditable {

	String getCreatedBy();

	void setCreatedBy(String createdBy);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);


}
