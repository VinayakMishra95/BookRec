package unipd.webapp.project.servlet;

import unipd.webapp.project.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.ServletException;
import java.io.IOException;

/**
 * Decide which servlet to call for searching.
 * 
 * @version 1.00
 * @since 1.00
 */
public final class SearchBarServlet extends AbstractDatabaseServlet {
    

	/**
	 * Select which servlet to call based on "options" parameter of search bar
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
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Get the search criteria from the request
        String searchType = req.getParameter("options");
        searchType = searchType.toLowerCase();

        // Determine which servlet to dispatch the request to based on search criteria
        if ("user".equals(searchType)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("user");
            dispatcher.forward(req, res);
        }
        else if ("book".equals(searchType)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("book");
            dispatcher.forward(req, res);
        }
        else if ("author".equals(searchType)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("author");
            dispatcher.forward(req, res);
        }
        else {
            // Handle unknown search types
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown search type");
            return;
        }
    }
}
