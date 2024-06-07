package dei.webapp.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import org.apache.logging.log4j.message.StringFormattedMessage;
import dei.webapp.resource.LogContext;

/**
 * Terminate session of a user.
 *
 * @version 1.00
 * @since 1.00
 */
 
public class LogoutServlet extends AbstractDatabaseServlet {
    /**
	 * Retrieve the session and, if it exist, it terminates it.
	 *
	 * @param req the HTTP request from the client.
	 * @param res the HTTP response from the server.
	 *
	 * @throws IOException if any error occurs in the client/server communication.
	 */
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        //get the session if it exist
        HttpSession session = req.getSession(false);
        //if it exist, perform logout
        if (session != null) {
            String outName = (String) session.getAttribute("username");
            session.invalidate();
            LOGGER.info("Session of user %s terminated.", outName);
        }
        //Redirect to the home page of the website
        res.sendRedirect("jsp/home.jsp");
    }
}

