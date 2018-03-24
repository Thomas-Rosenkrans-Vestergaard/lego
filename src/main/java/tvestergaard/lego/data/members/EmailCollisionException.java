package tvestergaard.lego.data.members;

import java.sql.SQLException;

/**
 * Thrown from {@link MemberDAO} operations when the unique constraint on the members table fails.
 */
public class EmailCollisionException extends Exception
{

    /**
     * The email that caused the unique constraint to fail.
     */
    private final String email;

    /**
     * Creates a new {@link EmailCollisionException}.
     *
     * @param cause The cause of the {@link EmailCollisionException}.
     * @param email The email that caused the unique constraint to fail.
     */
    public EmailCollisionException(SQLException cause, String email)
    {
        super(cause);
        this.email = email;
    }

    /**
     * Returns the email that caused the unique constraint to fail.
     *
     * @return The email that caused the unique constraint to fail.
     */
    public String getEmail()
    {
        return this.email;
    }
}
