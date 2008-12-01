package quizfun.view.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openid4java.discovery.Discovery;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.providers.encoding.ShaPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import quizfun.model.entity.User;
import quizfun.model.exception.UserNotFoundException;
import quizfun.model.service.UserService;

/**
 * MIDlet Login Servlet
 * 
 * @author M. Isuru Tharanga Chrishantha Perera
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 9209272103939210472L;

	private final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (logger.isTraceEnabled()) {
			logger.trace("Processing Login Request...");
		}

		// Retrieve the session object corresponding to this request.
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		if (logger.isTraceEnabled()) {
			if (session.isNew()) {
				logger.trace("New session created: {}", sessionId);
			} else {
				logger.trace("Used existing session: {}", sessionId);
			}
		}

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (logger.isTraceEnabled()) {
			logger.trace("Username: {}", username);
			logger.trace("Password: *******");
		}

		try {
			Identifier identifier = Discovery.parseIdentifier(username);
			username = identifier.getIdentifier();
			if (logger.isTraceEnabled()) {
				logger.trace("Parsed Username: {}", username);
			}
		} catch (DiscoveryException e) {
			if (logger.isTraceEnabled()) {
				logger.trace("Error parsing identifier", e);
			}
		}

		UserService userService = (UserService) ctx.getBean("userService");

		boolean loginFailed = false;
		final String invalidLogin = "Invalid Username or Password.";
		String message = null;
		User user = null;
		
		try {
			user = userService.findUser(username);
			ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
			if (passwordEncoder.isPasswordValid(user.getPassword(), password, username)) {
				loginFailed = false;
				message = "Login Successful.";
			} else {
				loginFailed = true;
				message = invalidLogin;
				user = null;
			}
		} catch (UserNotFoundException e) {
			if (logger.isTraceEnabled()) {
				logger.trace("Username \"{}\" not found.", username);
			}
			loginFailed = true;
			message = invalidLogin;
		}
		
		session.setAttribute("User", user);

		if (logger.isTraceEnabled()) {
			logger.trace(message);
		}

		out.write("<login>");
		out.write("<login-failed>");
		out.write(String.valueOf(loginFailed));
		out.write("</login-failed>");
		out.write("<message>");
		out.write(message);
		out.write("</message>");
		out.write("<session-id>");
		out.write(sessionId);
		out.write("</session-id>");
		out.write("</login>");
		out.close();
	}

}