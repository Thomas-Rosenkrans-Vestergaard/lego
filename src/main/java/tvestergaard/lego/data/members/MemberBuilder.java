package tvestergaard.lego.data.members;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Class providing methods for modifying members.
 */
public class MemberBuilder
{

    /**
     * The email address of the member being built.
     */
    private String email;

    /**
     * The password of the member being built.
     */
    private String password;

    /**
     * The {@link Role} of the member to build.
     */
    private Role role;

    /**
     * Creates a new {@link MemberBuilder}.
     *
     * @param member The member containing the values used as default values in the {@link MemberBuilder} to create.
     * @throws InvalidEmailException When the email address of the provided {@link Member} is considered invalid.
     */
    public MemberBuilder(Member member) throws InvalidEmailException
    {
        setEmail(member.getEmail());
        setPassword(member.getPassword());
        setRole(member.getRole());
    }

    /**
     * Creates a new {@link MemberBuilder}.
     *
     * @param email    The email address of the member to build.
     * @param password The password of the member to build.
     * @param role     The {@link Role} of the member to build.
     * @throws InvalidEmailException When the provided email address is considered invalid.
     */
    public MemberBuilder(String email, String password, Role role) throws InvalidEmailException
    {
        setEmail(email);
        setPassword(password);
        setRole(role);
    }

    /**
     * Sets the email address of the {@link Member} to build.
     *
     * @param email The new email address value.
     * @throws InvalidEmailException When the provided email address is considered invalid.
     */
    public void setEmail(String email) throws InvalidEmailException
    {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new InvalidEmailException(email);
        }

        this.email = email;
    }

    /**
     * Sets the password of the {@link Member} to build.
     *
     * @param password The new password value.
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Sets the role of the {@link Member} to build.
     *
     * @param role The new role value.
     */
    public void setRole(Role role)
    {
        this.role = role;
    }

    /**
     * Returns the email address of the {@link Member} being built.
     *
     * @return The email address of the {@link Member} being built.
     */
    public String getEmail()
    {
        return this.email;
    }

    /**
     * Returns the password of the {@link Member} being built.
     *
     * @return the password of the {@link Member} being built.
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * Returns the {@link Role} of the {@link Member} being built.
     *
     * @return the {@link Role} of the {@link Member} being built.
     */
    public Role getRole()
    {
        return this.role;
    }
}
