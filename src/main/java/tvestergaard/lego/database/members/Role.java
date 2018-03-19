package tvestergaard.lego.database.members;

/**
 * Represents the role of a {@link Member}.
 */
public enum Role
{

    /**
     * The default authorized users.
     */
    MEMBER(1),

    /**
     * Has access to the administration panel.
     */
    ADMINISTRATOR(2),

    /**
     * The owner of the application.
     */
    OWNER(3);

    /**
     * The id of the {@link Role}.
     */
    private final int id;

    /**
     * Creates a new {@link Role}.
     *
     * @param id The id of the {@link Role} to create.
     */
    Role(int id)
    {
        this.id = id;
    }

    /**
     * Returns the id of the {@link Role}.
     *
     * @return The id of the {@link Role}.
     */
    public int getId()
    {
        return this.id;
    }

    private static Role[] values = Role.values();

    /**
     * Returns the {@link Role} instance associated with the provided {@code id}.
     *
     * @param id The if of the {@link Role} instance to return.
     * @return The {@link Role} instance associated with the provided {@code id}.
     * @throws UnknownRoleException When no instance {@link Role} with the provided {@code id} exists.
     */
    static Role fromId(int id) throws UnknownRoleException
    {
        return values[id - 1];
    }
}
