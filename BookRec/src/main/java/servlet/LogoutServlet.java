package dei.webapp.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class LogoutServlet extends AbstractDatabaseServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("jsp/home.jsp"); // Reindirizza alla pagina principale
    }
}

