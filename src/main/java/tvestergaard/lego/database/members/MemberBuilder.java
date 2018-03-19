package tvestergaard.lego.database.members;

import org.apache.commons.validator.routines.EmailValidator;

public class MemberBuilder
{

    private String email;
    private String password;
    private Role   role;

    public MemberBuilder()
    {

    }

    public MemberBuilder(Member member)
    {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.role = member.getRole();
    }

    public void setEmail(String email) throws InvalidEmailException
    {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new InvalidEmailException(email);
        }

        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setRole(Role role)
    {
        this.role = role;
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
