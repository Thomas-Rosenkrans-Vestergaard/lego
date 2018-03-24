package tvestergaard.lego.data.members;

import java.sql.SQLException;

/**
 * Data access object concerned with persisting members to some data storage.
 */
public interface MemberDAO
{

    /**
     * Selects the member with the provided email address from the data source.
     *
     * @param email The email address of the member to select from the data source.
     * @return The {@link Member} instance representing the member with the provided email.
     * @throws SQLException
     */
    Member select(String email) throws SQLException;

    /**
     * Selects the member with the provided id from the data source.
     *
     * @param id The id of the member to select from the data source.
     * @return The {@link Member} instance representing the member with the provided id.
     * @throws SQLException
     */
    Member select(int id) throws SQLException;

    /**
     * Persists the provided {@link MemberBuilder} to the data source.
     *
     * @param builder The member builder containing the information about the member to insert.
     * @return The {@link Member} instance representing the newly inserted member.
     * @throws SQLException
     * @throws EmailCollisionException When the unique constraint on the email attribute fails.
     */
    Member create(MemberBuilder builder) throws SQLException, EmailCollisionException;
}
