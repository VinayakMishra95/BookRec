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

import dei.webapp.database.DeleteUserDAO;
import dei.webapp.database.LoginDAO;
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
public final class DeleteUserServlet extends AbstractDatabaseServlet {

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
		LogContext.setAction(Actions.DELETE_USER);

		// request parameters
		String name = null;
		String email = null;
		String password = null;
		String requester = (String) req.getSession().getAttribute("username");

		// model
		User e = null;
		User u = null;
		Message m = null;

		try {
			// retrieves the request parameters
			name = req.getParameter("username");
			email = req.getParameter("email");
			password = req.getParameter("password");

			if (email==null || email.isEmpty() ||
					password==null || password.isEmpty() ||
					name==null || name.isEmpty()){

				m = new Message("Some fields are empty","E200","Missing fields");
				LOGGER.error("Problems with fields: {}", m.getMessage());

			}
			else if (!name.equals(requester)){
				m = new Message("User and session are different","E303","User "+requester+" wants to delete "+name+" .");
				LOGGER.error("User {} wanted to delete another user", requester);
			}
			else {
				// set the name of the user as the resource in the log context
				// at this point we know it is a valid integer
				LogContext.setResource(req.getParameter("username"));

				// creates a new user from the request parameters cchxiara
				e = new User(name, email, password);
				int to_del = new DeleteUserDAO(getConnection(), e).access().getOutputParam();
				
				if (to_del == 1){
                    m = new Message(String.format("Account successfully deleted. Goodbye %s !", name));
				    LOGGER.info("User %s successfully deleted from the database.", name);
				}
				else if (to_del == 0){
				    m = new Message("Cannot delete user.","E401", "Wrong parameters.");
				    LOGGER.error("User {} not deleted, wrong parameters.", name);
				}
				else {
				    m = new Message("Cannot delete user.","E505", "Something went wrong.");
				    LOGGER.error("User {} not deleted, unknown error.", name);
				}
			}

		} catch (NumberFormatException ex) {
			m = new Message(
					"Cannot delete the user. Invalid input parameters: email, user and password must be string.", "E100", ex.getMessage());
			LOGGER.error(
					"Cannot delete the user. Invalid input parameters: email, user and password must be string.", ex);
		} catch (SQLException ex) {
				m = new Message("Cannot delete the user: unexpected error while accessing the database.", "E200", ex.getMessage());
				LOGGER.error("Cannot delete the user: unexpected error while accessing the database.", ex);
			}

		try {
			// stores the deleted user and the message as a request attribute
			req.setAttribute("user", e);
			req.setAttribute("message", m);

			// forwards the control to the signup-result JSP
			req.getRequestDispatcher("/jsp/delete-user-result.jsp").forward(req, res);

		} catch (Exception ex) {
			LOGGER.error(new StringFormattedMessage("Unable to call JSP when deleting user %s.", name), ex);
			throw ex;
		} finally {
			LogContext.removeIPAddress();
			LogContext.removeAction();
			LogContext.removeResource();
		}

	}

}
