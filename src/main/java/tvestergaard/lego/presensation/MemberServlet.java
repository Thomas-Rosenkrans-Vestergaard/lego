package tvestergaard.lego.presensation;

import tvestergaard.lego.database.members.EmailCollisionException;
import tvestergaard.lego.database.members.InvalidEmailException;
import tvestergaard.lego.database.members.Member;
import tvestergaard.lego.logic.MemberFacade;
import tvestergaard.lego.logic.Notifications;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/member")
public class MemberServlet extends HttpServlet
{

    private static final String ACTION_LOGIN    = "login";
    private static final String ACTION_REGISTER = "register";

    /**
     * Renders the /member page, where users can create a user account or log in to an existing user account.
     *
     * @param req  The incoming request.
     * @param resp The response to the requester.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/WEB-INF/member.jsp").forward(req, resp);
    }

    /**
     * Receives form data submitted from both the registration and login form on the /member page.
     *
     * @param req  The incoming request.
     * @param resp The response to the requester.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Notifications notifications = Presentation.notifications(req);

        try {

            String action = req.getParameter("action");

            if (ACTION_LOGIN.equals(action)) {
                Member member = MemberFacade.login(req.getParameter("email"), req.getParameter("password"));
                if (member == null) {
                    notifications.error("Incorrect email or password.");
                    resp.sendRedirect("member");
                    return;
                }

                req.getSession().setAttribute("member", member);
                resp.sendRedirect("profile");
                return;
            }

            if (ACTION_REGISTER.equals(action)) {
                Member member = MemberFacade.register(req.getParameter("email"), req.getParameter("password"));
                if (member == null) {
                    notifications.error("An error occurred, your user account was not created.");
                    resp.sendRedirect("member");
                    return;
                }

                notifications.success("Your user account was successfully created.");
                req.getSession().setAttribute("member", member);
                resp.sendRedirect("profile");
            }

        } catch (InvalidEmailException e) {
            notifications.error("The provided email address is not valid.");
            resp.sendRedirect("member");
        } catch (EmailCollisionException e) {
            notifications.error("The provided email address is already in use on our site.");
            resp.sendRedirect("member");
        }
    }
}
