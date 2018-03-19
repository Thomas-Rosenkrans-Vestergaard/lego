package tvestergaard.lego.database.members;

/**
 * Provides operations related to members to some data source.
 */
public interface MemberDAO
{

    /**
     * Selects the member with the provided email address from the data source.
     *
     * @param email The email address of the member to select from the data source.
     * @return The {@link Member} instance representing the member with the provided email.
     */
    Member select(String email);

    /**
     * Selects the member with the provided id from the data source.
     *
     * @param id The id of the member to select from the data source.
     * @return The {@link Member} instance representing the member with the provided id.
     */
    Member select(int id);

    /**
     * Persists the provided {@link MemberBuilder} to the data source.
     *
     * @param builder The member builder containing the information about the member to insert.
     * @return The {@link Member} instance representing the newly inserted member.
     * @throws EmailCollisionException When the unique constraint on the email attribute fails.
     */
    Member create(MemberBuilder builder) throws EmailCollisionException;
}
