package tvestergaard.lego.presensation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/house")
public class HouseServlet extends HttpServlet
{

    /**
     * Calculate the bricks needed to build the house described by the provided GET parameters.
     *
     * @param req  The incoming request.
     * @param resp The response to the requester.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Presentation.notifications(req);
        req.getRequestDispatcher("/WEB-INF/house.jsp").forward(req, resp);
    }
}
