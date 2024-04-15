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

package unipd.webapp.project.servlet;

import unipd.webapp.project.database.CreateUserDAO;
import unipd.webapp.project.resource.Actions;
import unipd.webapp.project.resource.User;
import unipd.webapp.project.resource.LogContext;
import unipd.webapp.project.resource.Message;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		LogContext.setIPAddress(req.getRemoteAddr());
		LogContext.setAction(Actions.CREATE_USER);

		// request parameters
		String name = null;
		String email = null;
		String password = null;

		// model
		User e = null;
		Message m = null;

		try {
			// retrieves the request parameters
			name = req.getParameter("username");
			email = req.getParameter("email");
			password = req.getParameter("password");

			// set the name of the user as the resource in the log context
			// at this point we know it is a valid integer
			LogContext.setResource(req.getParameter("username"));

			// creates a new user from the request parameters
			e = new User(name, email, password);

			// creates a new object for accessing the database and stores the user
			new CreateUserDAO(getConnection(), e).access();

			m = new Message(String.format("User %d successfully created.", name));

			LOGGER.info("User %d successfully created in the database.", name);

		} catch (NumberFormatException ex) {
			m = new Message(
					"Cannot create the user. Invalid input parameters: name, age, and salary must be integer.",
					"E100", ex.getMessage());

			LOGGER.error(
					"Cannot create the user. Invalid input parameters: name, age, and salary must be integer.",
					ex);
		} catch (SQLException ex) {
			if ("23505".equals(ex.getSQLState())) {
				m = new Message(String.format("Cannot create the user: user %d already exists.", name), "E300",
						ex.getMessage());

				LOGGER.error(
						new StringFormattedMessage("Cannot create the user: user %d already exists.", name),
						ex);
			} else {
				m = new Message("Cannot create the user: unexpected error while accessing the database.", "E200",
						ex.getMessage());

				LOGGER.error("Cannot create the user: unexpected error while accessing the database.", ex);
			}
		}

		try {
			// set the MIME media type of the response
			res.setContentType("text/html; charset=utf-8");

			// get a stream to write the response
			PrintWriter out = res.getWriter();

			// write the HTML page
			out.printf("<!DOCTYPE html>%n");

			out.printf("<html lang=\"en\">%n");
			out.printf("<head>%n");
			out.printf("<meta charset=\"utf-8\">%n");
			out.printf("<title>Create User</title>%n");
			out.printf("</head>%n");

			out.printf("<body>%n");
			out.printf("<h1>Create User</h1>%n");
			out.printf("<hr/>%n");

			if (m.isError()) {
				out.printf("<ul>%n");
				out.printf("<li>error code: %s</li>%n", m.getErrorCode());
				out.printf("<li>message: %s</li>%n", m.getMessage());
				out.printf("<li>details: %s</li>%n", m.getErrorDetails());
				out.printf("</ul>%n");
			} else {
				out.printf("<p>%s</p>%n", m.getMessage());
				out.printf("<ul>%n");
				out.printf("<li>Username: %s</li>%n", e.getName());
				out.printf("<li>E-mail: %s</li>%n", e.getEmail());
				out.printf("</ul>%n");
			}

			out.printf("</body>%n");

			out.printf("</html>%n");

			// flush the output stream buffer
			out.flush();

			// close the output stream
			out.close();
		} catch (IOException ex) {
			LOGGER.error(new StringFormattedMessage("Unable to send response when creating user %d.", name), ex);
			throw ex;
		} finally {
			LogContext.removeIPAddress();
			LogContext.removeAction();
			LogContext.removeResource();
		}

	}

}
