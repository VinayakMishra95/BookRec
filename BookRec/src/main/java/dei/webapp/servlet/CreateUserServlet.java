/*
 * Copyright 2018 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dei.webapp.servlet;

import dei.webapp.database.CreateUserDAO;
import dei.webapp.resource.Actions;
import dei.webapp.resource.User;
import dei.webapp.resource.LogContext;
import dei.webapp.resource.Message;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Creates a new user into the database.
 *
 * @version 1.00
 * @since 1.00
 */
public final class CreateUserServlet extends AbstractDatabaseServlet {

	/**
	 * Creates a new user into the database.
	 *
	 * @param req the HTTP request from the client.
	 * @param res the HTTP response from the server.
	 *
	 * @throws IOException if any error occurs in the client/server communication.
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException {

		LogContext.setIPAddress(req.getRemoteAddr());
		LogContext.setAction(Actions.CREATE_USER);

		// request parameters
		String name = null;
		String email = null;
		String password = null;

		// model
		User e = null;
		Message m = null;

		String regex_psw = "^(?=.*[A-Z])(?=.*[0-9]).{8,}$";

		try {
			// retrieves the request parameters
			name = req.getParameter("username");
			email = req.getParameter("email");
			password = req.getParameter("password");

			if (email==null|| email.isEmpty() ||
					password==null|| password.isEmpty() ||
					name==null|| name.isEmpty()){

				m = new Message("Some fields are empty","E200","Missing fields");
				LOGGER.error("problems with fields: {}", m.getMessage());

			}
			// check password is compliant
			/*
			else if (!password.matches(regex_psw)){
				m = new Message("This password is not compliant","E200","Password not compliant");

				LOGGER.error("problems with fields: {}", m.getMessage());

			}
			*/
			else {
				// set the name of the user as the resource in the log context
				// at this point we know it is a valid integer
				LogContext.setResource(req.getParameter("username"));

				// creates a new user from the request parameters
				e = new User(name, email, password);

				// creates a new object for accessing the database and stores the user
				new CreateUserDAO(getConnection(), e).access();

				m = new Message(String.format("Account successfully created. Welcome %s !", name));
				LOGGER.info("User %s successfully created in the database.", name);
				
				// creates a session for the user
				HttpSession session = req.getSession();
                session.setAttribute("username", name);
                LOGGER.info("Session for user %s created.", name);
			}

		} catch (NumberFormatException ex) {
			m = new Message(
					"Cannot create the user. Invalid input parameters: email, user and password must be string.",
					"E100", ex.getMessage());

			LOGGER.error(
					"Cannot create the user. Invalid input parameters: email, user and password must be string.",
					ex);
		} catch (SQLException ex) {
			if ("23505".equals(ex.getSQLState())) {
				m = new Message(String.format("Cannot create the user: user %s already exists.", name), "E300",
						ex.getMessage());

				LOGGER.error(
						new StringFormattedMessage("Cannot create the user: user %s already exists.", name),
						ex);
			} else {
				m = new Message("Cannot create the user: unexpected error while accessing the database.", "E200",
						ex.getMessage());

				LOGGER.error("Cannot create the user: unexpected error while accessing the database.", ex);
			}
		}

		try {
			// stores the user and the message as a request attribute
			req.setAttribute("user", e);
			req.setAttribute("message", m);

			// forwards the control to the signup-result JSP
			req.getRequestDispatcher("/jsp/signup-result.jsp").forward(req, res);

		} catch (Exception ex) {
			LOGGER.error(new StringFormattedMessage("Unable to call JSP when creating user %s.", name), ex);
			throw ex;
		} finally {
			LogContext.removeIPAddress();
			LogContext.removeAction();
			LogContext.removeResource();
		}

	}

}
