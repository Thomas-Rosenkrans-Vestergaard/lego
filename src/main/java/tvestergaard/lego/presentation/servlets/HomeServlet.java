package tvestergaard.lego.presentation.servlets;

import tvestergaard.lego.data.orders.MysqlOrderDAO;
import tvestergaard.lego.data.orders.OrderBuilder;
import tvestergaard.lego.data.orders.Status;
import tvestergaard.lego.logic.BrickFacade;
import tvestergaard.lego.logic.OrderFacade;
import tvestergaard.lego.logic.ProductionConnection;
import tvestergaard.lego.logic.building.*;
import tvestergaard.lego.logic.geometry.Position;
import tvestergaard.lego.presentation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static tvestergaard.lego.logic.building.BricklayerException.Reason.*;

@WebServlet(urlPatterns = {"/home", ""})
public class HomeServlet extends HttpServlet
{

    private OrderFacade orderFacade = new OrderFacade(new MysqlOrderDAO(ProductionConnection.source()));

    /**
     * Displays the home page, where visitors can generate lego houses.
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Authentication authentication = new Authentication(req, resp);
        Notifications  notifications  = Presentation.notifications(req);

        if (!authentication.isMember()) {
            notifications.error("You must be authenticated to place orders.");
            authentication.redirect("home");
            return;
        }

        notifications.record();
        HouseParametersHelper validator = new HouseParametersHelper();
        validator.validateParameters(req, notifications);

        if (notifications.hasNew()) {
            resp.sendRedirect("home");
            return;
        }

        Parameters parameters = new Parameters(req);
        Window     window     = null;
        Door       door       = null;

        if (parameters.isPresent("door") && parameters.getString("door").equals("on")) {
            door = new Door(
                    parameters.getInt("door-width"),
                    parameters.getInt("door-height"),
                    Position.of(parameters.getInt("door-x"), 0),
                    getSide(parameters.getInt("door-side"))
            );
        }

        if (parameters.isPresent("window") && parameters.getString("window").equals("on")) {
            window = new Window(
                    parameters.getInt("window-width"),
                    parameters.getInt("window-height"),
                    Position.of(parameters.getInt("window-x"), parameters.getInt("window-y")),
                    getSide(parameters.getInt("window-side"))
            );
        }

        try {

            int width  = parameters.getInt("width");
            int height = parameters.getInt("height");
            int depth  = parameters.getInt("depth");

            House              house     = BrickFacade.build(width, height, depth, door, window);
            HouseJsonConverter converter = new HouseJsonConverter();
            OrderBuilder       builder   = new OrderBuilder(authentication.getMember(), width, height, depth, converter.convert(house), Status.PLACED, null);
            orderFacade.create(builder);

            notifications.success("Your order was placed successfully.");
            resp.sendRedirect("profile");

        } catch (BricklayerException e) {

            for (BricklayerException.Reason reason : e.getReasons()) {

                if (reason == WIDTH_LESS_THAN_4)
                    notifications.error("The width of the house must be at least 4.");

                if (reason == HEIGHT_LESS_THAN_1)
                    notifications.error("The height of the house must be at least 1.");

                if (reason == DEPTH_LESS_THAN_4)
                    notifications.error("The depth of the house must be at least 4.");

                if (reason == DOOR_WIDTH_LESS_THAN_1)
                    notifications.error("The width of the door must be at least 1.");

                if (reason == DOOR_HEIGHT_LESS_THAN_1)
                    notifications.error("The height of the door must be at least 1.");

                if (reason == DOOR_OUT_OF_BOUNDS_TOP)
                    notifications.error("The door is out of bounds on the top of the wall.");

                if (reason == DOOR_OUT_OF_BOUNDS_LEFT)
                    notifications.error("The door is out of bounds on the left side of the wall.");

                if (reason == DOOR_OUT_OF_BOUNDS_RIGHT)
                    notifications.error("The door is out of bounds on the right side of the wall.");

                if (reason == WINDOW_WIDTH_LESS_THAN_1)
                    notifications.error("The width of the window must be at least 1.");

                if (reason == WINDOW_HEIGHT_LESS_THAN_1)
                    notifications.error("The height of the window must be at least 1.");

                if (reason == WINDOW_OUT_OF_BOUNDS_TOP)
                    notifications.error("The window is out of bounds on the top of the wall.");

                if (reason == WINDOW_OUT_OF_BOUNDS_LEFT)
                    notifications.error("The window is out of bounds on the left side of the wall.");

                if (reason == WINDOW_OUT_OF_BOUNDS_RIGHT)
                    notifications.error("The window is out of bounds on the right side of the wall.");

                if (reason == DOOR_WINDOW_COLLISION)
                    notifications.error("The window and door collides.");
            }

            resp.sendRedirect("home");
            return;
        }
    }

    private Side getSide(int code)
    {

        if (code == 1)
            return Side.FRONT;

        if (code == 2)
            return Side.LEFT;

        if (code == 3)
            return Side.RIGHT;

        if (code == 4)
            return Side.BACK;

        throw new UnsupportedOperationException();
    }
}
