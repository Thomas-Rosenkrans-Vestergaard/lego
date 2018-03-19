package tvestergaard.lego.database.members;

import com.mysql.cj.jdbc.MysqlDataSource;
import tvestergaard.lego.logic.ApplicationException;

import java.sql.*;

public class MysqlMemberDAO implements MemberDAO
{

    private final MysqlDataSource source;

    public MysqlMemberDAO(MysqlDataSource source)
    {
        this.source = source;
    }

    @Override public Member findByEmail(String email)
    {
        String select = "SELECT * FROM members WHERE email = ?";

        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(select)
        ) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.first())
                return null;

            return createMember(resultSet);

        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }

    @Override public Member create(MemberBuilder builder) throws EmailCollisionException
    {
        String select = "INSERT INTO members (email, password, role) VALUES (?, ?, ?)";

        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(select, Statement.RETURN_GENERATED_KEYS)
        ) {

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
            return new Member(generatedKey.getInt(1), email, password, role);

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
                Role.fromId(resultSet.getInt("role"))
        );
    }
}
