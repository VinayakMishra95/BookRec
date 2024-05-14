package dei.webapp.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TestForwardServlet extends HttpServlet {
    
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
