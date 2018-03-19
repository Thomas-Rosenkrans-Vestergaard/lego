package tvestergaard.lego.database.members;

public class EmailCollisionException extends Exception
{

    private final String email;

    public EmailCollisionException(Throwable cause, String email)
    {
        super(cause);
        this.email = email;
    }

    public String getEmail()
    {
        return this.email;
    }
}
