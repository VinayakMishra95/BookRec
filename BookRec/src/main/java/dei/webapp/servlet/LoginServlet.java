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
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		LogContext.setIPAddress(req.getRemoteAddr());
		LogContext.setAction(Actions.LOGIN_USER);
		
		//request parameters
        String name = null;
        String password = null;
        
        // model
        User user = null;
        Message m = null;
        
        // check for password complexity
		String regex_psw = "^(?=.*[A-Z])(?=.*[0-9]).{8,}$";

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
                m = new Message("The user does not exist","E200","Missing user");
                LOGGER.error("Problems with user: {}", m.getMessage());
            }
			else{
                m = new Message("Login successful.");
<<<<<<< HEAD:BookRec/src/main/java/servlet/LoginServlet.java
                LOGGER.info("The user %s logged in.", name);
=======
                LOGGER.info("The user {} logged in.",user.getName());
                
                //if login is successful, start a session for this user
>>>>>>> master:BookRec/src/main/java/dei/webapp/servlet/LoginServlet.java
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
			// set the MIME media type of the response
			res.setContentType("text/html; charset=utf-8");

			// get a stream to write the response
			PrintWriter out = res.getWriter();

			// write the HTML page
			out.printf("<!DOCTYPE html>%n");

			out.printf("<html lang=\"en\">%n");
			out.printf("<head>%n");
			out.printf("<meta charset=\"utf-8\">%n");
			out.printf("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/form-result.css\">%n");
			out.printf("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">%n");
			out.printf("<title>BookRec Login</title>%n");
			out.printf("</head>%n");

			out.printf("<body>%n");
			out.printf("<h1>User Login</h1>%n");
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
				out.printf("<li>Username: %s</li>%n", user.getName());
				out.printf("<li>E-mail: %s</li>%n", user.getEmail());
				out.printf("</ul>%n");
			}
			// Add the button to go back to home.jsp
			out.printf("<button onclick=\"window.location.href='jsp/home.jsp'\">Go to Home</button>%n");

			out.printf("</body>%n");

			out.printf("</html>%n");

			// flush the output stream buffer
			out.flush();

			// close the output stream
			out.close();
		} catch (IOException ex) {
			LOGGER.error(new StringFormattedMessage("Unable to send response when logging in user %s.", name), ex);
			throw ex;
		} finally {
			LogContext.removeIPAddress();
			LogContext.removeAction();
			LogContext.removeResource();
		}

	}

}
