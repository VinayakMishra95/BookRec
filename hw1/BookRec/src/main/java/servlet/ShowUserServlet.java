/*
 * Copyright 2018-2023 University of Padua, Italy
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

import unipd.webapp.project.database.SearchUserDAO;
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
import java.util.List;

/**
 * Searches users by their name.
 *
 * @version 1.00
 * @since 1.00
 */
public final class ShowUserServlet extends AbstractDatabaseServlet {

	/**
	 * Searches users by their name.
	 *
	 * @param req the HTTP request from the client.
	 * @param res the HTTP response from the server.
	 *
	 * @throws IOException if any error occurs in the client/server communication.
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		LogContext.setIPAddress(req.getRemoteAddr());
		LogContext.setAction(Actions.SEARCH_USER);


		// request parameter
		String name = null;

		// model
		List<User> el = null;
		Message m = null;

		try {

			// retrieves the request parameter
			name = req.getParameter("search");

			// creates a new object for accessing the database and searching the users
			el = new SearchUserDAO(getConnection(), name).access().getOutputParam();

			m = new Message("Users successfully searched.");

			LOGGER.info("Users successfully searched by name %s.", name);

		} catch (NumberFormatException ex) {
			m = new Message("Cannot search for users. Invalid input parameters: name must be string.", "E100",
					ex.getMessage());

			LOGGER.error("Cannot search for users. Invalid input parameters: name must be string.", ex);
		} catch (SQLException ex) {
			m = new Message("Cannot search for users: unexpected error while accessing the database.", "E200",
					ex.getMessage());

			LOGGER.error("Cannot search for users: unexpected error while accessing the database.", ex);
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
			out.printf("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style1.css\">%n");
			out.printf("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">%n");
			out.printf("<title>BookRec Search</title>%n");
			out.printf("</head>%n");

			out.printf("<body>%n");
			out.printf("<h1>Search User Results</h1>%n");
			out.printf("<hr/>%n");

			if (m.isError()) {
				out.printf("<ul>%n");
				out.printf("<li>error code: %s</li>%n", m.getErrorCode());
				out.printf("<li>message: %s</li>%n", m.getMessage());
				out.printf("<li>details: %s</li>%n", m.getErrorDetails());
				out.printf("</ul>%n");
			} else {
				out.printf("<p>%s</p>%n", m.getMessage());

				out.printf("<table>%n");
				out.printf("<tr>%n");
				out.printf("<td>Username</td>%n");
				out.printf("</tr>%n");

				for (User e : el) {
					out.printf("<tr>%n");
					out.printf("<td>%s</td>%n", e.getName());
					out.printf("</tr>%n");
				}
				out.printf("</table>%n");
			}

			out.printf("</body>%n");

			out.printf("</html>%n");

			// flush the output stream buffer
			out.flush();

			// close the output stream
			out.close();
		} catch (IOException ex) {
			LOGGER.error(new StringFormattedMessage("Unable to send response when creating user %s.", name), ex);
			throw ex;
		} finally {
			LogContext.removeIPAddress();
			LogContext.removeAction();
			LogContext.removeUser();
		}
	}

}
