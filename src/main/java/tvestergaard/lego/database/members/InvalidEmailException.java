package tvestergaard.lego.database.members;

/**
 * Thrown when attempting the provided {@link MemberBuilder#setEmail(String)} with an invalid email address.
 */
public class InvalidEmailException extends Exception
{

    /**
     * The invalid email address that caused the exception.
     */
    private final String email;

    /**
     * Creates a new {@link InvalidEmailException}.
     *
     * @param email The invalid email address that caused the exception.
     */
    public InvalidEmailException(String email)
    {
        this.email = email;
    }

    /**
     * Returns the invalid email address that caused the exception.
     *
     * @return The invalid email address that caused the exception.
     */
    public String getEmail()
    {
        return this.email;
    }
}
