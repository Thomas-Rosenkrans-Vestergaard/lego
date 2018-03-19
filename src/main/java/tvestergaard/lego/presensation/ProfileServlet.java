package tvestergaard.lego.presensation;

import tvestergaard.lego.database.members.Member;
import tvestergaard.lego.logic.Notifications;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "profile")
public class ProfileServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Notifications notifications = Presentation.notifications(req);
        Member        member        = (Member) req.getSession().getAttribute("member");

        if (member == null) {
            notifications.error("You must be authenticated before you can access the user page.");
            resp.sendRedirect("member");
            return;
        }

        req.setAttribute("member", member);
        req.getRequestDispatcher("/WEB-INF/member.jsp").forward(req, resp);
    }
}
