package tvestergaard.lego.database.members;

public enum Role
{

    MEMBER(1),
    ADMINISTRATOR(2),
    OWNER(3);

    private static Role[] values = Role.values();

    static Role fromId(int id) throws UnknownRoleException
    {
        return values[id];
    }

    private final int id;

    Role(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }
}
