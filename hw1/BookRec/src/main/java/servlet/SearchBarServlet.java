package unipd.webapp.project.servlet;

import unipd.webapp.project.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Decide which servlet to call for searching.
 * 
 * @version 1.00
 * @since 1.00
 */
public final class SearchBarServlet extends AbstractDatabaseServlet {
    

	/**
	 * Searches authors by their name.
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
    AbstractDatabaseServlet servletToDispatch;
    if ("user".equals(searchType)) {
        servletToDispatch = new ShowUserServlet();
    }
    else if ("book".equals(searchType)) {
        servletToDispatch = new SearchBookServlet();
    }
    else if ("author".equals(searchType)) {
        servletToDispatch = new SearchAuthorServlet();
    }
    else {
        // Handle unknown search types
        res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown search type");
        return;
    }

    // Dispatch the request to the appropriate servlet
    servletToDispatch.service(req, res);
    }
}
