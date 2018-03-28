package tvestergaard.lego.presentation;

import javax.servlet.http.HttpServletRequest;

public class HouseParametersHelper
{

    public void validateParameters(HttpServletRequest request, Notifications notifications)
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
