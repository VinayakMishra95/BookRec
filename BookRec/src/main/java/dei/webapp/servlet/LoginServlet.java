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
 * Searches and login a specific user in the database.
 *
 * @version 1.00
 * @since 1.00
 */
public final class LoginServlet extends AbstractDatabaseServlet {

	/**
	 * Searches the user to login in the database.
	 *
	 * @param req the HTTP request from the client.
	 * @param res the HTTP response from the server.
	 *
	 * @throws IOException if any error occurs in the client/server communication.
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException {

		LogContext.setIPAddress(req.getRemoteAddr());
		LogContext.setAction(Actions.LOGIN_USER);
		
		//request parameters
        String name = null;
        String password = null;
        
        // model
        User user = null;
        Message m = null;

        try {
            //take from the request, the parameters (name and password)
            name = req.getParameter("name");
            password = req.getParameter("password");
            LOGGER.info("User %s is trying to login", name);
            User u = new User(name, "email@example.com", password);
            
            // try to find the user in the database
            user = new LoginDAO(getConnection(), u).access().getOutputParam();
            
            //the LoginDAO will tell us if the name exists and the password matches
            if (user == null){
                //if not, tell it to the user
                m = new Message("User not found","E200","The user/password match was not found, check parameters and retry");
                LOGGER.error("Problems with user: {}", m.getMessage());
            }
			else{
                m = new Message("Login successful.");
                LOGGER.info("The user %s logged in.", name);
                
                //if login is successful, start a session for this user
                HttpSession session = req.getSession();
                session.setAttribute("username", name);
            }
        } catch (SQLException ex){
            m = new Message("An error occurred SQL","E200",ex.getMessage());
            LOGGER.error("stacktrace:", ex);
        }
        catch (NumberFormatException ex){
            m = new Message("An error occurred handling numbers","E200",ex.getMessage());
            LOGGER.error("stacktrace:", ex);
        }
        
		try {
			// stores the user and the message as a request attribute
			req.setAttribute("user", user);
			req.setAttribute("message", m);

			// forwards the control to the login-result JSP
			req.getRequestDispatcher("/jsp/login-result.jsp").forward(req, res);

		} catch (Exception ex) {
			LOGGER.error(new StringFormattedMessage("Unable to call JSP when logging in user %s.", name), ex);
			throw ex;
		}
		finally {
			LogContext.removeIPAddress();
			LogContext.removeAction();
			LogContext.removeResource();
		}
	}
}
