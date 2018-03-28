package tvestergaard.lego.presentation.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet
{

    /**
     * Shows a generic error page in case an unhandled exception is raised on another page.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/WEB-INF/error.jsp");
    }
}
