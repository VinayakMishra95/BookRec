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

import unipd.webapp.project.database.LoginDAO;
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

        User user = null;
        Message m = null;
        String name = null;

		String regex_psw = "^(?=.*[A-Z])(?=.*[0-9]).{8,}$";

        try {
            //take from the request, the parameters (name and password)
            name = req.getParameter("name");
            String password = req.getParameter("password");
            LOGGER.info("user {} is trying to login",name);
            User u = new User(name, "email@example.com", password);
            
            // try to find the user in the database
            user = new LoginDAO(getConnection(), u).access().getOutputParam();
            
            //the LoginDAO will tell us if the name exists and the password matches
            if (user == null){
                //if not, tell it to the user
                m = new Message("The user does not exist","E200","Missing user");
                LOGGER.error("Problems with user: {}", m.getMessage());
            }
			else if (!password.matches(regex_psw)){
				// if the password is not compliant with the format
				m = new Message("The password is not compliant","E200","Password not compliant");
				LOGGER.error("problems with fields: {}", m.getMessage());
			}
			else{
                m = new Message("Login success");
                LOGGER.info("the STUDENT {} LOGGED IN",user.getName());
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
			out.printf("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style1.css\">%n");
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
			LogContext.removeResource();
		}

	}

}
