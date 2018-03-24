package tvestergaard.lego.presentation.servlets;

import tvestergaard.lego.logic.BrickFacade;
import tvestergaard.lego.logic.building.Door;
import tvestergaard.lego.logic.building.HouseJsonConverter;
import tvestergaard.lego.logic.building.Side;
import tvestergaard.lego.logic.building.Window;
import tvestergaard.lego.logic.geometry.Position;
import tvestergaard.lego.presentation.Notifications;
import tvestergaard.lego.presentation.Parameters;
import tvestergaard.lego.presentation.Presentation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/generate")
public class GenerateServlet extends HttpServlet
{

    private final String INPUT_WIDTH  = "width";
    private final String INPUT_HEIGHT = "height";
    private final String INPUT_DEPTH  = "depth";

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
        Notifications notifications = Presentation.notifications(req);
        Parameters    parameters    = new Parameters(req);

        notifications.record();
        validateParameters(req, notifications);

        if (notifications.hasNew()) {
            resp.sendRedirect(Presentation.referer(req, "home"));
            return;
        }

        int    width  = parameters.getInt(INPUT_WIDTH);
        int    height = parameters.getInt(INPUT_HEIGHT);
        int    depth  = parameters.getInt(INPUT_DEPTH);
        Door   door   = null;
        Window window = null;

        try {

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

            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print(new HouseJsonConverter().convert(BrickFacade.build(width, height, depth, door, window)));
            out.flush();

        } catch (Exception e) {
            throw new RuntimeException(e);
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

        if (parameters.notPresent(INPUT_WIDTH) || parameters.notInt(INPUT_WIDTH) || parameters.notPositiveInt(INPUT_WIDTH)) {
            notifications.error("Incorrectly formatted width value.");
        }

        if (parameters.notPresent(INPUT_HEIGHT) || parameters.notInt(INPUT_HEIGHT) || parameters.notPositiveInt(INPUT_HEIGHT)) {
            notifications.error("Incorrectly formatted height value.");
        }

        if (parameters.notPresent(INPUT_DEPTH) || parameters.notInt(INPUT_DEPTH) || parameters.notPositiveInt(INPUT_DEPTH)) {
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
