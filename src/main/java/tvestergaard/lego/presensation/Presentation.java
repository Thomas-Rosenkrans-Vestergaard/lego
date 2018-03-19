package tvestergaard.lego.presensation;

import tvestergaard.lego.logic.Notifications;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Presentation
{

    public static Notifications notifications(HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        Object      o       = session.getAttribute("notifications");
        if (o == null) {
            Notifications notifications = new Notifications();
            session.setAttribute("notifications", notifications);
            return notifications;
        }

        return (Notifications) o;
    }
}
