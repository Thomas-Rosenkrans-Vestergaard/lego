package tvestergaard.lego.presentation.servlets;

import tvestergaard.lego.data.orders.OrderBuilder;
import tvestergaard.lego.data.orders.Status;
import tvestergaard.lego.logic.BrickFacade;
import tvestergaard.lego.logic.OrderFacade;
import tvestergaard.lego.logic.building.*;
import tvestergaard.lego.logic.geometry.Position;
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

import static tvestergaard.lego.logic.building.BricklayerException.Reason.*;

@WebServlet("/order")
public class OrderServlet extends HttpServlet
{

    private final OrderFacade orderFacade = new OrderFacade();

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
        validateParameters(req, notifications);

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

    private void validateParameters(HttpServletRequest request, Notifications notifications)
    {
        Parameters parameters = new Parameters(request);

        if (parameters.notPresent("width") || parameters.notInt("width") || parameters.notPositiveInt("width")) {
            notifications.error("Incorrectly formatted width value.");
        }

        if (parameters.notPresent("height") || parameters.notInt("height") || parameters.notPositiveInt("height")) {
            notifications.error("Incorrectly formatted height value.");
        }

        if (parameters.notPresent("depth") || parameters.notInt("depth") || parameters.notPositiveInt("depth")) {
            notifications.error("Incorrectly formatted depth value.");
        }

        if (parameters.isPresent("door") && parameters.getString("door").equals("on")) {

            if (parameters.notPresent("door-side") || parameters.isNegativeInt("door-side")) {
                notifications.error("Incorrectly formatted for side.");
            }

            if (parameters.notPresent("door-x") || parameters.notInt("door-x") || parameters.isNegativeInt("door-x")) {
                notifications.error("Incorrectly formatted door x coordinate.");
            }

            if (parameters.notPresent("door-width") || parameters.notInt("door-width") || parameters.getInt("door-width") < 1) {
                notifications.error("Incorrectly formatted door width.");
            }

            if (parameters.notPresent("door-height") || parameters.notInt("door-height") || parameters.getInt("door-height") < 1) {
                notifications.error("Incorrectly formatted door height.");
            }
        }

        if (parameters.isPresent("window") && parameters.getString("window").equals("on")) {

            if (parameters.notPresent("window-side") || parameters.isNegativeInt("window-side")) {
                notifications.error("Incorrectly formatted for side.");
            }

            if (parameters.notPresent("window-x") || parameters.notInt("window-x") || parameters.isNegativeInt("window-x")) {
                notifications.error("Incorrectly formatted window x coordinate.");
            }

            if (parameters.notPresent("window-y") || parameters.notInt("window-y") || parameters.isNegativeInt("window-y")) {
                notifications.error("Incorrectly formatted window y coordinate.");
            }

            if (parameters.notPresent("window-width") || parameters.notInt("window-width") || parameters.getInt("window-width") < 1) {
                notifications.error("Incorrectly formatted window width.");
            }

            if (parameters.notPresent("window-height") || parameters.notInt("window-height") || parameters.getInt("window-height") < 1) {
                notifications.error("Incorrectly formatted window height.");
            }
        }
    }
}
