package unipd.webapp.project.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Show error page when unexistent page are requested.
 *
 * @version 1.00
 * @since 1.00
 */
public final class Error404Servlet extends AbstractDatabaseServlet {

	/**
	 * Generates an error page when unmapped link are visited.
	 *
	 * @param req the HTTP request from the client.
	 * @param res the HTTP response from the server.
	 *
	 * @throws IOException if any error occurs in the client/server communication.
	 */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Generate error page
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
		out.printf("<title>BookRec Error</title>%n");
		out.printf("</head>%n");
        out.println("<h1>Error 404: page not found</h1>");
        out.println("<p>This page doesn't exist, check the link and retry.</p>");
        out.println("</body></html>");
    }
}

