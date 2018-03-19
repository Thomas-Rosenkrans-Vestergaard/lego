package tvestergaard.lego.database.members;

import com.mysql.cj.jdbc.MysqlDataSource;
import tvestergaard.lego.logic.ApplicationException;

import java.sql.*;

/**
 * Provides operations related to members to some MySQL database.
 */
public class MysqlMemberDAO implements MemberDAO
{

    /**
     * The {@link MysqlDataSource} to perform operations upon.
     */
    private final MysqlDataSource source;

    /**
     * Creates a new {@link MysqlMemberDAO}.
     *
     * @param source The {@link MysqlDataSource} to perform operations upon.
     */
    public MysqlMemberDAO(MysqlDataSource source)
    {
        this.source = source;
    }

    /**
     * Selects the member with the provided id from the data source.
     *
     * @param id The id of the member to select from the data source.
     * @return The {@link Member} instance representing the member with the provided id.
     */
    @Override public Member select(int id)
    {
        String select = "SELECT * FROM members WHERE id = ?";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(select)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.first())
                return null;

            return createMember(resultSet);

        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }

    /**
     * Selects the member with the provided email address from the data source.
     *
     * @param email The email address of the member to select from the data source.
     * @return The {@link Member} instance representing the member with the provided email.
     */
    @Override public Member select(String email)
    {
        String select = "SELECT * FROM members WHERE email = ?";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(select)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.first())
                return null;

            return createMember(resultSet);

        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }

    /**
     * Persists the provided {@link MemberBuilder} to the data source.
     *
     * @param builder The member builder containing the information about the member to insert.
     * @return The {@link Member} instance representing the newly inserted member.
     * @throws EmailCollisionException When the unique constraint on the email attribute fails.
     */
    @Override public Member create(MemberBuilder builder) throws EmailCollisionException
    {
        String select = "INSERT INTO members (email, password, role) VALUES (?, ?, ?)";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(select, Statement.RETURN_GENERATED_KEYS)) {

            String email    = builder.getEmail();
            String password = builder.getPassword();
            Role   role     = builder.getRole();

            statement.setString(1, email);
            statement.setString(2, password);
            statement.setInt(3, role.getId());
            int effected = statement.executeUpdate();
            if (effected != 1)
                return null;

            ResultSet generatedKey = statement.getGeneratedKeys();
            generatedKey.first();
            return select(generatedKey.getInt(1));

        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }

    private Member createMember(ResultSet resultSet) throws SQLException
    {
        return new Member(
                resultSet.getInt("id"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                Role.fromId(resultSet.getInt("role")),
                resultSet.getTimestamp("created_at"),
                resultSet.getTimestamp("updated_at")
        );
    }
}
