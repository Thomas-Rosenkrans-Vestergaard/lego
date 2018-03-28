package tvestergaard.lego.presentation.servlets;

import tvestergaard.lego.data.orders.MysqlOrderDAO;
import tvestergaard.lego.data.orders.Order;
import tvestergaard.lego.logic.OrderFacade;
import tvestergaard.lego.logic.ProductionConnection;
import tvestergaard.lego.presentation.Authentication;
import tvestergaard.lego.presentation.Notifications;
import tvestergaard.lego.presentation.Parameters;
import tvestergaard.lego.presentation.Presentation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet
{

    private final OrderFacade orderFacade = new OrderFacade(new MysqlOrderDAO(ProductionConnection.source()));

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
        Parameters     parameters     = new Parameters(req);

        if (!authentication.isMember()) {
            notifications.error("You must be authenticated to access the profile page.");
            authentication.redirect("profile");
            return;
        }

        if (parameters.isPresent("order")) {
            Order order = orderFacade.select(parameters.getInt("order"));
            if (order == null) {
                resp.sendRedirect("home");
                return;
            }
            req.setAttribute("order", order);
            req.setAttribute("back", "profile");
            req.getRequestDispatcher("/WEB-INF/order.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("member", authentication.getMember());
        req.setAttribute("orders", orderFacade.select(authentication.getMember()));
        req.getRequestDispatcher("/WEB-INF/profile.jsp").forward(req, resp);
    }
}
