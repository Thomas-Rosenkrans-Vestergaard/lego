package tvestergaard.lego.presentation.servlets;

import tvestergaard.lego.presentation.Authentication;
import tvestergaard.lego.presentation.Presentation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home", ""})
public class HomeServlet extends HttpServlet
{

    /**
     * Displays the home page.
     *
     * @param req  The incoming request.
     * @param resp The response to the requester.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Authentication authentication = new Authentication(req, resp);
        Presentation.notifications(req);
        req.setAttribute("member", authentication.getMember());
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }
}