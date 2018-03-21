package tvestergaard.lego.presentation;

import tvestergaard.lego.logic.BrickCalculator;
import tvestergaard.lego.logic.House;
import tvestergaard.lego.logic.IllegalHouseDimensionsException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/house")
public class HouseServlet extends HttpServlet
{

    private final String INPUT_WIDTH  = "width";
    private final String INPUT_HEIGHT = "height";
    private final String INPUT_DEPTH  = "depth";
    private final String INPUT_DOOR   = "door";
    private final String INPUT_WINDOW = "window";

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

        if (parameters.notPresent(INPUT_WIDTH) || parameters.notInt(INPUT_WIDTH) || parameters.notPositiveInt(INPUT_WIDTH)) {
            notifications.error("Incorrectly formatted width value.");
        }

        if (parameters.notPresent(INPUT_HEIGHT) || parameters.notInt(INPUT_HEIGHT) || parameters.notPositiveInt(INPUT_HEIGHT)) {
            notifications.error("Incorrectly formatted height value.");
        }

        if (parameters.notPresent(INPUT_DEPTH) || parameters.notInt(INPUT_DEPTH) || parameters.notPositiveInt(INPUT_DEPTH)) {
            notifications.error("Incorrectly formatted depth value.");
        }

        if (parameters.isPresent(INPUT_DOOR) && (parameters.notInt(INPUT_DOOR) || parameters.notPositiveInt(INPUT_DOOR))) {
            notifications.error("Incorrectly formatted door value.");
        }

        if (parameters.isPresent(INPUT_WINDOW) && (parameters.notInt(INPUT_WINDOW) || parameters.notPositiveInt(INPUT_WINDOW))) {
            notifications.error("Incorrectly formatted window value.");
        }

        if (notifications.hasNew()) {
            resp.sendRedirect(Presentation.referer(req, "home"));
            return;
        }

        int width  = parameters.getInt(INPUT_WIDTH);
        int height = parameters.getInt(INPUT_HEIGHT);
        int depth  = parameters.getInt(INPUT_DEPTH);
        int door   = parameters.isPresent(INPUT_DOOR) ? parameters.getInt(INPUT_DOOR) : 0;
        int window = parameters.isPresent(INPUT_WINDOW) ? parameters.getInt(INPUT_WINDOW) : 0;

        try {

            BrickCalculator calculator = new BrickCalculator();
            House           house      = calculator.calculate(width, height, depth, door, window);

        } catch (IllegalHouseDimensionsException e) {

        }
        req.getRequestDispatcher("/WEB-INF/house.jsp").forward(req, resp);
    }
}
