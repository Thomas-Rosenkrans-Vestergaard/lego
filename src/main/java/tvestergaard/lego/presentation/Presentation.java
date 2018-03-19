package tvestergaard.lego.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Presentation
{

    private static String NOTIFICATIONS_KEY = "notifications";

    public static Notifications notifications(HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        Object      o       = session.getAttribute(NOTIFICATIONS_KEY);
        if (o == null) {
            Notifications notifications = new Notifications();
            session.setAttribute(NOTIFICATIONS_KEY, notifications);
            req.setAttribute(NOTIFICATIONS_KEY, notifications);
            return notifications;
        }

        return (Notifications) o;
    }
}
