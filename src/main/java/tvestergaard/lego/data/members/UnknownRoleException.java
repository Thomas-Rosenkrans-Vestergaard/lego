package tvestergaard.lego.data.members;

/**
 * Thrown by {@link Role#fromId(int)} when a {@link Role} with the provided id does not exist.
 */
public class UnknownRoleException extends RuntimeException
{

    /**
     * The {@link Role} id that caused the exception.
     */
    private final int id;

    /**
     * Creates a new {@link UnknownRoleException}.
     *
     * @param id The {@link Role} id that caused the exception.
     */
    public UnknownRoleException(int id)
    {
        this.id = id;
    }

    /**
     * Returns the {@link Role} id that caused the exception.
     *
     * @return The {@link Role} id that caused the exception.
     */
    public int getId()
    {
        return this.id;
    }
}
