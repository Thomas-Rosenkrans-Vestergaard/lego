package tvestergaard.lego.presentation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet
{

    /**
     * Shows the /profile page where members can see their profile information and view their previously placed orders.
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
        Notifications  notifications  = Presentation.notifications(req);

        if (!authentication.isMember()) {
            notifications.error("You must be authenticated to access the profile page.");
            authentication.redirect("profile");
            return;
        }

        req.setAttribute("member", authentication.getMember());
        req.getRequestDispatcher("/WEB-INF/profile.jsp").forward(req, resp);
    }
}
