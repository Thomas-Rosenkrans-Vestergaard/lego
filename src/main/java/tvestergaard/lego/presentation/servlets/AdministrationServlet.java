package tvestergaard.lego.presentation.servlets;

import tvestergaard.lego.logic.OrderFacade;
import tvestergaard.lego.presentation.Authentication;
import tvestergaard.lego.presentation.Notifications;
import tvestergaard.lego.presentation.Presentation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administration")
public class AdministrationServlet extends HttpServlet
{

    private final OrderFacade orderFacade = new OrderFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Authentication authentication = new Authentication(req, resp);
        Notifications  notifications  = Presentation.notifications(req);

        if (!authentication.isAdministrator()) {
            notifications.error("You must be an administrator to access the profile page.");
            authentication.redirect("administration");
            return;
        }

        req.setAttribute("member", authentication.getMember());
        req.setAttribute("orders", orderFacade.select());
        req.getRequestDispatcher("/WEB-INF/administration.jsp").forward(req, resp);
    }
}
