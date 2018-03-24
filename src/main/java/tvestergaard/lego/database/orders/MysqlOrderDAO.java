package tvestergaard.lego.database.orders;

import com.mysql.cj.jdbc.MysqlDataSource;
import tvestergaard.lego.database.members.Member;
import tvestergaard.lego.database.members.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlOrderDAO implements OrderDAO
{

    private final MysqlDataSource source;

    public MysqlOrderDAO(MysqlDataSource source)
    {
        this.source = source;
    }

    @Override public Order create(OrderBuilder builder) throws SQLException
    {
        String insert = "INSERT INTO orders (member, width, height, depth, specification) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, builder.getMember().getId());
            statement.setInt(2, builder.getWidth());
            statement.setInt(3, builder.getHeight());
            statement.setInt(4, builder.getDepth());
            statement.setString(5, builder.getSpecification());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (!resultSet.first())
                return null;

            return new Order(
                    resultSet.getInt(1),
                    builder.getMember(),
                    builder.getWidth(),
                    builder.getHeight(),
                    builder.getDepth(),
                    builder.getSpecification(),
                    builder.getStatus()
            );
        }
    }

    @Override public Order select(int id) throws SQLException
    {
        String select = "SELECT * FROM orders INNER JOIN members ON orders.member = members.id WHERE id = ?";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(select)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.first())
                return null;

            return createOrder(resultSet);
        }
    }

    @Override public List<Order> select(Member member) throws SQLException
    {
        List<Order> orders = new ArrayList<>();
        String      select = "SELECT * FROM orders INNER JOIN members ON orders.member = members.id WHERE member = ?";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(select)) {

            statement.setInt(1, member.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                orders.add(createOrder(resultSet));

            return orders;
        }
    }

    @Override public List<Order> select() throws SQLException
    {
        List<Order> orders = new ArrayList<>();
        String      select = "SELECT * FROM orders INNER JOIN members ON orders.member = members.id";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(select)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                orders.add(createOrder(resultSet));

            return orders;
        }
    }

    private Order createOrder(ResultSet resultSet) throws SQLException
    {
        return new Order(
                resultSet.getInt("id"),
                new Member(
                        resultSet.getInt("members.id"),
                        resultSet.getString("members.email"),
                        resultSet.getString("members.password"),
                        Role.fromId(resultSet.getInt("members.role")),
                        resultSet.getTimestamp("members.created_at"),
                        resultSet.getTimestamp("members.updated_at")
                ),
                resultSet.getInt("width"),
                resultSet.getInt("height"),
                resultSet.getInt("depth"),
                resultSet.getString("specification"),
                Status.from(resultSet.getInt("status"))
        );
    }
}
