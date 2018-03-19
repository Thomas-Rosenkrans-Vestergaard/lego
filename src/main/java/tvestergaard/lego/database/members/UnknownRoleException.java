package tvestergaard.lego.database.members;

public class UnknownRoleException extends RuntimeException
{

    private int id;

    public UnknownRoleException(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }
}
