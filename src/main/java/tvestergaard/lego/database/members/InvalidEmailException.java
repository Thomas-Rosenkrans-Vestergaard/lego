package tvestergaard.lego.database.members;

public class InvalidEmailException extends Exception
{

    private final String email;

    public InvalidEmailException(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return this.email;
    }
}
