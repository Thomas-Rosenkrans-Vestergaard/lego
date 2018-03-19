package tvestergaard.lego.presensation;

import tvestergaard.lego.database.members.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Authentication
{

    private final HttpServletRequest  request;
    private final HttpServletResponse response;

    public Authentication(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
    }

    public boolean isMember()
    {
        return request.getSession().getAttribute("member") != null;
    }

    public void redirect(String ref) throws IOException
    {
        response.sendRedirect("membership?from=" + ref);
    }

    public Member getMember() throws IOException
    {
        return (Member) request.getSession().getAttribute("member");
    }
}
