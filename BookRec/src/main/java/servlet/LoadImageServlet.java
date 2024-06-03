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

import dei.webapp.database.BookCoverDAO;
import dei.webapp.resource.Actions;
import dei.webapp.resource.LogContext;
import dei.webapp.resource.Message;
import dei.webapp.resource.Book;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.io.OutputStream;

/**
 * Used to show images on browser.
 *
 * @version 1.00
 * @since 1.00
 */
public final class LoadImageServlet extends AbstractDatabaseServlet {
    /**
	 * Send the images received to the JSP needing them.
	 *
	 * @param req the HTTP request from the client.
	 * @param res the HTTP response from the server.
	 *
	 * @throws IOException if any error occurs in the client/server communication.
	 */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        String isbn = null;
        try {
            isbn = req.getParameter("book-cover");
            Book b = new BookCoverDAO(getConnection(), isbn).access().getOutputParam();
            res.setContentType(b.getCoverType());
            res.getOutputStream().write(b.getCover());
            res.getOutputStream().flush();
            LOGGER.info("Cover of book %s successfully sent.", isbn);

        } catch (Exception ex) {
            if (isbn == null) {
                LOGGER.error("Unable to retrieve which book's cover to load.", ex);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            else {
                LOGGER.error("Unable to load the cover of the book.", ex);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeUser();
        }

    }
}
