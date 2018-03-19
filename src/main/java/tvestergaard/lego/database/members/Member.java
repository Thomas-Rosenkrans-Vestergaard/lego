package tvestergaard.lego.database.members;

public class Member
{

    private final int    id;
    private final String email;
    private final String password;
    private final Role   role;

    public Member(int id, String email, String password, Role role)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId()
    {
        return this.id;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPassword()
    {
        return this.password;
    }

    public Role getRole()
    {
        return this.role;
    }
}
