package tvestergaard.lego.presentation.servlets;

import tvestergaard.lego.data.orders.MysqlOrderDAO;
import tvestergaard.lego.data.orders.Order;
import tvestergaard.lego.data.orders.OrderBuilder;
import tvestergaard.lego.data.orders.Status;
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
import java.sql.Timestamp;

@WebServlet("/administration")
public class AdministrationServlet extends HttpServlet
{

    /**
     * The {@link OrderFacade} used to query the persistent storage.
     */
    private final OrderFacade orderFacade = new OrderFacade(new MysqlOrderDAO(ProductionConnection.source()));

    /**
     * Shows the /administration page, where administrators can view orders and change their shipping date. To access
     * the page, the user must be authenticated and an administrator or higher.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Authentication authentication = new Authentication(req, resp);
        Notifications  notifications  = Presentation.notifications(req);
        Parameters     parameters     = new Parameters(req);

        if (!authentication.isAdministrator()) {
            notifications.error("You must be an administrator to access the profile page.");
            authentication.redirect("administration");
            return;
        }

        if (parameters.isPresent("order")) {
            Order order = orderFacade.select(parameters.getInt("order"));
            if (order == null) {
                resp.sendRedirect("home");
                return;
            }
            req.setAttribute("order", order);
            req.setAttribute("back", "administration");
            req.getRequestDispatcher("/WEB-INF/order.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("member", authentication.getMember());
        req.setAttribute("orders", orderFacade.select());
        req.getRequestDispatcher("/WEB-INF/administration.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Authentication authentication = new Authentication(req, resp);
        Notifications  notifications  = Presentation.notifications(req);

        if (!authentication.isAdministrator()) {
            notifications.error("You must be an administrator to change shipping status.");
            authentication.redirect("administration");
            return;
        }

        Parameters parameters = new Parameters(req);
        if (!parameters.isPresent("id") || parameters.notInt("id")) {
            notifications.error("Missing or malformed id parameter");
            resp.sendRedirect("administration");
            return;
        }

        Order order = orderFacade.select(parameters.getInt("id"));

        if (order == null) {
            notifications.error("Unknown order record.");
            resp.sendRedirect("administration");
            return;
        }

        OrderBuilder builder = new OrderBuilder(order);
        builder.setShippedAt(new Timestamp(System.currentTimeMillis()));
        builder.setStatus(Status.SHIPPED);
        orderFacade.update(parameters.getInt("id"), builder);
        notifications.success("The timestamp was successfully set.");
        resp.sendRedirect("administration");
    }
}
