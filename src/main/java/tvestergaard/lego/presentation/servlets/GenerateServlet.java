package tvestergaard.lego.presentation.servlets;

import org.json.JSONArray;
import org.json.JSONObject;
import tvestergaard.lego.logic.BrickFacade;
import tvestergaard.lego.logic.building.*;
import tvestergaard.lego.logic.building.BricklayerException.Reason;
import tvestergaard.lego.logic.geometry.Position;
import tvestergaard.lego.presentation.HouseParametersHelper;
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
        HouseParametersHelper helper = new HouseParametersHelper();
        helper.validateParameters(req, notifications);

        if (notifications.hasNew()) {
            resp.sendRedirect(Presentation.referer(req, "home"));
            return;
        }

        int    width  = parameters.getInt(INPUT_WIDTH);
        int    height = parameters.getInt(INPUT_HEIGHT);
        int    depth  = parameters.getInt(INPUT_DEPTH);
        Door   door   = getDoor(parameters);
        Window window = getWindow(parameters);

        try {
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print(new HouseJsonConverter().convert(BrickFacade.build(width, height, depth, door, window)));
            out.flush();
        } catch (BricklayerException e) {
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print(getErrorJson(e));
            out.flush();
        }
    }

    /**
     * Returns a {@link JSONObject} containing a property 'errors' containing an error of {@link Reason}s.
     *
     * @param e
     * @return
     */
    private JSONObject getErrorJson(BricklayerException e)
    {
        JSONObject root   = new JSONObject();
        JSONArray  errors = new JSONArray();
        int        index  = 0;
        for (Reason reason : e.getReasons()) {
            errors.put(index++, reason.toString());
        }

        root.put("errors", errors);
        return root;
    }

    /**
     * Builds a {@link Door} from the provided {@link Parameters}.
     *
     * @param parameters The parameters from which to build the {@link Door}.
     * @return The resulting {@link Door}.
     */
    private Door getDoor(Parameters parameters)
    {
        if (parameters.isPresent("door") && parameters.getString("door").equals("on")) {
            return new Door(
                    parameters.getInt("door-width"),
                    parameters.getInt("door-height"),
                    Position.of(parameters.getInt("door-x"), 0),
                    getSide(parameters.getInt("door-side"))
            );
        }

        return null;
    }

    /**
     * Builds a {@link Window} from the provided {@link Parameters}.
     *
     * @param parameters The parameters from which to build the {@link Window}.
     * @return The resulting {@link Window}.
     */
    private Window getWindow(Parameters parameters)
    {
        if (parameters.isPresent("window") && parameters.getString("window").equals("on")) {
            return new Window(
                    parameters.getInt("window-width"),
                    parameters.getInt("window-height"),
                    Position.of(parameters.getInt("window-x"), parameters.getInt("window-y")),
                    getSide(parameters.getInt("window-side"))
            );
        }

        return null;
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
