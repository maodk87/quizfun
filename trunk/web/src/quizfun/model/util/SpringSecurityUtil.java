package quizfun.model.util;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import quizfun.model.entity.User;

/**
 * @author M. Isuru Tharanga Chrishantha Perera
 */
public class SpringSecurityUtil {
	
	public static String getUsername() {
		String username = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object obj = authentication.getPrincipal();
			// Can't obtain UserDetails from the OpenIDAuthenticationToken. 
			// It will only return the identity URL.
			// See http://jira.springframework.org/browse/SEC-931
			if (obj instanceof String) {
				username = (String) obj;
			} else if (obj instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) obj;
				username = userDetails.getUsername();
			} else if (obj instanceof User) {
				User user = (User) obj;
				username = user.getUsername();
			}
		}
		return username;
	}
}
