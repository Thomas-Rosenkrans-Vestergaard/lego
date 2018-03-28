package tvestergaard.lego.data.orders;

import com.mysql.cj.jdbc.MysqlDataSource;
import tvestergaard.lego.data.members.Member;
import tvestergaard.lego.data.members.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object concerned with persisting orders in {@code MySQL} databases.
 */
public class MysqlOrderDAO implements OrderDAO
{

    /**
     * The {@link MysqlDataSource} used for persistent storage.
     */
    private final MysqlDataSource source;

    /**
     * Creates a new {@link MysqlOrderDAO}.
     *
     * @param source The {@link MysqlDataSource} used for persistent storage.
     */
    public MysqlOrderDAO(MysqlDataSource source)
    {
        this.source = source;
    }

    /**
     * Creates a new order record.
     *
     * @param builder The {@link OrderBuilder} containing the values to use when creating the new order record.
     * @return The {@link Order} instance representing the newly created record.
     * @throws SQLException
     */
    @Override public Order create(OrderBuilder builder) throws SQLException
    {
        String insert = "INSERT INTO orders (member, width, height, depth, specification, shipped_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, builder.getMember().getId());
            statement.setInt(2, builder.getWidth());
            statement.setInt(3, builder.getHeight());
            statement.setInt(4, builder.getDepth());
            statement.setString(5, builder.getSpecification());
            statement.setTimestamp(6, builder.getShippedAt());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (!resultSet.first())
                return null;

            return select(resultSet.getInt(1));
        }
    }

    /**
     * Selects the order record with the provided {@code id}.
     *
     * @param id The {@code id} of the order record to select.
     * @return The {@link Order} instance representing the selected record. Returns {@code null} if no such record exists.
     * @throws SQLException
     */
    @Override public Order select(int id) throws SQLException
    {
        String select = "SELECT * FROM orders INNER JOIN members ON orders.member = members.id WHERE orders.id = ?";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(select)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.first())
                return null;

            return createOrder(resultSet);
        }
    }

    /**
     * Selects all the order records by the provided {@link Member}.
     *
     * @param member The {@link Member} whose order records to select.
     * @return The list of {@link Order} instances representing the selected records.
     * @throws SQLException
     */
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

    /**
     * Selects all the order records.
     *
     * @return The list of {@link Order} instances representing the selected records.
     * @throws SQLException
     */
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

    /**
     * Updates the order record with the provided {@code id} using the provided {@link OrderBuilder}.
     *
     * @param id      The id of the order record to update.
     * @param builder The values to update to.
     * @return The {@link Order} instance representing the updated record.
     * @throws SQLException
     */
    @Override public Order update(int id, OrderBuilder builder) throws SQLException
    {
        String update = "UPDATE orders SET member = ?, width = ?, height = ?, depth = ?, specification = ?, shipped_at = ?, status = ? WHERE id = ?";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, builder.getMember().getId());
            statement.setInt(2, builder.getWidth());
            statement.setInt(3, builder.getHeight());
            statement.setInt(4, builder.getDepth());
            statement.setString(5, builder.getSpecification());
            statement.setTimestamp(6, builder.getShippedAt());
            statement.setInt(7, builder.getStatus().getCode());
            statement.setInt(8, id);

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (!resultSet.first())
                return null;

            return select(resultSet.getInt(1));
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
                Status.from(resultSet.getInt("status")),
                resultSet.getTimestamp("created_at"),
                resultSet.getTimestamp("updated_at"),
                resultSet.getTimestamp("shipped_at")
        );
    }
}
