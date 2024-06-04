package dei.webapp.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet that handles the forwarding of a request to a JSP page.
 */
public class TestForwardServlet extends HttpServlet {

    /**
     * Handles the HTTP POST request.
     *
     * @param request  the HttpServletRequest object that contains the request
     *                 the client has made of the servlet
     * @param response the HttpServletResponse object that contains the response
     *                 the servlet sends to the client
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException      if an input or output error is detected when the
     *                          servlet handles the request
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Get the parameter value from the request
        String test = request.getParameter("test");
        
        // Set the parameter value as an attribute in the request
        request.setAttribute("testing", test);
        
        // Forward the request to the JSP page
        request.getRequestDispatcher("/jsp/test-result.jsp").forward(request, response);
    }
}
