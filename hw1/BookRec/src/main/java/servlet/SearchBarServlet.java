package unipd.webapp.project.servlet;

import unipd.webapp.project.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public final class SearchBarServlet extends AbstractDatabaseServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Get the search criteria from the request
        String searchType = req.getParameter("options");

        // Determine which servlet to dispatch the request to based on search criteria
        HttpServlet servletToDispatch;
        if ("user".equals(searchType)) {
            servletToDispatch = new SearchUserServlet();
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

