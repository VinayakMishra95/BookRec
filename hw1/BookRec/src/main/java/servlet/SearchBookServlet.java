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

import unipd.webapp.project.database.SearchBookDAO;
import unipd.webapp.project.resource.Actions;
import unipd.webapp.project.resource.Book;
import unipd.webapp.project.resource.LogContext;
import unipd.webapp.project.resource.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;

import javax.sql.DataSource;

/**
 * Searches books by their title.
 * 
 * @version 1.00
 * @since 1.00
 */
public final class SearchBookServlet extends AbstractDatabaseServlet {

	/**
	 * Searches books by their title.
	 * 
	 * @param req
	 *            the HTTP request from the client.
	 * @param res
	 *            the HTTP response from the server.
	 * 
	 * @throws ServletException
	 *             if any error occurs while executing the servlet.
	 * @throws IOException
	 *             if any error occurs in the client/server communication.
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		LogContext.setIPAddress(req.getRemoteAddr());
		LogContext.setAction(Actions.SEARCH_BOOK);

		// request parameter
		String title = "";

		// model
		List<Book> el = null;
		Message m = null;

		try {

			// retrieves the request parameter
			title = req.getParameter("search");

			// creates a new object for accessing the database and searching the books
			el = new SearchBookDAO(getConnection(), title).access().getOutputParam();

			m = new Message("Books successfully searched.");

			LOGGER.info("Books successfully searched by title %s.", title);

		} catch (NumberFormatException ex) {
			m = new Message("Cannot search for books. Invalid input parameters: title must be string.", "E100",
					ex.getMessage());

			LOGGER.error("Cannot search for books. Invalid input parameters: title must be string.", ex);
		} catch (SQLException ex) {
			m = new Message("Cannot search for books: unexpected error while accessing the database.", "E200",
					ex.getMessage());

			LOGGER.error("Cannot search for books: unexpected error while accessing the database.", ex);
		}


		try {
			// stores the book list and the message as a request attribute
			req.setAttribute("bookList", el);
			req.setAttribute("message", m);

			// forwards the control to the search-book-result JSP
			req.getRequestDispatcher("/jsp/search-book-result.jsp").forward(req, res);

		} catch(Exception ex) {
			LOGGER.error(new StringFormattedMessage("Unable to send response when creating book %s.", title), ex);
			throw ex;
		} finally {
			LogContext.removeIPAddress();
			LogContext.removeAction();
			LogContext.removeUser();
		}
	}

}
